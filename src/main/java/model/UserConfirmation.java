package model;

import javax.persistence.*;

@Entity
@Table(name = "confirmation")
public class UserConfirmation {

    @Id
    @Column(name = "confirmation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "confirmation_code")
    private String confirmationCode;

    @Column(name = "enabled")
    private boolean userEnabled;

    @OneToOne()
    @JoinColumn(name = "user_id")
    private UserAccount userAccountRef;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConfirmationCode() {
        return confirmationCode;
    }

    public void setConfirmationCode(String confirmationCode) {
        this.confirmationCode = confirmationCode;
    }

    public boolean isUserEnabled() {
        return userEnabled;
    }

    public void setUserEnabled(boolean userEnabled) {
        this.userEnabled = userEnabled;
    }

    public UserAccount getUserAccountRef() {
        return userAccountRef;
    }

    public void setUserAccountRef(UserAccount userAccount) {
        this.userAccountRef = userAccount;
    }

    @Override
    public String toString() {
        String sUserAccount = (this.userAccountRef == null) ? "NA" : this.userAccountRef.toString();
        return "confirmationId=" + this.id + ", confirmationCode=" + this.confirmationCode + ", enabled=" + this.userEnabled + ", user=" + sUserAccount;
    }
}
