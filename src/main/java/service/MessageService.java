package service;

import model.Message;

import java.util.Map;

/**
 * Created by Ivo Raykov on 12.10.2016 г..
 */
public interface MessageService {

    Map<Message, String> getMessagesTo(int userId);

    int getUnreadMessagesCountTo(int userId);

    Map<Message, String> findMessagesFrom(int userId);

    void deleteMessage(int messageId);

    void sendMessage(String text, int fromId, String toName);

}
