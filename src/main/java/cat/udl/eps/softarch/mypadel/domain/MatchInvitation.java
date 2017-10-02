package cat.udl.eps.softarch.mypadel.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Calendar;

@Entity
public class MatchInvitation {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private Calendar eventDate;

    //methods
    public Long getId(){ return this.id;}
    public void setId(long id){ this.id= id; }

    public Calendar getEventDate(){ return eventDate;}
    public void setEventDate(Calendar date){ eventDate = date; }
}
