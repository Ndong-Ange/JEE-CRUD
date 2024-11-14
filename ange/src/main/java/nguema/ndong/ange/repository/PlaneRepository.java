package nguema.ndong.ange.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import nguema.ndong.ange.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
