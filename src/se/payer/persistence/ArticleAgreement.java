package se.payer.persistence;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "ArticleAgreement", indexes = {
		@Index(name = "index_WebsiteId_ArticleNo", columnList = "WebsiteId,ArticleNo", unique = true) })
public class ArticleAgreement extends PersistenceBase {

	@Column(name = "WebsiteId", length = 40)
	private String WebsiteId;

	@Column(name = "ArticleNo", length = 64)
	private String ArticleNo;

	@Column(name = "AgreementStoreId")
	private Integer AgreementStoreId;

	public ArticleAgreement() {
		super();
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

	public Integer getAgreementStoreId() {
		return AgreementStoreId;
	}

	public void setAgreementStoreId(Integer agreementStoreId) {
		AgreementStoreId = agreementStoreId;
	}

}
