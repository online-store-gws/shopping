package uz.greenwhite.shopping.service;

import uz.greenwhite.shopping.entity.User;


public interface UserService {

    User create(User user);
    User register(User user);

    boolean activation(Long userId, Integer activationCode);
}
