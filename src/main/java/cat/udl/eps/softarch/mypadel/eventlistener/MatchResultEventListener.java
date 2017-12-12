package cat.udl.eps.softarch.mypadel.eventlistener;

import cat.udl.eps.softarch.mypadel.domain.MatchResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.event.AbstractRepositoryEventListener;

import javax.validation.constraints.NotNull;

public class MatchResultEventListener extends AbstractRepositoryEventListener<MatchResult> {

	private final Logger logger = LoggerFactory.getLogger(MatchResult.class);

	@Override
	protected void onBeforeCreate(@NotNull MatchResult entity) {
		super.onBeforeCreate(entity);
		logger.info("Before creating: {}", entity.toString());
	}

}
