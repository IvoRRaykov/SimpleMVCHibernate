package controller;


import model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.UserService;

import javax.servlet.http.HttpSession;


@Controller
public class UserController {

    private UserService userService;

    @Autowired(required=true)
    @Qualifier(value="userService")
    public void setUserService(UserService ps){
        this.userService = ps;
    }

    @RequestMapping("/user/editUser")
    public String editUser(@ModelAttribute("user") UserAccount userAccount, Model model, HttpSession session){
        model.addAttribute("user", session.getAttribute("loggedUser"));
        return "editUser";
    }

    @RequestMapping("/user/doEditUser")
    public String doEditUser(@ModelAttribute("user") UserAccount userAccount, Model model, HttpSession session){
        this.userService.updateUser(userAccount);
        session.setAttribute("loggedUser",userAccount);
        model.addAttribute("user", userAccount);
        return "userInfo";
    }

    @RequestMapping( value = {"/user/register", "/register"})
    public String registerUser(Model model){
        model.addAttribute("user", new UserAccount());
        return "registerUser";
    }

    @RequestMapping(value = {"/user/doRegister"}, method = RequestMethod.POST)
    public String registerUser(@ModelAttribute("user") UserAccount user){

        this.userService.registerUser(user);
        return "redirect:/user/login";
    }


    @RequestMapping( value = {"/user/login", "/login"})
    public String loginUser(Model model){
        model.addAttribute("user", new UserAccount());
        return "loginUser";
    }

    @RequestMapping( value = {"/user/doLogin"})
    public String doLoginUser(@ModelAttribute("user") UserAccount user, HttpSession session, Model model){
        UserAccount userAccount = this.userService.getUserByUserNameAndPassword(user.getUserName(), user.getPassword());
        if(userAccount == null){
            return "redirect:/user/login";
        }
        else{
            session.setAttribute("loggedUser",userAccount);
            model.addAttribute("user", userAccount);
            return "redirect:/user/userInfo";
        }
    }


    @RequestMapping( value = {"/user/userInfo", "/userInfo"})
    public String loginUser(Model model, HttpSession session){
        model.addAttribute("user", session.getAttribute("loggedUser"));
        return "userInfo";
    }

}