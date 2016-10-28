package model;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @NotEmpty(message = "The product must have a code")
    @Pattern(regexp = "[a-zA-Z0-9]{1,6}$", message = "The code must have from 1 to 6 letters or/and digits")
    @Column(name = "code")
    private String code;

    @NotEmpty(message = "The product should have a name")
    @Column(name = "name")
    private String name;

    @Range(min = 0, message = "The price must be greater than 0")
    @Column(name = "price")
    private float price;

    @Column(name = "for_sale")
    private boolean forSale;

    @Column(name = "picture_file_path")
    private String pictureFilePath;


    @Column(name = "genre")
    private String genre;

    @Transient
    private String pictureBase64String;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserAccount userAccount;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<Song> songs = new HashSet<Song>(0);

    public Set<Song> getSongs() {
        return songs;
    }

    public void setSongs(Set<Song> songs) {
        this.songs = songs;
    }

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

    public String getPictureFilePath() {
        return pictureFilePath;
    }

    public void setPictureFilePath(String pictureFilePath) {
        this.pictureFilePath = pictureFilePath;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public String getPictureBase64String() {
        return pictureBase64String;
    }

    public void setPictureBase64String(String pictureBase64String) {
        this.pictureBase64String = pictureBase64String;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String toString() {

        String sUserAccount = (this.userAccount == null) ? "NA" : this.userAccount.toString();

        return "code=" + code + ", name=" + name + ", price=" + price + ", forSale=" + this.forSale + ", userAccount" + sUserAccount;
    }


}
