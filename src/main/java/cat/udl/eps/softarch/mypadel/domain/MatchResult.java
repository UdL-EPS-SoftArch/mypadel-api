package cat.udl.eps.softarch.mypadel.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;

@Entity
@Table(name = "MatchResult")
public class MatchResult extends UriEntity<Integer> {

	//TODO Add Players and NotNull Checkings

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

	private ArrayList<Player> winningPair;

	private ArrayList<Player> losingPair;

	private boolean isDraw;

	private Match match;

    @Override
    public Integer getId() {
        return this.id;
    }

    public ArrayList<Player> getWinningPair () { return this.winningPair; }

    public void setWinningPair (ArrayList<Player> winners) { this.winningPair = winners; }

	public ArrayList<Player> getLosingPair () { return this.losingPair; }

	public void setLosingPair (ArrayList<Player> losers) { this.losingPair = losers; }

    public boolean isDraw() {
        return isDraw;
    }

    public void setDraw(boolean draw) {
        isDraw = draw;
    }

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}
}
