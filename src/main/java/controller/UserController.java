package controller;


import model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Controller
public class UserController {

    private UserService userService;

    @Autowired(required = true)
    @Qualifier(value = "userService")
    public void setUserService(UserService ps) {
        this.userService = ps;
    }

    @RequestMapping("/user/editUser")
    public String editUser(@ModelAttribute("user") UserAccount userAccount, Model model, HttpSession session) {
        if (userAccount.getUserName() == null)
            model.addAttribute("user", session.getAttribute("loggedUser"));
        else
            model.addAttribute("user", userAccount);

        return "editUser";
    }

    @RequestMapping("/user/doEditUser")
    public String doEditUser(@Valid @ModelAttribute("user") UserAccount userAccount, BindingResult result, Model model, HttpSession session) {

        if (result.hasErrors()) {
            return "editUser";
        }

        this.userService.updateUser(userAccount);
        session.setAttribute("loggedUser", userAccount);
        model.addAttribute("user", userAccount);
        return "userInfo";
    }

    @RequestMapping(value = {"/user/register", "/register"})
    public String registerUser(Model model) {
        model.addAttribute("user", new UserAccount());
        return "registerUser";
    }

    @RequestMapping(value = {"/user/doRegister"}, method = RequestMethod.POST)
    public String doRegisterUser(@Valid @ModelAttribute("user") UserAccount user, BindingResult result) {
        if (result.hasErrors()) {
            return "registerUser";
        }

        this.userService.registerUser(user);
        return "redirect:/user/login";
    }


    @RequestMapping(value = {"/user/login", "/login"})
    public String loginUser(Model model) {
        model.addAttribute("user", new UserAccount());
        return "loginUser";


    }

    @RequestMapping(value = {"/user/doLogin"})
    public String doLoginUser(@ModelAttribute("user") UserAccount user, HttpSession session, Model model) {

        UserAccount userAccount = this.userService.getUserByUserNameAndPassword(user.getUserName(), user.getPassword());
        if (userAccount == null) {
            model.addAttribute("errorString", "UserName or password are wrong!");
            return "loginUser";
        } else {
            session.setAttribute("loggedUser", userAccount);
            model.addAttribute("user", userAccount);
            return "redirect:/user/userInfo";
        }
    }

    @RequestMapping(value = {"/user/userInfo", "/userInfo"})
    public String userInfo(Model model, HttpSession session) {
        UserAccount user = (UserAccount) session.getAttribute("loggedUser");
        model.addAttribute("user", user);
        return "userInfo";
    }

    @RequestMapping(value = {"/user/logout", "/logout"})
    public String logoutUser(HttpSession session) {
        session.removeAttribute("loggedUser");
        return "home";
    }


}