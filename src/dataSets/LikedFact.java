package dataSets;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="liked_fact")
public class LikedFact implements Serializable {
    @Id
    @Column(name = "id_u")
    private long user_id;

    @Id
    @Column(name = "id_f")
    private long fact_id;

    public LikedFact() {
        Integer test = 0;
        ++test;
    }

    public LikedFact(long user_id, long fact_id) {
        this.setUserId(user_id);
        this.setFactId(fact_id);
    }

    public long getUserId() {
        return user_id;
    }

    public void setUserId(long user_id) {
        this.user_id = user_id;
    }

    public long getFactId() {
        return fact_id;
    }

    public void setFactId(long fact_id) {
        this.fact_id = fact_id;
    }
}
