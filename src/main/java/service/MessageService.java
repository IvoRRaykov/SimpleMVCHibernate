package service;

import model.Message;

import java.util.List;
import java.util.Map;

/**
 * Created by Ivo Raykov on 12.10.2016 г..
 */
public interface MessageService {
    public Map<Message, String> findAllMessagesToUserId(int userId);
    public int findAllUnreadMessagesCountToUserId(int userId);
    public Map<Message, String> findAllMessagesFromUserId(int userId);
    public void deleteMessage(int messageId);
    public  void sendMessage(String text, int fromId, String toName);

}
