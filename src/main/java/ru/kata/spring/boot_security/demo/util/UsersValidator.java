//package ru.kata.spring.boot_security.demo.util;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.validation.Errors;
//import org.springframework.validation.Validator;
//import ru.kata.spring.boot_security.demo.model.User;
//import ru.kata.spring.boot_security.demo.service.UserService;
//
//@Component
//public class UsersValidator implements Validator {
//
//    private final UserService userService;
//
//    @Autowired
//    public UsersValidator(UserService userService) {
//        this.userService = userService;
//    }
//
//    @Override
//    public boolean supports(Class<?> clazz) {
//        return User.class.equals(clazz);
//    }
//
//    @Override
//    public void validate(Object target, Errors errors) {
//        User user = (User) target;
//
//        if (userService.show(user.getEmail()).isPresent()) {
//            errors.rejectValue("email", "", "This email is already used");
//        }
//    }
//}
