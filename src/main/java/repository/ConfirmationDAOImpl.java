package repository;

import model.UserAccount;
import model.UserConfirmation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ConfirmationDAOImpl implements ConfirmationDAO{

    private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    @Override
    public void createConfirmation(UserConfirmation confirmation) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(confirmation);

        logger.info("Confirmation saved successfully, confirmation Details=" + confirmation.toString());

    }

    @Override
    public void updateConfirmation(UserConfirmation confirmation) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(confirmation);

        logger.info("Confirmation updated successfully, confirmation details =" + confirmation.toString());
    }

    @Override
    @SuppressWarnings("unchecked")
    public UserConfirmation getConfirmation(UserAccount userAccount) {
        List<UserConfirmation> confirmations = new ArrayList<>();

        confirmations = sessionFactory.getCurrentSession()
                .createQuery("from UserConfirmation where user_id = :userId")
                .setInteger("userId", userAccount.getId())
                .list();

        logger.info("UserConfirmation obtained successfully by user = " + userAccount.toString());

        if (confirmations.size() > 0) {
            return confirmations.get(0);
        } else {
            return null;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public UserConfirmation getConfirmation(String code) {
        List<UserConfirmation> confirmations = new ArrayList<>();

        confirmations = sessionFactory.getCurrentSession()
                .createQuery("from UserConfirmation where confirmationCode = :code")
                .setString("code", code)
                .list();

        logger.info("UserConfirmation obtained successfully by code = " + code);

        if (confirmations.size() > 0) {
            return confirmations.get(0);
        } else {
            return null;
        }
    }

}
