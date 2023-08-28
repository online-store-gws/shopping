package uz.greenwhite.shopping.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
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

}
