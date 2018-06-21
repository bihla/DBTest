package se.payer.persistence;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.log4j.Logger;

@SuppressWarnings("serial")
@Entity
@Table(name = "FactoringInvoicePayment2")
public class FactoringInvoicePayment extends PersistenceBase {

	private transient Logger logger = Logger.getLogger(FactoringInvoicePayment.class);

	/*
	`Id` int(11) NOT NULL AUTO_INCREMENT,
	  `FactoringInvoicePaymentBatchId` int(11) DEFAULT NULL,
	  `FactoringPaymentProcessingBatchId` int(11) DEFAULT NULL,
	  `CustomerNumber` varchar(40) DEFAULT NULL,
	  `InvoiceNumber` bigint(16) DEFAULT NULL,
	  `OrderNumber` varchar(32) DEFAULT NULL,
	  `ReadDate` datetime DEFAULT NULL,
	  `EventType` varchar(16) DEFAULT NULL,
	  `PaymentDate` date DEFAULT NULL,
	  `PaymentAmount` decimal(12,2) DEFAULT NULL,
	  `PaymentReminderAmount` decimal(12,2) DEFAULT NULL,
	  `AdjustmentDate` date DEFAULT NULL,
	  `AdjustmentAmount` decimal(12,2) DEFAULT NULL,
	  `CreditDate` date DEFAULT NULL,
	  `CreditAmount` decimal(12,2) DEFAULT NULL,
	  `Description` varchar(64) DEFAULT NULL,
	  `OverdueDate` date DEFAULT NULL,
	  `ReminderDate` date DEFAULT NULL,
	  `ReminderAmount` decimal(12,2) DEFAULT NULL,
	  `CollectionDate` date DEFAULT NULL,
	  `InterestInvoiceDate` date DEFAULT NULL,
	  `InterestInvoiceNumber` bigint(16) DEFAULT NULL,
	  `ChargeLogId` int(11) DEFAULT NULL,
*/	  
	private String InvoiceNumber;
	private String OrderNumber;
	private Date ReadDate;
	private String EventType;
	private Date PaymentDate;
	private Double PaymentAmount;
	private Double PaymentReminderAmount;
	private Date AdjustmentDate;
	private Double AdjustmentAmount;
	private Date CreditDate;
	private Double CreditAmount;
	private String Description;
	private Date OverdueDate;
	private Date ReminderDate;
	private Double ReminderAmount;
	private Date CollectionDate;
	private Date InterestInvoiceDate;
	private String InterestInvoiceNumber;
	private Long ChargeLogId;

	public FactoringInvoicePayment() {
		super();
	}

	public String getInvoiceNumber() {
		return InvoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		InvoiceNumber = invoiceNumber;
	}

	public String getOrderNumber() {
		return OrderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		OrderNumber = orderNumber;
	}

	public Date getReadDate() {
		return ReadDate;
	}

	public void setReadDate(Date readDate) {
		ReadDate = readDate;
	}

	public String getEventType() {
		return EventType;
	}

	public void setEventType(String eventType) {
		EventType = eventType;
	}

	public Date getPaymentDate() {
		return PaymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		PaymentDate = paymentDate;
	}

	public Double getPaymentAmount() {
		return PaymentAmount;
	}

	public void setPaymentAmount(Double paymentAmount) {
		PaymentAmount = paymentAmount;
	}

	public Double getPaymentReminderAmount() {
		return PaymentReminderAmount;
	}

	public void setPaymentReminderAmount(Double paymentReminderAmount) {
		PaymentReminderAmount = paymentReminderAmount;
	}

	public Date getAdjustmentDate() {
		return AdjustmentDate;
	}

	public void setAdjustmentDate(Date adjustmentDate) {
		AdjustmentDate = adjustmentDate;
	}

	public Double getAdjustmentAmount() {
		return AdjustmentAmount;
	}

	public void setAdjustmentAmount(Double adjustmentAmount) {
		AdjustmentAmount = adjustmentAmount;
	}

	public Date getCreditDate() {
		return CreditDate;
	}

	public void setCreditDate(Date creditDate) {
		CreditDate = creditDate;
	}

	public Double getCreditAmount() {
		return CreditAmount;
	}

	public void setCreditAmount(Double creditAmount) {
		CreditAmount = creditAmount;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public Date getOverdueDate() {
		return OverdueDate;
	}

	public void setOverdueDate(Date overdueDate) {
		OverdueDate = overdueDate;
	}

	public Date getReminderDate() {
		return ReminderDate;
	}

	public void setReminderDate(Date reminderDate) {
		ReminderDate = reminderDate;
	}

	public Double getReminderAmount() {
		return ReminderAmount;
	}

	public void setReminderAmount(Double reminderAmount) {
		ReminderAmount = reminderAmount;
	}

	public Date getCollectionDate() {
		return CollectionDate;
	}

	public void setCollectionDate(Date collectionDate) {
		CollectionDate = collectionDate;
	}

	public Date getInterestInvoiceDate() {
		return InterestInvoiceDate;
	}

	public void setInterestInvoiceDate(Date interestInvoiceDate) {
		InterestInvoiceDate = interestInvoiceDate;
	}

	public String getInterestInvoiceNumber() {
		return InterestInvoiceNumber;
	}

	public void setInterestInvoiceNumber(String interestInvoiceNumber) {
		InterestInvoiceNumber = interestInvoiceNumber;
	}

	public Long getChargeLogId() {
		return ChargeLogId;
	}

	public void setChargeLogId(Long chargeLogId) {
		ChargeLogId = chargeLogId;
	}

	@Transient
	@SuppressWarnings("unchecked")
	public static List<FactoringInvoicePayment> getByInvoiceNumber(String invoiceNumber) {
		List<FactoringInvoicePayment> fip = (List<FactoringInvoicePayment>) HibernateUtil.currentSession()
				.createQuery("FROM FactoringInvoicePayment WHERE InvoiceNumber=:ino")
				.setParameter("ino", invoiceNumber)
				.list();
		return fip;
	}
}
