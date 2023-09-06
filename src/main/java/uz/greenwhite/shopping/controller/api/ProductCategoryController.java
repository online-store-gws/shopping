package uz.greenwhite.shopping.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.greenwhite.shopping.entity.ProductCategory;
import uz.greenwhite.shopping.error.ErrorResponse;
import uz.greenwhite.shopping.service.ProductCategoryService;

import java.util.List;

@RestController("pc-c-api")
@RequestMapping("/api/v2/product-category")
@AllArgsConstructor
public class ProductCategoryController {
    private final ProductCategoryService service;


    @GetMapping()
    public ResponseEntity<?> getAll(Pageable pageable) {
        return ResponseEntity.ok( service.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id, @RequestParam String name) {
        try {
            return ResponseEntity.ok(service.getById(id));
        } catch (Exception ex) {
            return new ResponseEntity<>(ErrorResponse.error("Kategoriya topilmadi"), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ProductCategory create(@RequestBody ProductCategory category) {

        ObjectMapper objectMapper = new ObjectMapper();

        return service.create(category);
    }


    @PutMapping()
    public ProductCategory update(@RequestBody ProductCategory category) {
        return service.update(category);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }

}
