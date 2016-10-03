package dao;

import model.UserAccount;

import java.util.List;


public interface UserDAO {

    public void registerUser(UserAccount user);
    public void updateUser(UserAccount user);
    public List<UserAccount> listUsers();
    public UserAccount getUserById(int id);
    public void removeUser(int id);
    public UserAccount getUserByUserNameAndPassword(String username, String Password);
}