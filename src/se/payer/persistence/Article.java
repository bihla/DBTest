package se.payer.persistence;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "Article", indexes = {
		@Index(name = "index_WebsiteId_ArticleNo", columnList = "WebsiteId,ArticleNo", unique = true),
		@Index(name = "index_WebsiteId_ArticleDescription", columnList = "WebsiteId,ArticleDescription", unique = false) })

public class Article extends PersistenceBase {
	@Column(name = "WebsiteId", length = 40)
	private String websiteId;

	@Column(name = "ArticleNo", length = 64)
	private String articleNo;
	
	@Column(name = "ArticleDescription", length = 255)
	private String articleDescription;
	
	@Column(name = "ArticleTemplate", length = 64)
	private String articleTemplate;
	
	@Column(name = "DefaultPeriodicity", length = 16)
	private String defaultPeriodicity;
	
	@Column(name = "DefaultPeriod")
	private int defaultPeriod;
	
	@Column(name = "DefaultPrice")
	private double defaultPrice;
	
	@Column(name = "DefaultUnit", length = 32)
	private String defaultUnit;
	
	@Column(name = "DefaultVat")
	private double defaultVat;
	
	@Column(name = "DefaultCustomerAccountId", length = 16)
	private String defaultCustomerAccountId;

	public Article() {
		super();
	}

	// Getters and Setters

	public Article(String websiteId, String articleNo, String articleDescription, String articleTemplate,
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
		return this.websiteId;
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
		return defaultPeriod;
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

	public static Article getArticle(String articleNo) {

		Article article = (Article) HibernateUtilTarget.currentSession().createQuery("FROM Article WHERE ArticleNo = :p1")
				.setParameter("p1", articleNo).uniqueResult();

		return article;

	}

	public static Article getArticle(String websiteId, String articleNo) {

		Article article = (Article) HibernateUtilTarget.currentSession()
				.createQuery("FROM Article WHERE WebsiteId = :p1 AND ArticleNo = :p2").setParameter("p1", websiteId)
				.setParameter("p2", articleNo).uniqueResult();

		return article;

	}

}
