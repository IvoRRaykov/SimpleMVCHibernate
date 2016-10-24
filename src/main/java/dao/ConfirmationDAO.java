package dao;

import model.UserAccount;
import model.UserConfirmation;

/**
 * Created by Ivo Raykov on 7.10.2016 г..
 */
public interface ConfirmationDAO {

    void createConfirmation(UserConfirmation confirmation);

    void updateConfirmation(UserConfirmation confirmation);

    UserConfirmation getConfirmation(UserAccount user);

    UserConfirmation getConfirmation(String code);
}
