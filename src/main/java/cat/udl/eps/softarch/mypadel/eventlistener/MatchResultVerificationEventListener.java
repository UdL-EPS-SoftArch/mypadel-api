package cat.udl.eps.softarch.mypadel.eventlistener;

import cat.udl.eps.softarch.mypadel.domain.MatchResultVerification;
import cat.udl.eps.softarch.mypadel.repository.MatchResultRepository;
import cat.udl.eps.softarch.mypadel.repository.PlayerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.event.AbstractRepositoryEventListener;

import javax.validation.constraints.NotNull;

public class MatchResultVerificationEventListener extends AbstractRepositoryEventListener<MatchResultVerification> {

	private final Logger logger = LoggerFactory.getLogger(MatchResultVerification.class);

	@Autowired
	private MatchResultRepository matchResultRepository;

	@Autowired
	private PlayerRepository playerRepositoryRepository;

	@Override
	protected void onBeforeCreate(@NotNull MatchResultVerification entity) {
		super.onBeforeCreate(entity);
		this.logger.info("Before creating: {}", entity.toString());
	}

}
