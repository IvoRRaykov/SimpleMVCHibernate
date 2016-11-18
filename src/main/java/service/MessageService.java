package service;

import model.Message;

import java.util.Map;

public interface MessageService {

    Map<Message, String> getMessagesTo(int userId);

    int getUnreadMessagesCountTo(int userId);

    Map<Message, String> findMessagesFrom(int userId);

    void deleteMessage(int messageId);

    void sendMessage(String text, int fromId, String toName);

}
