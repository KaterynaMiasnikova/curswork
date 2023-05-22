package dataSets;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="fact")
public class Fact implements Serializable {
    @Id
    @Column(name = "id_f")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "fact")
    private String fact;

    @Column(name = "link")
    private String link;

    @Column(name = "picture")
    private String picture;

    public Fact() {
        Integer test = 0;
        ++test;
    }

    public Fact(String fact, String link, String picture) {
        this.setId(-1);
        this.setFact(fact);
        this.setLink(link);
        this.setPicture(picture);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFact() {
        return fact;
    }

    public void setFact(String fact) {
        this.fact = fact;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
