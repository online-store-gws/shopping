package uz.greenwhite.shopping.service.impl;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uz.greenwhite.shopping.entity.User;
import uz.greenwhite.shopping.entity.UserRole;
import uz.greenwhite.shopping.repository.UserRepository;
import uz.greenwhite.shopping.service.UserService;

import javax.print.attribute.standard.PresentationDirection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpRequest;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository repository;

    @Override
    public User create(User user)  {
        return repository.save(user);
    }

    @Override
    public User register(User user) {
        user.setRole(UserRole.USER);
        user.setActive(false);
        user.setRegisterDate(Date.from(Instant.now()));
        user.setVerificationCode((int) Math.floor(Math.random() * 100000 + 100000));
        return repository.save(user);
    }

    @Override
    public boolean activation(Long userId, Integer activationCode) {
        User user = repository.findById(userId).orElseThrow();
        if (Objects.equals(user.getVerificationCode(), activationCode)) {
            user.setActive(true);
            user.setVerificationCode(null);
            repository.save(user);
            return true;
        }
        return false;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> data =  repository.findByUsername(username);
        if (data.isPresent()) {
            User user = data.get();

            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getUsername())
                    .password(user.getPassword())
                    .roles(user.getRole().name())
                    .accountLocked(false)
                    .credentialsExpired(false)
                    .disabled(!user.getActive())
                    .accountExpired(false)
                    .build();
        }
        throw new UsernameNotFoundException("user not found");
    }
}
