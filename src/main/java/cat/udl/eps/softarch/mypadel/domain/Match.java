package cat.udl.eps.softarch.mypadel.domain;

import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.time.Duration;
import java.util.Date;

public class Match extends UriEntity<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Date startDate;

    private Duration duration;

    private boolean courtIsIndoor;

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

    public boolean getCourtType() {
        return courtIsIndoor;
    }

    public void setCourtType(boolean courtType) {
        this.courtIsIndoor = courtType;
    }

    public Date getCancelationDeadline() {
        return cancelationDeadline;
    }

    public void setCancelationDeadline(Date cancelationDeadline) {
        this.cancelationDeadline = cancelationDeadline;
    }
}
