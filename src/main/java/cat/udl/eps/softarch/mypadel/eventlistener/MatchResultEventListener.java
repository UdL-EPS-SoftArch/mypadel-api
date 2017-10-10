package cat.udl.eps.softarch.mypadel.eventlistener;

import cat.udl.eps.softarch.mypadel.domain.MatchResult;
import org.springframework.data.rest.core.event.AbstractRepositoryEventListener;

public class MatchResultEventListener extends AbstractRepositoryEventListener<MatchResult> {

	@Override
	protected void onBeforeCreate(MatchResult entity) {
		super.onBeforeCreate(entity);
	}

}
