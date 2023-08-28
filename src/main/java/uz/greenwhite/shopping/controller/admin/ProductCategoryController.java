package uz.greenwhite.shopping.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/data/product-categories")
public class ProductCategoryController {

    @GetMapping()
    public String index() {
        return "admin/data/product-categories";
    }
}
