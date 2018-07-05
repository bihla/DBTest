package se.payer.persistence;

import java.util.*;
import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "PaymentLog")
public class PaymentLog extends PersistenceBase {
	@Column(name = "OrderId")
	private Long orderId;

	@Column(name = "CreateDate")
	private Date createDate = new Date();

	@Column(name = "Currency", length = 3)
	private String currency = "SEK";

	@Column(name = "Amount")
	private Double amount = 0.0;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "PaymentDetailId", nullable = true)
	PaymentDetail paymentDetail;

	public PaymentLog() {
		super();
	}

	public PaymentLog(Double amount) {
		super();
		this.amount = amount;
	}

	public PaymentLog(Double amount, String currency) {
		this(amount);
		this.currency = currency;
	}

	public PaymentLog(PaymentDetail paymentDetail, Double amount, String currency) {
		this(amount, currency);
		this.paymentDetail = paymentDetail;
	}

	@Transient
	public String toString() {
		return "[PaymentLog]"
	+ ",id:" + id 
	+ ",createDate:" + createDate 
	+ ",currency:" + currency 
	+ ",amount:" + amount
	+ ",paymentDetail:" + paymentDetail.id;
	}
}