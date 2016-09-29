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
public class UserController {

    private UserService userService;

    @Autowired(required=true)
    @Qualifier(value="userService")
    public void setUserService(UserService ps){
        this.userService = ps;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String listUsers(Model model) {
        model.addAttribute("user", new UserAccount());
        model.addAttribute("listUsers", this.userService.listUsers());
        return "user";
    }

    //For add and update user both
    @RequestMapping(value= "/user/add", method = RequestMethod.POST)
    public String registerUser(@ModelAttribute("user") UserAccount p){

        if(p.getId() == 0){
            //new user, add it
            this.userService.registerUser(p);
        }else{
            //existing user, call update
            this.userService.updateUser(p);
        }

        return "redirect:/users";

    }

    @RequestMapping("/removeUser/{id}")
    public String removeUser(@PathVariable("id") int id){

        this.userService.removeUser(id);
        return "redirect:/users";
    }

    @RequestMapping("/editUser/{id}")
    public String editUser(@PathVariable("id") int id, Model model){
        model.addAttribute("user", this.userService.getUserById(id));
        model.addAttribute("listUsers", this.userService.listUsers());
        return "user";
    }

}