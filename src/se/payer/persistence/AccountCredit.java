package se.payer.persistence;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue("Credit")
public class AccountCredit extends Account {

	@Column(name = "Amount", precision=2)
	Double amount;

	public AccountCredit() {
		super();
	}

	public AccountCredit(AccountVerification verification, String account, Double amount, String description) {
		super(verification);
		this.amount = amount;
		setAmountCredit(amount);
		setAccountNumber(account);
		setDescription(description);
	}
}
