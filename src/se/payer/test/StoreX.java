package se.payer.test;

import java.util.UUID;

import org.apache.log4j.Logger;

import se.payer.persistence.AccountVerification;
import se.payer.persistence.Article;
import se.payer.persistence.ArticleAgreement;
import se.payer.persistence.ArticleWebsitePrice;
import se.payer.persistence.HibernateUtilTarget;
import se.payer.persistence.OrderToken;
import se.payer.persistence.Payment;
import se.payer.persistence.PaymentDetail;
import se.payer.persistence.Website;

public class StoreX implements Runnable {
	static Logger logger = Logger.getLogger(StoreX.class);

	long startID;
	long numberOfUnits;
	long threadID;

	public StoreX(long startId, long numberOfUnits) {
		this.startID = startId;
		this.numberOfUnits = numberOfUnits;
	}

	@Override
	public void run() {
		threadID = Thread.currentThread().getId();

		String start = "Thread " + threadID + " started. StartId:" + startID;
		String end = "Thread " + threadID + " ended. StartId:" + startID;

		HibernateUtilTarget.currentSession().beginTransaction();

		System.out.println(start);
		logger.info(start);
		createX(startID);
		logger.info(end);
		System.out.println(end);

		HibernateUtilTarget.currentSession().getTransaction().commit();
	}

	public void createX(long startId) {
		long startPos = startId * numberOfUnits;
		long endPos = startPos + numberOfUnits;

		for (long pos = startPos; pos < endPos; pos++) {
			logger.debug("#pos:"+pos+", threadID:"+threadID);
			if (true) {
				Article a = new Article();
				a.setWebsiteId("PR_EXAMPLES");
				a.setArticleNo("ArticleNo i:" + pos + ", j:" + startId);
				a.setArticleDescription("ThreadId: " + threadID);
				HibernateUtilTarget.currentSession().save(a);
			}
			if (true) {
				ArticleAgreement aa = new ArticleAgreement();
				aa.setWebsiteId("PR_EXAMPLES");
				aa.setArticleNo("ArticleNo:" + pos);
				HibernateUtilTarget.currentSession().save(aa);
			}
			if (true) {
				ArticleWebsitePrice awp = new ArticleWebsitePrice();
				awp.setWebsiteId("PR_EXAMPLES");
				awp.setArticleNo("ArticleNo:" + pos);
				awp.setArticleDescription("Test item " + pos);
				awp.setArticleTemplate(UUID.randomUUID().toString());
				awp.setDefaultPrice(1.00 * pos);
				awp.setDefaultUnit("st");
				awp.setDefaultPeriodicity("once");
				awp.setDefaultVat(0);
				HibernateUtilTarget.currentSession().save(awp);
			}
			if (true) {
				Website w = new Website("WS_" + pos, "Name_of_website_" + pos);
				w.setMerchantInfoId(null);
				w.setWL3Key1("KEY1:" + UUID.randomUUID().toString());
				w.setWL3Key2("KEY2:" + UUID.randomUUID().toString());
				HibernateUtilTarget.currentSession().save(w);
			}
			if (true) {
				OrderToken p = new OrderToken("Object " + pos, "PAYMENT_LINK");
				p.createMetaData("key-1", "Object value 1:" + pos + " A");
				p.createMetaData("key-2", "Object value 2:" + pos + " AA");
				p.createMetaData("key-3", "Object value 3:" + pos + " AAA");
				p.createMetaData("key-4", "Object value 4:" + pos + " AAAA");
				p.createMetaData("key-5", "Object value 5:" + pos + " AAAAA");
				HibernateUtilTarget.currentSession().save(p);
			}
			if (true) {
				Payment p = new Payment();
				HibernateUtilTarget.currentSession().save(p);

				PaymentDetail pd1 = new PaymentDetail(p, "First one:" + pos);
				PaymentDetail pd2 = new PaymentDetail(p, "First second:" + pos, 1.0);
				PaymentDetail pd3 = new PaymentDetail(p, "First third:" + pos, 2.10);
				PaymentDetail pd4 = new PaymentDetail(p, "First fourth:" + pos, 3.50);
				PaymentDetail pd5 = new PaymentDetail(p, "First fifth:" + pos, 4.99);

				pd2.fake("2-12345-" + pos);
				pd3.fake(null);
				pd4.fake("3-54321-" + pos);

				HibernateUtilTarget.currentSession().save(pd1);
				HibernateUtilTarget.currentSession().save(pd2);
				HibernateUtilTarget.currentSession().save(pd3);
				HibernateUtilTarget.currentSession().save(pd4);
				HibernateUtilTarget.currentSession().save(pd5);
			}
			if (true) {
				try {
					AccountVerification avX = new AccountVerification("Verifikat #A#:" + pos);
					avX.addAccount("1910", 150.0 * pos);
					avX.addAccount("3041", -80.0 * pos);
					avX.addAccount("3042", -20.0 * pos);
					avX.addAccount("3043", -40.0 * pos);
					avX.addAccount("1911", 50.0 * pos);
					avX.addAccount("3044", -30.0 * pos);
					avX.addAccount("2611", -20.0 * pos);
					avX.addAccount("2612", -10.0 * pos);
					avX.commit();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				try {
					AccountVerification avX = new AccountVerification("Verifikat #B#:" + pos);
					avX.addAccount("1910", 100.0 * pos / 100L);
					avX.addAccount("3010", -75.0 * pos / 100L);
					avX.addAccount("2611", -25.0 * pos / 100L);
					avX.commit();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
