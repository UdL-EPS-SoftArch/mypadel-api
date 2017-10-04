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

	private List<Player> winningPair;

	private List<Player> losingPair;

	private boolean isDraw;

    @Override
    public Integer getId() {
        return this.id;
    }

    public List<Player> getWinningPair () { return this.winningPair; }

    public void setWinningPair (List<Player> winners) { this.winningPair = winners; }

	public List<Player> getLosingPair () { return this.losingPair; }

	public void setLosingPair (List<Player> losers) { this.winningPair = losers; }

    public boolean isDraw() {
        return isDraw;
    }

    public void setDraw(boolean draw) {
        isDraw = draw;
    }

}
