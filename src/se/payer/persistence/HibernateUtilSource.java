package se.payer.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtilSource {

	private static final SessionFactory sessionFactory;

	static {
		try {
			Configuration ac = new Configuration();
							
			ac.addAnnotatedClass(FactoringInvoiceHeader.class);
			ac.addAnnotatedClass(FactoringInvoiceDetail.class);
			ac.addAnnotatedClass(FactoringInvoicePayment.class);
			ac.addAnnotatedClass(Website.class);

//			ac.addResource("hibernate-source.cfg.xml");
			sessionFactory = ac.configure("hibernate-source.cfg.xml").buildSessionFactory();

		} catch (Throwable ex) {
			System.err.println("Error initializing Hibernate." + ex.getMessage());
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static Session currentSession() {
		return sessionFactory.getCurrentSession();
	}

}