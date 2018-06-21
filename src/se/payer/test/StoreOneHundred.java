package se.payer.test;

import java.util.UUID;

import org.apache.log4j.Logger;

import se.payer.persistence.AccountVerification;
import se.payer.persistence.Article;
import se.payer.persistence.ArticleAgreement;
import se.payer.persistence.ArticleWebsitePrice;
import se.payer.persistence.HibernateUtil;
import se.payer.persistence.OrderToken;
import se.payer.persistence.Payment;
import se.payer.persistence.PaymentDetail;
import se.payer.persistence.Website;

public class StoreOneHundred implements Runnable {
	static Logger logger = Logger.getLogger(StoreOneHundred.class);
	int startId;

	public StoreOneHundred(int startId) {
		this.startId = startId;
	}

	@Override
	public void run() {
		long threadId = Thread.currentThread().getId();
		HibernateUtil.currentSession().beginTransaction();
		logger.info("Thread " + threadId + " started. StartId:" + startId);
		createOneHundred(startId);
		HibernateUtil.currentSession().getTransaction().commit();
		logger.info("Thread " + threadId + " ended.");
	}

	public void createOneHundred(int j) {
		for (int i = j * 100; i < (j * 100 + 100); i++) {
			long threadId = Thread.currentThread().getId();
			Article a = new Article();
			a.setWebsiteId("PR_EXAMPLES");
			a.setArticleNo("ArticleNo i:" + i + ", j:" + j);
			a.setArticleDescription("Thread: " + threadId);
			HibernateUtil.currentSession().save(a);

			ArticleAgreement aa = new ArticleAgreement();
			aa.setWebsiteId("PR_EXAMPLES");
			aa.setArticleNo("ArticleNo:" + i);
			HibernateUtil.currentSession().save(aa);

			ArticleWebsitePrice awp = new ArticleWebsitePrice();
			awp.setWebsiteId("PR_EXAMPLES");
			awp.setArticleNo("ArticleNo:" + i);
			awp.setArticleDescription("Test item " + i);
			awp.setArticleTemplate(UUID.randomUUID().toString());
			awp.setDefaultPrice(1.00 * i);
			awp.setDefaultUnit("st");
			awp.setDefaultPeriodicity("once");
			awp.setDefaultVat(0);
			HibernateUtil.currentSession().save(awp);

			Website w = new Website("WS_" + i, "Name_of_website_" + i);
			w.setMerchantInfoId(null);
			w.setWL3Key1("KEY1:" + UUID.randomUUID().toString());
			w.setWL3Key2("KEY2:" + UUID.randomUUID().toString());
			HibernateUtil.currentSession().save(w);
			{
				OrderToken p = new OrderToken("Object " + i, "PAYMENT_LINK");
				p.createMetaData("key-1", "Object value 1:" + i + " A");
				p.createMetaData("key-2", "Object value 2:" + i + " AA");
				p.createMetaData("key-3", "Object value 3:" + i + " AAA");
				p.createMetaData("key-4", "Object value 4:" + i + " AAAA");
				p.createMetaData("key-5", "Object value 5:" + i + " AAAAA");
				HibernateUtil.currentSession().save(p);
			}
			{
				Payment p = new Payment();
				HibernateUtil.currentSession().save(p);

				PaymentDetail pd1 = new PaymentDetail(p, "First one:" + i);
				PaymentDetail pd2 = new PaymentDetail(p, "First second:" + i, 1.0);
				PaymentDetail pd3 = new PaymentDetail(p, "First third:" + i, 2.10);
				PaymentDetail pd4 = new PaymentDetail(p, "First fourth:" + i, 3.50);
				PaymentDetail pd5 = new PaymentDetail(p, "First fifth:" + i, 4.99);

				pd2.fake("2-12345-" + i);
				pd3.fake(null);
				pd4.fake("3-54321-" + i);

				HibernateUtil.currentSession().save(pd1);
				HibernateUtil.currentSession().save(pd2);
				HibernateUtil.currentSession().save(pd3);
				HibernateUtil.currentSession().save(pd4);
				HibernateUtil.currentSession().save(pd5);
			}
			{
				AccountVerification avX = new AccountVerification("Verifikat #" + i);
				avX.addAccount("1910", 100.0 * i / 100L);
				avX.addAccount("3010", -75.0 * i / 100L);
				avX.addAccount("2611", -25.0 * i / 100L);
				HibernateUtil.currentSession().save(avX);
			}
		}

	}

}
