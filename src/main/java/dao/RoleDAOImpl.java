package dao;

import model.UserAccount;
import model.UserRole;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public Set<UserRole> getRoleByUser(UserAccount userAccount) {
        List<UserRole> roles = new ArrayList<>();

        roles = sessionFactory.getCurrentSession()
                .createQuery("from UserRole where user_id = :userId")
                .setInteger("userId", userAccount.getId())
                .list();

        logger.info("Role obtained successfully by user = " + userAccount.toString());

        return new HashSet<UserRole>(roles);

    }
}
