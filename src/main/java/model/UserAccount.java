package model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user_account")
public class UserAccount {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "gender")
    private String gender;


    @Column(name = "password")
    private String password;

    @Column(name = "money")
    private float money;

    @OneToMany(mappedBy = "userAccount" , fetch = FetchType.EAGER, cascade=CascadeType.REFRESH)
    private Set<Product> products = new HashSet<Product>(0);

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setProducts(Set<Product> prducts) {
        this.products = prducts;
    }

    public Set<Product> getProducts() {
        return products;
    }


    @Override
    public String toString() {
        return "id=" + id + ", userName=" + userName + ", gender=" + gender;
    }
}