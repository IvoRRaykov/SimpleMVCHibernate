package dao;

import model.UserAccount;
import model.UserRole;

import java.util.List;
import java.util.Set;

/**
 * Created by Ivo Raykov on 18.10.2016 г..
 */
public interface RoleDAO {


    void createRole(UserRole role);
    public List<String> listUsersNames();

    public Set<UserRole> getRoleByUser(UserAccount userAccount) ;

    }
