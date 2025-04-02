package ecom.clothes.repositories;

import ecom.clothes.model.CategoriesEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoriesRepository extends JpaRepository<CategoriesEntity, Long> {
    @Query(value = "select c from CategoriesEntity c where lower(c.categoryName) like lower(concat('%', :categoryName, '%'))")
    Page<CategoriesEntity> findByCategoryName(@Param("categoryName") String categoryName, Pageable pageable);

    CategoriesEntity findByCategoryNameIs(String categoryName);
}
