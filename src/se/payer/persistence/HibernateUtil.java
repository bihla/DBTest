package se.payer.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static final SessionFactory sessionFactory;

	static {
		try {
			Configuration ac = new Configuration();
			
			ac.addAnnotatedClass(AccountPlan.class);
			ac.addAnnotatedClass(AccountVerification.class);
			ac.addAnnotatedClass(Account.class);
			
			ac.addAnnotatedClass(Article.class);
			ac.addAnnotatedClass(ArticleAgreement.class);
			ac.addAnnotatedClass(ArticleWebsitePrice.class);
			
			ac.addAnnotatedClass(PaymentType2.class);
			ac.addAnnotatedClass(Payment.class);
			ac.addAnnotatedClass(PaymentDetail.class);
			
			ac.addAnnotatedClass(OrderToken.class);
			ac.addAnnotatedClass(OrderTokenKeyValue.class);
			
			ac.addAnnotatedClass(Website.class);

			ac.addResource("hibernate.cfg.xml");
			sessionFactory = ac.configure().buildSessionFactory();

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