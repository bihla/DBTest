package se.payer.test;

import org.apache.log4j.Logger;

import se.payer.persistence.*;

public class Testing {

	private HibernateUtilTarget hibernateUtilTarget = new HibernateUtilTarget();
	private HibernateUtilSource hibernateUtilSource = new HibernateUtilSource();

	public static void main(String[] args) throws Exception {
		Logger logger = Logger.getLogger(Testing.class);

		// TODO Auto-generated method stub
		if (true) {
			HibernateUtilTarget.currentSession().beginTransaction();

			AccountPlan ap = new AccountPlan();
			ap.importPlan();
			HibernateUtilTarget.currentSession().flush();
			AccountPlan ap2 = AccountPlan.getByAccountNumber("1010");
			System.out.println("AccountPlan:n:" + ap2.getAccountNumber());
			System.out.println("AccountPlan:d:" + ap2.getDescription());

			HibernateUtilTarget.currentSession().getTransaction().commit();
		}
		
		if (true) {
			logger.debug("Starting threads...");
			for (int startId = 1; startId <= 10; startId++) {
				new Thread(new StoreX(startId, 500)).start();
			}
			logger.debug("...threads started.");
		}
		if (true) {
			HibernateUtilTarget.currentSession().beginTransaction();

			AccountVerification av1 = new AccountVerification("Verifikat #1");
			av1.addAccount("1010", 100.0);
			av1.addAccount("2010", -80.0);
			av1.addAccount("3011", -20.0);
			av1.commit();

			AccountVerification av2 = new AccountVerification("Verifikat #2");
			av2.addAccount("1010", 200.0);
			av2.addAccount("2010", -160.0);
			av2.addAccount("3011", -40.0);
			av2.commit();

			try {

				AccountVerification av3 = new AccountVerification("Verifikat #3 (exception)");
				av3.addAccount("1010", 300.0);
				av3.addAccount("2010", -160.0);
				av3.addAccount("3011", -40.0);
				av3.commit();

			} catch (Exception e) {
				logger.debug("Exception caught - OK (expected)", e);
			}

			if (true) {
				PaymentLog pl1 = new PaymentLog(100.0, "SEK");
				PaymentLog pl2 = new PaymentLog(200.0, "SEK");
				PaymentLog pl3 = new PaymentLog(300.0, "SEK");
				HibernateUtilTarget.currentSession().save(pl1);
				HibernateUtilTarget.currentSession().save(pl2);
				HibernateUtilTarget.currentSession().save(pl3);

				HibernateUtilTarget.currentSession().flush();
			}

			if (true) {
				for (int j = 1000; j < 1050; j++) {

					AccountVerification avX = new AccountVerification("Verifikat # - #" + j);
					avX.addAccount("1910", 150.0 * j);
					avX.addAccount("3041", -80.0 * j);
					avX.addAccount("3042", -20.0 * j);
					avX.addAccount("3043", -40.0 * j);
					avX.addAccount("1911", 50.0 * j);
					avX.addAccount("3044", -30.0 * j);
					avX.addAccount("2611", -20.0 * j);
					avX.addAccount("2612", -10.0 * j);
					avX.commit();

					if (j % 10 == 0) {
						System.out.println("AccountVerification:" + j);
						HibernateUtilTarget.currentSession().flush();
					}
				}
			}
			HibernateUtilTarget.currentSession().getTransaction().commit();
		}

		if (true) {
			HibernateUtilTarget.currentSession().beginTransaction();
			int i = 99999;
			Article a = new Article();
			a.setWebsiteId("PR_EXAMPLES");
			a.setArticleNo(":" + i);
			a.setArticleDescription("Test item " + i);
			HibernateUtilTarget.currentSession().save(a);

			Payment pp1 = new Payment();
			pp1.settle("referenceId", "data", 10.0);
			HibernateUtilTarget.currentSession().save(pp1);

			Payment pp2 = new Payment();
			pp2.refund("referenceId", "data", 10.0);
			HibernateUtilTarget.currentSession().save(pp2);

			Payment p1 = new Payment(Payment.PaymentType.BANK, "1234567", 120.0);
			HibernateUtilTarget.currentSession().save(p1);

			HibernateUtilTarget.currentSession().getTransaction().commit();
		}

		if (false) {
			HibernateUtilTarget.currentSession().beginTransaction();
			OrderToken p1 = (OrderToken) HibernateUtilTarget.currentSession().get(OrderToken.class, 5L);
			logger.debug("# Description:" + p1.getDescription());
			logger.debug("# +:" + p1.getMetaData("key-1"));
			logger.debug("# +:" + p1.getMetaData("key-2"));
			logger.debug("# +:" + p1.getMetaData("key-3"));

			p1.updateMetaData("key-3", "XXX");
			p1.updateMetaData("key-2", "XX");
			p1.updateMetaData("key-1", "X");

			logger.debug("# UPDATE:" + p1.updateMetaData("key-1", "Z"));
			logger.debug("# UPDATE:" + p1.updateMetaData("key-1", "ZZ"));
			logger.debug("# UPDATE:" + p1.updateMetaData("key-1", "ZZZ"));
			logger.debug("# UPDATE:" + p1.updateMetaData("key-1", "X (almost back to original)"));

			logger.debug("# +:" + p1.getMetaData("key-1"));
			logger.debug("# +:" + p1.getMetaData("key-2"));
			logger.debug("# +:" + p1.getMetaData("key-3"));

			// p1.deleteMetaData("key-1"); // p1.deleteMetaData("key-2"); //
			p1.deleteMetaData("key-3");

			logger.debug("# AFTER UPDATE +:" + p1.getMetaData("key-1"));
			logger.debug("# AFTER UPDATE +:" + p1.getMetaData("key-2"));
			logger.debug("# AFTER UPDATE +:" + p1.getMetaData("key-3"));

			HibernateUtilTarget.currentSession().getTransaction().commit();
		}

		if (false) {
			HibernateUtilTarget.currentSession().beginTransaction();
			HibernateUtilTarget.currentSession()
					.delete((OrderToken) HibernateUtilTarget.currentSession().get(OrderToken.class, 1L));
			HibernateUtilTarget.currentSession()
					.delete((OrderToken) HibernateUtilTarget.currentSession().get(OrderToken.class, 2L));
			HibernateUtilTarget.currentSession()
					.delete((OrderToken) HibernateUtilTarget.currentSession().get(OrderToken.class, 3L));
			HibernateUtilTarget.currentSession()
					.delete((OrderToken) HibernateUtilTarget.currentSession().get(OrderToken.class, 4L));

			HibernateUtilTarget.currentSession()
					.delete((OrderToken) HibernateUtilTarget.currentSession().get(OrderToken.class, 6L));
			HibernateUtilTarget.currentSession()
					.delete((OrderToken) HibernateUtilTarget.currentSession().get(OrderToken.class, 7L));
			HibernateUtilTarget.currentSession()
					.delete((OrderToken) HibernateUtilTarget.currentSession().get(OrderToken.class, 8L));
			HibernateUtilTarget.currentSession()
					.delete((OrderToken) HibernateUtilTarget.currentSession().get(OrderToken.class, 9L));
			HibernateUtilTarget.currentSession()
					.delete((OrderToken) HibernateUtilTarget.currentSession().get(OrderToken.class, 10L));
			HibernateUtilTarget.currentSession().getTransaction().commit();
		}

		if (false) {
			HibernateUtilTarget.currentSession().beginTransaction();
			OrderToken p2 = (OrderToken) HibernateUtilTarget.currentSession().get(OrderToken.class, 6L);
			p2.deleteMetaData("key-1");
			p2.deleteMetaData("key-2");
			p2.deleteMetaData("key-3");

			OrderToken p3 = (OrderToken) HibernateUtilTarget.currentSession().get(OrderToken.class, 5L);
			p3.deleteMetaData("key-2");

			HibernateUtilTarget.currentSession().delete(HibernateUtilTarget.currentSession().get(OrderToken.class, 5L));
			HibernateUtilTarget.currentSession().delete(HibernateUtilTarget.currentSession().get(OrderToken.class, 6L));
			HibernateUtilTarget.currentSession().getTransaction().commit();

		}
		if (false) {
			HibernateUtilTarget.currentSession().beginTransaction();
			OrderToken p1 = (OrderToken) HibernateUtilTarget.currentSession().get(OrderToken.class, 5L);
			p1.updateMetaData("key-3", "MMM (key-3)");
			p1.updateMetaData("key-2", "MM (key-2)");
			p1.updateMetaData("key-1", "M (key-1)");
			HibernateUtilTarget.currentSession().update(p1);
			HibernateUtilTarget.currentSession().getTransaction().commit();
		}
		
		logger.debug("#Testing ended");
		System.out.println("#Testing ended");
	}

}
