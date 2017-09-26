package cat.udl.eps.softarch.mypadel.domain;

import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.time.Duration;
import java.util.Date;

public class Match extends UriEntity<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "startDate", nullable = false)
    private Date startDate;

    @Column(name = "duration", nullable = false)
    private Duration duration;

    private enum courtType{
        INDOOR,
        OUTDOOR,
        UNDEFINED
    };

    @Column(name = "cancelationDeadLine")
    private Date cancelationDeadline;

    @Override
    public String getId() {
        return Long.toString(id);
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public Date getCancelationDeadline() {
        return cancelationDeadline;
    }

    public void setCancelationDeadline(Date cancelationDeadline) {
        this.cancelationDeadline = cancelationDeadline;
    }
}
