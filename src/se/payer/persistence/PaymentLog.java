package se.payer.persistence;

import java.util.*;
import javax.persistence.*;

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

	public PaymentLog() {
		super();
	}
}