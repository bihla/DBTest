package se.payer.bankid;

public class AuthRequest {
	/*
	"personalNumber" => "196605057253",
		    "endUserIp" => "94.140.57.181"
	*/
	public String personalNumber;
	public String endUserIp;
	
	public String toString() {
		return "[AuthRequest]"
				+",personalNumber="+personalNumber
				+",endUserIp="+endUserIp
				+"";
	}
}
