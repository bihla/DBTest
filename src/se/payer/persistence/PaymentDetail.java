package se.payer.persistence;

import java.util.*;
import javax.persistence.*;

import se.payer.persistence.Payment.PaymentType;

@Entity
@Table(name = "PaymentDetail")
public class PaymentDetail extends PersistenceBase {
	public enum Type {
		INIT, AUTH, AUTH_REVERSE, SETTLE, REFUND, FAKE;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "Type", length = 16)
	Type type = Type.INIT;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "PaymentId", nullable = false)
	private Payment payment;

	@Column(name = "CreateDate")
	private Date createDate = new Date();

	@Column(name = "RunDate")
	private Date runDate;

	@Column(name = "Detail", length = 128)
	private String detail;

	@Column(name = "ReferenceId", length = 128)
	private String referenceId;

	@Column(name = "Data", length = 128)
	private String data;

	@Column(name = "RequestedAmount")
	private Double requestedAmount = 0.0;

	@Column(name = "Amount")
	private Double amount = 0.0;

	public enum Status {
		UNSET, PENDING, GENERIC_ERROR, FAILED, OK
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "Status", length = 16)
	Status status = Status.UNSET;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "PaymentLogId", nullable = true)
	PaymentLog paymentLog;

	public PaymentDetail() {
		super();
	}

	public PaymentDetail(Payment payment) {
		super();
		this.payment = payment;
	}

	public PaymentDetail(Payment payment, String referenceId) {
		this(payment);
		this.referenceId = referenceId;
	}

	public PaymentDetail(Payment payment, String referenceId, Double requestedAmount) {
		this.payment = payment;
		this.referenceId = referenceId;
		this.requestedAmount = requestedAmount;
	}
	
	@Transient
	public PaymentDetail ok(String data) {
		this.runDate = new Date();
		this.status = Status.OK;
		this.amount = requestedAmount;
		this.data = data;
		return this;
	}
	
	@Transient
	public PaymentDetail fake(String data) {
		this.runDate = new Date();
		this.type = Type.FAKE;
		this.status = Status.OK;
		this.data = data;
		this.amount = requestedAmount;
		return this;
	}
	
	@Transient
	public PaymentDetail settle(String data) {
		this.runDate = new Date();
		this.type = Type.SETTLE;
		this.status = Status.OK;
		this.data = data;
		this.amount = requestedAmount;
		this.paymentLog = new PaymentLog(this, requestedAmount, "SEK");
		HibernateUtilTarget.currentSession().save(this.paymentLog);
		return this;
	}
	
	@Transient
	public PaymentDetail refund(String data, Double refundAmount) {
		this.data = data;
		this.runDate = new Date();
		this.type = Type.REFUND;
		this.amount = -refundAmount;
		this.status = Status.OK;
		this.paymentLog = new PaymentLog(this, -refundAmount, "SEK");
		HibernateUtilTarget.currentSession().save(this.paymentLog);
		return this;
	}
	@Transient
	public String toString() {
		return "[PaymentDetail]"
	+ ",id:" + id 
	+ ",detail:" + detail 
	+ ",status:" + status 
	+ ",runDate:" + runDate 
	+ ",type:" + type
	+ ",amount:" + amount;
	}
}