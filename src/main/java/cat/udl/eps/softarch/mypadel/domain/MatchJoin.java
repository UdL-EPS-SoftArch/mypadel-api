package cat.udl.eps.softarch.mypadel.domain;

import cat.udl.eps.softarch.mypadel.domain.UriEntity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Entity
public class MatchJoin extends UriEntity<String> {

    private long id;

    private GregorianCalendar eventDate;

    @Override
    public String getId() { return Long.toString(id); }

    public GregorianCalendar getDate() { return this.eventDate; }

}