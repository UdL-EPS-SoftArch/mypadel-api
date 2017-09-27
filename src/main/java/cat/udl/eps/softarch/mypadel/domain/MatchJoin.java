package cat.udl.eps.softarch.mypadel.domain;

import cat.udl.eps.softarch.mypadel.domain.UriEntity;

import javax.persistence.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Entity
public class MatchJoin extends UriEntity<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private GregorianCalendar eventDate;

    @Override
    public String getId() { return Long.toString(id); }

    public void setDate(GregorianCalendar c){ this.eventDate = c; }

    public GregorianCalendar getDate() { return eventDate; }

}