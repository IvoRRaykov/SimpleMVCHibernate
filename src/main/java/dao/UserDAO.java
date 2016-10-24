package dao;

import model.UserAccount;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;


public interface UserDAO {

    void createUser(UserAccount user) throws ConstraintViolationException;

    void updateUser(UserAccount user) throws ConstraintViolationException, DataIntegrityViolationException;

    List<UserAccount> listUsers();

    UserAccount getUser(int id);

    UserAccount getUser(String name);

    void removeUser(int id);

    List<String> findSimilarNames(String to);
}