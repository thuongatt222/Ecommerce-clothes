package ecom.clothes.repositories;

import ecom.clothes.model.ShopEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ShopRepository extends JpaRepository<ShopEntity, Long> {

    @Query(value = "select s from ShopEntity s where lower(s.shopName) like :shopName")
    Page<ShopEntity> findByShopName(String shopName, Pageable pageable);
    ShopEntity findByShopNameIs(String shopName);
}
