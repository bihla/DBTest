/**
$Id: FactoringInvoiceHeader.java,v 1.1.2.1.2.62.2.8.2.6 2017/09/08 09:47:01 bihla Exp $
$Header: /usr/local/cvsroot/PayReadJavaEngine/WEB-INF/src/se/payread/persistent/Attic/FactoringInvoiceHeader.java,v 1.1.2.1.2.62.2.8.2.6 2017/09/08 09:47:01 bihla Exp $
$Log: FactoringInvoiceHeader.java,v $
Revision 1.1.2.1.2.62.2.8.2.6  2017/09/08 09:47:01  bihla
all collector stuff

Revision 1.1.2.1.2.62.2.8.2.5  2017/08/10 08:49:38  maker
Added the getPaidDateStr function

Revision 1.1.2.1.2.62.2.8.2.4  2017/06/07 15:31:54  bihla
hibernate 3.6x

Revision 1.1.2.1.2.62.2.8.2.3  2017/06/02 14:40:26  maker
Core V2

Revision 1.1.2.1.2.62.2.8.2.2  2017/06/01 15:46:56  bihla
JoinColumn remarked

Revision 1.1.2.1.2.62.2.8.2.1  2017/05/11 13:20:33  maker
Added Collector FlowType FACTORING

Revision 1.1.2.1.2.62.2.8  2017/04/13 10:55:28  bihla
getInvoiceProvider NEVER returns null or empty - default to "Intrum"

Revision 1.1.2.1.2.62.2.7  2017/04/11 07:44:09  bihla
setDueDays
effectiveInvoiceProvider

effective null handling

Revision 1.1.2.1.2.62.2.6  2017/04/10 09:48:42  bihla
if invoice provider in null or empty - default to "Intrum"

Revision 1.1.2.1.2.62.2.5  2017/03/31 13:27:16  bihla
Default null and EMPTY to Intrum

Revision 1.1.2.1.2.62.2.4  2017/03/02 16:13:21  bihla
det senaste

Revision 1.1.2.1.2.62.2.3  2016/12/15 16:01:39  bihla
"halv" still there

Revision 1.1.2.1.2.62.2.2  2016/11/22 16:26:03  bihla
changed invoice rounding a little

Revision 1.1.2.1.2.62.2.1  2016/11/21 14:11:10  bihla
ToPayAmount is isRounding aware

Revision 1.1.2.1.2.62  2016/09/13 10:05:38  bihla
getWebsiteSystemConfigValueAsBoolByKeyAndWebsiteId => getValueAsBool

renamed the API function

Revision 1.1.2.1.2.61  2016/08/11 07:29:26  maker
Changed billing charge article idn for Collector Direct and Installment

Revision 1.1.2.1.2.60  2016/07/01 12:14:21  bihla
faster - brought WebsiteSystemConfig out of the loop

Revision 1.1.2.1.2.59  2016/06/28 13:11:14  bihla
making use of PayerConstants

Revision 1.1.2.1.2.58  2016/06/10 13:57:17  bihla
add handling of insured  transactions

Revision 1.1.2.1.2.57  2016/06/08 09:32:27  bihla
fixed constructor

Revision 1.1.2.1.2.56  2016/06/01 08:15:12  bihla
do not reset dueDate if set (Collector)

Revision 1.1.2.1.2.55  2016/05/31 13:59:28  bihla
invoiceActivation now handles "buggY statE"

Revision 1.1.2.1.2.54  2016/05/30 09:02:54  bihla
add Account for "collectorUser" user and Collector

Revision 1.1.2.1.2.53  2016/05/25 14:36:50  bihla
merged some small stuff with maker

Revision 1.1.2.1.2.51  2016/05/25 08:42:04  bihla
added type INSTALLMET (76, 78 and 77)

Revision 1.1.2.1.2.50  2016/05/24 14:01:31  bihla
Billing handling factoring + installment

Revision 1.1.2.1.2.49  2016/05/23 09:47:22  maker
Fraud Management for Collector Installment

Revision 1.1.2.1.2.48  2016/05/16 15:26:19  bihla
never set IsBankId to "null"

Revision 1.1.2.1.2.47  2016/05/13 14:10:42  bihla
bankId from int to Long

Revision 1.1.2.1.2.46  2016/04/27 14:04:46  bihla
merged with maker and bihla

Revision 1.1.2.1.2.42  2016/04/07 12:33:43  maker
Added DeliveryType Override

Revision 1.1.2.1.2.41  2016/03/30 14:58:19  bihla
facilitated the names (SystemConfig & WebsiteSystemConfig)

Revision 1.1.2.1.2.40  2016/03/23 13:01:36  bihla
changed old stuff to BillingHelper

Revision 1.1.2.1.2.39  2016/03/08 09:05:41  bihla
Sell the collector invoces too

Revision 1.1.2.1.2.38  2016/02/26 09:16:43  atibb
 + InvoiceCustomization BIG CHECK IN

Revision 1.1.2.1.2.37  2016/02/10 14:06:56  bihla
if directOrderHeader dueDays is null - use Website dueDays - otherwise use directOrderHeader dueDays

Revision 1.1.2.1.2.36  2016/01/19 10:21:42  bihla
setOptionFlagsFromOrder function added.

Revision 1.1.2.1.2.35  2016/01/18 13:02:35  bihla
Det senaste!

Revision 1.1.2.1.2.34  2016/01/18 09:11:49  bihla
force isPrintAsService when DeliveryType is 'print'

chanegd logging just a minor...

logging when Hibernating...

Revision 1.1.2.1.2.33  2016/01/14 14:55:07  maker
Liberalerna fix

Revision 1.1.2.1.2.32  2016/01/12 11:04:03  maker
Fixed nulls in keyValueMapper

Revision 1.1.2.1.2.31  2016/01/05 14:59:00  maker
DeliveryType fix

Revision 1.1.2.1.2.30  2015/12/17 15:14:00  bihla
added activationOptions to invoiceActivation

Revision 1.1.2.1.2.29  2015/12/10 09:13:05  bihla
small update of speling in log

Revision 1.1.2.1.2.28  2015/12/04 10:29:28  bihla
Set all three fields when invoice is created...
		setWebsiteId(directOrder.getWebsiteId());
		setOwnerId(directOrder.getWebsiteId());
		setOriginalWebsiteId(directOrder.getWebsiteId());

Revision 1.1.2.1.2.27  2015/12/01 12:45:27  bihla
isPrintService is set according to flag.

Account is created for PAYERKOP too.

Revision 1.1.2.1.2.26  2015/12/01 09:46:42  bihla
Only set WebsiteId to PAYERKOP when sold. Dont touch Original or Ownerid - they are already correct.

Revision 1.1.2.1.2.25  2015/11/30 15:40:57  bihla
use "synchronized" to avoid doublets when creating Invoices

Revision 1.1.2.1.2.24  2015/11/26 12:56:33  bihla
use getStatusStr instead of flicky getStatus

Revision 1.1.2.1.2.23  2015/11/26 11:19:52  bihla
added getMerchantCustomerId in DirectOrderHeader and FactoringInvoiceHeader

Revision 1.1.2.1.2.22  2015/11/20 18:30:40  bihla
configurable:
add zero amount order lines to invoice
add infoline lines to invoice

Revision 1.1.2.1.2.21  2015/11/19 14:46:42  bihla
add printed date and log dates in Swedish format.

Revision 1.1.2.1.2.20  2015/11/19 11:03:32  bihla
factoring date is set by invoiceActivation

Revision 1.1.2.1.2.19  2015/11/17 13:44:51  bihla
added "Options" string

Revision 1.1.2.1.2.18  2015/11/16 11:06:03  bihla
at invoiceActivation "isPrinted" date is set

Revision 1.1.2.1.2.17  2015/11/12 13:31:32  bihla
det �r den senaste

Revision 1.1.2.1.2.16  2015/11/11 16:56:30  bihla
better handling of RemoteException

Revision 1.1.2.1.2.15  2015/11/09 20:35:17  bihla
try/cacth added around TemplateType

Revision 1.1.2.1.2.13  2015/11/03 14:32:36  bihla
det senaste

Revision 1.1.2.1.2.12  2015/10/30 16:11:17  bihla
better and more concise handling of order to invoice

Revision 1.1.2.1.2.11  2015/10/30 10:21:50  bihla
adding 	private Integer TemplateType;

Revision 1.1.2.1.2.10  2015/10/29 14:22:04  bihla
+ added getVatPrc

Revision 1.1.2.1.2.9  2015/10/26 13:04:40  bihla
removed logging

Revision 1.1.2.1.2.8  2015/10/16 08:47:55  bihla
added currency handling

Revision 1.1.2.1.2.7  2015/10/09 15:22:43  bihla
better logging

Revision 1.1.2.1.2.6  2015/10/06 11:28:18  bihla
Lindorff => Intrum

Revision 1.1.2.1.2.5  2015/10/06 09:22:04  bihla
added InvoiceURL
set createDate in constructor
use lindorffMapping for sequence number

Revision 1.1.2.1.2.4  2015/09/22 08:11:52  bihla
+ added InvoiceProvider

Revision 1.1.2.1.2.3  2015/07/16 15:47:06  bihla
Improved Invoice handling

Revision 1.1.2.1.2.2  2015/04/10 16:06:21  bihla
changed the logger to be transient (and private/protected static and final)

Revision 1.1.2.1.2.1  2014/11/05 16:13:44  bihla
adding the latest

Revision 1.1.2.1  2013/11/01 08:23:06  bihla
Adding stuff for refund Factoring/Invoice/RefundLog

 */
