package com.osu.web;

import com.osu.domain.User;
import com.osu.domain.UserCreateForm;
import com.osu.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Ekaterina Pyataeva
 */
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    protected static final String PATH_ROOT = "/users";
    protected static final String PATH_CREATE = "/user/create";
    protected static final String PATH_SAVE = "/users/save";
    protected static final String PATH_GET = "users/get/{userId}";
    protected static final String PATH_DELETE = "/users/delete/{userId}";

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = PATH_CREATE, method = RequestMethod.GET)
    public String getUserCreatePage(Model model) {
        LOGGER.debug("Getting user create form");
        model.addAttribute("form", new UserCreateForm());
        return "userCreate";
    }



    @RequestMapping(PATH_ROOT)
    @Secured("ROLE_ADMIN")
    public String getUsers(Model model) {
        LOGGER.debug("Getting users list");
        model.addAttribute("user", new User());
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @RequestMapping(value = PATH_SAVE, method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public String saveUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        LOGGER.debug("Getting save user action");
        if (bindingResult.hasErrors()) {
            return "userForm";
        }
        userService.save(user);
        return "redirect:/users";
    }

    @RequestMapping(PATH_GET)
    @Secured("ROLE_ADMIN")
    public String getUser(@PathVariable("userId") Long userId, Model model) {
        LOGGER.debug("Getting get user action" + userId);
        User user = userService.getUserById(userId);
        model.addAttribute("user", user);
        model.addAttribute("password", "invisible");
        return "userForm";
    }

    @RequestMapping(value = PATH_DELETE, method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public String deleteUser(@PathVariable("userId") Long userId) {
        LOGGER.debug("Delete user by id action");
        User user = userService.getUserById(userId);
        userService.delete(user);
        return "redirect:/users";
    }

}
