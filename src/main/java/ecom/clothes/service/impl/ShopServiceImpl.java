package ecom.clothes.service.impl;

import ecom.clothes.controller.request.Shop.ShopCreateRequest;
import ecom.clothes.controller.request.Shop.ShopUpdateRequest;
import ecom.clothes.controller.response.Shop.ShopPageResponse;
import ecom.clothes.controller.response.Shop.ShopResponse;
import ecom.clothes.controller.response.User.UserPageResponse;
import ecom.clothes.controller.response.User.UserResponse;
import ecom.clothes.exception.ResourceNotFoundException;
import ecom.clothes.model.ShopEntity;
import ecom.clothes.model.UserEntity;
import ecom.clothes.repositories.ShopRepository;
import ecom.clothes.service.ShopService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j(topic = "Shop-Service-Impl")
@AllArgsConstructor
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;


    @Override
    public ShopPageResponse getShopPage(String keyword, String sort, int page, int size) {
        Sort.Order order = new Sort.Order(Sort.Direction.ASC, "id");
        if (StringUtils.hasLength(sort)) {
            Pattern pattern = Pattern.compile("(\\w+?)(:)(.*)");
            Matcher matcher = pattern.matcher(sort);
            if (matcher.find()) {
                String columnName = matcher.group(1);
                if (matcher.group(3).equalsIgnoreCase("asc")) {
                    order = new Sort.Order(Sort.Direction.ASC, columnName);
                } else {
                    order = new Sort.Order(Sort.Direction.DESC, columnName);
                }
            }
        }

        int pageNo = 0;
        if (page > 0) {
            pageNo = page - 1;
        }

        Pageable pageable = PageRequest.of(pageNo, size, Sort.by(order));

        Page<ShopEntity> shopSearchList = null;

        if (StringUtils.hasLength(keyword)) {
            keyword = "%" + keyword + "%";
            shopSearchList = shopRepository.findByShopName(keyword, pageable);
        } else {
            shopSearchList = shopRepository.findAll(pageable);
        }

        return getShopPageResponse(page, size, shopSearchList);
    }

    private ShopPageResponse getShopPageResponse(int page, int size, Page<ShopEntity> shopSearchList) {
        List<ShopResponse> shopResponseList = shopSearchList.stream().map(shop -> ShopResponse.builder()
                .shopId(shop.getId())
                .shopName(shop.getShopName())
                .shopDescription(shop.getShopDescription())
                .shopBanner(shop.getShopBanner())
                .shopRating(shop.getShopRating())
                .build()
        ).toList();

        ShopPageResponse shopPageResponse = new ShopPageResponse();
        shopPageResponse.setPage(page);
        shopPageResponse.setTotalElements(shopPageResponse.getTotalElements());
        shopPageResponse.setTotalPages(shopPageResponse.getTotalPages());
        shopPageResponse.setSize(size);
        shopPageResponse.setShops(shopResponseList);
        return shopPageResponse;
    }

    @Override
    public ShopResponse getShop(Long id) {

        ShopEntity shop =  findByShopId(id);

        return ShopResponse.builder()
                .shopId(shop.getId())
                .shopName(shop.getShopName())
                .shopDescription(shop.getShopDescription())
                .shopBanner(shop.getShopBanner())
                .shopRating(shop.getShopRating())
                .build();
    }

    @Override
    public Long save(ShopCreateRequest request) {
        ShopEntity checkShopName = shopRepository.findByShopNameIs(request.getShopName());
        if (checkShopName != null) {
            throw new ResourceNotFoundException("Shop name already exists");
        }
        ShopEntity shop = new ShopEntity();
        shop.setShopName(request.getShopName());
        shop.setUser(request.getUser());
        shopRepository.save(shop);
        return shop.getId();
    }

    @Override
    public void update(ShopUpdateRequest request) {
        ShopEntity checkShopName = shopRepository.findByShopNameIs(request.getShopName());
        if (checkShopName != null) {
            throw new ResourceNotFoundException("Shop name already exists");
        }
        ShopEntity shop = new ShopEntity();
        shop.setShopName(request.getShopName());
        shop.setShopBanner(request.getShopBanner());
        shop.setShopDescription(request.getShopDescription());
        shopRepository.save(shop);
    }

    @Override
    public void delete(Long id) {
        shopRepository.deleteById(id);
    }

    private ShopEntity findByShopId(Long id) {
        return shopRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Shop not found"));
    }
}
