/*
 * $Id: Website.java,v 1.41.8.3.2.2.2.4.2.16.2.7 2017/04/13 10:55:28 bihla Exp $
 * $Header: /usr/local/cvsroot/PayReadJavaEngine/WEB-INF/src/se/payread/persistent/Website.java,v 1.41.8.3.2.2.2.4.2.16.2.7 2017/04/13 10:55:28 bihla Exp $
 * $Log: Website.java,v $
 * Revision 1.41.8.3.2.2.2.4.2.16.2.7  2017/04/13 10:55:28  bihla
 * getInvoiceProvider NEVER returns null or empty - default to "Intrum"
 *
 * Revision 1.41.8.3.2.2.2.4.2.16.2.6  2017/04/10 09:48:42  bihla
 * if invoice provider in null or empty - default to "Intrum"
 *
 * Revision 1.41.8.3.2.2.2.4.2.16.2.5  2017/03/08 14:21:20  bihla
 * det senaste
 *
 * Revision 1.41.8.3.2.2.2.4.2.16.2.4  2017/03/03 16:06:08  bihla
 * User.getUserById => User.get
 *
 * Revision 1.41.8.3.2.2.2.4.2.16.2.3  2017/03/02 16:13:32  bihla
 * det senaste
 *
 * Revision 1.41.8.3.2.2.2.4.2.16.2.2  2017/02/16 10:36:50  bihla
 * default empty instead of NULL
 *
 * Revision 1.41.8.3.2.2.2.4.2.16.2.1  2017/02/13 16:28:57  bihla
 * added classes for Login
 *
 * Revision 1.41.8.3.2.2.2.4.2.16  2016/09/13 10:05:27  bihla
 * added API isPF_swedbank
 *
 * Revision 1.41.8.3.2.2.2.4.2.15  2016/05/25 08:42:56  bihla
 * added InstallmentPayoutDelayDays
 *
 * Revision 1.41.8.3.2.2.2.4.2.14  2016/04/22 11:04:56  bihla
 * invoiceProvider is Intrum if null
 *
 * Revision 1.41.8.3.2.2.2.4.2.13  2016/03/17 12:11:02  bihla
 * innihilateWebsiteById - added DebtDistribution among cleaned tables
 *
 * Revision 1.41.8.3.2.2.2.4.2.12  2015/11/04 20:52:50  bihla
 * IsBlocked is added.
 *
 * Revision 1.41.8.3.2.2.2.4.2.11  2015/10/09 15:24:22  bihla
 * added innihilateWebsiteById
 *
 * Revision 1.41.8.3.2.2.2.4.2.10  2015/10/06 09:00:52  bihla
 * add a constructor
 *
 * Revision 1.41.8.3.2.2.2.4.2.9  2015/08/31 09:08:25  maker
 * Intrum fix
 *
 * Revision 1.41.8.3.2.2.2.4.2.8  2015/04/21 11:56:24  bihla
 * adding clientVersion for Website
 *
 * Revision 1.41.8.3.2.2.2.4.2.7  2015/03/12 18:11:56  bihla
 * added lastPing
 *
 * Revision 1.41.8.3.2.2.2.4.2.6  2014/12/05 11:54:04  bihla
 * added warpspeed
 *
 * Revision 1.41.8.3.2.2.2.4.2.5  2014/11/10 14:56:23  bihla
 * adding
 * 	@Transient
 * 	public int getInvoiceCredibilityCheckConsumerLimit() {
 * 		return getInvoiceCredibilityCheckLimit();
 * 	}
 *
 * 	@Transient
 * 	public void setInvoiceCredibilityCheckConsumerLimit(int invoiceCredibilityCheckLimit) {
 * 		setInvoiceCredibilityCheckLimit(invoiceCredibilityCheckLimit);
 * 	}
 *
 * 	@Transient
 * 	public int getInvoiceAddressCheckConsumerLimit() {
 * 		return getInvoiceAddressCheckLimit();
 * 	}
 *
 * 	@Transient
 * 	public void setInvoiceAddressCheckConsumerLimit(int invoiceAddressCheckLimit) {
 * 		setInvoiceAddressCheckLimit(invoiceAddressCheckLimit);
 * 	}
 *
 * Revision 1.41.8.3.2.2.2.4.2.4  2014/11/10 10:49:16  bihla
 * +adding protected int invoiceCredibilityCheckCompanyLimit;
 * +adding protected int invoiceAddressCheckCompanyLimit;
 *
 * Revision 1.41.8.3.2.2.2.4.2.3  2014/10/27 12:27:03  bihla
 * adding setter and getter for InvoiceDueDays
 *
 * Revision 1.41.8.3.2.2.2.4.2.2  2014/10/10 09:38:08  bihla
 * delete Website added
 *
 * Revision 1.41.8.3.2.2.2.4.2.1  2014/09/05 13:04:37  bihla
 * added
 * +protected int SmsPayoutDelayDays;
 * +protected int FactoringPayoutDelayDays;
 *
 * Revision 1.41.8.3.2.2.2.4  2013/11/28 08:09:01  bihla
 * add "AuthOnly" functionality directly on Website
 *
 * Revision 1.41.8.3.2.2.2.3  2013/04/22 18:40:36  bihla
 * Bug 496 - IBP for clients with their own IBP-Agreements (CustomPayout)
 *
 * Revision 1.41.8.3.2.2.2.2  2012/12/14 15:25:31  bihla
 * All latest Refactoring stuff
 *
 * Revision 1.41.8.3.2.2.2.1  2012/10/19 11:28:26  bihla
 * added EnforceUniqueMerchantReference
 *
 * Revision 1.41.8.3.2.2  2012/05/09 10:13:00  bihla
 * + added getWebsiteById
 *
 * Revision 1.41.8.3.2.1  2011/11/08 10:10:41  bihla
 * Bug 371 - Add age detection before Soliditet template - avoid unnecessary calls (high cost)
 * + added this parameters:
 *     protected int InvoiceAgeLimit;
 *
 * Bug 366 - The possibility to block all cards NOT 3DSecure and ENROLLED.
 *     protected boolean Enforce3DsEnrolledCards;
 *     protected double Enforce3DsEnrolledLimit;
 *
 * Revision 1.41.8.3  2011/08/09 12:15:28  bihla
 * feeAddedVAT added to Website object.
 *
 * Revision 1.41.8.2  2011/05/03 19:41:09  bihla
 * helper functions to handle passwords for sites. dibsPassword (encrypted)
 *
 * Revision 1.41.8.1  2011/05/03 14:26:03  bihla
 * Added CVS tags.
 *
 */
