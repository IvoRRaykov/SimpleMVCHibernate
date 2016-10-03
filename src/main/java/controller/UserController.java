package controller;


import model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public String _adminListUsers(Model model) {
        model.addAttribute("user", new UserAccount());
        model.addAttribute("listUsers", this.userService.listUsers());
        return "_adminUsers";
    }

    //For add and update user both
    @RequestMapping(value = {"/admin/register"}, method = RequestMethod.POST)
    public String _adminRegisterUser(@ModelAttribute("user") UserAccount user){

       if(user.getId() == 0){
            //new user, add it
            this.userService.registerUser(user);
        }else{
            //existing user, call update
            this.userService.updateUser(user);
        }
        return "redirect:/admin/users";
    }

    @RequestMapping("admin/removeUser/{id}")
    public String _adminRemoveUser(@PathVariable("id") int id){

        this.userService.removeUser(id);
        return "redirect:/admin/users";
    }

    @RequestMapping("admin/editUser/{id}")
    public String _adminEditUser(@PathVariable("id") int id, Model model){
        model.addAttribute("user", this.userService.getUserById(id));
        model.addAttribute("listUsers", this.userService.listUsers());
        return "_adminUsers";
    }

    @RequestMapping("user/editUser")
    public String editUser(@ModelAttribute("user") UserAccount userAccount, Model model, HttpSession session){
        model.addAttribute("user", session.getAttribute("loggedUser"));
        return "editUserView";
    }

    @RequestMapping("user/doEditUser")
    public String doEditUser(@ModelAttribute("user") UserAccount userAccount, Model model, HttpSession session){
        this.userService.updateUser(userAccount);
        session.setAttribute("loggedUser",userAccount);
        model.addAttribute("user", userAccount);
        return "userInfoView";
    }

    @RequestMapping( value = {"/user/register", "/register"})
    public String registerUser(Model model){
        model.addAttribute("user", new UserAccount());
        return "registerUserView";
    }

    @RequestMapping(value = {"/user/doRegister"}, method = RequestMethod.POST)
    public String registerUser(@ModelAttribute("user") UserAccount user){

        this.userService.registerUser(user);
        return "redirect:/user/login";
    }


    @RequestMapping( value = {"/user/login", "/login"})
    public String loginUser(Model model){
        model.addAttribute("user", new UserAccount());
        return "loginUserView";
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
        return "userInfoView";
    }

}