package se.payer.persistence;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.log4j.Logger;

import se.payread.common.CurrencyUtil;

@SuppressWarnings("serial")
@Entity
@Table(name = "FactoringInvoiceHeader")
public class FactoringInvoiceHeader implements Serializable {

	private static final transient Logger logger = Logger.getLogger(FactoringInvoiceHeader.class);

	private String InvoiceNumber;
	private boolean Test;
	private boolean Factoring;
	private boolean Stopped;
	private String WebsiteId;
	private String OriginalWebsiteId;
	private String OwnerId;
	private Long WebsiteSeqNo;
	private String UserId;
	private Integer FactoringBatchId;
	private Integer AckBatchId;
	private Long DirectOrderHeaderId;
	private Long ChargeLogId;
	private Date Printed;
	private String OcrNumber;
	private String Description;
	private Date CreateDate;
	private Date InvoiceDate;
	private Integer DueDays;
	private Date DueDate;
	private Double InvoiceAmount = 0.0;
	private Double VatAmount = 0.0;
	private Double RoundingAmount = 0.0;
	private Double ToPayAmount = 0.0;
	private Double PaidAmount;
	private Double PaidReminderAmount;
	private Date PaidDate;
	private Double CreditAmount;
	private Double AdjustmentAmount;
	private Double FeeAmount;
	private Double ReminderAmount;
	private String CurrencyCode = "SEK";
	private Double OriginalAmount;
	private Double OutstandingAmount;
	private Double PayedOutAmount;
	private boolean PrintService;
	private Long BankId = 0L;
	// private Date BankIdProvided;
	private String Status = "NEW";
	private String ClientAddress;
	private String SessionId;
	private String InvoiceURL;
	private String InvoiceProvider;
	private String DeliveryType;
	private String DeliveryProvider;
	private Integer TemplateType = 1;
	private String Options;
	// private boolean isRounding = false;

