package dao;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import model.UserAccount;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;

import java.sql.SQLException;
import java.util.List;


public interface UserDAO {

    public void registerUser(UserAccount user) throws ConstraintViolationException;
    public void updateUser(UserAccount user) throws ConstraintViolationException ,DataIntegrityViolationException;
    public List<UserAccount> listUsers();
    public UserAccount getUserById(int id);
    public void removeUser(int id);
    public UserAccount getUserByUserNameAndPassword(String username, String Password);

    public UserAccount getUserByName(String name);

    public List<String> findSimilarNames(String to);
    public String findUserNameFromId(int id);
}