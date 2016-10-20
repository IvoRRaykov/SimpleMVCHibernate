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

    UserAccount getUserById(int id);

    UserAccount getUserByIdForUpdate(int id);

    UserAccount getUserByName(String name);

    List<String> getSimilarNames(String to);

    String getRandomAvatar();

    List<UserAccount> listUsers();

}