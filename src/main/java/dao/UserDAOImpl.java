package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import model.UserAccount;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;


@Repository
public class UserDAOImpl implements UserDAO {

    private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    @Override
    public void registerUser(UserAccount user) throws ConstraintViolationException {

        Session session = this.sessionFactory.getCurrentSession();

        session.persist(user);

        logger.info("UserAccount saved successfully, UserAccount Details=" + user);
    }

    @Override
    public void updateUser(UserAccount user) throws ConstraintViolationException,DataIntegrityViolationException {

        Session session = this.sessionFactory.getCurrentSession();

        session.update(user);

        logger.info("UserAccount updated successfully, UserAccount Details=" + user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<UserAccount> listUsers() {

        Session session = this.sessionFactory.getCurrentSession();

        List<UserAccount> usersList = session.createQuery("from UserAccount").list();
        for (UserAccount user : usersList) {
            logger.info("UserAccount List::" + user);
        }

        return usersList;
    }

    @Override
    public UserAccount getUserById(int id) {

        Session session = this.sessionFactory.getCurrentSession();

        UserAccount user = (UserAccount) session.load(UserAccount.class, id);

        logger.info("UserAccount loaded successfully, UserAccount details=" + user);

        return user;
    }


    @Override
    @SuppressWarnings("unchecked")
    public UserAccount getUserByName(String userName) {

        List<UserAccount> users = new ArrayList<>();

        users = sessionFactory.getCurrentSession()
                .createQuery("from UserAccount where userName = :userName")
                .setString("userName", userName)
                .list();

        if (users.size() > 0) {

            logger.info("UserAccount obtained successfully by , user details=" + userName);

            return users.get(0);
        } else {
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

        logger.info("UserAccount deleted successfully, user details=" + user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public UserAccount getUserByUserNameAndPassword(String userName, String password) {

        List<UserAccount> users = new ArrayList<>();

        users = sessionFactory.getCurrentSession()
                .createQuery("from UserAccount where userName = :userName and password =  :password")
                .setString("userName", userName)
                .setString("password", password)
                .list();

        if (users.size() > 0) {

            logger.info("UserAccount obtained successfully by , user details=" + userName + ", " + password);

            return users.get(0);
        } else {
            return null;
        }

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

            logger.info("names obtained successfully similar to " + to);

            return names;
        } else {
            return null;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public String findUserNameFromId(int id) {

        List<String> usersNames = new ArrayList<>();

        usersNames = sessionFactory.getCurrentSession()
                .createQuery("select userName from UserAccount where user_id = :userId")
                .setInteger("userId", id)
                .list();

        if (usersNames.size() > 0) {

            logger.info("UserName obtained successfully by , user id =" + id);

            return usersNames.get(0);
        } else {
            return null;
        }

    }


}