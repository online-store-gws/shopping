package uz.greenwhite.shopping.config;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.greenwhite.shopping.entity.ProductCategory;
import uz.greenwhite.shopping.entity.User;
import uz.greenwhite.shopping.entity.UserRole;
import uz.greenwhite.shopping.repository.ProductCategoryRepository;
import uz.greenwhite.shopping.repository.UserRepository;

import java.io.File;

@Component
@AllArgsConstructor
public class AppInit implements ApplicationRunner {

    private final ProductCategoryRepository productCategoryRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        fillProductCategory();

        users();

        File file = new File("FILES");
        file.mkdir();


    }

    private void users() {

        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = new User();
            admin.setRole(UserRole.ADMIN);
            admin.setActive(true);
            admin.setFirstName("Administrator");
            admin.setUsername("admin123");
            admin.setPassword(encoder.encode("Admin123!"));

            userRepository.save(admin);
        }

        if (userRepository.findByUsername("user").isEmpty()) {
            User user = new User();
            user.setRole(UserRole.USER);
            user.setActive(false);
            user.setVerificationCode(1234);
            user.setFirstName("User");
            user.setUsername("user123");
            user.setPassword(encoder.encode("User123!"));

            userRepository.save(user);
        }


    }


    private void fillProductCategory() {
        Faker faker = new Faker();
        for (int i = 1; i <= 100; i++) {
            productCategoryRepository.save(new ProductCategory(faker.book().title()));
        }
    }
}
