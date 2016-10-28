package service;

import repository.MessageDAO;
import repository.UserDAO;
import model.Message;
import model.UserAccount;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class MessageServiceImpl implements MessageService {

    private MessageDAO messageDAO;
    private UserDAO userDAO;

    public void setMessageDAO(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    @Transactional
    public Map<Message, String> getMessagesTo(int userId) {

        Map<Message, String> nameToMessageMap = new TreeMap<>();
        List<Message> mList = this.messageDAO.findMessagesTo(userId);

        for (Message m : mList) {
            String fromuName = this.messageDAO.findFromName(m.getMessageId());
            nameToMessageMap.put(m, fromuName);

            if (!m.isSeen()) {
                this.messageDAO.updateMessageSeen(m.getMessageId());
            }
        }

        return nameToMessageMap;
    }

    @Override
    @Transactional
    public Map<Message, String> findMessagesFrom(int userId) {

        Map<Message, String> nameToMessageMap = new TreeMap<>();
        List<Message> mList = this.messageDAO.findMessagesFrom(userId);

        for (Message m : mList) {
            String touName = this.messageDAO.findToName(m.getMessageId());
            nameToMessageMap.put(m, touName);
        }

        return nameToMessageMap;
    }


    @Override
    @Transactional
    public void sendMessage(String text, int fromId, String toName) {

        UserAccount fromUser = this.userDAO.findUser(fromId);

        UserAccount toUser = this.userDAO.findUser(toName);

        Message message = new Message();

        message.setText(text);
        message.setDateSent(new Date());
        message.setFromu(fromUser);
        message.setTou(toUser);
        message.setSeen(false);

        this.messageDAO.createMessage(message);
    }

    @Override
    @Transactional
    public int getUnreadMessagesCountTo(int userId) {
        return this.messageDAO.findUnreadMessagesCountTo(userId);
    }


    @Override
    @Transactional
    public void deleteMessage(int messageId) {
        this.messageDAO.deleteMessage(messageId);
    }

}
