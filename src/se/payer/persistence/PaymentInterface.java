package se.payer.persistence;

public interface PaymentInterface {
	public String initialize();

	public String authenticate();

	public String authorize();

	public String settle();

	public String refund();
}
