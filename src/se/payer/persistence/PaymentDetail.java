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

	@Column(name = "RequestedAmount", length = 128)
	private Double requestedAmount = 0.0;

	@Column(name = "Amount", length = 128)
	private Double amount = 0.0;

	public enum Status {
		UNSET, GENERIC_ERROR, FAILED, OK
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "Status", length = 16)
	Status status = Status.UNSET;

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
		this(payment, referenceId);
		this.referenceId = referenceId;
		this.requestedAmount = requestedAmount;
	}

	@Transient
	public PaymentDetail fake(String data) {
		this.data = data;
		this.runDate = new Date();
		this.type = Type.FAKE;
		this.amount = requestedAmount;
		this.status = Status.OK;
		return this;
	}
}