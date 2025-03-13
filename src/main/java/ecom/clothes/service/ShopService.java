package ecom.clothes.service;

import ecom.clothes.controller.response.Shop.ShopPageResponse;
import ecom.clothes.controller.response.Shop.ShopResponse;

public interface ShopService {
    ShopPageResponse getShopPage();
    ShopResponse getShop();
}
