package se.payer.persistence;

@SuppressWarnings("serial")
public class NoSuchChargeLogException extends java.lang.RuntimeException {
	public NoSuchChargeLogException(String theMessage) {
		super(theMessage);
	}
}
