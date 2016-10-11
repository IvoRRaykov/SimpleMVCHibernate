package dao;

import model.UserAccount;
import model.UserConfirmation;

/**
 * Created by Ivo Raykov on 7.10.2016 г..
 */
public interface ConfirmationDAO {

    public void createConfirmation(UserConfirmation confirmation);
    public void updateConfirmation(UserConfirmation confirmation);
    public UserConfirmation getConfirmationByUser(UserAccount user);
    public UserConfirmation getConfirmationByCode(String code);
}
