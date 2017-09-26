
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public abstract class MatchJoin extends UriEntity<String>{

    private long id;

    private Date date;

    @Override
    public String getId() { return Long.toString(id); }

    public String getDate() { return date; }

}