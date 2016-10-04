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

@Controller
public class AdminController {

    private UserService userService;

    @Autowired(required=true)
    @Qualifier(value="userService")
    public void setUserService(UserService ps){
        this.userService = ps;
    }

    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public String adminListUsers(Model model) {
        model.addAttribute("user", new UserAccount());
        model.addAttribute("listUsers", this.userService.listUsers());
        return "adminUsers";
    }

    //For add and update user both
    @RequestMapping(value = {"/admin/register"}, method = RequestMethod.POST)
    public String adminRegisterUser(@ModelAttribute("user") UserAccount user){

        if(user.getId() == 0){
            //new user, add it
            this.userService.registerUser(user);
        }else{
            //existing user, call update
            this.userService.updateUser(user);
        }
        return "redirect:/admin/users";
    }

    @RequestMapping("/admin/removeUser/{id}")
    public String adminRemoveUser(@PathVariable("id") int id){

        this.userService.removeUser(id);
        return "redirect:/admin/users";
    }

    @RequestMapping("/admin/editUser/{id}")
    public String adminEditUser(@PathVariable("id") int id, Model model){
        model.addAttribute("user", this.userService.getUserById(id));
        model.addAttribute("listUsers", this.userService.listUsers());
        return "adminUsers";
    }

    @RequestMapping("/admin/home")
    public String adminHome(){
        return "adminHome";
    }
}
