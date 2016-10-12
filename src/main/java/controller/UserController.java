package controller;


import model.Product;
import model.UserAccount;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import service.ProductService;
import service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;


@Controller
public class UserController {

    private UserService userService;
    private ProductService productService;

    @Autowired(required = true)
    @Qualifier(value = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired(required = true)
    @Qualifier(value = "productService")
    public void setProductService(ProductService productService) {this.productService = productService;}



    @RequestMapping(value = {"/user/create"}, method = RequestMethod.GET)
    public String createUser(Model model) {

        model.addAttribute("user", new UserAccount());

        return "registerUser";
    }

    @RequestMapping(value = {"/user/doCreate"}, method = RequestMethod.POST)
    public String doCreateUser(@Valid @ModelAttribute("user") UserAccount user, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "registerUser";
        }
        try {
            this.userService.createUser(user);
        } catch (ConstraintViolationException e) {

            model.addAttribute("errorString", "Account with this username or email already exists!");
            return "registerUser";
        }
        return "redirect:/user/login";
    }

    @RequestMapping(value = {"/user/update"},  method = RequestMethod.GET)
    public String updateUser(Model model, HttpSession session) {

            int userId = (int) session.getAttribute("loggedUserId");
            UserAccount userAccount = this.userService.getUserById(userId);

            model.addAttribute("user", userAccount);

        return "updateUser";
    }

    @RequestMapping(value = {"/user/doUpdate"}, method = RequestMethod.POST)
    public String doUpdateUser(@Valid @ModelAttribute("user") UserAccount userAccount, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "updateUser";
        }

        List<Product> list = this.productService.listProductsForUser(userAccount.getId());
        userAccount.setProducts(new HashSet<>(list));
        this.userService.updateUser(userAccount);

        return "redirect:/user/" + userAccount.getId();
    }


    @RequestMapping(value = {"/user/login"}, method = RequestMethod.GET)
    public String loginUser(Model model) {

        model.addAttribute("user", new UserAccount());

        return "loginUser";

    }

    @RequestMapping(value = {"/user/login"}, method = RequestMethod.POST)
    public String doLoginUser(@ModelAttribute("user") UserAccount user, HttpSession session, Model model) {


        UserAccount userAccount = this.userService.loginUser(user.getUserName(), user.getPassword());
        if (userAccount == null) {

            model.addAttribute("errorString", "UserName or password are incorrect");

            return "loginUser";

        } else {
            if(!userAccount.getUserConfirmation().isUserEnabled()){

                model.addAttribute("confirmedMessage", "This user is not confirmed!");

                return "loginUser";
            }

            session.setAttribute("loggedUserId", userAccount.getId());

            return "redirect:/user/" + userAccount.getId();
        }
    }

    @RequestMapping(value = {"/user/{userId}"}, method = RequestMethod.GET)
    public String userInfo(@PathVariable(value = "userId") int userId, Model model) {

        UserAccount user = this.userService.getUserById(userId);

        model.addAttribute("user", user);

        return "userInfo";
    }

    @RequestMapping(value = {"/user/logout"}, method = RequestMethod.GET)
    public String logoutUser(HttpSession session) {

        session.removeAttribute("loggedUserId");

        return "home";
    }

    @RequestMapping(value = {"/user/confirm/{code}"})
    public String confirmUser(@PathVariable(value = "code") String code, Model model){

        model.addAttribute("user", new UserAccount());
        model.addAttribute("confirmedMessage", "You have successfully confirmed your email!");

        userService.updateUserConfirmation(code);

        return "loginUser";
    }


}