package se.payer.persistence;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue("Debit")
public class AccountDebit extends Account {
	public AccountDebit() {
		super();
	}

	public AccountDebit(AccountVerification verification, String account, Double amount, String description) {
		super(verification);
		setAmount(amount);
		setAmountDebit(amount);
		setAccountNumber(account);
		setDescription(description);
	}
}
