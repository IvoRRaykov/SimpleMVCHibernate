package dao;

import model.Message;

import java.util.List;
import java.util.Set;


public interface MessageDAO {

    public List<Message> findAllMessagesToUserId(int userId);
    public int findAllUnreadMessagesCountToUserId(int userId);
    public List<Message> findAllMessagesFromUserId(int userId);

    public String findFromuNameByMessageId(int messageId) ;
    public String findTouNameByMessageId(int messageId);

    public void updateMessageSeen(int messageId);
    public void createMessage(Message message);
    public void deleteMessage(int messageId);

}
