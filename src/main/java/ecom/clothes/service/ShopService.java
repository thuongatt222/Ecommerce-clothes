package ecom.clothes.service;

import ecom.clothes.controller.request.Shop.ShopCreateRequest;
import ecom.clothes.controller.request.Shop.ShopUpdateRequest;
import ecom.clothes.controller.response.Shop.ShopPageResponse;
import ecom.clothes.controller.response.Shop.ShopResponse;

public interface ShopService {

    ShopPageResponse getShopPage(String keyword, String sort, int page, int size);

    ShopResponse getShop(Long id);

    Long save(ShopCreateRequest request);

    void update(ShopUpdateRequest request);

    void delete(Long id);

}