	private List<FactoringInvoiceDetail> factoringInvoiceDetail = new LinkedList<FactoringInvoiceDetail>();

	/**
	 * Constructor
	 */
	public FactoringInvoiceHeader() {
		super();
		setCreateDate(new Date());
	}

	public FactoringInvoiceHeader(String websiteId, String userId, String description, String sessionId) {
		logger.debug("constructor::FactoringInvoiceHeader(websiteId:" + websiteId + ", userId:" + userId
				+ ", description:" + description + ", sessionId:" + sessionId + ")");
		WebsiteId = websiteId;
		OriginalWebsiteId = websiteId;
		OwnerId = websiteId;
		UserId = userId;
		Description = description;
		SessionId = sessionId;
		setCreateDate(new Date());
	}

	public FactoringInvoiceHeader(String websiteId, String userId, String description, String sessionId,
			String clientIp) {
		this(websiteId, userId, description, sessionId);
		logger.debug("constructor::FactoringInvoiceHeader(websiteId:" + websiteId + ", userId:" + userId
				+ ", description:" + description + ", sessionId:" + sessionId + ", clientIp:" + clientIp + ")");
		ClientAddress = clientIp;
	}

	@Id
	@Column(name = "InvoiceNumber")
	public String getInvoiceNumber() {
		return InvoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		InvoiceNumber = invoiceNumber;
	}

	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "factoringInvoiceHeader")
	public List<FactoringInvoiceDetail> getFactoringInvoiceDetail() {
		return factoringInvoiceDetail;
	}

	public void setFactoringInvoiceDetail(List<FactoringInvoiceDetail> factoringInvoiceDetail) {
		this.factoringInvoiceDetail = factoringInvoiceDetail;
	}

	public String getWebsiteId() {
		return WebsiteId;
	}

	public void setWebsiteId(String websiteId) {
		WebsiteId = websiteId;
	}

	public String getOriginalWebsiteId() {
		return OriginalWebsiteId;
	}

	public void setOriginalWebsiteId(String originalWebsiteId) {
		OriginalWebsiteId = originalWebsiteId;
	}

	public String getOwnerId() {
		return OwnerId;
	}

	public void setOwnerId(String ownerId) {
		OwnerId = ownerId;
	}

	public Long getWebsiteSeqNo() {
		return WebsiteSeqNo;
	}

	public void setWebsiteSeqNo(Long websiteSeqNo) {
		WebsiteSeqNo = websiteSeqNo;
	}

	public String getUserId() {
		return UserId;
	}

	public void setUserId(String userId) {
		UserId = userId;
	}

	public Integer getFactoringBatchId() {
		return FactoringBatchId;
	}

	public void setFactoringBatchId(Integer factoringBatchId) {
		FactoringBatchId = factoringBatchId;
	}

	public Long getDirectOrderHeaderId() {
		return DirectOrderHeaderId;
	}

	public void setDirectOrderHeaderId(Long directOrderHeaderId) {
		DirectOrderHeaderId = directOrderHeaderId;
	}

	public Long getChargeLogId() {
		return ChargeLogId;
	}

	public void setChargeLogId(Long chargeLogId) {
		ChargeLogId = chargeLogId;
	}

	public Integer getAckBatchId() {
		return AckBatchId;
	}

	public void setAckBatchId(Integer ackBatchId) {
		AckBatchId = ackBatchId;
	}

	public Date getPrinted() {
		return Printed;
	}

	public void setPrinted(Date printed) {
		Printed = printed;
	}

	public String getOcrNumber() {
		return OcrNumber;
	}

	public void setOcrNumber(String ocrNumber) {
		OcrNumber = ocrNumber;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public Date getCreateDate() {
		return CreateDate;
	}

	public void setCreateDate(Date createDate) {
		CreateDate = createDate;
	}

	public Date getInvoiceDate() {
		return InvoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		InvoiceDate = invoiceDate;
	}

	public Integer getDueDays() {
		return DueDays;
	}

	public void setDueDays(Integer dueDays) {
		DueDays = dueDays;
	}

	public Date getDueDate() {
		return DueDate;
	}

	public void setDueDate(Date dueDate) {
		DueDate = dueDate;
	}

	public Double getInvoiceAmount() {
		return InvoiceAmount;
	}

	public void setInvoiceAmount(Double invoiceAmount) {
		InvoiceAmount = CurrencyUtil.round(invoiceAmount, 2);
	}

	public Double getVatAmount() {
		return VatAmount;
	}

	public void setVatAmount(Double vatAmount) {
		VatAmount = vatAmount;
	}

	@Column(name = "RoundingAmount")
	public Double getRoundingAmount() {
		return RoundingAmount;
	}

	protected void setRoundingAmount(Double roundingAmount) {
		this.RoundingAmount = roundingAmount;
	}

	public Double getToPayAmount() {
		return ToPayAmount;
	}

	public void setToPayAmount(Double toPayAmount) {
		ToPayAmount = toPayAmount;
	}

	public Double getPaidAmount() {
		return PaidAmount;
	}

	public void setPaidAmount(Double paidAmount) {
		PaidAmount = paidAmount;
	}

	public Double getPaidReminderAmount() {
		return PaidReminderAmount;
	}

	public void setPaidReminderAmount(Double paidReminderAmount) {
		PaidReminderAmount = paidReminderAmount;
	}

	public Date getPaidDate() {
		return PaidDate;
	}

	public void setPaidDate(Date paidDate) {
		PaidDate = paidDate;
	}

	public Double getCreditAmount() {
		return CreditAmount;
	}

	public void setCreditAmount(Double creditAmount) {
		CreditAmount = creditAmount;
	}

	public Double getAdjustmentAmount() {
		return AdjustmentAmount;
	}

	public void setAdjustmentAmount(Double adjustmentAmount) {
		AdjustmentAmount = adjustmentAmount;
	}

	public Double getFeeAmount() {
		return FeeAmount;
	}

	public void setFeeAmount(Double feeAmount) {
		FeeAmount = feeAmount;
	}

	public Double getReminderAmount() {
		return ReminderAmount;
	}

	public void setReminderAmount(Double reminderAmount) {
		ReminderAmount = reminderAmount;
	}

	public String getCurrencyCode() {
		return CurrencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		CurrencyCode = currencyCode;
	}

	public Double getOriginalAmount() {
		return OriginalAmount;
	}

	public void setOriginalAmount(Double originalAmount) {
		OriginalAmount = originalAmount;
	}

	public Double getOutstandingAmount() {
		return OutstandingAmount;
	}

	public void setOutstandingAmount(Double outstandingAmount) {
		OutstandingAmount = outstandingAmount;
	}

	public Double getPayedOutAmount() {
		return PayedOutAmount;
	}

	public void setPayedOutAmount(Double payedOutAmount) {
		PayedOutAmount = payedOutAmount;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getClientAddress() {
		return ClientAddress;
	}

	public void setClientAddress(String clientAddress) {
		ClientAddress = clientAddress;
	}

	public String getSessionId() {
		return SessionId;
	}

	public void setSessionId(String sessionId) {
		SessionId = sessionId;
	}

	public Integer getTemplateType() {
		return TemplateType;
	}

	public void setTemplateType(Integer templateType) {
		TemplateType = templateType;
	}

	@Transient
	public Double getVatPrc() {
		if (Math.round(getInvoiceAmount()) == 0 || Math.round(getVatAmount()) == 0)
			return 0.0;
		return 100.0 * (getVatAmount() / (getInvoiceAmount() - getVatAmount()));
	}

	@Column(name = "IsTest")
	public boolean isTest() {
		return Test;
	}

	public void setTest(boolean test) {
		Test = test;
	}

	@Column(name = "IsFactoring")
	public boolean isFactoring() {
		return Factoring;
	}

	public void setFactoring(boolean factoring) {
		Factoring = factoring;
	}

	@Column(name = "IsStopped")
	public boolean isStopped() {
		return Stopped;
	}

	public void setStopped(boolean stopped) {
		Stopped = stopped;
	}

	@Column(name = "IsPrintService")
	public boolean isPrintService() {
		return PrintService;
	}

	public void setPrintService(boolean printService) {
		PrintService = printService;
	}

	@Column(name = "IsBankId")
	public Long getBankId() {
		return BankId;
	}

	public void setBankId(Long bankId) {
		BankId = bankId;
	}

	public String getInvoiceProvider() {
		if (InvoiceProvider == null)
			return "Intrum";
		if (InvoiceProvider.trim().isEmpty())
			return "Intrum";
		return InvoiceProvider;
	}

	public void setInvoiceProvider(String invoiceProvider) {
		InvoiceProvider = invoiceProvider;
	}

	public String getDeliveryType() {
		return DeliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		DeliveryType = deliveryType;
	}

	public String getDeliveryProvider() {
		return DeliveryProvider;
	}

	public void setDeliveryProvider(String deliveryProvider) {
		DeliveryProvider = deliveryProvider;
	}

	public String getInvoiceURL() {
		return InvoiceURL;
	}

	public void setInvoiceURL(String invoiceURL) {
		this.InvoiceURL = invoiceURL;
	}

	public String getOptions() {
		return Options;
	}

	public void setOptions(String options) {
		Options = options;
	}

	/***************************************************************/

	@Transient
	public Long addFactoringInvoiceDetail(FactoringInvoiceDetail fid) {
		if (factoringInvoiceDetail != null && fid != null) {
			fid.setFactoringInvoiceHeader(this);
			factoringInvoiceDetail.add(fid);
			return (Long) HibernateUtilTarget.currentSession().save(fid);
		}
		return null;
	}

	@Transient
	public void updateInvoiceTotSumStatus(String status) {
		updateInvoiceTotSum();
		logger.debug("updateInvoiceTotSumStatus(" + status + ")...");
		setStatus(status);
	}

	@Transient
	public synchronized void updateInvoiceTotSum() {
		logger.debug("updateInvoiceTotSum()...");

		double subTotalSummary = 0.0;
		double vatTotalSummary = 0.0;
		for (FactoringInvoiceDetail fid : getFactoringInvoiceDetail()) {
			subTotalSummary += fid.getSubtotal();
			vatTotalSummary += fid.getSubtotalVat();
		}

		setInvoiceAmount(CurrencyUtil.round(subTotalSummary, 2));
		setRoundingAmount(0.0);
		setToPayAmount(CurrencyUtil.round(subTotalSummary, 2));
		setVatAmount(CurrencyUtil.round(vatTotalSummary, 2));

		logger.debug("   InvoiceAmount:" + getInvoiceAmount());
		logger.debug("  RoundingAmount:" + getRoundingAmount());
		logger.debug("     ToPayAmount:" + getToPayAmount());
		logger.debug("       VatAmount:" + getVatAmount());
	}

	@Transient
	public int daysBetween(Date d1, Date d2) {
		return (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
	}

	@Transient
	public Integer getFactoringInvoiceHeaderId() {
		if (InvoiceNumber == null)
			return null;
		return new Integer(InvoiceNumber);
	}

	@Transient
	public static FactoringInvoiceHeader getFactoringInvoiceHeaderByInvoiceNumber(String invoiceNumber) {

		FactoringInvoiceHeader invoiceHeader = (FactoringInvoiceHeader) HibernateUtilTarget.currentSession()
				.createQuery("FROM FactoringInvoiceHeader WHERE InvoiceNumber=:ino").setParameter("ino", invoiceNumber)
				.uniqueResult();
		return invoiceHeader;
	}

	@Transient
	public static FactoringInvoiceHeader getFactoringInvoiceHeaderByInvoiceNumberAndWebsite(String invoiceNumber,
			String websiteId) {

		FactoringInvoiceHeader invoiceHeader = (FactoringInvoiceHeader) HibernateUtilTarget.currentSession()
				.createQuery("FROM FactoringInvoiceHeader WHERE InvoiceNumber=:ino AND WebsiteId=:wid")
				.setParameter("ino", invoiceNumber).setParameter("wid", websiteId).uniqueResult();
		return invoiceHeader;
	}

	@Transient
	public static FactoringInvoiceHeader getFactoringInvoiceHeaderByChargeLogId(long chargeLogId) {

		FactoringInvoiceHeader invoiceHeader = (FactoringInvoiceHeader) HibernateUtilTarget.currentSession()
				.createQuery("FROM FactoringInvoiceHeader WHERE ChargeLogId=:clog").setParameter("clog", chargeLogId)
				.uniqueResult();
		return invoiceHeader;
	}

	@Transient
	public static FactoringInvoiceHeader getFactoringInvoiceHeaderByOrderId(long orderId) {

		FactoringInvoiceHeader invoiceHeader = (FactoringInvoiceHeader) HibernateUtilTarget.currentSession()
				.createQuery("FROM FactoringInvoiceHeader WHERE DirectOrderHeaderId=:iod").setParameter("iod", orderId)
				.uniqueResult();
		return invoiceHeader;
	}

	@Transient
	public void fixInvoiceDates(Date referenceDate) {
		///////////////////////////////////////////////////////////////////
		// Set the invoice date and mark as printed
		///////////////////////////////////////////////////////////////////
		Date invoiceDate = referenceDate == null ? new Date() : referenceDate;

		setInvoiceDate(invoiceDate);
		setPrinted(invoiceDate);

		String createDateStr = getCreateDateStr();
		String invoiceDateStr = getInvoiceDateStr();
		String printDateStr = getPrintedDateStr();

		logger.debug(" CreateDateStr:" + createDateStr);
		logger.debug("   InvoiceDate:" + invoiceDateStr);
		logger.debug("     PrintDate:" + printDateStr);

		if (getDueDays() != null) {
			logger.debug("dueDays != null (set it)");
			if (getDueDate() == null) {
				logger.debug("dueDate == null (set it)");
				// never reset already set date
				Calendar dueDate = Calendar.getInstance();
				dueDate.setTime(referenceDate);
				dueDate.add(Calendar.DATE, getDueDays());
				setDueDate(dueDate.getTime());
			} else {
				logger.debug(
						"dueDate != null (already set):" + new SimpleDateFormat("yyyy-MM-dd").format(getDueDate()));
			}

			String dueDateStr = new SimpleDateFormat("yyyy-MM-dd").format(getDueDate());
			logger.debug("       DueDays:" + getDueDays());
			logger.debug("       DueDate:" + dueDateStr);
		} else {
			logger.error("dueDays == null (strange!)");
		}
	}

	@Transient
	public String getStatusStr() {
		Status = (Status == null ? "" : Status.toUpperCase());
		switch (Status) {

		case "ACK":
		case "ACK-FAILED":
		case "ACK-SUCCESS":
		case "NEW":
		case "PENDING":
		case "STOPPED":
			return Status;
		}

		return "NEW";
	}

	@Transient
	public String toString() {
		return "[FactoringInvoiceHeader]:" + "InvoiceNumber=" + getInvoiceNumber() + ", WebsiteId=" + getWebsiteId()
				+ ", UserId=" + getUserId() + ", InvoiceAmount=" + getInvoiceAmount() + ", ToPayAmount="
				+ getToPayAmount() + ", RoundingAmount=" + getRoundingAmount() + ", Status=" + getStatus()
				+ ", CreateDate=" + getCreateDateStr() + ", InvoiceDate=" + getInvoiceDateStr() + ", DueDate="
				+ getDueDateStr() + ", Printed=" + getPrintedDateStr() + "";
	}

	@Transient
	public String getCreateDateStr() {
		return getCreateDate() == null ? "" : new SimpleDateFormat("yyyy-MM-dd").format(getCreateDate());
	}

	@Transient
	public String getInvoiceDateStr() {
		return getInvoiceDate() == null ? "" : new SimpleDateFormat("yyyy-MM-dd").format(getInvoiceDate());
	}

	@Transient
	public String getPaidDateStr() {
		return getPaidDate() == null ? "" : new SimpleDateFormat("yyyy-MM-dd").format(getPaidDate());
	}

	@Transient
	public String getDueDateStr() {
		return getDueDate() == null ? "" : new SimpleDateFormat("yyyy-MM-dd").format(getDueDate());
	}

	@Transient
	public String getPrintedDateStr() {
		return getPrinted() == null ? "" : new SimpleDateFormat("yyyy-MM-dd").format(getPrinted());
	}

	@Transient
	public static void delete(String invoiceNumber) {
		HibernateUtilTarget.currentSession()
				.createQuery("DELETE FROM FactoringInvoiceDetail WHERE FactoringInvoiceHeaderId=:id")
				.setParameter("id", invoiceNumber).executeUpdate();
		HibernateUtilTarget.currentSession().createQuery("DELETE FROM FactoringInvoiceHeader WHERE InvoiceNumber=:id")
				.setParameter("id", invoiceNumber).executeUpdate();
	}

	@Transient
	@SuppressWarnings("unchecked")
	public static List<FactoringInvoiceHeader> getByWebsiteId(String websiteId) {

		List<FactoringInvoiceHeader> fihs = (List<FactoringInvoiceHeader>) HibernateUtilTarget.currentSession()
				.createQuery("FROM FactoringInvoiceHeader WHERE WebsiteId=:wid "/* "# AND Status <> 'FAKTURERAD'" */)
				.setParameter("wid", websiteId).list();
		return fihs;
	}

	@Transient
	public static void purgeByWebsiteId(String websiteId) {
		logger.debug("purgeByWebsiteId(String websiteId=" + websiteId + ")...");
		for (FactoringInvoiceHeader fih : FactoringInvoiceHeader.getByWebsiteId(websiteId)) {
			FactoringInvoiceHeader.delete(fih.getInvoiceNumber());
		}
	}

	@Transient
	public void doUpdateInvoiceTotalAmountsAndRounding(boolean isRounding) {
		logger.debug("doRounding(" + isRounding + ")...");

		updateInvoiceTotSum();
		InvoiceAmount = CurrencyUtil.round(getInvoiceAmount(), 2);

		if (isRounding) {
			logger.debug("isRounding ? true (perform rounding)");
			setToPayAmount(CurrencyUtil.round(InvoiceAmount, 0));
			setRoundingAmount(CurrencyUtil.round(getToPayAmount() - InvoiceAmount, 2));

			logger.debug(" == after doRounding() ===");
			logger.debug("      isRounding:" + (isRounding ? "true (rounding)" : "false (no rounding)"));
			logger.debug("   InvoiceAmount:" + getInvoiceAmount());
			logger.debug("  RoundingAmount:" + getRoundingAmount());
			logger.debug("     ToPayAmount:" + getToPayAmount());
			logger.debug("       VatAmount:" + getVatAmount());
		}

	}

}
