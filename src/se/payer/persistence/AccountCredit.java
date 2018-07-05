package se.payer.persistence;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue("Credit")
public class AccountCredit extends Account {
	public AccountCredit() {
		super();
	}

	public AccountCredit(AccountVerification verification, String account, Double amount, String description) {
		super(verification);
		setAmount(amount);
		setAmountCredit(-amount);
		setAccountNumber(account);
		setDescription(description);
	}
}
