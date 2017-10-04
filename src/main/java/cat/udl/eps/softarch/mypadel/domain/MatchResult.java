package cat.udl.eps.softarch.mypadel.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Entity
public class MatchResult extends UriEntity<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

	public List<Player> winningPair;

	public List<Player> loosingPair;

	private boolean isDraw = false;

    @Override
    public Integer getId() {
        return this.id;
    }

    public List<Player> getWinningPair () { return this.winningPair; }

    public void setWinningPair (List<Player> winners) { this.winningPair = winners; }

	public List<Player> getLoosingPair () { return this.loosingPair; }

	public void setLoosingPair (List<Player> loosers) { this.winningPair = loosers; }

    public boolean isDraw() {
        return isDraw;
    }

    public void setDraw(boolean draw) {
        isDraw = draw;
    }
	
}
