package uz.greenwhite.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.greenwhite.shopping.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
