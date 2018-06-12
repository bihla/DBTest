package se.payer.persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "AccountVerification")
public class AccountVerification extends PersistenceBase {

	@Column(name = "Description", length = 255)
	private String Description;

	@Column(name = "CreateDate")
	private Date createDate = new Date();

	@OneToMany(mappedBy = "accountVerification", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Account> accounts = new ArrayList<Account>();

	public AccountVerification() {
		super();
	}

	// Getters and Setters

	public AccountVerification(String description) {
		Description = description;
	}

	public void addAccount(String accountNumber, Long amount) {
		AccountPlan ap = AccountPlan.getByAccountNumber(accountNumber);
		String description = (ap == null ? "(unknown account:"+accountNumber+")" : ap.getDescription());
		accounts.add(new Account(this, accountNumber, amount, description));
	}

}
