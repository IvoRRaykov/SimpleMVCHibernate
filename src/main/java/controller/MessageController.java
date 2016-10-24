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

import static util.Constants.*;

@Controller
public class MessageController {

    private MessageService messageService;
    private UserService userService;

    @Autowired(required = true)
    @Qualifier(value = MESSAGE_SERVICE)
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    @Autowired(required = true)
    @Qualifier(value = USER_SERVICE)
    public void setUserService(UserService userService) {this.userService = userService; }


    @RequestMapping(value = {"/message"}, method = RequestMethod.GET)
    public String createMessage(Model model) {

        model.addAttribute(MESSAGE_ATTRIBUTE, new Message());

        return "messageCreate";
    }

    @RequestMapping(value = {"/message/to"}, method = RequestMethod.POST)
    public String findReceiver(HttpServletRequest request, Model model) {

        String to = (String) request.getParameter("to");

        List<String> similarNames = this.userService.getSimilarNames(to);

        if(similarNames == null){
            model.addAttribute(ERROR_STRING_ATTRIBUTE, "No match in database");
            return "messageCreate";
        }

        model.addAttribute(SIMILAR_NAMES_ATTRIBUTE, similarNames);


        return "messageCreate";
    }

    @RequestMapping(value = {"/message/to/{name}"}, method = RequestMethod.GET)
    public String openTextField(@PathVariable(value = "name") String name, Model model) {

        model.addAttribute(TO_ATTRIBUTE, name);

        return "messageCreate";
    }

    @RequestMapping(value = {"/message/{name}"}, method = RequestMethod.POST)
    public String sendMessage(@PathVariable(value = "name") String name, HttpServletRequest request, HttpSession session, Model model) {

        String text = (String) request.getParameter(TEXT_ATTRIBUTE);
        int id = (int) session.getAttribute(LOGGED_USER_ID_ATTRIBUTE);
        String to = name;

        this.messageService.sendMessage(text,id,to);

        return "redirect:/message/sent";
    }

    @RequestMapping(value = {"/message/sent"}, method = RequestMethod.GET)
    public String viewSentMessages(HttpSession session, Model model) {

        model.addAttribute(SENT_MESSAGES_LIST_ATTRIBUTE, this.messageService.findMessagesFrom((int)session.getAttribute(LOGGED_USER_ID_ATTRIBUTE)));

        return "messagesSent";
    }

    @RequestMapping(value = {"/message/received"}, method = RequestMethod.GET)
    public String viewReceivedMessages(HttpSession session, Model model) {

        model.addAttribute(RECEIVED_MESSAGES_LIST_ATTRIBUTE, this.messageService.getMessagesTo((int)session.getAttribute(LOGGED_USER_ID_ATTRIBUTE)));

        return "messagesReceived";
    }

    @RequestMapping(value = {"/message/{from}/{messageId}"}, method = RequestMethod.GET)
    public String deleteMessage(@PathVariable(value = "from") String from, @PathVariable(value = "messageId") int messageId,HttpSession session, Model model) {

        this.messageService.deleteMessage(messageId);

        if(from.equals(SENT_URL_MARKER)){
            return "redirect:/message/sent";
        }else if(from.equals(RECEIVED_URL_MARKER)){
            return "redirect:/message/received";
        }else{
            return "home";
        }


    }

}
