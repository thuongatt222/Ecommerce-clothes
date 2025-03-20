package ecom.clothes.repositories;

import ecom.clothes.model.ShopEntity;
import ecom.clothes.model.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ShopRepository extends JpaRepository<ShopEntity, Long> {
    Page<ShopEntity> findByShopName(String shopName, Pageable pageable);
    ShopEntity findByShopNameIs(String shopName);
}
