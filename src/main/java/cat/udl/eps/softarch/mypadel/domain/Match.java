package cat.udl.eps.softarch.mypadel.domain;

import javax.persistence.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.time.Duration;
import java.util.GregorianCalendar;

@Entity
public class Match extends UriEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private GregorianCalendar startDate;

    @Column(nullable = false)
    private Duration duration;

    public enum courtType{
        INDOOR,
        OUTDOOR,
        UNDEFINED
    }

    @Column(name = "courtType", nullable = false)
    private courtType court;

    @Column(name = "cancelationDeadLine", nullable = false)
    private GregorianCalendar cancelationDeadline;

    @Override
    public Long getId() {
        return id;
    }

    public GregorianCalendar getStartDate() {
        return startDate;
    }

    public void setStartDate(GregorianCalendar startDate) {
        this.startDate = startDate;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public courtType getCourt() {
        return court;
    }

    public void setCourt(courtType court) {
        this.court = court;
    }

    public GregorianCalendar getCancelationDeadline() {
        return cancelationDeadline;
    }

    public void setCancelationDeadline(GregorianCalendar cancelationDeadline) {
        this.cancelationDeadline = cancelationDeadline;
    }
}
