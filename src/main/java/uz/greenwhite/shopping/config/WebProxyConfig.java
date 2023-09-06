package uz.greenwhite.shopping.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebProxyConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        // public
        registry.addViewController("/").setViewName("public/index");

        // admin
        registry.addViewController("/admin").setViewName("redirect:admin/dashboard");
        registry.addViewController("/admin/dashboard").setViewName("admin/dashboard");

        // user
        registry.addViewController("/user").setViewName("redirect:user/dashboard");
        registry.addViewController("/user/dashboard").setViewName("user/dashboard");
    }
}
