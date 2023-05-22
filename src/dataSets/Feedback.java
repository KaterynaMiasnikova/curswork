package dataSets;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="feedback")
public class Feedback implements Serializable {
    @Id
    @Column(name = "id_fb")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "answer1")
    private int answer1;

    @Column(name = "answer2")
    private int answer2;

    @Column(name = "answer3")
    private int answer3;

    @Column(name = "id_u")
    private long user_id;

    @Column(name = "id_p")
    private long portion_id;

    public Feedback() {
        Integer test = 0;
        ++test;
    }

    public Feedback(int answer1, int answer2, int answer3, long user_id, long portion_id) {
        this.setId(-1);
        this.setAnswer1(answer1);
        this.setAnswer2(answer2);
        this.setAnswer3(answer3);
        this.setUserId(user_id);
        this.setPortionId(portion_id);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAnswer1() {
        return answer1;
    }

    public void setAnswer1(int answer1) {
        this.answer1 = answer1;
    }

    public int getAnswer2() {
        return answer2;
    }

    public void setAnswer2(int answer2) {
        this.answer2 = answer2;
    }

    public int getAnswer3() {
        return answer3;
    }

    public void setAnswer3(int answer3) {
        this.answer3 = answer3;
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
