package cat.udl.eps.softarch.mypadel.handler;

import cat.udl.eps.softarch.mypadel.domain.UriEntity;

import java.io.Serializable;

public interface IEventHandler<T extends UriEntity<? extends Serializable>> {

	void handleBeforeCreate(T beforeCreate);

	void handleBeforeSave(T beforeSave);

	void handleBeforeDelete(T beforeDelete);

	void handleBeforeLinkSave(T beforeLinkSave, Object object);

	void handleAfterCreate(T afterCreate);

	void handleAfterSave(T afterSave);

	void handleAfterDelete(T afterDelete);

	void handleAfterLinkSave(T afterLinkSave, Object object);

}

