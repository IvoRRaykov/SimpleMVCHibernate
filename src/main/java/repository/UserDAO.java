package repository;

import model.UserAccount;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;


public interface UserDAO {

    void createUser(UserAccount user) throws ConstraintViolationException;

    void updateUser(UserAccount user) throws ConstraintViolationException, DataIntegrityViolationException;

    List<UserAccount> listUsers();

    UserAccount findUser(int id);

    UserAccount findUser(String name);

    void removeUser(int id);

    List<String> findSimilarNames(String to);
}