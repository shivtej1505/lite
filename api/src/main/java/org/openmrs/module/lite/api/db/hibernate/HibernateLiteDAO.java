package org.openmrs.module.lite.api.db.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.openmrs.module.lite.api.db.LiteDao;

public class HibernateLiteDAO implements LiteDao {
	
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
}
