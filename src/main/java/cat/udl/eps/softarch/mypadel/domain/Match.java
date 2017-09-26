package cat.udl.eps.softarch.mypadel.domain;

import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.time.Duration;
import java.util.GregorianCalendar;

public class Match extends UriEntity<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private GregorianCalendar startDate;

    @Column(nullable = false)
    private Duration duration;

    private enum courtType{
        INDOOR,
        OUTDOOR,
        UNDEFINED
    };

    @Column(name = "courtType", nullable = false)
    private courtType court;

    @Column(name = "cancelationDeadLine")
    private GregorianCalendar cancelationDeadline;

    @Override
    public String getId() {
        return Long.toString(id);
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

    public GregorianCalendar getCancelationDeadline() {
        return cancelationDeadline;
    }

    public void setCancelationDeadline(GregorianCalendar cancelationDeadline) {
        this.cancelationDeadline = cancelationDeadline;
    }
}
