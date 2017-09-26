package cat.udl.eps.softarch.mypadel.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MatchResult extends UriEntity<Integer> {

    @Id
    private int id;

    private boolean isDraw;

    @Override
    public Integer getId() {
        return this.id;
    }

    public boolean isDraw() {
        return isDraw;
    }
    public void setDraw(boolean draw) {
        isDraw = draw;
    }

}
