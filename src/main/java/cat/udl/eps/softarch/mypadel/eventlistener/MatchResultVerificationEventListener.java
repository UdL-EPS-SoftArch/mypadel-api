package cat.udl.eps.softarch.mypadel.eventlistener;

import cat.udl.eps.softarch.mypadel.domain.MatchResultVerification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.event.AbstractRepositoryEventListener;

import javax.validation.constraints.NotNull;

public class MatchResultVerificationEventListener extends AbstractRepositoryEventListener<MatchResultVerification> {

	private final Logger logger = LoggerFactory.getLogger(MatchResultVerification.class);

	@Override
	protected void onBeforeCreate(@NotNull MatchResultVerification entity) {
		super.onBeforeCreate(entity);
		logger.info("Before creating: {}", entity.toString());
	}

}
