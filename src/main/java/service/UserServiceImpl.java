package service;

import java.util.List;

import dao.UserDAO;
import model.UserAccount;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    @Transactional
    public void registerUser(UserAccount user) {
        this.userDAO.registerUser(user);
    }

    @Override
    @Transactional
    public void updateUser(UserAccount user) {
        this.userDAO.updateUser(user);
    }

    @Override
    @Transactional
    public List<UserAccount> listUsers() {
        return this.userDAO.listUsers();
    }

    @Override
    @Transactional
    public UserAccount getUserById(int id) {
        return this.userDAO.getUserById(id);
    }

    @Override
    @Transactional
    public void removeUser(int id) {
        this.userDAO.removeUser(id);
    }

    @Override
    @Transactional
    public UserAccount getUserByUserNameAndPassword(String userName, String password) {
        return this.userDAO.getUserByUserNameAndPassword(userName,password);
    }
}