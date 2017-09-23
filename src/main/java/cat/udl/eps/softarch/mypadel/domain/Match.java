package cat.udl.eps.softarch.mypadel.domain;

import java.time.Duration;
import java.util.Date;

public class Match extends UriEntity<String> {

    private Date startDate;
    private Duration duration;
    private Enum courtType;
    private Date cancelationDeadline;

    @Override
    public String getId() {
        return null;
    }
}
