package controller;

import model.UserAccount;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static util.Constants.*;


@Controller
public class MainController {

    private UserService userService;

    @Autowired(required = true)
    @Qualifier(value = USER_SERVICE)
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(value = {"/home", "/"}, method = RequestMethod.GET)
    public String home(HttpSession session) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();

            UserAccount user = this.userService.getUserByName(userDetail.getUsername());

            session.setAttribute(LOGGED_USER_ID_ATTRIBUTE ,user.getId());
            session.setAttribute(LOGGED_USER_NAME_ATTRIBUTE ,user.getUserName());
        }

        return "home";
    }


    @RequestMapping(value = {"/account/create"}, method = RequestMethod.GET)
    public String createAccount(Model model) {

        model.addAttribute(AVATAR_ATTRIBUTE, this.userService.getRandomAvatar());
        model.addAttribute(USER_ATTRIBUTE, new UserAccount());

        return "register";
    }

    @RequestMapping(value = {"/account/doCreate/{avatar}"}, method = RequestMethod.POST)
    public String doCreateAccount(@Valid @ModelAttribute("user") UserAccount user, BindingResult result, @PathVariable(value = "avatar") String avatar, Model model) {

        if (result.hasErrors()) {
            model.addAttribute(AVATAR_ATTRIBUTE, AVATAR_PREFIX + avatar);
            return "register";
        }

        try {
            user.setAvatar(AVATAR_PREFIX + avatar);
            this.userService.createUser(user);

        } catch (ConstraintViolationException e) {

            model.addAttribute(AVATAR_PREFIX + avatar);
            model.addAttribute(ERROR_STRING_ATTRIBUTE, "Account with this username or email already exists!");

            return "register";
        }

        return "redirect:/login";
    }



    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout, HttpServletRequest request, Model model, HttpServletResponse response) {

        HttpSession session = request.getSession();

        if (error != null) {
            model.addAttribute("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
        }

        if (logout != null) {

            session.removeAttribute(LOGGED_USER_ID_ATTRIBUTE);
            session.removeAttribute(LOGGED_USER_NAME_ATTRIBUTE);

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null){
                new SecurityContextLogoutHandler().logout(request, response, auth);
            }


            model.addAttribute("msg", "You've been logged out successfully.");
        }

        return "login";
    }

    @RequestMapping(value = {"/account/confirm/{code}"})
    public String confirmUser(@PathVariable(value = "code") String code, Model model) {

        model.addAttribute(USER_ATTRIBUTE, new UserAccount());
        model.addAttribute(CONFIRMED_MESSAGE_ATTRIBUTE, "You have successfully confirmed your email!");

        userService.updateUserConfirmation(code);

        return "login";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accesssDenied(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            System.out.println(userDetail);

            model.addAttribute("username", userDetail.getUsername());
        }

        return "403";

    }

    private String getErrorMessage(HttpServletRequest request, String key) {

        Exception exception = (Exception) request.getSession().getAttribute(key);

        String error = "";
        if (exception instanceof BadCredentialsException) {
            error = "Invalid username and password!";
        } else if (exception instanceof LockedException) {
            error = exception.getMessage();
        } else {
            error = "Invalid username and password!";
        }

        return error;
    }



}
