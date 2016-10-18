package controller;


import model.UserAccount;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.MessageService;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import java.security.Principal;

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


    @RequestMapping(value = {"/user/update"}, method = RequestMethod.GET)
    public String updateUser(Model model, HttpSession session) {

        int userId = (int) session.getAttribute(LOGGED_USER_ID_ATTRIBUTE);
        UserAccount user = this.userService.getUserById(userId);

        model.addAttribute(USER_ATTRIBUTE, user);
        model.addAttribute(AVATAR_ATTRIBUTE, user.getAvatar());

        return "userUpdate";
    }

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



    @RequestMapping(value = {"/user/{userId}"}, method = RequestMethod.GET)
    public String userInfo(@PathVariable(value = "userId") int userId, Model model) {

        model.addAttribute(USER_ATTRIBUTE, this.userService.getUserById(userId));
        model.addAttribute(UNREAD_MESSAGES, this.messageService.findAllUnreadMessagesCountToUserId(userId));

        return "userInfo";
    }

}