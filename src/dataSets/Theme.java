package dataSets;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="theme")
public class Theme implements Serializable {
    @Id
    @Column(name = "id_t")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name_t")
    private String name;

    @Column(name = "portion_num")
    private int portions;

    @Column(name = "description_t")
    private String description;

    @Column(name = "picture")
    private String picture;

    public Theme() {
        Integer test = 0;
        ++test;
    }

    public Theme(String name, int portions, String description, String picture) {
        this.setId(-1);
        this.setName(name);
        this.setPortions(portions);
        this.setDescription(description);
        this.setPicture(picture);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPortions() {
        return portions;
    }

    public void setPortions(int portions) {
        this.portions = portions;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}