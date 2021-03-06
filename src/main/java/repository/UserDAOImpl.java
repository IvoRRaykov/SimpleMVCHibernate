package repository;

import model.UserAccount;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class UserDAOImpl implements UserDAO {

    private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    @Override
    public void createUser(UserAccount user) throws ConstraintViolationException {

        Session session = this.sessionFactory.getCurrentSession();

        session.persist(user);

        logger.info("UserAccount saved successfully, UserAccount Details =" + user.toString());
    }

    @Override
    public void updateUser(UserAccount user) throws ConstraintViolationException, DataIntegrityViolationException {

        Session session = this.sessionFactory.getCurrentSession();

        session.update(user);

        logger.info("UserAccount updated successfully, UserAccount Details =" + user.toString());
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<UserAccount> listUsers() {

        Session session = this.sessionFactory.getCurrentSession();

        List<UserAccount> usersList = session.createQuery("from UserAccount").list();

        logger.info("UserAccount List obtained successfully");
        for (UserAccount user : usersList) {
            logger.info("  user = " + user.toString());
        }

        return usersList;
    }


    @Override
    public UserAccount findUser(int id) {

        Session session = this.sessionFactory.getCurrentSession();

        UserAccount user = (UserAccount) session.load(UserAccount.class, id);

        logger.info("UserAccount loaded successfully by Id, user details = " + user.toString());

        return user;
    }


    @Override
    @SuppressWarnings("unchecked")
    public UserAccount findUser(String userName) {

        List<UserAccount> users = new ArrayList<>();

        users = sessionFactory.getCurrentSession()
                .createQuery("from UserAccount where userName = :userName")
                .setString("userName", userName)
                .list();

        if (users.size() > 0) {

            logger.info("UserAccount loaded successfully by name=" + userName + ", user details = " + users.get(0).toString());

            return users.get(0);
        } else {

            logger.info("UserAccount NOT loaded successfully by name = " + userName);

            return null;
        }
    }

    @Override
    public void removeUser(int id) {

        Session session = this.sessionFactory.getCurrentSession();

        UserAccount user = (UserAccount) session.load(UserAccount.class, id);
        if (null != user) {
            session.delete(user);
        }

        logger.info("UserAccount deleted successfully, user id = " + id);
    }


    @Override
    @SuppressWarnings("unchecked")
    public List<String> findSimilarNames(String to) {
        List<String> names = new ArrayList<>();

        names = sessionFactory.getCurrentSession()
                .createQuery("select userName from UserAccount where userName like :userName")
                .setString("userName", "%" + to + "%")
                .list();

        if (names.size() > 0) {

            logger.info("Similar names obtained successfully by name = " + to);

            return names;
        } else {
            logger.info("Similar names NOT obtained successfully by name = " + to);
            return null;
        }
    }


}