package se.payer.persistence;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "ArticleAgreement", indexes = {
		@Index(name = "index_WebsiteId_ArticleNo", columnList = "WebsiteId,ArticleNo", unique = true) })
public class ArticleAgreement extends PersistenceBase {

	@Column(name = "WebsiteId", length = 40)
	private String websiteId;

	@Column(name = "ArticleNo", length = 64)
	private String articleNo;

	@Column(name = "AgreementStoreId")
	private Integer agreementStoreId;

	public ArticleAgreement() {
		super();
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

	public Integer getAgreementStoreId() {
		return this.agreementStoreId;
	}

	public void setAgreementStoreId(Integer agreementStoreId) {
		this.agreementStoreId = agreementStoreId;
	}

}
