package controller;

import model.UserAccount;
import model.UserRole;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static util.Constants.*;

@Controller
public class AdminController {

    private UserService userService;

    @Autowired(required = true)
    @Qualifier(value = "userService")
    public void setUserService(UserService ps) {
        this.userService = ps;
    }

    @RequestMapping(value = {"/admin/users"}, method = RequestMethod.GET)
    public String adminListUsers(Model model) {

        model.addAttribute(ROLES_ATTRIBUTE, this.generateRoles());
        model.addAttribute(AVATAR_ATTRIBUTE, this.userService.getRandomAvatar());
        model.addAttribute(USER_ATTRIBUTE, new UserAccount());
        model.addAttribute(USER_LIST_ATTRIBUTE, this.userService.listUsers());

        return "adminManageUsers";
    }


    @RequestMapping(value = {"/admin/registerUser"}, method = RequestMethod.POST)
    public String adminRegisterUser(@ModelAttribute("user") UserAccount user, Model model, HttpServletRequest request) {

        String role = request.getParameter(ROLES_ATTRIBUTE);

        if (user.getId() == 0) {
            try {
                this.userService.createUser(user,role);
            } catch (ConstraintViolationException e) {

                return this.generateErrors(model);
            }
        } else {
            try {
                this.userService.updateUser(user);
            } catch (ConstraintViolationException | DataIntegrityViolationException e) {

                return this.generateErrors(model);
            }
        }

        return "redirect:/admin/users";
    }


    @RequestMapping(value = {"/admin/removeUser/{id}"}, method = RequestMethod.GET)
    public String adminRemoveUser(@PathVariable("id") int id) {

        this.userService.removeUser(id);

        return "redirect:/admin/users";
    }

    @RequestMapping("/admin/editUser/{id}")
    public String adminEditUser(@PathVariable("id") int id, Model model) {

        UserAccount user = this.userService.getUserById(id);

        model.addAttribute(AVATAR_ATTRIBUTE, user.getAvatar());
        model.addAttribute(USER_ATTRIBUTE, user);
        model.addAttribute(USER_LIST_ATTRIBUTE, this.userService.listUsers());

        return "adminManageUsers";
    }

    @RequestMapping("/admin/home")
    public String adminHome() {

        return "adminHome";
    }

    private String generateErrors(Model model) {

        model.addAttribute(USER_LIST_ATTRIBUTE, this.userService.listUsers());
        model.addAttribute(ERROR_STRING_ATTRIBUTE, "Account with this username or email already exists!");
        return "adminManageUsers";
    }

    private List<UserRole> generateRoles() {
        List<UserRole> roles = new ArrayList<>();
        UserRole ur1 = new UserRole();
        ur1.setRole(USER_ROLE);
        ur1.setUserAccountInRole(new UserAccount());


        UserRole ur2 = new UserRole();
        ur2.setRole(ADMIN_ROLE);
        ur2.setUserAccountInRole(new UserAccount());

        roles.add(ur1);
        roles.add(ur2);
        return roles;
    }


}
