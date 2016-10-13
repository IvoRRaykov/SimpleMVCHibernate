package model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Date;
import java.util.concurrent.Callable;

@Entity
@Table(name = "message")
public class Message implements Comparable<Message> {

    @Id
    @Column(name="message_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int messageId;

    @Column(name = "text")
    @NotEmpty(message = "You should enter some text")
    private String text;

    @Column(name = "date_sent", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fromu")
    private UserAccount fromu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tou")
    private UserAccount tou;

    @Column(name = "seen")
    private boolean seen;

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDateSent() {
        return dateSent;
    }

    public void setDateSent(Date dateSent) {
        this.dateSent = dateSent;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    public UserAccount getFromu() {
        return fromu;
    }

    public void setFromu(UserAccount from) {
        this.fromu = from;
    }

    public UserAccount getTou() {
        return tou;
    }

    public void setTou(UserAccount tou) {
        this.tou = tou;
    }


    @Override
    public int compareTo(Message o) {
        if(o.dateSent.after(this.dateSent)){
            return 1;
        }else if(this.dateSent.after(o.dateSent)){
            return -1;
        }
        return 0;
    }
}
