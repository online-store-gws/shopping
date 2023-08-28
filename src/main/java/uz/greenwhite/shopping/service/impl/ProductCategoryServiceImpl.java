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
}
