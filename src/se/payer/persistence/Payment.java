package se.payer.persistence;

import java.util.*;
import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "Payment")
public class Payment extends PersistenceBase {
	@Column(name = "WebsiteId", length = 40)
	private String websiteId;
	
	@Column(name = "OrderId", length = 40)
	private Long orderId;
	
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	PaymentType2 paymentType2;

	public enum PaymentType {
		CARD, INVOICE, INSTALLMENT, BANK, SWISH, UNKNOWN
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name = "PaymentType", length = 16)
	PaymentType paymentType = PaymentType.UNKNOWN;

	@Column(name = "PaymentIdentifier", length = 128, unique=true, nullable=false)
	private String paymentIdentifier = UUID.randomUUID().toString();

	@Column(name = "CreateDate")
	private Date createDate = new Date();
	
	@Column(name = "Currency", length = 3)
	private String currency = "SEK";

	@OneToMany(mappedBy = "payment", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PaymentDetail> payments = new ArrayList<PaymentDetail>();

	public Payment() {
		super();
	}
	public void settle(String referenceId, String data, Double amount) {
		PaymentDetail pd = new PaymentDetail(this, referenceId, amount);
		pd.settle(data);
		payments.add(pd);
	}
	public void refund(String referenceId, String data, Double amount) {
		PaymentDetail pd = new PaymentDetail(this, referenceId, amount);
		pd.refund(data, amount);
		payments.add(pd);
	}

	public Payment(PaymentType paymentType, String referenceId, Double requestedAmount) {
		super();
		this.paymentType = paymentType;
		payments.add(new PaymentDetail(this, referenceId, requestedAmount));
	}

}