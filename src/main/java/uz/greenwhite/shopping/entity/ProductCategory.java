package uz.greenwhite.shopping.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private ProductCategory parent;

    @OneToMany(mappedBy = "parent")
    private List<ProductCategory> children;

    @OneToMany(mappedBy = "category")
    private List<Product> products;

    private LocalDateTime modifiedOn;

    public ProductCategory(String name) {
        this.name = name;
    }

}
