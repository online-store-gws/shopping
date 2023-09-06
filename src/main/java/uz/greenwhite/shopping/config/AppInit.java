package uz.greenwhite.shopping.config;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import uz.greenwhite.shopping.entity.ProductCategory;
import uz.greenwhite.shopping.repository.ProductCategoryRepository;

import java.io.File;

@Component
@AllArgsConstructor
public class AppInit implements ApplicationRunner {

    private ProductCategoryRepository productCategoryRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        fillProductCategory();

        File file = new File("FILES");
        file.mkdir();
    }


    private void fillProductCategory() {
        Faker faker = new Faker();
        for (int i = 1; i <= 100; i++) {
            productCategoryRepository.save(new ProductCategory(faker.book().title()));
        }
    }
}
