/**
$Id: ChargeLogDebitType.java,v 1.3.4.1.2.3.6.4.6.1 2017/01/09 15:30:47 bihla Exp $
Last modified: $Date $, by $Author: bihla $
Log:
$Log: ChargeLogDebitType.java,v $
Revision 1.3.4.1.2.3.6.4.6.1  2017/01/09 15:30:47  bihla
added cardStrToId, getByCardStr and toString

Revision 1.3.4.1.2.3.6.4  2013/11/01 08:22:19  bihla
added TransGroup

Revision 1.3.4.1.2.3.6.3  2013/09/26 18:28:21  bihla
added getters that does not throw exceptions

Revision 1.3.4.1.2.3.6.2  2013/05/08 11:46:18  bihla
Use this to get ChargeLogDebitTypeId by UniqName

Revision 1.3.4.1.2.3.6.1  2012/10/19 11:27:53  bihla
remarked / saved SQL

Revision 1.3.4.1.2.3  2011/02/28 15:48:24  admin
reviewed by: kchai

Revision 1.3.4.1.2.2  2011/02/02 11:01:46  bihla
SMALL changes about the word "commit"

Revision 1.3.4.1.2.1  2011/01/12 10:16:03  bihla
Improved logging. Improved Refund method.

Revision 1.3.4.1  2010/08/26 08:56:59  bihla
Improved getClassName(primary index) method

Revision 1.3  2010/06/03 07:52:26  bihla
Split getTransactionByName  into two versions - with and without TX


 */

/*
 DROP table if exists ChargeLogDebitTypeSort;
 CREATE table ChargeLogDebitTypeSort
 select * from ChargeLogDebitType order by Id;
 CREATE UNIQUE INDEX IndexNameUniq ON ChargeLogDebitTypeSort (UniqName);
 ALTER TABLE ChargeLogDebitTypeSort ADD PRIMARY KEY (Id);
 */

package se.payer.persistence;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
public class ChargeLogDebitType extends PersistenceBase {

	private String Name;
	private String UniqName;
	private String Type;
	private String DisplayType;
	private String RefundMethod;
	private String TransGroup;
	private String Description;

	public ChargeLogDebitType() {
		super();
	}

	@Transient
	public static ChargeLogDebitType getChargeLogDebitType(long id) throws NoSuchChargeLogDebitTypeException {
		ChargeLogDebitType chargeLogDebitType = (ChargeLogDebitType) HibernateUtilTarget.currentSession().get(ChargeLogDebitType.class, new Long(id));
		if (chargeLogDebitType == null) {
			throw new NoSuchChargeLogDebitTypeException("ChargeLogDebitType[" + id + "] not found.");
		}
		return chargeLogDebitType;
	}

	@Transient
	public static ChargeLogDebitType getChargeLogDebitType_NoException(long id) {
		ChargeLogDebitType chargeLogDebitType = (ChargeLogDebitType) HibernateUtilTarget.currentSession().get(ChargeLogDebitType.class, new Long(id));
		return chargeLogDebitType;
	}

	@Transient
	public static ChargeLogDebitType getChargeLogDebitTypeByUniqName(String uniqName) {
		ChargeLogDebitType chargeLogDebitType = (ChargeLogDebitType) HibernateUtilTarget.currentSession().createQuery("from ChargeLogDebitType where UniqName=:id").setString("id", uniqName).uniqueResult();
		return chargeLogDebitType;
	}

