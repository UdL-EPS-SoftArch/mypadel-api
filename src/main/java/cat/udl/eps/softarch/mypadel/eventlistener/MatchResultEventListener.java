package cat.udl.eps.softarch.mypadel.eventlistener;

import cat.udl.eps.softarch.mypadel.domain.Admin;
import cat.udl.eps.softarch.mypadel.domain.MatchResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.event.AbstractRepositoryEventListener;

public class MatchResultEventListener extends AbstractRepositoryEventListener<MatchResult> {

	private final Logger logger = LoggerFactory.getLogger(Admin.class);

	@Override
	protected void onBeforeCreate(MatchResult entity) {
		super.onBeforeCreate(entity);
		logger.info("Before creating: {}", entity.toString());
	}

}
