package se.payer.persistence;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue("Debit")
public class AccountDebit extends Account {

	@Column(name = "Amount", precision=2)
	Double amount;

	public AccountDebit() {
		super();
	}

	public AccountDebit(AccountVerification verification, String account, Double amount, String description) {
		super(verification);
		this.amount = amount;
		setAmountDebit(amount);
		setAccountNumber(account);
		setDescription(description);
	}
}
