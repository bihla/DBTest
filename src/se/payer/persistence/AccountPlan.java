package se.payer.persistence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "AccountPlan", indexes = {
		@Index(name = "AccountNumber_uniq", columnList = "AccountNumber", unique = true) })

public class AccountPlan extends PersistenceBase {

	@Column(name = "AccountNumber", length = 16)
	private String AccountNumber;

	@Column(name = "Description", length = 255)
	private String description;

	public AccountPlan() {
		super();
	}

	public AccountPlan(String accountNumber, String description) {
		this.AccountNumber = accountNumber;
		this.description = description;
	}

	// Getters and Setters

	public String getAccountNumber() {
		return AccountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		AccountNumber = accountNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void importPlan() {

		File testFile = new File("");
		String currentPath = testFile.getAbsolutePath();
		String currentFile = currentPath + "/src/se/payer/persistence/AccountPlan.txt";
		System.out.println("current path is: " + currentPath);
		System.out.println("current file is: " + currentFile);

		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(currentFile));
			String lineHeader = reader.readLine();
			String encodedWithISO88591 = reader.readLine();
			while (encodedWithISO88591 != null && encodedWithISO88591.length() > 4) {
				String decodedToUTF8 = new String(encodedWithISO88591.getBytes("ISO-8859-1"), "UTF-8");
				System.out.println(decodedToUTF8);
				String account = decodedToUTF8.substring(0, 4);
				String desc = decodedToUTF8.substring(5);
				System.out.println(" acc=" + account);
				System.out.println(" desc=" + desc);
				AccountPlan ap = new AccountPlan(account, desc);
				HibernateUtil.currentSession().save(ap);
				// read next line
				encodedWithISO88591 = reader.readLine();
			}
			reader.close();
			HibernateUtil.currentSession().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Transient
	public static AccountPlan getByAccountNumber(String accountNumber) {
		return (AccountPlan) HibernateUtil.currentSession()
				.createQuery("FROM AccountPlan WHERE AccountNumber = :anumber")
				.setParameter("anumber", accountNumber)
				.uniqueResult();
	}

}
