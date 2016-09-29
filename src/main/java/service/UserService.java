package service;

import model.UserAccount;

import java.util.List;


public interface UserService {

    public void registerUser(UserAccount user);
    public void updateUser(UserAccount user);
    public List<UserAccount> listUsers();
    public UserAccount getUserById(int id);
    public void removeUser(int id);

}