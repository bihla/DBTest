package se.payer.persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "AccountVerification")
public class AccountVerification extends PersistenceBase {

	@Column(name = "CreateDate")
	private Date createDate = new Date();

	@Column(name = "Description", length = 255)
	private String description;

	@OneToMany(mappedBy = "accountVerification", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Account> accounts = new ArrayList<Account>();

	public AccountVerification() {
		super();
	}

	public AccountVerification(String description) {
		this.description = description;
	}

	public void addAccount(String accountNumber, Double amount) {
		AccountPlan ap = AccountPlan.getByAccountNumber(accountNumber);
		String description = (ap == null ? "(unknown account:" + accountNumber + ")" : ap.getDescription());
		accounts.add(amount >= 0 ? new AccountDebit(this, accountNumber, amount, description)
				: new AccountCredit(this, accountNumber, amount, description));
	}

	public long commit() throws Exception {
		
		long amount = 0;
		long debit = 0;
		long credit = 0;

		for (Account account : accounts) {
			debit += (account.getAmountDebit() == null ? 0.0 : account.getAmountDebit()) * 100;
			credit += (account.getAmountCredit() == null ? 0.0 : account.getAmountCredit()) * 100;
			amount += (account.getAmount() == null ? 0.0 : account.getAmount()) * 100;
		}
		if ((debit - credit) == 0 && amount == 0) {
			return (long) HibernateUtilTarget.currentSession().save(this);
		}
		logger.debug("throwing Exception");
		logger.debug(" - debit:" + debit + ", credit:" + credit + ", amount:" + amount);
		throw new Exception("Not in balance:" + "debit:" + debit + ", credit:" + credit + ", amount:" + amount);
	}
}
