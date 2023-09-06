package uz.greenwhite.shopping.controller.admin;

import jakarta.servlet.http.HttpServletResponse;
import jdk.jfr.Category;
import lombok.AllArgsConstructor;
import org.apache.catalina.webresources.FileResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.greenwhite.shopping.entity.ProductCategory;
import uz.greenwhite.shopping.service.ProductCategoryService;

import java.io.*;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin/data/product-category")
@AllArgsConstructor
public class ProductCategoryController {

    private final String FILE_ROOT = "FILES";

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
    public String addFilm(@ModelAttribute ProductCategory category, @RequestParam("file") MultipartFile file) throws IOException {
        category = service.create(category);
        saveFile(category, file);
        return "redirect:/admin/data/product-category";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam(name = "id") Long id, Model model) {
        model.addAttribute("category", service.getById(id));
        return "admin/data/product-category/entry";
    }

    @PostMapping("/edit")
    public String editCategory(@ModelAttribute ProductCategory category, @RequestParam("file") MultipartFile file) throws IOException {
        category = service.update(category);
        saveFile(category, file);
        return "redirect:/admin/data/product-category";
    }

    @GetMapping("/delete/{id}")
    public String deleteFilm(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/admin/data/product-category";
    }

    @GetMapping("/image/{id}")
    public void image(@PathVariable Long id, HttpServletResponse response) {
        ProductCategory category = service.getById(id);
        File file = new File(FILE_ROOT + "/category/" + category.getImage());
        if (file.exists()) {
            response.setContentType("application/octet-stream");
            String headerKey = "Content-Disposition";
        String headerValue = String.format("inline; filename=\"%s\"", file.getName());
        response.setHeader(headerKey, headerValue);
            FileInputStream inputStream;
            try {
                inputStream = new FileInputStream(file);
                try {
                    int c;
                    while ((c = inputStream.read()) != -1) {
                        response.getWriter().write(c);
                    }
                } finally {
                    if (inputStream != null)
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    response.getWriter().close();
                }
            } catch (IOException e) {
                e.printStackTrace();

            }
        } else {
            throw new RuntimeException("File not exist");
        }

    }

    public void saveFile(ProductCategory category, MultipartFile file) throws IOException {

        new File(FILE_ROOT + "/category").mkdir();

        String fileName = file.getOriginalFilename();
        String ext = "";
        if (fileName != null && fileName.contains(".")) {
            ext = fileName.substring(fileName.lastIndexOf("."));
        }
        File sf = new File(FILE_ROOT + "/category/" + category.getId() + ext);

        FileOutputStream fos = new FileOutputStream(sf);
        BufferedOutputStream bw = new BufferedOutputStream(fos);

        bw.write(file.getBytes());
        bw.close();
        fos.close();

        category.setImage(sf.getName());
        service.update(category);

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
