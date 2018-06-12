package se.payer.persistence;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "Account", indexes = {
		@Index(name = "index_AccountNumber_uniq", columnList = "Id,AccountNumber", unique = true),
		@Index(name = "index_AccountNumber_fast", columnList = "AccountNumber", unique = false)})

public class Account extends PersistenceBase {

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "AccountVerificationId", nullable = false)
	private AccountVerification accountVerification;

	@Column(name = "AccountNumber", length = 16)
	private String accountNumber;

	@Column(name = "Description", length = 255)
	private String description;

	@Column(name = "Debit")
	private Long debitAmount;

	@Column(name = "Credit")
	private Long creditAmount;

	public Account() {
		super();
	}

	public Account(AccountVerification accountVerification) {
		super();
		this.accountVerification = accountVerification;
	}

	public Account(AccountVerification verification, String account, Long amount, String description) {
		this(verification);
		if (amount > 0)
			this.debitAmount = amount;
		if (amount < 0)
			this.creditAmount = amount;
		this.accountNumber = account;
		this.description = description;
	}

	public AccountVerification getAccountVerification() {
		return accountVerification;
	}

	public void setAccountVerification(AccountVerification accountVerification) {
		this.accountVerification = accountVerification;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getAmountDebit() {
		return debitAmount;
	}

	public void setAmountDebit(Long amountDebit) {
		this.debitAmount = amountDebit;
	}

	public Long getAmountCredit() {
		return creditAmount;
	}

	public void setAmountCredit(Long amountCredit) {
		this.creditAmount = amountCredit;
	}

	// Getters and Setters
	
}
