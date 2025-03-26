package ecom.clothes.service;

import ecom.clothes.controller.request.Categories.CategoriesCreateRequest;
import ecom.clothes.controller.request.Categories.CategoriesUpdateRequest;
import ecom.clothes.controller.response.Categories.CategoriesPageResponse;
import ecom.clothes.controller.response.Categories.CategoriesResponse;
import ecom.clothes.controller.response.Shop.ShopPageResponse;
import ecom.clothes.controller.response.Shop.ShopResponse;

public interface CategoriesService {

    CategoriesPageResponse getCategories(String keyword, String sort, int page, int size);

    CategoriesResponse getCategory(Long id);

    Long addCategory(CategoriesCreateRequest request);

    void updateCategory(CategoriesUpdateRequest request);

    void deleteCategory(Long id);
}
