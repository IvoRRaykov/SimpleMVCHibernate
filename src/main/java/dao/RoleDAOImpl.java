package dao;

import model.UserRole;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

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

        logger.info("Role saved successfully, role Details=" + role);

    }
}
