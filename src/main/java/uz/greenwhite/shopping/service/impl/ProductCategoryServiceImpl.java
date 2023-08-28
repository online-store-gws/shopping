package uz.greenwhite.shopping.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.greenwhite.shopping.entity.ProductCategory;
import uz.greenwhite.shopping.repository.ProductCategoryRepository;
import uz.greenwhite.shopping.service.ProductCategoryService;

@Service
@AllArgsConstructor
public class ProductCategoryServiceImpl implements ProductCategoryService {
    private final ProductCategoryRepository repository;

    @Override
    public Page<ProductCategory> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public ProductCategory create(ProductCategory category) {
        if (category.getId() != null) throw new RuntimeException("id should not be present");
        return repository.save(category);
    }

    @Override
    public ProductCategory getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("not found"));
    }

    @Override
    public ProductCategory update(ProductCategory category) {
        if (category.getId() == null) throw new RuntimeException("id should be present");
        return repository.save(category);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
