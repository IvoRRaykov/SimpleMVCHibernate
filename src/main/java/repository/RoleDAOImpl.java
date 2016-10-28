package repository;

import model.UserAccount;
import model.UserRole;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static util.Constants.USER_ROLE;

@Repository
public class RoleDAOImpl implements RoleDAO {


    private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }


    @Override
    public void createRole(UserRole role) {

        this.sessionFactory.getCurrentSession().persist(role);

        logger.info("Role saved successfully, role Details=" + role.toString());

    }

    @Override
    @SuppressWarnings("unchecked")
    public List<String> listUsersNames() {

        Session session = this.sessionFactory.getCurrentSession();

        List<UserAccount> userList = session.createQuery("select userAccountInRole from UserRole where role=:role")
                .setString("role", USER_ROLE)
                .list();

        List<String> userNamesList = new ArrayList<>();
        logger.info("UserNames List obtained successfully");
        for (UserAccount user : userList) {
            userNamesList.add(user.getUserName());
            logger.info("  userName = " + user.getUserName());
        }

        return userNamesList;
    }


    @Override
    @SuppressWarnings("unchecked")
    public Set<UserRole> getRoles(UserAccount userAccount) {
        List<UserRole> roles = new ArrayList<>();

        roles = sessionFactory.getCurrentSession()
                .createQuery("from UserRole where user_id = :userId")
                .setInteger("userId", userAccount.getId())
                .list();

        logger.info("Role obtained successfully by user = " + userAccount.toString());

        return new HashSet<UserRole>(roles);

    }
}
