package se.payer.bankid;

public class AuthResponse {
	/*
		"orderRef":"131daac9-16c6-4618-beb0-365768f37288",
	 	"autoStartToken":"7c40b5c9-fa74-49cf-b98c-bfe651f9a7c6"
	*/	 
	public String orderRef;
	public String autoStartToken;

/*{
 "orderRef":"131daac9-16c6-4618-beb0-365768f37288",
 "autoStartToken":"7c40b5c9-fa74-49cf-b98c-bfe651f9a7c6"
}
*/
	public String toString() {
		return "[PaymentResponse]"
				+",id="+id
				+",payeePaymentReference="+payeePaymentReference
				+",paymentReference="+paymentReference
				+",callbackUrl="+callbackUrl
				+",payerAlias="+payerAlias
				+",payeeAlias="+payeeAlias
				+",amount="+amount
				+",currency="+currency
				+",message="+message
				+",status="+status
				+",dateCreated="+dateCreated
				+",datePaid="+datePaid
				+",errorCode="+errorCode
				+",errorMessage="+errorMessage
				+",additionalInformation="+additionalInformation
				+"";
	}
}
