package controller;

import model.UserAccount;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;


@Controller
public class HomeController {

    @RequestMapping(value = {"/home", "/"}, method = RequestMethod.GET)
    public String listProducts(Model model, HttpSession session) {

        UserAccount user = (UserAccount) session.getAttribute("loggedUser");
        model.addAttribute("user", user);

        return "home";
    }
}
