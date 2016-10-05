package model;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @Column(name="code")
    private String code;

    @Column(name="name")
    private String name;

    @Column(name="price")
    private float price;

    @Column(name="for_sale")
    private boolean forSale;

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserAccount userAccount;


    public boolean isForSale() {
        return forSale;
    }

    public void setForSale(boolean forSale) {
        this.forSale = forSale;
    }


    public String getCode() {
        return code;
    }


    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public String toString() {
        return "code=" + code + ", name=" + name + ", price=" + price + "User Name= " + userAccount.getUserName();
    }


}
