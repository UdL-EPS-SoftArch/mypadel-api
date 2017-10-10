package cat.udl.eps.softarch.mypadel.handler;

import cat.udl.eps.softarch.mypadel.domain.MatchResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.annotation.*;

import javax.transaction.Transactional;

@RepositoryEventHandler(MatchResult.class)
public class MatchResultEventHandler implements IEventHandler<MatchResult> {

	private final Logger logger = LoggerFactory.getLogger(MatchResult.class);

	@HandleBeforeCreate
	@Transactional
	public void handleBeforeCreate(MatchResult beforeCreate) {
		logger.info("Before creating: {}", beforeCreate.toString());
	}

	@HandleBeforeSave
	@Transactional
	public void handleBeforeSave(MatchResult beforeSave) {
		logger.info("Before updating: {}", beforeSave.toString());
	}

	@HandleBeforeDelete
	@Transactional
	public void handleBeforeDelete(MatchResult beforeDelete) {
		logger.info("Before deleting: {}", beforeDelete.toString());
	}

	@HandleBeforeLinkSave
	public void handleBeforeLinkSave(MatchResult beforeLinkSave, Object object) {
		logger.info("Before linking: {} to {}", beforeLinkSave.toString(), object.toString());
	}

	@HandleAfterCreate
	@Transactional
	public void handleAfterCreate(MatchResult afterCreate) {
		logger.info("After creating: {}", afterCreate.toString());
	}

	@HandleAfterSave
	@Transactional
	public void handleAfterSave(MatchResult afterSave) {
		logger.info("After updating: {}", afterSave.toString());
	}

	@HandleAfterDelete
	@Transactional
	public void handleAfterDelete(MatchResult afterDelete) {
		logger.info("After deleting: {}", afterDelete.toString());
	}

	@HandleAfterLinkSave
	public void handleAfterLinkSave(MatchResult afterLinkSave, Object object) {
		logger.info("After linking: {} to {}", afterLinkSave.toString(), object.toString());
	}

}
