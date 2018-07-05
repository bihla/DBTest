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
	private String websiteId;

	@Column(name = "ArticleNo", length = 64)
	private String articleNo;

	@Column(name = "ArticleDescription", length = 255)
	private String articleDescription;

	@Column(name = "ArticleTemplate", length = 255)
	private String articleTemplate;

	@Column(name = "DefaultPeriodicity", length = 16)
	private String defaultPeriodicity;

	@Column(name = "DefaultPeriod")
	private int defaultPeriod;

	@Column(name = "DefaultPrice")
	private double defaultPrice;

	@Column(name = "DefaultUnit", length = 16)
	private String defaultUnit;

	@Column(name = "DefaultVat")
	private double defaultVat;

	@Column(name = "DefaultCustomerAccountId", length = 16)
	private String defaultCustomerAccountId;

	public ArticleWebsitePrice() {
		super();
	}

	public ArticleWebsitePrice(String websiteId, String articleNo, String articleDescription, String articleTemplate,
			String defaultPeriodicity, int defaultPeriod, double defaultPrice, String defaultUnit, double defaultVat,
			String defaultCustomerAccountId) {

		super();

		this.websiteId = websiteId;
		this.articleNo = articleNo;
		this.articleDescription = articleDescription;
		this.articleTemplate = articleTemplate;
		this.defaultPeriodicity = defaultPeriodicity;
		this.defaultPeriod = defaultPeriod;
		this.defaultPrice = defaultPrice;
		this.defaultUnit = defaultUnit;
		this.defaultVat = defaultVat;
		this.defaultCustomerAccountId = defaultCustomerAccountId;
	}

	public String getWebsiteId() {
		return websiteId;
	}

	public void setWebsiteId(String websiteId) {
		this.websiteId = websiteId;
	}

	public String getArticleNo() {
		return this.articleNo;
	}

	public void setArticleNo(String articleNo) {
		this.articleNo = articleNo;
	}

	public String getArticleDescription() {
		return this.articleDescription;
	}

	public void setArticleDescription(String articleDescription) {
		this.articleDescription = articleDescription;
	}

	public String getArticleTemplate() {
		return this.articleTemplate;
	}

	public void setArticleTemplate(String articleTemplate) {
		this.articleTemplate = articleTemplate;
	}

	public String getDefaultPeriodicity() {
		return this.defaultPeriodicity;
	}

	public void setDefaultPeriodicity(String defaultPeriodicity) {
		this.defaultPeriodicity = defaultPeriodicity;
	}

	public int getDefaultPeriod() {
		return this.defaultPeriod;
	}

	public void setDefaultPeriod(int defaultPeriod) {
		this.defaultPeriod = defaultPeriod;
	}

	public double getDefaultPrice() {
		return this.defaultPrice;
	}

	public void setDefaultPrice(double defaultPrice) {
		this.defaultPrice = defaultPrice;
	}

	public String getDefaultUnit() {
		return this.defaultUnit;
	}

	public void setDefaultUnit(String defaultUnit) {
		this.defaultUnit = defaultUnit;
	}

	public double getDefaultVat() {
		return this.defaultVat;
	}

	public void setDefaultVat(double defaultVat) {
		this.defaultVat = defaultVat;
	}

	public String getDefaultCustomerAccountId() {
		return this.defaultCustomerAccountId;
	}

	public void setDefaultCustomerAccountId(String defaultCustomerAccountId) {
		this.defaultCustomerAccountId = defaultCustomerAccountId;
	}

	public static ArticleWebsitePrice getArticle(String websiteId, String articleNo) {

		ArticleWebsitePrice article = (ArticleWebsitePrice) HibernateUtilTarget.currentSession()
				.createQuery("FROM Article WHERE WebsiteId = :wid and ArticleNo = :anumber")
				.setParameter("wid", websiteId).setParameter("anumber", articleNo).uniqueResult();

		return article;

	}

}
