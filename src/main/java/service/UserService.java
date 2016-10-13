package service;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import model.UserAccount;
import org.hibernate.exception.ConstraintViolationException;

import java.sql.SQLException;
import java.util.List;


public interface UserService {

    public void createUser(UserAccount user) throws ConstraintViolationException;
    public void updateUser(UserAccount user);
    public List<UserAccount> listUsers();
    public UserAccount getUserById(int id);
    public UserAccount getUserByName(String name);
    public void removeUser(int id);
    public UserAccount loginUser(String userName, String password);
    public void updateUserConfirmation(String code);

    public List<String> getSimilarNames(String to);
}