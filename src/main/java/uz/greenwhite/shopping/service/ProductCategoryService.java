package uz.greenwhite.shopping.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uz.greenwhite.shopping.entity.ProductCategory;

public interface ProductCategoryService {

    /**
     * Bu barcha mahsulot kategoriyalarni paging asosida olib beradi
     * @return
     */
    public Page<ProductCategory> getAll(Pageable pageable);
}
