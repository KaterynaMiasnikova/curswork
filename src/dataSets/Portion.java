package dataSets;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="portion")
public class Portion implements Serializable {
    @Id
    @Column(name = "id_p")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name_p")
    private String name;

    @Column(name = "portion")
    private String portion;

    @Column(name = "id_t")
    private long id_t;

    public Portion() {
        Integer test = 0;
        ++test;
    }

    public Portion(String name, String portion, long id_t) {
        this.setId(-1);
        this.setName(name);
        this.setPortion(portion);
        this.setThemeId(id_t);
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

    public String getPortion() {
        return portion;
    }

    public void setPortion(String portion) {
        this.portion = portion;
    }

    public long getThemeId() {
        return id_t;
    }

    public void setThemeId(long id_t) {
        this.id_t = id_t;
    }
}
