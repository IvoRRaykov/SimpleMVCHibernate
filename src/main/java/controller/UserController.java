package controller;


import model.UserAccount;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.MessageService;
import service.ProductService;
import service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashSet;


@Controller
public class UserController {

    private UserService userService;
    private ProductService productService;
    private MessageService messageService;

    @Autowired(required = true)
    @Qualifier(value = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired(required = true)
    @Qualifier(value = "productService")
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired(required = true)
    @Qualifier(value = "messageService")
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }


    @RequestMapping(value = {"/user/create"}, method = RequestMethod.GET)
    public String createUser(Model model) {

        model.addAttribute("avatar", this.userService.getRandomAvatar());
        model.addAttribute("user", new UserAccount());

        return "userRegister";
    }

    @RequestMapping(value = {"/user/doCreate/{avatar}"}, method = RequestMethod.POST)
    public String doCreateUser(@Valid @ModelAttribute("user") UserAccount user, BindingResult result, @PathVariable(value = "avatar") String avatar, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("avatar", "http://api.adorable.io/avatar/200/" + avatar);
            return "userRegister";
        }

        try {
            user.setAvatar("http://api.adorable.io/avatar/200/" + avatar);
            this.userService.createUser(user);

        } catch (ConstraintViolationException e) {
            model.addAttribute("avatar", "http://api.adorable.io/avatar/200/" + avatar);
            model.addAttribute("errorString", "Account with this username or email already exists!");
            return "userRegister";
        }
        return "redirect:/user/login";
    }

    @RequestMapping(value = {"/user/update"}, method = RequestMethod.GET)
    public String updateUser(Model model, HttpSession session) {

        int userId = (int) session.getAttribute("loggedUserId");
        UserAccount user = this.userService.getUserById(userId);

        model.addAttribute("user", user);
        model.addAttribute("avatar", user.getAvatar());

        return "userUpdate";
    }

    //too much logic in controller
    @RequestMapping(value = {"/user/doUpdate/{avatar}"}, method = RequestMethod.POST)
    public String doUpdateUser(@Valid @ModelAttribute("user") UserAccount userAccount, BindingResult result, @PathVariable(value = "avatar") String avatar, HttpSession session, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("avatar", "http://api.adorable.io/avatar/200/" + avatar);
            return "userUpdate";
        }


        try {
            userAccount.setAvatar("http://api.adorable.io/avatar/200/" + avatar);
            this.userService.updateUser(userAccount);
        } catch (ConstraintViolationException | DataIntegrityViolationException e) {

            model.addAttribute("errorString", "This userName is already in use");
            model.addAttribute("avatar", "http://api.adorable.io/avatar/200/" + avatar);
            return "userUpdate";
        }

        session.setAttribute("loggedUserName", userAccount.getUserName());

        return "redirect:/user/" + userAccount.getId();
    }


    @RequestMapping(value = {"/user/login"}, method = RequestMethod.GET)
    public String loginUser(Model model) {

        model.addAttribute("user", new UserAccount());

        return "userLogin";

    }

    @RequestMapping(value = {"/user/login"}, method = RequestMethod.POST)
    public String doLoginUser(@ModelAttribute("user") UserAccount user, HttpSession session, Model model) {


        UserAccount userAccount = this.userService.loginUser(user.getUserName(), user.getPassword());
        if (userAccount == null) {

            model.addAttribute("errorString", "UserName or password are incorrect");

            return "userLogin";

        }

        if (!userAccount.getUserConfirmation().isUserEnabled()) {

            model.addAttribute("confirmedMessage", "This user is not confirmed!");

            return "userLogin";
        }

        session.setAttribute("loggedUserId", userAccount.getId());
        session.setAttribute("loggedUserName", userAccount.getUserName());

        return "redirect:/user/" + userAccount.getId();

    }

    @RequestMapping(value = {"/user/{userId}"}, method = RequestMethod.GET)
    public String userInfo(@PathVariable(value = "userId") int userId, Model model) {

        UserAccount user = this.userService.getUserById(userId);

        model.addAttribute("user", user);
        model.addAttribute("unreadMessages", this.messageService.findAllUnreadMessagesCountToUserId(userId));

        return "userInfo";
    }

    @RequestMapping(value = {"/user/logout"}, method = RequestMethod.GET)
    public String logoutUser(HttpSession session) {

        session.removeAttribute("loggedUserId");
        session.removeAttribute("loggedUserName");

        return "home";
    }

    @RequestMapping(value = {"/user/confirm/{code}"})
    public String confirmUser(@PathVariable(value = "code") String code, Model model) {

        model.addAttribute("user", new UserAccount());
        model.addAttribute("confirmedMessage", "You have successfully confirmed your email!");

        userService.updateUserConfirmation(code);

        return "userLogin";
    }


}