package se.payer.persistence;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
public class Website implements Serializable {

	@Id
	@Column(name = "Id", length = 40)
	private String Id;

	@Column(name = "Name", length = 128)
	private String Name;
	
	@Column(name = "Password", length = 64)
	private String Password;

	@Column(name = "WL3Key1", length = 128)
	private String WL3Key1;

	@Column(name = "WL3Key2", length = 128)
	private String WL3Key2;

	@Column(name = "MainAccountUserId")
	private String MainAccountUserId;

	@Column(name = "MerchantInfoId")
	private Long MerchantInfoId;

	@Transient
	private String sql = "select * from Settings";

	/**
	 * Constructor
	 */

	public Website() {
		super();
	}

	public Website(String websiteId, String name) {
		super();
		setId(websiteId);
		setName(name);
	}

	public String getId() {
		return Id;
	}

	public void setId(String theId) {
		Id = theId;
	}

	public String getName() {
		return Name;
	}

	public void setName(String theName) {
		Name = theName;
	}

	public String getWL3Key1() {
		return WL3Key1;
	}

	public void setWL3Key1(String key1) {
		this.WL3Key1 = key1;
	}

	public String getWL3Key2() {
		return WL3Key2;
	}

	public void setWL3Key2(String key2) {
		this.WL3Key2 = key2;
	}

	public Long getMerchantInfoId() {
		return MerchantInfoId;
	}

	public void setMerchantInfoId(Long merchantInfoId) {
		this.MerchantInfoId = merchantInfoId;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		this.Password = password;
	}

	public String toString() {
		return "[Website]:" + "Id=" + getId() + ",Name=" + getName() + ",MainAccountUserId=" + MainAccountUserId + "";
	}

}