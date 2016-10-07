package model;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @NotEmpty(message = "The product must have a code")
    @Pattern(regexp = "[a-zA-Z0-9]{1,6}$", message = "The code must have from 1 to 6 letters or/and digits")
    @Column(name="code")
    private String code;

    @NotEmpty(message = "The product should have a name")
    @Column(name="name")
    private String name;

    @Range(min = 0, message = "The price must be greater than 0")
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
