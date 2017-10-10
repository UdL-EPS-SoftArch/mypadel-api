package cat.udl.eps.softarch.mypadel.handler;

import cat.udl.eps.softarch.mypadel.domain.Admin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.annotation.*;

import javax.transaction.Transactional;

@RepositoryEventHandler(Admin.class)
public class AdminEventHandler implements IEventHandler<Admin> {

	private final Logger logger = LoggerFactory.getLogger(Admin.class);

	@HandleBeforeCreate
	@Transactional
	public void handleBeforeCreate(Admin beforeCreate) {
		logger.info("Before creating: {}", beforeCreate.toString());
	}

	@HandleBeforeSave
	@Transactional
	public void handleBeforeSave(Admin beforeSave) {
		logger.info("Before updating: {}", beforeSave.toString());
	}

	@HandleBeforeDelete
	@Transactional
	public void handleBeforeDelete(Admin beforeDelete) {
		logger.info("Before deleting: {}", beforeDelete.toString());
	}

	@HandleBeforeLinkSave
	public void handleBeforeLinkSave(Admin beforeLinkSave, Object object) {
		logger.info("Before linking: {} to {}", beforeLinkSave.toString(), object.toString());
	}

	@HandleAfterCreate
	@Transactional
	public void handleAfterCreate(Admin afterCreate) {
		logger.info("After creating: {}", afterCreate.toString());
	}

	@HandleAfterSave
	@Transactional
	public void handleAfterSave(Admin afterSave) {
		logger.info("After updating: {}", afterSave.toString());
	}

	@HandleAfterDelete
	@Transactional
	public void handleAfterDelete(Admin afterDelete) {
		logger.info("After deleting: {}", afterDelete.toString());
	}

	@HandleAfterLinkSave
	public void handleAfterLinkSave(Admin afterLinkSave, Object object) {
		logger.info("After linking: {} to {}", afterLinkSave.toString(), object.toString());
	}

}
