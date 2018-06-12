package se.payer.test;

import org.apache.log4j.Logger;

import se.payer.persistence.AccountPlan;
import se.payer.persistence.AccountVerification;
import se.payer.persistence.Article;
import se.payer.persistence.HibernateUtil;
import se.payer.persistence.OrderToken;
import se.payer.persistence.Payment;
import se.payer.persistence.PaymentDetail;

public class Testing {

	private HibernateUtil hibernateUtil = new HibernateUtil();

	public static void main(String[] args) {
		Logger logger = Logger.getLogger(Testing.class);

		// TODO Auto-generated method stub
		HibernateUtil.currentSession().beginTransaction();
		AccountPlan ap = new AccountPlan();
		ap.importPlan();
		HibernateUtil.currentSession().flush();
		AccountPlan ap2 = AccountPlan.getByAccountNumber("1010");
		System.out.println("AccountPlan:n:"+ap2.getAccountNumber());
		System.out.println("AccountPlan:d:"+ap2.getDescription());
		
		HibernateUtil.currentSession().getTransaction().commit();
		
		HibernateUtil.currentSession().beginTransaction();
		
		if (true) {
			AccountVerification av1 = new AccountVerification("Verifikat #1");
			av1.addAccount("1010", 100L);
			av1.addAccount("2010", -80L);
			av1.addAccount("3011", -20L);
			HibernateUtil.currentSession().save(av1);

			AccountVerification av2 = new AccountVerification("Verifikat #2");
			av2.addAccount("1010", 200L);
			av2.addAccount("2010", -160L);
			av2.addAccount("3015", -40L);
			HibernateUtil.currentSession().save(av2);

			for (int j = 100; j < 1000; j++) {
				AccountVerification avX = new AccountVerification("Verifikat #" + j);
				avX.addAccount("1910", 100L * j);
				avX.addAccount("3010", -75L * j);
				avX.addAccount("2611", -25L * j);
				HibernateUtil.currentSession().save(avX);
				HibernateUtil.currentSession().flush();
			}
		}

		if (true) {
			int i = 99999;
			Article a = new Article();
			a.setWebsiteId("PR_EXAMPLES");
			a.setArticleNo(":" + i);
			a.setArticleDescription("Test item " + i);
			HibernateUtil.currentSession().save(a);

			Payment p = new Payment();
			HibernateUtil.currentSession().save(p);

			Payment p1 = new Payment(Payment.PaymentType.BANK, "1234567", 120.0);

			HibernateUtil.currentSession().save(p1);

			PaymentDetail pd1 = new PaymentDetail(p, "First one");
			PaymentDetail pd2 = new PaymentDetail(p, "First second", 1.0);
			PaymentDetail pd3 = new PaymentDetail(p, "First third", 2.10);
			PaymentDetail pd4 = new PaymentDetail(p, "First fourth", 3.50);
			PaymentDetail pd5 = new PaymentDetail(p, "First fifth", 4.99);

			pd2.fake("2-12345");
			pd3.fake(null);
			pd4.fake("3-54321");

			HibernateUtil.currentSession().save(pd1);
			HibernateUtil.currentSession().save(pd2);
			HibernateUtil.currentSession().save(pd3);
			HibernateUtil.currentSession().save(pd4);
			HibernateUtil.currentSession().save(pd5);

		}
		HibernateUtil.currentSession().getTransaction().commit();

		for (int i = 1; i <= 5; i++) {
			new Thread(new StoreOneHundred(i)).start();
		}

		HibernateUtil.currentSession().beginTransaction();
		if (false) {
			HibernateUtil.currentSession().beginTransaction();
			OrderToken p1 = (OrderToken) HibernateUtil.currentSession().get(OrderToken.class, 5L);
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
		}

		if (false) {
			HibernateUtil.currentSession()
					.delete((OrderToken) HibernateUtil.currentSession().get(OrderToken.class, 1L));
			HibernateUtil.currentSession()
					.delete((OrderToken) HibernateUtil.currentSession().get(OrderToken.class, 2L));
			HibernateUtil.currentSession()
					.delete((OrderToken) HibernateUtil.currentSession().get(OrderToken.class, 3L));
			HibernateUtil.currentSession()
					.delete((OrderToken) HibernateUtil.currentSession().get(OrderToken.class, 4L));

			HibernateUtil.currentSession()
					.delete((OrderToken) HibernateUtil.currentSession().get(OrderToken.class, 6L));
			HibernateUtil.currentSession()
					.delete((OrderToken) HibernateUtil.currentSession().get(OrderToken.class, 7L));
			HibernateUtil.currentSession()
					.delete((OrderToken) HibernateUtil.currentSession().get(OrderToken.class, 8L));
			HibernateUtil.currentSession()
					.delete((OrderToken) HibernateUtil.currentSession().get(OrderToken.class, 9L));
			HibernateUtil.currentSession()
					.delete((OrderToken) HibernateUtil.currentSession().get(OrderToken.class, 10L));
		}

		if (false) {
			OrderToken p2 = (OrderToken) HibernateUtil.currentSession().get(OrderToken.class, 6L);
			p2.deleteMetaData("key-1");
			p2.deleteMetaData("key-2");
			p2.deleteMetaData("key-3");

			OrderToken p3 = (OrderToken) HibernateUtil.currentSession().get(OrderToken.class, 5L);
			p3.deleteMetaData("key-2");

			HibernateUtil.currentSession().delete(HibernateUtil.currentSession().get(OrderToken.class, 5L));
			HibernateUtil.currentSession().delete(HibernateUtil.currentSession().get(OrderToken.class, 6L));

		}
		if (false) {
			OrderToken p1 = (OrderToken) HibernateUtil.currentSession().get(OrderToken.class, 5L);
			p1.updateMetaData("key-3", "MMM (key-3)");
			p1.updateMetaData("key-2", "MM (key-2)");
			p1.updateMetaData("key-1", "M (key-1)");
			HibernateUtil.currentSession().update(p1);
		}
		HibernateUtil.currentSession().getTransaction().commit();

	}

}
