package dataSets;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="learned_portion")
public class LearnedPortion implements Serializable {
    @Id
    @Column(name = "id_u")
    private long user_id;

    @Id
    @Column(name = "id_p")
    private long portion_id;

    public LearnedPortion() {
        Integer test = 0;
        ++test;
    }

    public LearnedPortion(long user_id, long portion_id) {
        this.setUserId(user_id);
        this.setPortionId(portion_id);
    }

    public long getUserId() {
        return user_id;
    }

    public void setUserId(long user_id) {
        this.user_id = user_id;
    }

    public long getPortionId() {
        return portion_id;
    }

    public void setPortionId(long portion_id) {
        this.portion_id = portion_id;
    }
}