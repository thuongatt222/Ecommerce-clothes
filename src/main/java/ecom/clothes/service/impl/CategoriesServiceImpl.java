package ecom.clothes.service.impl;

import ecom.clothes.controller.request.Categories.CategoriesCreateRequest;
import ecom.clothes.controller.request.Categories.CategoriesUpdateRequest;
import ecom.clothes.controller.response.Categories.CategoriesPageResponse;
import ecom.clothes.controller.response.Categories.CategoriesResponse;
import ecom.clothes.exception.InvalidDataException;
import ecom.clothes.exception.ResourceNotFoundException;
import ecom.clothes.model.CategoriesEntity;
import ecom.clothes.repositories.CategoriesRepository;
import ecom.clothes.service.CategoriesService;
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
@Slf4j(topic = "Categories-Service-Impl")
@AllArgsConstructor
public class CategoriesServiceImpl implements CategoriesService {

    private final CategoriesRepository categoriesRepository;

    @Override
    public CategoriesPageResponse getCategories(String keyword, String sort, int page, int size) {
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

        Page<CategoriesEntity> categoriesSearchList;

        if (StringUtils.hasLength(keyword)) {
            keyword = "%" + keyword + "%";
            categoriesSearchList = categoriesRepository.findByCategoryName(keyword, pageable);
        } else {
            categoriesSearchList = categoriesRepository.findAll(pageable);
        }

        return getCategoriesPageResponse(page, size, categoriesSearchList);
    }

    @Override
    public CategoriesResponse getCategory(Long id) {
        CategoriesEntity categoriesEntity = getCategoryEntity(id);

        return CategoriesResponse.builder()
                .categoryId(categoriesEntity.getId())
                .categoryName(categoriesEntity.getCategoryName())
                .categoryImage(categoriesEntity.getCategoryImage())
                .subCategoryId(categoriesEntity.getSubCategoryId())
                .build();
    }

    @Override
    public Long addCategory(CategoriesCreateRequest request) {
        CategoriesEntity categoriesEntity = new CategoriesEntity();
        categoriesEntity.setCategoryName(request.getCategoryName());
        categoriesEntity.setCategoryImage(request.getCategoryImage());

        // Xử lý subCategoryId
        Long subCategoryId = request.getSubCategoriesId();
        if (subCategoryId != null) {
            CategoriesEntity subCategory = categoriesRepository.findById(subCategoryId)
                    .orElseThrow(() -> new ResourceNotFoundException("Subcategory with ID " + subCategoryId + " not found"));
            categoriesEntity.setSubCategoryId(subCategory);
        } else {
            categoriesEntity.setSubCategoryId(null); // Không có danh mục con
        }

        categoriesRepository.save(categoriesEntity);
        return categoriesEntity.getId();
    }

    @Override
    public void updateCategory(CategoriesUpdateRequest request) {
        CategoriesEntity categoriesEntity = categoriesRepository.findByCategoryNameIs(request.getCategoryName());
        if (categoriesEntity != null && !categoriesEntity.getCategoryName().equals(request.getCategoryName())) {
            throw new InvalidDataException("Danh mục với tên " + request.getCategoryName() + " đã tồn tại");
        }

        // Nếu không tìm thấy theo tên, tìm theo ID hoặc tạo mới logic phù hợp
        if (categoriesEntity == null) {
            throw new ResourceNotFoundException("Category not found for update");
        }

        categoriesEntity.setCategoryName(request.getCategoryName());
        categoriesEntity.setCategoryImage(request.getCategoryImage());

        // Xử lý subCategoryId
        Long subCategoryId = request.getSubCategoriesId().getId();
        if (subCategoryId != null) {
            CategoriesEntity subCategory = categoriesRepository.findById(subCategoryId)
                    .orElseThrow(() -> new ResourceNotFoundException("Subcategory with ID " + subCategoryId + " not found"));
            categoriesEntity.setSubCategoryId(subCategory);
        } else {
            categoriesEntity.setSubCategoryId(null); // Xóa liên kết nếu subCategoryId là null
        }

        categoriesRepository.save(categoriesEntity);
    }

    @Override
    public void deleteCategory(Long id) {
        categoriesRepository.deleteById(id);
    }

    private CategoriesPageResponse getCategoriesPageResponse(int pageNo, int size, Page<CategoriesEntity> categoriesSearchList) {
        List<CategoriesResponse> categoriesResponses = categoriesSearchList.stream()
                .map(category -> CategoriesResponse.builder()
                        .categoryId(category.getId())
                        .categoryName(category.getCategoryName())
                        .categoryImage(category.getCategoryImage())
                        .subCategoryId(category.getSubCategoryId())
                        .build()
                )
                .toList();

        CategoriesPageResponse categoriesPageResponse = new CategoriesPageResponse();
        categoriesPageResponse.setPage(pageNo);
        categoriesPageResponse.setSize(size);
        categoriesPageResponse.setTotalElements(categoriesSearchList.getTotalElements());
        categoriesPageResponse.setTotalPages(categoriesSearchList.getTotalPages());
        categoriesPageResponse.setCategories(categoriesResponses);
        return categoriesPageResponse;
    }

    private CategoriesEntity getCategoryEntity(Long id) {
        return categoriesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
    }
}