	@Transient
	public static String getDisplayName_no_TX_nonono(int debttype) {
		ChargeLogDebitType cdtype = (ChargeLogDebitType) HibernateUtilTarget.currentSession().createQuery("from ChargeLogDebitType where Id=:id").setLong("id", debttype).uniqueResult();
		return cdtype.getDisplayType();
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getUniqName() {
		return UniqName;
	}

	public void setUniqName(String uniqName) {
		UniqName = uniqName;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public String getDisplayType() {
		return DisplayType;
	}

	public void setDisplayType(String displayType) {
		DisplayType = displayType;
	}

	public String getRefundMethod() {
		return RefundMethod;
	}

	public void setRefundMethod(String refundMethod) {
		RefundMethod = refundMethod;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getTransGroup() {
		return TransGroup;
	}

	public void setTransGroup(String transGroup) {
		TransGroup = transGroup;
	}

	@Transient
	public static int cardStrToId(String pspBrandStr, String paymentTypeStr, String cardBrandStr, String payoutTypeStr) {
		Map<String, Integer> v0 = new HashMap<String, Integer>() {
			{
				put("PAYER", 0);
				put("DIBS", 1);
				put("NETGIRO", 2);
				put("NG", 2);
				put("EVRY", 3);
			}
		};
		Map<String, Integer> v1 = new HashMap<String, Integer>() {
			{
				put("CARD", 1);
				put("MP", 2); // MasterPass
				put("MASTERPASS", 2); // MasterPass
				put("RC", 3); // Recurring
				put("RECURRING", 3); // Recurring
			}
		};
		Map<String, Integer> v2 = new HashMap<String, Integer>() {
			{
				// "DEFAULT"=>1 ,"VISA"=>2 , "MASTERCARD"=>3 , "DINERS"=>4 , "AMEX"=>5 , "DISCOVER"=>6 , "DANCARD"=>7
				put("DEFAULT", 1);
				put("VISA", 2);
				put("MC", 3);
				put("MASTERCARD", 3);
				put("DINERS", 4);
				put("AMEX", 5);
				put("AMERICAN EXPRESS", 5);
				put("DISCOVER", 6);
				put("DISC", 6);
				put("DANCARD", 7);
				put("DANC", 7);
			}
		};
		Map<String, Integer> v3 = new HashMap<String, Integer>() {
			{
				put("PF", 1); // PaymentFacilitator (Swedbank)
				put("CARD", 1); // PaymentFacilitator (Swedbank)
				put("CP", 2); // CUSTOM_PAYOUT
				put("CUSTOM_PAYOUT", 2); // CUSTOM_PAYOUT
			}
		};
		
		int pspBrand=1; // default DIBS
		int paymentType=1; // default CARD
		int cardBrand=1; // default DEFAULT
		int payoutType=1; // default PF
		
		if (v0.get(pspBrandStr)!=null) pspBrand=v0.get(pspBrandStr);
		if (v1.get(paymentTypeStr)!=null) paymentType=v1.get(paymentTypeStr);
		if (v2.get(cardBrandStr)!=null) cardBrand=v2.get(cardBrandStr);
		if (v3.get(payoutTypeStr)!=null) payoutType=v3.get(payoutTypeStr);
		
		int Id = 1000000 + 10000 * payoutType // PF/CP
				+ 1000 * pspBrand // DIBS/NETGIRO
				+ 100 * paymentType // KORT/MASTERPASS
				+ 1 * cardBrand; // VISA/MASTERCARD/DINERS/AMEX/DISCOVER/DANCARD

		logger.debug("pspBrandStr="+pspBrandStr+"=>"+pspBrand
				+", paymentTypeStr"+paymentTypeStr+"=>"+paymentType
				+", cardBrandStr"+cardBrandStr+"=>"+cardBrand
				+", payoutTypeStr"+payoutTypeStr+"=>"+payoutType
				+", id="+Id);
		
		ChargeLogDebitType cldt = (ChargeLogDebitType) HibernateUtilTarget.currentSession().get(ChargeLogDebitType.class, new Long(Id));
		if (cldt!=null)
			logger.debug(((ChargeLogDebitType) HibernateUtilTarget.currentSession().get(ChargeLogDebitType.class, new Long(Id))).toString());
		else
			logger.warn("ChargeLogDebitType("+Id+") is not found in database.");
		
		return Id;
	}
	
	public ChargeLogDebitType getByCardStr(String pspBrandStr, String paymentTypeStr, String cardBrandStr, String payoutTypeStr) {
		return (ChargeLogDebitType) HibernateUtilTarget.currentSession().get(ChargeLogDebitType.class, new Long(ChargeLogDebitType.cardStrToId(pspBrandStr, paymentTypeStr, cardBrandStr, payoutTypeStr)));
	}

	public String toString() {
		return "[ChargeLogDebitType]::Id=" + getId() + ", Name=" + getName() + ", UniqName=" + getUniqName() + ", Type=" + getType() + ", DisplayType=" + getDisplayType() + ", Description=" + getDescription();
	}

}
