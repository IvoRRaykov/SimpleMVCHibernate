package controller;

import model.UserAccount;
import org.hibernate.exception.ConstraintViolationException;
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
public class AdminController {

    private UserService userService;

    @Autowired(required=true)
    @Qualifier(value="userService")
    public void setUserService(UserService ps){
        this.userService = ps;
    }

    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public String adminListUsers(Model model, HttpSession session) {
        session.removeAttribute("loggedUser");
        model.addAttribute("user", new UserAccount());
        model.addAttribute("listUsers", this.userService.listUsers());
        return "adminManageUsers";
    }

    @RequestMapping(value = {"/admin/registerUser"}, method = RequestMethod.POST)
    public String adminRegisterUser(@ModelAttribute("user") UserAccount user, HttpSession session, Model model){
        session.removeAttribute("loggedUser");

        if(user.getId() == 0){
            try {
                this.userService.createUser(user);
            } catch (ConstraintViolationException e) {
                model.addAttribute("errorString", "Account with this username or email already exists!");
                return "userRegister";
            }
        }else{
            this.userService.updateUser(user);
        }
        return "redirect:/admin/users";
    }

    @RequestMapping("/admin/removeUser/{id}")
    public String adminRemoveUser(@PathVariable("id") int id, HttpSession session){
        session.removeAttribute("loggedUser");

        this.userService.removeUser(id);
        return "redirect:/admin/users";
    }

    @RequestMapping("/admin/editUser/{id}")
    public String adminEditUser(@PathVariable("id") int id, Model model, HttpSession session){
        session.removeAttribute("loggedUser");

        model.addAttribute("user", this.userService.getUserById(id));
        model.addAttribute("listUsers", this.userService.listUsers());
        return "adminManageUsers";
    }

    @RequestMapping("/admin/home")
    public String adminHome(HttpSession session){
        session.removeAttribute("loggedUser");

        return "adminHome";
    }
}
