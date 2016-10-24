package service;

import model.UserAccount;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;


public interface UserService {

    void createUser(UserAccount user) throws ConstraintViolationException;

    void updateUser(UserAccount user) throws ConstraintViolationException, DataIntegrityViolationException;

    void createUser(UserAccount user, String role);

    void updateUserConfirmation(String code);

    void removeUser(int id);

    UserAccount getUser(int id);

    UserAccount getUserForUpdate(int id);

    UserAccount getUser(String name);

    List<String> getSimilarNames(String to);

    String getRandomAvatar();

    List<UserAccount> listUsers();

    List<String> listUsersNames();

}