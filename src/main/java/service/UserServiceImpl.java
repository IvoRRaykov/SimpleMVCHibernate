package service;

import dao.ConfirmationDAO;
import dao.UserDAO;
import model.UserAccount;
import model.UserConfirmation;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;
import java.util.UUID;


@Service
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;
    private ConfirmationDAO confirmationDAO;


    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void setConfirmationDAO(ConfirmationDAO confirmationDAO) {
        this.confirmationDAO = confirmationDAO;
    }

    @Override
    @Transactional
    public void createUser(UserAccount user) throws ConstraintViolationException {

        UserConfirmation confirmation = new UserConfirmation();

        confirmation.setUserAccountRef(user);
        confirmation.setConfirmationCode(UUID.randomUUID().toString());

        this.userDAO.registerUser(user);

        this.sendEmail(confirmation.getConfirmationCode(), user.getEmail());

        this.confirmationDAO.createConfirmation(confirmation);
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
    public UserAccount getUserByName(String name) {
        return this.userDAO.getUserByName(name);
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
    public UserAccount loginUser(String userName, String password) {

        UserAccount user = this.userDAO.getUserByUserNameAndPassword(userName, password);
        if (user == null) {
            return null;
        }

        UserConfirmation userConfirmation = this.confirmationDAO.getConfirmationByUser(user);
        user.setUserConfirmation(userConfirmation);

        return user;
    }

    @Override
    @Transactional
    public void updateUserConfirmation(String code) {

        UserConfirmation confirmation = this.confirmationDAO.getConfirmationByCode(code);
        confirmation.setUserEnabled(true);

        this.confirmationDAO.updateConfirmation(confirmation);

    }

    @Override
    @Transactional
    public List<String> getSimilarNames(String to) {
        return this.userDAO.findSimilarNames(to);
    }

    private void sendEmail(String text, String email) {

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("ivo.raykow@gmail.com", "ivoqweasd");
                    }
                });

        try {

            javax.mail.Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("SpringMVCHibernateApp@localhost.com"));
            message.setRecipients(javax.mail.Message.RecipientType.TO,
                    InternetAddress.parse(email));
            message.setSubject("Account confirmation");
            message.setText("Click the link to confirm your registration:   http://localhost:8080/user/confirm/" + text);

            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}