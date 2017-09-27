package cat.udl.eps.softarch.mypadel.domain;

import javax.persistence.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.util.GregorianCalendar;

@Entity
public class Match extends UriEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private GregorianCalendar startDate;

    @NotNull
    private Duration duration;

    @NotNull
    private CourtType courtType;

    @NotNull
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

    public CourtType getCourtType() {
        return courtType;
    }

    public void setCourtType(CourtType court) {
        this.courtType = court;
    }

    public GregorianCalendar getCancelationDeadline() {
        return cancelationDeadline;
    }

    public void setCancelationDeadline(GregorianCalendar cancelationDeadline) {
        this.cancelationDeadline = cancelationDeadline;
    }
}
