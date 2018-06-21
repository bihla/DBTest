/**
$Id: FactoringInvoiceDetail.java,v 1.1.2.1.2.10 2016/06/28 13:11:14 bihla Exp $
$Header: /usr/local/cvsroot/PayReadJavaEngine/WEB-INF/src/se/payread/persistent/Attic/FactoringInvoiceDetail.java,v 1.1.2.1.2.10 2016/06/28 13:11:14 bihla Exp $
$Log: FactoringInvoiceDetail.java,v $
Revision 1.1.2.1.2.10  2016/06/28 13:11:14  bihla
making use of PayerConstants

Revision 1.1.2.1.2.9  2016/06/08 09:32:28  bihla
fixed constructor

Revision 1.1.2.1.2.8  2016/04/22 11:03:27  bihla
added getSubtotalVat

Revision 1.1.2.1.2.7  2015/11/20 18:29:40  bihla
+added toString

Revision 1.1.2.1.2.6  2015/11/11 16:56:30  bihla
better handling of RemoteException

Revision 1.1.2.1.2.5  2015/10/30 16:11:17  bihla
better and more concise handling of order to invoice

Revision 1.1.2.1.2.4  2015/10/09 15:22:43  bihla
better logging

Revision 1.1.2.1.2.3  2015/10/06 11:12:15  bihla
+ added copy and copyDelivered

Revision 1.1.2.1.2.2  2015/09/25 10:01:52  bihla
added Constructor with copy FactoringInvoiceDetail(DirectOrderDetail dod)

Revision 1.1.2.1.2.1  2014/11/05 16:13:44  bihla
adding the latest

Revision 1.1.2.1  2013/11/01 08:23:06  bihla
Adding stuff for refund Factoring/Invoice/RefundLog

*/
package se.payer.persistence;

import javax.persistence.*;

import org.apache.log4j.Logger;

@SuppressWarnings("serial")
@Entity
@Table(name = "FactoringInvoiceDetail2")
public class FactoringInvoiceDetail extends PersistenceBase {

	private transient Logger logger = Logger.getLogger(FactoringInvoiceDetail.class);

	private String EntryType;
	private Integer Position;
	private String Description;
	private String ItemNumber;
	private String Account;
	private String Unit;
	private Integer UnitQty;
	private Double Price;
	private Double VatPercentage;
	private FactoringInvoiceHeader factoringInvoiceHeader;

	@ManyToOne
	@JoinColumn(name = "FactoringInvoiceHeaderId", nullable = false)
	public FactoringInvoiceHeader getFactoringInvoiceHeader() {
		return factoringInvoiceHeader;
	}

	public void setFactoringInvoiceHeader(FactoringInvoiceHeader factoringInvoiceHeader) {
		this.factoringInvoiceHeader = factoringInvoiceHeader;
	}

	/**
	 * Constructor
	 */
	public FactoringInvoiceDetail() {
		super();
	}

	public String getEntryType() {
		return EntryType;
	}

	public void setEntryType(String entryType) {
		EntryType = entryType;
	}

	public Integer getPosition() {
		return Position;
	}

	public void setPosition(Integer position) {
		Position = position;
	}

	public String getItemNumber() {
		return ItemNumber;
	}

	public void setItemNumber(String itemNumber) {
		ItemNumber = itemNumber;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getAccount() {
		return Account;
	}

	public void setAccount(String account) {
		Account = account;
	}

	public String getUnit() {
		return Unit;
	}

	public void setUnit(String unit) {
		Unit = unit;
	}

	public Integer getUnitQty() {
		return UnitQty;
	}

	public void setUnitQty(Integer unitQty) {
		UnitQty = unitQty;
	}

	public Double getPrice() {
		return Price;
	}

	public void setPrice(Double price) {
		Price = price;
	}

	public Double getVatPercentage() {
		return VatPercentage;
	}

	public void setVatPercentage(Double vatPercentage) {
		VatPercentage = vatPercentage;
	}

	@Transient
	public Double getSubtotal() {
		if (Price == null || Price == 0.0)
			return 0.0;
		if (UnitQty == null || UnitQty == 0)
			return 0.0;

		return Price * UnitQty;
	}

	@Transient
	public Double getSubtotalVat() {
		if (Price == null || Price == 0.0)
			return 0.0;
		if (UnitQty == null || UnitQty == 0)
			return 0.0;

		return (Price * UnitQty) - ((Price * UnitQty) / (1 + getVatPercentage() / 100.0));
	}

	public String toString() {
		/*
		 * private String EntryType; private int Position; private String ItemNumber;
		 * private String Description; private String Unit; private Integer UnitQty;
		 * private Double Price; private Double VatPercentage; private String Status;
		 * private Date DeliverDate; private String Account;
		 * 
		 * private Integer DeliveredQty; private Integer RestQty; private Integer
		 * UndeliverableQty;
		 */
		try {
			return String.format("InvoiceDetail[%d] : %s Pos:%04d No:%s D:%s U:%s Qty:%d P:%.2f Vat:%.2f", getId(),
					EntryType.toString(), Position, ItemNumber, Description, Unit, UnitQty, Price, VatPercentage);
		} catch (Exception ex) {
			logger.error("Caught Exception(safely handeled):" + ex.getMessage(), ex);
			return "toString exception";
		}
	}

}
