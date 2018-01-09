package cat.udl.eps.softarch.mypadel.eventlistener;

import cat.udl.eps.softarch.mypadel.domain.MatchResult;
import cat.udl.eps.softarch.mypadel.domain.MatchResultVerification;
import cat.udl.eps.softarch.mypadel.repository.MatchResultRepository;
import cat.udl.eps.softarch.mypadel.repository.MatchResultVerificationRepository;
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
	private MatchResultVerificationRepository matchResultVerificationRepository;

	@Autowired
	private PlayerRepository playerRepositoryRepository;

	@Override
	protected void onAfterCreate(@NotNull MatchResultVerification entity) {
		super.onAfterCreate(entity);
		this.logger.info("After creating: {}", entity.toString());
		final MatchResult matchResult = this.matchResultRepository.findOne(entity.getId());
	}

}
