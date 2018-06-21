package se.payer.persistence;

@SuppressWarnings("serial")
public class NoSuchChargeLogDebitTypeException extends java.lang.RuntimeException {
    public NoSuchChargeLogDebitTypeException(String theMessage)
    {
	super(theMessage);
    }
}
