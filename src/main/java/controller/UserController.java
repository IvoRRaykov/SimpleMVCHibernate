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

import static util.Constants.*;


@Controller
public class UserController {

    private UserService userService;
    private MessageService messageService;


    @Autowired(required = true)
    @Qualifier(value = USER_SERVICE)
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired(required = true)
    @Qualifier(value = MESSAGE_SERVICE)
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }


    @RequestMapping(value = {"/user/create"}, method = RequestMethod.GET)
    public String createUser(Model model) {

        model.addAttribute(AVATAR_ATTRIBUTE, this.userService.getRandomAvatar());
        model.addAttribute(USER_ATTRIBUTE, new UserAccount());

        return "userRegister";
    }

    @RequestMapping(value = {"/user/doCreate/{avatar}"}, method = RequestMethod.POST)
    public String doCreateUser(@Valid @ModelAttribute("user") UserAccount user, BindingResult result, @PathVariable(value = "avatar") String avatar, Model model) {

        if (result.hasErrors()) {
            model.addAttribute(AVATAR_ATTRIBUTE, AVATAR_PREFIX + avatar);

            return "userRegister";
        }

        try {
            user.setAvatar(AVATAR_PREFIX + avatar);
            this.userService.createUser(user);

        } catch (ConstraintViolationException e) {

            model.addAttribute(AVATAR_PREFIX + avatar);
            model.addAttribute(ERROR_STRING_ATTRIBUTE, "Account with this username or email already exists!");

            return "userRegister";
        }
        return "redirect:/user/login";
    }

    @RequestMapping(value = {"/user/update"}, method = RequestMethod.GET)
    public String updateUser(Model model, HttpSession session) {

        int userId = (int) session.getAttribute(LOGGED_USER_ID_ATTRIBUTE);
        UserAccount user = this.userService.getUserById(userId);

        model.addAttribute(USER_ATTRIBUTE, user);
        model.addAttribute(AVATAR_ATTRIBUTE, user.getAvatar());

        return "userUpdate";
    }

    //too much logic in controller
    @RequestMapping(value = {"/user/doUpdate/{avatar}"}, method = RequestMethod.POST)
    public String doUpdateUser(@Valid @ModelAttribute("user") UserAccount userAccount, BindingResult result, @PathVariable(value = "avatar") String avatar, HttpSession session, Model model) {

        if (result.hasErrors()) {
            model.addAttribute(AVATAR_ATTRIBUTE, AVATAR_PREFIX + avatar);

            return "userUpdate";
        }
        try {
            userAccount.setAvatar(AVATAR_PREFIX + avatar);
            this.userService.updateUser(userAccount);

        } catch (ConstraintViolationException | DataIntegrityViolationException e) {

            model.addAttribute(ERROR_STRING_ATTRIBUTE, "This userName is already in use");
            model.addAttribute(AVATAR_ATTRIBUTE, AVATAR_PREFIX + avatar);

            return "userUpdate";
        }

        session.setAttribute(LOGGED_USER_NAME_ATTRIBUTE, userAccount.getUserName());

        return "redirect:/user/" + userAccount.getId();
    }


    @RequestMapping(value = {"/user/login"}, method = RequestMethod.GET)
    public String loginUser(Model model) {

        model.addAttribute(USER_ATTRIBUTE, new UserAccount());

        return "userLogin";

    }

    @RequestMapping(value = {"/user/login"}, method = RequestMethod.POST)
    public String doLoginUser(@ModelAttribute("user") UserAccount user, HttpSession session, Model model) {


        UserAccount userAccount = this.userService.loginUser(user.getUserName(), user.getPassword());
        if (userAccount == null) {

            model.addAttribute(ERROR_STRING_ATTRIBUTE, "UserName or password are incorrect");

            return "userLogin";

        }

        if (!userAccount.getUserConfirmation().isUserEnabled()) {

            model.addAttribute(CONFIRMED_MESSAGE_ATTRIBUTE, "This user is not confirmed!");

            return "userLogin";
        }

        session.setAttribute(LOGGED_USER_ID_ATTRIBUTE, userAccount.getId());
        session.setAttribute(LOGGED_USER_NAME_ATTRIBUTE, userAccount.getUserName());

        return "redirect:/user/" + userAccount.getId();

    }

    @RequestMapping(value = {"/user/{userId}"}, method = RequestMethod.GET)
    public String userInfo(@PathVariable(value = "userId") int userId, Model model) {

        UserAccount user = this.userService.getUserById(userId);

        model.addAttribute(USER_ATTRIBUTE, user);
        model.addAttribute(UNREAD_MESSAGES, this.messageService.findAllUnreadMessagesCountToUserId(userId));

        return "userInfo";
    }

    @RequestMapping(value = {"/user/logout"}, method = RequestMethod.GET)
    public String logoutUser(HttpSession session) {

        session.removeAttribute(LOGGED_USER_ID_ATTRIBUTE);
        session.removeAttribute(LOGGED_USER_NAME_ATTRIBUTE);

        return "home";
    }

    @RequestMapping(value = {"/user/confirm/{code}"})
    public String confirmUser(@PathVariable(value = "code") String code, Model model) {

        model.addAttribute(USER_ATTRIBUTE, new UserAccount());
        model.addAttribute(CONFIRMED_MESSAGE_ATTRIBUTE, "You have successfully confirmed your email!");

        userService.updateUserConfirmation(code);

        return "userLogin";
    }


}