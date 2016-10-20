package model;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "user_role")
public class UserRole{

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_role_id")
    private Integer userRoleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserAccount userAccountInRole;

    @Column(name = "role")
    private String role;

    public Integer getUserRoleId() {
        return this.userRoleId;
    }

    public void setUserRoleId(Integer userRoleId) {
        this.userRoleId = userRoleId;
    }

    public UserAccount getUserAccountInRole() {
        return this.userAccountInRole;
    }

    public void setUserAccountInRole(UserAccount user) {
        this.userAccountInRole = user;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "userRoleId=" + this.userRoleId + ", role=" + this.role + ", user = " + userAccountInRole;
    }
}