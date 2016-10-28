package repository;

import model.UserAccount;
import model.UserRole;

import java.util.List;
import java.util.Set;

/**
 * Created by Ivo Raykov on 18.10.2016 г..
 */
public interface RoleDAO {


    void createRole(UserRole role);

    List<String> listUsersNames();

    Set<UserRole> getRoles(UserAccount userAccount);

}
