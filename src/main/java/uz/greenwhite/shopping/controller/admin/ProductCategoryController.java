package uz.greenwhite.shopping.controller.admin;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.greenwhite.shopping.entity.ProductCategory;
import uz.greenwhite.shopping.service.ProductCategoryService;

import java.util.List;

@Controller
@RequestMapping("/admin/data/product-category")
@AllArgsConstructor
public class ProductCategoryController {

    private final ProductCategoryService service;

    @GetMapping()
    public String list(Model model, Pageable pageable) {
        model.addAttribute("list", service.getAll(pageable));
        return "admin/data/product-category/list";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("category", new ProductCategory());
        return "admin/data/product-category/entry";
    }

    @PostMapping("/add")
    public String addFilm(@ModelAttribute ProductCategory category) {
        service.create(category);
        return "redirect:/admin/data/product-category";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam(name = "id") Long id, Model model) {
        model.addAttribute("category", service.getById(id));
        return "admin/data/product-category/entry";
    }

    @PostMapping("/edit")
    public String editCategory(@ModelAttribute ProductCategory category) {
        service.update(category);
        return "redirect:/admin/data/product-category";
    }
    @GetMapping("/delete/{id}")
    public String deleteFilm(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/admin/data/product-category";
    }


//
//    @GetMapping()
//    public String listPage(Model model,
//                           @RequestParam(name = "page", required = false) Integer page,
//                           @RequestParam(name = "size", required = false) Integer size) {
//
//        page = (page == null ? 1 :  page);
//        size = (size == null ? 10 : size);
//
//        List<Film> films = service.getAll(page > 1 ? (page - 1) * size : 0, size);
//        StringBuilder s = new StringBuilder();
//        model.addAttribute("filmsSize", films.size());
//        model.addAttribute("films", films);
//        model.addAttribute("currentPage", page);
//        model.addAttribute("pageSize", size);
//        model.addAttribute("totalPage", Math.ceil(service.getAll().size() / (double) size));
//
//        return "pages/data/film/list";
//    }

}
