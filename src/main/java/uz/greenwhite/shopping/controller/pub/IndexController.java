package uz.greenwhite.shopping.controller.pub;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() { return "public/index"; }
}
