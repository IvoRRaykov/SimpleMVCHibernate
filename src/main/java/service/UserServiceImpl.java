package service;

import repository.ConfirmationDAO;
import repository.RoleDAO;
import repository.UserDAO;
import model.UserAccount;
import model.UserConfirmation;
import model.UserRole;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.*;

import static util.Constants.*;


@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    PasswordEncoder passwordEncoder;

    private UserDAO userDAO;
    private ConfirmationDAO confirmationDAO;
    private RoleDAO roleDAO;

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void setConfirmationDAO(ConfirmationDAO confirmationDAO) {
        this.confirmationDAO = confirmationDAO;
    }

    public void setRoleDAO(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }


    @Override
    @Transactional
    public List<UserAccount> listUsers() {
        return this.userDAO.listUsers();
    }

    @Override
    @Transactional
    public List<String> listUsersNames() {
        return this.roleDAO.listUsersNames();
    }

    @Override
    @Transactional
    public UserAccount getUser(String name) {
        return this.userDAO.findUser(name);
    }


    @Override
    @Transactional
    public UserAccount getUser(int id) {
        return this.userDAO.findUser(id);
    }

    @Override
    @Transactional
    public UserAccount getUserForUpdate(int id) { return this.userDAO.findUser(id); }

    @Override
    @Transactional
    public void removeUser(int id) {
        this.userDAO.removeUser(id);
    }

    @Override
    @Transactional
    public List<String> getSimilarNames(String to) {
        return this.userDAO.findSimilarNames(to);
    }

    @Override
    @Transactional
    public void updateUserConfirmation(String code) {

        UserConfirmation confirmation = this.confirmationDAO.getConfirmation(code);
        confirmation.setUserEnabled(true);

        this.confirmationDAO.updateConfirmation(confirmation);
    }

    @Override
    @Transactional
    public void createUser(UserAccount user) throws ConstraintViolationException {

        UserConfirmation confirmation = this.createConfirmation(user);
        UserRole role = this.createRole(user);

        this.doCreateUser(user,confirmation,role);

        this.sendEmailOnBackgroundThread(confirmation.getConfirmationCode(), user.getEmail());
    }

    @Override
    @Transactional
    public void createUser(UserAccount user, String role) {
        UserConfirmation confirmation = this.createConfirmation(user);
        confirmation.setUserEnabled(true);

        UserRole userRole = this.createRole(user);
        userRole.setRole(role);

        this.doCreateUser(user,confirmation,userRole);
    }

    @Override
    @Transactional
    public void updateUser(UserAccount user) throws ConstraintViolationException, DataIntegrityViolationException {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        this.userDAO.updateUser(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

        model.UserAccount user = this.userDAO.findUser(username);

        UserConfirmation confirmation = this.confirmationDAO.getConfirmation(user);
        user.setUserConfirmation(confirmation);

        Set<UserRole> roles = this.roleDAO.getRoles(user);
        user.setUserRole(roles);

        List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRole());

        return buildUserForAuthentication(user, authorities);

    }


    @Override
    public String getRandomAvatar() {
        int random = new Random().nextInt(MAX_RANGE_AVATAR_URL) + MIN_RANGE_AVATAR_URL;
        return AVATAR_PREFIX + random;
    }


    private void doCreateUser(UserAccount user, UserConfirmation confirmation, UserRole role){
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        this.userDAO.createUser(user);

        this.confirmationDAO.createConfirmation(confirmation);
        this.roleDAO.createRole(role);
    }


    private User buildUserForAuthentication(model.UserAccount user, List<GrantedAuthority> authorities) {
        return new User(user.getUserName(), user.getPassword(), user.getUserConfirmation().isUserEnabled(), true, true, true, authorities);
    }


    private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {

        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

        for (UserRole userRole : userRoles) {
            setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
        }

        List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

        return Result;
    }

    private UserRole createRole(UserAccount user) {
        UserRole role = new UserRole();
        role.setUserAccountInRole(user);
        role.setRole(USER_ROLE);
        return role;
    }

    private UserConfirmation createConfirmation(UserAccount user) {

        UserConfirmation confirmation = new UserConfirmation();
        confirmation.setUserAccountRef(user);
        confirmation.setConfirmationCode(UUID.randomUUID().toString());

        return confirmation;
    }

    private void sendEmailOnBackgroundThread(final String text, final String email) {
        final Thread t = new Thread() {
            @Override
            public void run() {

                UserServiceImpl.this.sendEmail(text, email);
            }
        };
        t.start();
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
            message.setText("Click the link to confirm your registration:   " + CONFIRM_PATH + text);

            Transport.send(message);

            System.out.println("Confirmation mail sent");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}