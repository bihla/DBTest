package se.payer.test;

import java.util.UUID;

import org.junit.*;

import se.payer.persistence.Article;
import se.payer.persistence.ArticleAgreement;
import se.payer.persistence.ArticleWebsitePrice;
import se.payer.persistence.HibernateUtil;
import se.payer.persistence.OrderToken;
import se.payer.persistence.Website;

public class JUnitTest {
	int i = 1;

	// Run once, e.g. Database connection, connection pool
	@BeforeClass
	public static void runOnceBeforeClass() {
		System.out.println("@BeforeClass - runOnceBeforeClass");
	}

	// Run once, e.g close connection, cleanup
	@AfterClass
	public static void runOnceAfterClass() {
		System.out.println("@AfterClass - runOnceAfterClass");
	}

	// Should rename to @BeforeTestMethod
	// e.g. Creating an similar object and share for all @Test
	@Before
	public void runBeforeTestMethod() {
		System.out.println("@Before - runBeforeTestMethod");
		HibernateUtil.currentSession().beginTransaction();
	}

	// Should rename to @AfterTestMethod
	@After
	public void runAfterTestMethod() {
		System.out.println("@After - runAfterTestMethod");
		HibernateUtil.currentSession().getTransaction().commit();
	}

	@Test
	public void test_method_1() {

		System.out.println("@Test - test_method_1");
		Article a = new Article();
		a.setWebsiteId("PR_EXAMPLES");
		a.setArticleNo(":" + i);
		a.setArticleDescription("Test item " + i);
		HibernateUtil.currentSession().save(a);
	}

	@Test
	public void test_method_2() {
		System.out.println("@Test - test_method_2");
		ArticleAgreement aa = new ArticleAgreement();
		aa.setWebsiteId("PR_EXAMPLES");
		aa.setArticleNo(":" + i);
		HibernateUtil.currentSession().save(aa);
	}

	@Test
	public void test_method_3() {
		System.out.println("@Test - test_method_3");
		ArticleWebsitePrice awp = new ArticleWebsitePrice();
		awp.setWebsiteId("PR_EXAMPLES");
		awp.setArticleNo(":" + i);
		awp.setArticleDescription("Test item " + i);
		awp.setArticleTemplate(UUID.randomUUID().toString());
		awp.setDefaultPrice(1.00 * i);
		awp.setDefaultUnit("st");
		awp.setDefaultPeriodicity("once");
		awp.setDefaultVat(0);
		HibernateUtil.currentSession().save(awp);
	}

	@Test
	public void test_method_4() {
		System.out.println("@Test - test_method_4");
		Website w = new Website("WS_" + i, "Name_of_website_" + i);
		w.setMerchantInfoId(null);
		w.setWL3Key1("KEY1:" + UUID.randomUUID().toString());
		w.setWL3Key2("KEY2:" + UUID.randomUUID().toString());
		HibernateUtil.currentSession().save(w);
	}

	@Test
	public void test_method_5() {
		System.out.println("@Test - test_method_5");
		OrderToken p = new OrderToken("Object " + i, "PAYMENT_LINK");
		p.createMetaData("key-1", "Object value 1:" + i + " A");
		p.createMetaData("key-2", "Object value 2:" + i + " AA");
		p.createMetaData("key-3", "Object value 3:" + i + " AAA");
		HibernateUtil.currentSession().save(p);
	}

}
