package repository;

import model.Message;

import java.util.List;


public interface MessageDAO {

    List<Message> findMessagesTo(int userId);

    int findUnreadMessagesCountTo(int userId);

    List<Message> findMessagesFrom(int userId);

    String findFromName(int messageId);

    String findToName(int messageId);

    void updateMessageSeen(int messageId);

    void createMessage(Message message);

    void deleteMessage(int messageId);

}
