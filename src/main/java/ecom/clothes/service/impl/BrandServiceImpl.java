package ecom.clothes.service.impl;

import ecom.clothes.controller.request.Brand.BrandCreateRequest;
import ecom.clothes.controller.request.Brand.BrandUpdateRequest;
import ecom.clothes.controller.response.Brand.BrandPageResponse;
import ecom.clothes.controller.response.Brand.BrandResponse;
import ecom.clothes.exception.ResourceNotFoundException;
import ecom.clothes.model.BrandEntity;
import ecom.clothes.model.CategoriesEntity;
import ecom.clothes.repositories.BrandRepository;
import ecom.clothes.repositories.CategoriesRepository;
import ecom.clothes.service.BrandService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
@Slf4j(topic = "Brand-Service-Impl")
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final CategoriesRepository categoriesRepository;

    @Override
    public BrandPageResponse getAllBrands(String keyword, String sort, int page, int size) {
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

        Page<BrandEntity> brands = null;

        if (StringUtils.hasLength(keyword)) {
            keyword = "%" + keyword + "%";

            brands = brandRepository.findByBrandName(keyword, pageable);
        } else {
            brands = brandRepository.findAll(pageable);
        }
        return getBrandPageResponse(page,size,brands);
    }

    @Override
    public BrandResponse getBrand(Long brandId) {
        BrandEntity brandEntity = getBrandEntity(brandId);
        return BrandResponse.builder()
                .brandId(brandEntity.getId())
                .brandName(brandEntity.getBrandName())
                .brandImage(brandEntity.getBrandImage())
                .categoriesEntity(brandEntity.getCategoryId())
                .build();
    }

    @Override
    public Long saveBrand(BrandCreateRequest request) {
        BrandEntity brandEntity = new BrandEntity();

        // Lấy Category từ database dựa trên categoryId
        CategoriesEntity category = categoriesRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        brandEntity.setBrandName(request.getBrandName());
        brandEntity.setBrandImage(request.getBrandImage());
        brandEntity.setCategoryId(category); // Gán object Category thay vì chỉ ID

        brandRepository.save(brandEntity);
        return brandEntity.getId(); // Trả về ID của Brand vừa lưu
    }


    @Override
    public void updateBrand(BrandUpdateRequest request) {
        BrandEntity brandEntity = brandRepository.findByBrandNameIs(request.getBrandName());
        if (brandEntity == null) {
            throw new ResourceNotFoundException("Brand not found");
        }

        // Tìm category theo ID
        CategoriesEntity category = categoriesRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        brandEntity.setBrandName(request.getBrandName());
        brandEntity.setBrandImage(request.getBrandImage());
        brandEntity.setCategoryId(category);

        brandRepository.save(brandEntity);
    }

    @Override
    public void deleteBrand(Long brandId) {
        brandRepository.deleteById(brandId);
    }

    private BrandPageResponse getBrandPageResponse(int page, int size, Page<BrandEntity> brands) {
        List<BrandResponse> brandResponses = brands.stream().map(brandEntity -> BrandResponse.builder()
                        .brandId(brandEntity.getId())
                        .brandName(brandEntity.getBrandName())
                        .brandImage(brandEntity.getBrandImage())
                        .categoriesEntity(brandEntity.getCategoryId())
                        .build())
                .toList();

        BrandPageResponse brandPageResponse = new BrandPageResponse();
        brandPageResponse.setPage(page);
        brandPageResponse.setSize(size);
        brandPageResponse.setTotalElements(brands.getTotalElements());
        brandPageResponse.setTotalPages(brands.getTotalPages());
        brandPageResponse.setBrands(brandResponses);

        return brandPageResponse;
    }

    private BrandEntity getBrandEntity(Long id) {
        return brandRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Brand not found"));
    }
}
