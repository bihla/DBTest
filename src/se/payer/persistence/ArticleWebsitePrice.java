/*
$Id: ArticleWebsitePrice.java,v 1.1.14.1 2012/01/04 11:02:48 bihla Exp $
$Header: /usr/local/cvsroot/PayReadJavaEngine/WEB-INF/src/se/payread/persistent/ArticleWebsitePrice.java,v 1.1.14.1 2012/01/04 11:02:48 bihla Exp $
$Log: ArticleWebsitePrice.java,v $
Revision 1.1.14.1  2012/01/04 11:02:48  bihla
+ added CVS header
= changed the formatting

*/

package se.payer.persistence;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "ArticleWebsitePrice", indexes = {
		@Index(name = "index_WebsiteId_ArticleNo", columnList = "WebsiteId,ArticleNo", unique = true) })
public class ArticleWebsitePrice extends PersistenceBase {

	@Column(name = "WebsiteId", length = 40)
	private String WebsiteId;

	@Column(name = "ArticleNo", length = 64)
	private String ArticleNo;

	@Column(name = "ArticleDescription", length = 255)
	private String ArticleDescription;

	@Column(name = "ArticleTemplate", length = 255)
	private String ArticleTemplate;

	@Column(name = "DefaultPeriodicity", length = 16)
	private String DefaultPeriodicity;

	@Column(name = "DefaultPeriod")
	private int DefaultPeriod;

	@Column(name = "DefaultPrice")
	private double DefaultPrice;

	@Column(name = "DefaultUnit", length = 16)
	private String DefaultUnit;

	@Column(name = "DefaultVat")
	private double DefaultVat;

	@Column(name = "DefaultCustomerAccountId", length = 16)
	private String DefaultCustomerAccountId;

	public ArticleWebsitePrice() {
		super();
	}

	public ArticleWebsitePrice(String websiteId, String articleNo, String articleDescription, String articleTemplate,
			String defaultPeriodicity, int defaultPeriod, double defaultPrice, String defaultUnit, double defaultVat,
			String defaultCustomerAccountId) {

		super();

		this.WebsiteId = websiteId;
		this.ArticleNo = articleNo;
		this.ArticleDescription = articleDescription;
		this.ArticleTemplate = articleTemplate;
		this.DefaultPeriodicity = defaultPeriodicity;
		this.DefaultPeriod = defaultPeriod;
		this.DefaultPrice = defaultPrice;
		this.DefaultUnit = defaultUnit;
		this.DefaultVat = defaultVat;
		this.DefaultCustomerAccountId = defaultCustomerAccountId;
	}

	public String getWebsiteId() {
		return WebsiteId;
	}

	public void setWebsiteId(String websiteId) {
		WebsiteId = websiteId;
	}

	public String getArticleNo() {
		return ArticleNo;
	}

	public void setArticleNo(String articleNo) {
		ArticleNo = articleNo;
	}

	public String getArticleDescription() {
		return ArticleDescription;
	}

	public void setArticleDescription(String articleDescription) {
		ArticleDescription = articleDescription;
	}

	public String getArticleTemplate() {
		return ArticleTemplate;
	}

	public void setArticleTemplate(String articleTemplate) {
		ArticleTemplate = articleTemplate;
	}

	public String getDefaultPeriodicity() {
		return DefaultPeriodicity;
	}

	public void setDefaultPeriodicity(String defaultPeriodicity) {
		DefaultPeriodicity = defaultPeriodicity;
	}

	public int getDefaultPeriod() {
		return DefaultPeriod;
	}

	public void setDefaultPeriod(int defaultPeriod) {
		DefaultPeriod = defaultPeriod;
	}

	public double getDefaultPrice() {
		return DefaultPrice;
	}

	public void setDefaultPrice(double defaultPrice) {
		DefaultPrice = defaultPrice;
	}

	public String getDefaultUnit() {
		return DefaultUnit;
	}

	public void setDefaultUnit(String defaultUnit) {
		DefaultUnit = defaultUnit;
	}

	public double getDefaultVat() {
		return DefaultVat;
	}

	public void setDefaultVat(double defaultVat) {
		DefaultVat = defaultVat;
	}

	public String getDefaultCustomerAccountId() {
		return DefaultCustomerAccountId;
	}

	public void setDefaultCustomerAccountId(String defaultCustomerAccountId) {
		DefaultCustomerAccountId = defaultCustomerAccountId;
	}

	public static ArticleWebsitePrice getArticle(String websiteId, String articleNo) {

		ArticleWebsitePrice article = (ArticleWebsitePrice) HibernateUtilTarget.currentSession()
				.createQuery("FROM Article WHERE WebsiteId = :wid and ArticleNo = :anumber")
				.setParameter("wid", websiteId).setParameter("anumber", articleNo).uniqueResult();

		return article;

	}

}
