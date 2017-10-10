package cat.udl.eps.softarch.mypadel.handler;

import cat.udl.eps.softarch.mypadel.domain.MatchResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.annotation.*;

import javax.transaction.Transactional;

@RepositoryEventHandler(MatchResult.class)
public class MatchResultEventHandler {

	final Logger logger = LoggerFactory.getLogger(MatchResult.class);

	@HandleBeforeCreate(MatchResult.class)
	@Transactional
	public void handleMatchResultPreCreate(MatchResult matchResult) {
		logger.info("Before creating: {}", matchResult.toString());
	}

	@HandleBeforeSave(MatchResult.class)
	@Transactional
	public void handleMatchResultPreSave(MatchResult matchResult) {
		logger.info("Before updating: {}", matchResult.toString());
	}

	@HandleBeforeDelete(MatchResult.class)
	@Transactional
	public void handleMatchResultPreDelete(MatchResult matchResult) {
		logger.info("Before deleting: {}", matchResult.toString());
	}

	@HandleBeforeLinkSave(MatchResult.class)
	public void handleMatchResultPreLinkSave(MatchResult matchResult, Object o) {
		logger.info("Before linking: {} to {}", matchResult.toString(), o.toString());
	}

	@HandleAfterCreate(MatchResult.class)
	@Transactional
	public void handleMatchResultPostCreate(MatchResult matchResult) {
		logger.info("After creating: {}", matchResult.toString());
	}

	@HandleAfterSave(MatchResult.class)
	@Transactional
	public void handleMatchResultPostSave(MatchResult matchResult) {
		logger.info("After updating: {}", matchResult.toString());
	}

	@HandleAfterDelete(MatchResult.class)
	@Transactional
	public void handleMatchResultPostDelete(MatchResult matchResult) {
		logger.info("After deleting: {}", matchResult.toString());
	}

	@HandleAfterLinkSave(MatchResult.class)
	public void handleMatchResultPostLinkSave(MatchResult matchResult, Object o) {
		logger.info("After linking: {} to {}", matchResult.toString(), o.toString());
	}

}
