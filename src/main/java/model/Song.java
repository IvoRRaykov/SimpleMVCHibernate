package model;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name = "song")
public class Song implements Comparable<Song>{

    @Id
    @Column(name = "song_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String songName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_code")
    private Product product;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", songName='" + songName + '\'' +
                '}';
    }

    @Override
    public int compareTo(Song o) {

        return Integer.valueOf(this.songName.split("\\.")[0]).compareTo(Integer.valueOf(o.getSongName().split("\\.")[0]));
    }
}
