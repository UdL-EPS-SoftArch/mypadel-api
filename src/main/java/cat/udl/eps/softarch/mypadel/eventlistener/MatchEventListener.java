package cat.udl.eps.softarch.mypadel.eventlistener;

import cat.udl.eps.softarch.mypadel.domain.Match;
import cat.udl.eps.softarch.mypadel.domain.MatchResult;
import cat.udl.eps.softarch.mypadel.repository.MatchResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.event.AbstractRepositoryEventListener;

public class MatchEventListener extends AbstractRepositoryEventListener<Match>{

	@Autowired
	private MatchResultRepository matchResultRepository;

	@Override
	protected void onAfterCreate(Match entity) {
		final MatchResult matchResult = new MatchResult();
		matchResult.setId(0);
		matchResult.setMatch(entity);
		matchResultRepository.save(matchResult);
	}
}
