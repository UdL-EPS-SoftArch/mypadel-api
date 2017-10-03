package cat.udl.eps.softarch.mypadel.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Entity
public class JoinMatch extends UriEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime eventDate = ZonedDateTime.now(ZoneId.systemDefault());

    @Override
    public Long getId() { return id; }

    public void setEventDate(ZonedDateTime dateTime){ this.eventDate = ZonedDateTime.now(ZoneId.systemDefault()); }

    public ZonedDateTime getDate() { return eventDate; }

}