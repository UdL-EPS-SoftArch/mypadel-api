package cat.udl.eps.softarch.mypadel.eventlistener;

import cat.udl.eps.softarch.mypadel.domain.Admin;
import org.springframework.data.rest.core.event.AbstractRepositoryEventListener;

public class AdminEventListener extends AbstractRepositoryEventListener<Admin> {

	@Override
	protected void onBeforeCreate(Admin entity) {
		super.onBeforeCreate(entity);
	}

}
