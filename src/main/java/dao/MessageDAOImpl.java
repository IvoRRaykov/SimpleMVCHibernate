package dao;

import model.Message;
import model.Product;
import model.UserAccount;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public class MessageDAOImpl implements MessageDAO {


    private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Message> findAllMessagesToUserId(int userId) {
        Session session = this.sessionFactory.getCurrentSession();

        List<Message> messageList = session.createQuery("from Message where tou = :tou")
                .setInteger("tou", userId)
                .list();

        logger.info("Message List obtained successfully from userId " + userId);

        for (Message message : messageList) {

            logger.info("   message :" + message.toString());
        }

        return messageList;
    }

    @Override
    @SuppressWarnings("unchecked")
    public int findAllUnreadMessagesCountToUserId(int  userId) {
        Session session = this.sessionFactory.getCurrentSession();

        List<Message> messageList = session.createQuery("from Message where tou = :tou and seen = false")
                .setInteger("tou", userId)
                .list();

        int size = messageList.size();

        logger.info("Unread Messages size = " + size + "obtained successfully by userId = " + userId);

        return size;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Message> findAllMessagesFromUserId(int userId) {
        Session session = this.sessionFactory.getCurrentSession();

        List<Message> messageList = session.createQuery("from Message where fromu = :fromu")
                .setInteger("fromu", userId)
                .list();

        logger.info("Message List from userId " + userId);


        for (Message message : messageList) {

            logger.info("   message :" + message.toString());
        }

        return messageList;
    }

    @Override
    @SuppressWarnings("unchecked")
    public String findFromuNameByMessageId(int messageId) {


        List<UserAccount> usersIds = new ArrayList<>();

        usersIds = sessionFactory.getCurrentSession()
                .createQuery("select fromu from Message where message_id = :messageId")
                .setInteger("messageId", messageId)
                .list();

        if (usersIds.size() > 0) {

            logger.info("User Name obtained successfully by , message id =" + messageId);

            return usersIds.get(0).getUserName();
        } else {
            return null;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public String findTouNameByMessageId(int messageId) {

        List<UserAccount> usersIds = new ArrayList<>();

        usersIds = sessionFactory.getCurrentSession()
                .createQuery("select tou from Message where message_id = :messageId")
                .setInteger("messageId", messageId)
                .list();

        if (usersIds.size() > 0) {

            logger.info("Message obtained successfully by , message id =" + messageId);

            return usersIds.get(0).getUserName();
        } else {
            return null;
        }
    }

    @Override
    public void updateMessageSeen(int messageId) {

        Query query = sessionFactory.getCurrentSession()
                .createQuery("update Message set seen = :seen where message_id = :messageId");
        query.setBoolean("seen", true);
        query.setInteger("messageId", messageId);
        query.executeUpdate();

        logger.info("Message seen update successfully, message Id=" + messageId);

    }


    @Override
    public void createMessage(Message message) {
        Session session = this.sessionFactory.getCurrentSession();

        session.persist(message);

        logger.info("Message saved successfully, Message Details=" + message.toString());
    }

    @Override
    public void deleteMessage(int messageId) {
        Session session = this.sessionFactory.getCurrentSession();

        Message message = (Message) session.load(Message.class, messageId);
        if (null != message) {

            session.delete(message);
        }

        logger.info("Message deleted successfully, Message details=" + message.toString());
    }
}
