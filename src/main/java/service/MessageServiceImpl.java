package service;

import dao.MessageDAO;
import dao.UserDAO;
import model.Message;
import model.UserAccount;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class MessageServiceImpl implements MessageService {

    private MessageDAO messageDAO;
    private UserDAO userDAO;

    public void setMessageDAO(MessageDAO messageDAO) {this.messageDAO = messageDAO;}

    public void setUserDAO(UserDAO userDAO) {this.userDAO = userDAO;}

    @Override
    @Transactional
    public Map<Message, String> findAllMessagesToUserId(int userId) {

        Map<Message, String> nameToMessageMap = new TreeMap<>();
        List<Message> mList =  this.messageDAO.findAllMessagesToUserId(userId);

        for(Message m: mList){
            String fromuName = this.messageDAO.findFromuNameByMessageId(m.getMessageId());
            nameToMessageMap.put(m, fromuName);
        }

        return nameToMessageMap;
    }

    @Override
    @Transactional
    public int findAllUnreadMessagesCountToUserId(int userId) {
        return this.messageDAO.findAllUnreadMessagesCountToUserId(userId);
    }


    @Override
    @Transactional
    public Map<Message, String> findAllMessagesFromUserId(int userId) {

        Map<Message, String> nameToMessageMap = new TreeMap<>();
        List<Message> mList =  this.messageDAO.findAllMessagesFromUserId(userId);

        for(Message m: mList){
            String touName = this.messageDAO.findTouNameByMessageId(m.getMessageId());
            nameToMessageMap.put(m, touName);
        }

        return nameToMessageMap;
    }

    @Override
    @Transactional
    public void deleteMessage(int messageId) {

        this.deleteMessage(messageId);
    }

    @Override
    @Transactional
    public  void sendMessage(String text, int fromId, String toName){

        UserAccount fromUser = this.userDAO.getUserById(fromId);

        UserAccount toUser = this.userDAO.getUserByName(toName);

        Message message = new Message();

        message.setText(text);
        message.setDateSent(new Date());
        message.setFromu(fromUser);
        message.setTou(toUser);
        message.setSeen(false);

        this.messageDAO.createMessage(message);
    }
}
