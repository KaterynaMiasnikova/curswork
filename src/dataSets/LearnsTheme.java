package dataSets;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="learns_theme")
public class LearnsTheme implements Serializable {
    @Id
    @Column(name = "id_u")
    private long user_id;

    @Id
    @Column(name = "id_t")
    private long theme_id;

    public LearnsTheme() {
        Integer test = 0;
        ++test;
    }

    public LearnsTheme(long user_id, long theme_id) {
        this.setUserId(user_id);
        this.setThemeId(theme_id);
    }

    public long getUserId() {
        return user_id;
    }

    public void setUserId(long user_id) {
        this.user_id = user_id;
    }

    public long getThemeId() {
        return theme_id;
    }

    public void setThemeId(long theme_id) {
        this.theme_id = theme_id;
    }
}