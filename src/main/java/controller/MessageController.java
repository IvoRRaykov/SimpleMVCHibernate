package controller;

import model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.MessageService;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Ivo Raykov on 12.10.2016 г..
 */

@Controller
public class MessageController {

    private MessageService messageService;
    private UserService userService;

    @Autowired(required = true)
    @Qualifier(value = "messageService")
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    @Autowired(required = true)
    @Qualifier(value = "userService")
    public void setUserService(UserService userService) {this.userService = userService; }




    @RequestMapping(value = {"/message"}, method = RequestMethod.GET)
    public String createMessage(Model model) {

        model.addAttribute("message", new Message());

        return "messageCreate";
    }

    @RequestMapping(value = {"/message/to"}, method = RequestMethod.POST)
    public String findReceiver(HttpServletRequest request, Model model) {

        String to = (String) request.getParameter("to");

        List<String> similarNames = this.userService.getSimilarNames(to);

        if(similarNames == null){
            model.addAttribute("errorString", "No match in database");
            return "messageCreate";
        }

        model.addAttribute("similarNames", similarNames);


        return "messageCreate";
    }

    @RequestMapping(value = {"/message/to/{name}"}, method = RequestMethod.GET)
    public String openTextField(@PathVariable(value = "name") String name, Model model) {

        model.addAttribute("to", name);

        return "messageCreate";
    }

    @RequestMapping(value = {"/message/{name}"}, method = RequestMethod.POST)
    public String sendMessage(@PathVariable(value = "name") String name, HttpServletRequest request, HttpSession session, Model model) {

        String text = (String) request.getParameter("text");
        int id = (int) session.getAttribute("loggedUserId");
        String to = name;

        this.messageService.sendMessage(text,id,to);

        return "redirect:/message/sent";
    }

    @RequestMapping(value = {"/message/sent"}, method = RequestMethod.GET)
    public String viewSentMessages(HttpSession session, Model model) {

        model.addAttribute("sentMessagesList", this.messageService.findAllMessagesFromUserId((int)session.getAttribute("loggedUserId")));

        return "messagesSent";
    }

    @RequestMapping(value = {"/message/received"}, method = RequestMethod.GET)
    public String viewReceivedMessages(HttpSession session, Model model) {

        model.addAttribute("receivedMessagesList", this.messageService.findAllMessagesToUserId((int)session.getAttribute("loggedUserId")));

        return "messagesReceived";
    }

    @RequestMapping(value = {"/message/{from}/{messageId}"}, method = RequestMethod.GET)
    public String deleteMessage(@PathVariable(value = "from") String from, @PathVariable(value = "messageId") int messageId,HttpSession session, Model model) {

        this.messageService.deleteMessage(messageId);

        if(from.equals("s")){
            return "redirect:/message/sent";
        }else if(from.equals("r")){
            return "redirect:/message/received";
        }else{
            return "home";
        }


    }

}
