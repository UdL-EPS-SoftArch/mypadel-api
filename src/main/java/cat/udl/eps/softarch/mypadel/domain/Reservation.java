package cat.udl.eps.softarch.mypadel.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.util.Calendar;



public class Reservation extends  UriEntity<Long> {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private Calendar startDate;

    @NotNull
    private Duration duration;

    public enum courtType{
        INDOOR,
        OUTDOOR,
        UNDEFINED
    }

    @Column(name="courtType", nullable = false)
    private courtType courtType;


    @Override
    public Long getId() {
        return id;
    }

    public Calendar getStartDate(){
        return startDate;
    }

    public void setStartDate(Calendar startDate){
        this.startDate=startDate;

    }

    public Duration getDuration(){
        return duration;
    }

    public void setDuration(Duration duration){
        this.duration=duration;

    }
    public courtType getCourtType(){
        return courtType;
    }

    public void setCourtType(courtType court){
        this.courtType=court;
    }
}
