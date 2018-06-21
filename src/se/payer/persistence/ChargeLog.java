/*

$Id: ChargeLog.java,v 1.13.4.1.2.2.6.6.2.8.2.4.2.3 2017/09/08 09:47:00 bihla Exp $ 

$Header: /usr/local/cvsroot/PayReadJavaEngine/WEB-INF/src/se/payread/persistent/ChargeLog.java,v 1.13.4.1.2.2.6.6.2.8.2.4.2.3 2017/09/08 09:47:00 bihla Exp $ 

$Log: ChargeLog.java,v $
Revision 1.13.4.1.2.2.6.6.2.8.2.4.2.3  2017/09/08 09:47:00  bihla
all collector stuff

Revision 1.13.4.1.2.2.6.6.2.8.2.4.2.2  2017/05/30 15:13:14  bihla
added getChargeLogs

Revision 1.13.4.1.2.2.6.6.2.8.2.4.2.1  2017/05/18 15:30:44  bihla
added getRefundChargeLogs

Revision 1.13.4.1.2.2.6.6.2.8.2.4  2017/02/13 16:30:17  bihla
added getLastChargeLogForWebsiteId and getLastNdaysByWebsiteId

Revision 1.13.4.1.2.2.6.6.2.8.2.3  2017/01/18 16:31:02  bihla
toString using class

Revision 1.13.4.1.2.2.6.6.2.8.2.2  2017/01/16 13:52:51  bihla
+ added getLastChargeLog

Revision 1.13.4.1.2.2.6.6.2.8.2.1  2016/12/15 16:04:15  bihla
added stuff for masterpass

Revision 1.13.4.1.2.2.6.6.2.8  2016/04/22 09:53:48  bihla
added
+ getTimeStampStr

Revision 1.13.4.1.2.2.6.6.2.7  2016/04/01 08:26:01  bihla
added sessionId to ChargeLog constructor

Revision 1.13.4.1.2.2.6.6.2.6  2016/03/30 15:06:46  bihla
added commitChargeLog

Revision 1.13.4.1.2.2.6.6.2.5  2015/10/29 14:30:04  bihla
subTotal handling and currency

Revision 1.13.4.1.2.2.6.6.2.4  2015/09/16 12:46:00  bihla
added getChargeLogByWebsiteIdAndSessionId_NOT_DEBITED_YET

Revision 1.13.4.1.2.2.6.6.2.3  2015/08/25 14:51:21  bihla
adding the latest

Revision 1.1  2015/08/18 12:18:34  bihla
initial

Revision 1.1  2015/08/18 12:14:19  bihla
Initial

Revision 1.2  2015/08/10 10:14:12  bihla
cast to Long, not to long

Revision 1.1  2015/08/05 12:40:07  bihla
Initial store

Revision 1.13.4.1.2.2.6.6.2.2  2015/07/08 08:01:24  bihla
Code reviewed by: atibb

Revision 1.13.4.1.2.2.6.6.2.1  2014/07/09 15:00:37  bihla
WYWALLET ADDED

Revision 1.13.4.1.2.2.6.6  2013/11/12 15:12:13  bihla
added reportLogId and some static functions to search for ChargeLogs with.

Revision 1.13.4.1.2.2.6.5  2013/11/04 16:31:19  bihla
+ added getChargeLogByChargeLogIdAndWebsite

Revision 1.13.4.1.2.2.6.4  2013/11/01 08:21:57  bihla
from=>FROM

Revision 1.13.4.1.2.2.6.3  2013/10/11 12:44:18  bihla
+ added getSettledAmount and getRefundedAmount

Revision 1.13.4.1.2.2.6.2  2013/09/26 18:28:21  bihla
added getters that does not throw exceptions

Revision 1.13.4.1.2.2.6.1  2012/10/30 10:31:57  bihla
added some feautures for dealyed settlement

Revision 1.13.4.1.2.2  2011/02/28 15:47:18  admin
reviewed by: kchai


 */

package se.payer.persistence;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;

/**
 * this class provides an interface to the legacy Chargelog database table. it's
 * intended ONLY for the CREATION of new database entries for bank payments, so
 * it may be lacking in functionality. FIXME: To use in any other context, test
 * thoroughly.
 * 
 * @author dahla
 * 
 */
@SuppressWarnings("serial")
@Entity
public class ChargeLog extends PersistenceBase {

	// long id - inheritance
	long timeStamp = 0; // needs to be initialized

	String websiteId;
	String userId;
	String clientAddress;
	String url; // Same as details??
	String sessionId = "";

	long cost = 0;
	int currencyId = 1; // Create the mapping
	boolean isDebited = true; // Always "1" for bank payments
	long invoicelineId = 0; // "-1" for bank payments
	boolean isValid = true; // Always 1
	int vat = 12500;
	Long creditRefId = null; // allows null, ignore for now
	Long parentRefId = null; // points to parent transaction if in a serie
	Long reportLogId = null; // points to ReportLog where transaction is reported
	String details;
	int debitType = 0; // Reference from ChargeLogdebitType
	int debitTypeOrg = 0; // Reference from ChargeLogdebitType (stored in VeragentBuild)
	String customerAccountId = ""; // ignore for now
	Long debiTechLogId = null;
	long altReportIdInt = 0; // ignore
	long altReportIdExt = 0; // ignore
	long distToPayRead = 0; // ALLTID i SEK
	long distToRikab = 0; // ignore
	long distToCustomer = 0; // Alltid i SEK
	int fixedVat = 0; // ignore
	boolean testDebit = false;
	int extCurrencyId = 0; // if ZERO - everything is in SEK
	long extCost = 0;
	int extCurrencyDebited = 0;
	int originType = 7;
	int coreUsed = 0;
	int NoPayout;

	/**
	 * 
	 */
	public ChargeLog() {
		super();
	}

	/**
	 * @param websiteId
	 * @param userId
	 * @param details
	 * @param url
	 * @param debitType
	 * @param cost
	 * @param vat
	 * @param currencyId
	 * @param address
	 * @param debiTechlogId
	 * @param distToCustomer
	 * @param distToPayRead
	 * @param debit
	 */
	public ChargeLog(
			String websiteId,
			String userId,
			String url,
			String details,
			int debitType,
			long cost,
			int vat,
			int currencyId,
			String clientAddress,
			Long debiTechLogId,
			long distToCustomer,
			long distToPayRead,
			boolean testDebit,
			String sessionId
			) {
		super();

		this.websiteId = websiteId;
		this.userId = userId;
		this.details = details;
		this.url = url;
		this.debitType = debitType;
		this.cost = cost;
		this.vat = vat;
		this.currencyId = currencyId == 1 ? 0 : currencyId;
		this.clientAddress = clientAddress;
		this.debiTechLogId = debiTechLogId;
		this.distToCustomer = distToCustomer;
		this.distToPayRead = distToPayRead;
		this.testDebit = testDebit;
		this.sessionId = sessionId;
		// FIXME: Find a more concise way of doing this timestamp stuff - if
		// possible.
		Calendar cal = new GregorianCalendar();
		this.timeStamp = cal.getTimeInMillis() / 1000;

	}

	/**
	 * @return Returns the altReportIdExt.
	 */
	public long getAltReportIdExt() {
		return altReportIdExt;
	}

	/**
	 * @param altReportIdExt
	 *            The altReportIdExt to set.
	 */
	public void setAltReportIdExt(long altReportIdExt) {
		this.altReportIdExt = altReportIdExt;
	}

	/**
	 * @return Returns the altReportIdInt.
	 */
	public long getAltReportIdInt() {
		return altReportIdInt;
	}

	/**
	 * @param altReportIdInt
	 *            The altReportIdInt to set.
	 */
	public void setAltReportIdInt(long altReportIdInt) {
		this.altReportIdInt = altReportIdInt;
	}

	/**
	 * @return Returns the clientAddress.
	 */
	public String getClientAddress() {
		return clientAddress;
	}

	/**
	 * @param clientAddress
	 *            The clientAddress to set.
	 */
	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}

	/**
	 * @return Returns the coreUsed.
	 */
	public int getCoreUsed() {
		return coreUsed;
	}

	/**
	 * @param coreUsed
	 *            The coreUsed to set.
	 */
	public void setCoreUsed(int coreUsed) {
		this.coreUsed = coreUsed;
	}

	/**
	 * @return Returns the cost.
	 */
	public long getCost() {
		return cost;
	}

	/**
	 * @param cost
	 *            The cost to set.
	 */
	public void setCost(long cost) {
		this.cost = cost;
	}

	/**
	 * @return Returns the creditRefId.
	 */
	public Long getCreditRefId() {
		return creditRefId;
	}

	/**
	 * @param creditRefId
	 *            The creditRefId to set.
	 */
	public void setCreditRefId(Long creditRefId) {
		this.creditRefId = creditRefId;
	}

	/**
	 * @return Returns the currencyId.
	 */
	public int getCurrencyId() {
		return currencyId;
	}

	/**
	 * @param currencyId
	 *            The currencyId to set.
	 */
	public void setCurrencyId(int currencyId) {
		this.currencyId = currencyId;
	}

	/**
	 * @return Returns the customerAccountId.
	 */
	public String getCustomerAccountId() {
		return customerAccountId;
	}

	/**
	 * @param customerAccountId
	 *            The customerAccountId to set.
	 */
	public void setCustomerAccountId(String customerAccountId) {
		this.customerAccountId = customerAccountId;
	}

	/**
	 * @return Returns the debiTechlogId.
	 */
	public Long getDebiTechLogId() {
		return debiTechLogId;
	}

	/**
	 * @param debiTechlogId
	 *            The debiTechlogId to set.
	 */
	public void setDebiTechLogId(Long debiTechlogId) {
		this.debiTechLogId = debiTechlogId;
	}

	/**
	 * @return Returns the debitType.
	 */
	public int getDebitType() {
		return debitType;
	}

	/**
	 * @param debitType
	 *            The debitType to set.
	 */
	public void setDebitType(int debitType) {
		this.debitType = debitType;
	}

	/**
	 * @return Returns the details.
	 */
	public String getDetails() {
		return details;
	}

	/**
	 * @param details
	 *            The details to set.
	 */
	public void setDetails(String details) {
		this.details = details;
	}

	/**
	 * @return Returns the distToCustomer.
	 */
	public long getDistToCustomer() {
		return distToCustomer;
	}

	/**
	 * @param distToCustomer
	 *            The distToCustomer to set.
	 */
	public void setDistToCustomer(long distToCustomer) {
		this.distToCustomer = distToCustomer;
	}

	/**
	 * @return Returns the distToPayRead.
	 */
	public long getDistToPayRead() {
		return distToPayRead;
	}

	/**
	 * @param distToPayRead
	 *            The distToPayRead to set.
	 */
	public void setDistToPayRead(long distToPayRead) {
		this.distToPayRead = distToPayRead;
	}

	/**
	 * @return Returns the distToRikab.
	 */
	public long getDistToRikab() {
		return distToRikab;
	}

	/**
	 * @param distToRikab
	 *            The distToRikab to set.
	 */
	public void setDistToRikab(long distToRikab) {
		this.distToRikab = distToRikab;
	}

	/**
	 * @return Returns the extCost.
	 */
	public long getExtCost() {
		return extCost;
	}

	/**
	 * @param extCost
	 *            The extCost to set.
	 */
	public void setExtCost(long extCost) {
		this.extCost = extCost;
	}

	/**
	 * @return Returns the extCurrencyDebited.
	 */
	public int getExtCurrencyDebited() {
		return extCurrencyDebited;
	}

	/**
	 * @param extCurrencyDebited
	 *            The extCurrencyDebited to set.
	 */
	public void setExtCurrencyDebited(int extCurrencyDebited) {
		this.extCurrencyDebited = extCurrencyDebited;
	}

	/**
	 * @return Returns the extCurrencyId.
	 */
	public int getExtCurrencyId() {
		return extCurrencyId;
	}

	/**
	 * @param extCurrencyId
	 *            The extCurrencyId to set.
	 */
	public void setExtCurrencyId(int extCurrencyId) {
		this.extCurrencyId = extCurrencyId;
	}

	/**
	 * @return Returns the fixedVat.
	 */
	public int getFixedVat() {
		return fixedVat;
	}

	/**
	 * @param fixedVat
	 *            The fixedVat to set.
	 */
	public void setFixedVat(int fixedVat) {
		this.fixedVat = fixedVat;
	}

	/**
	 * @return Returns the invoicelineId.
	 */
	public long getInvoicelineId() {
		return invoicelineId;
	}

	/**
	 * @param invoicelineId
	 *            The invoicelineId to set.
	 */
	public void setInvoicelineId(long invoicelineId) {
		this.invoicelineId = invoicelineId;
	}

	/**
	 * @return Returns the isDebited.
	 */
	public boolean isIsDebited() {
		return isDebited;
	}

	/**
	 * @param isDebited
	 *            The isDebited to set.
	 */
	public void setIsDebited(boolean isDebited) {
		this.isDebited = isDebited;
	}

	/**
	 * @return Returns the isValid.
	 */
	public boolean isIsValid() {
		return isValid;
	}

	/**
	 * @param isValid
	 *            The isValid to set.
	 */
	public void setIsValid(boolean isValid) {
		this.isValid = isValid;
	}

	/**
	 * @return Returns the originType.
	 */
	public int getOriginType() {
		return originType;
	}

	/**
	 * @param originType
	 *            The originType to set.
	 */
	public void setOriginType(int originType) {
		this.originType = originType;
	}

	/**
	 * @return Returns the testDebit.
	 */
	public boolean isTestDebit() {
		return testDebit;
	}

	/**
	 * @param testDebit
	 *            The testDebit to set.
	 */
	public void setTestDebit(boolean testDebit) {
		this.testDebit = testDebit;
	}

	/**
	 * @return Returns the url.
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            The url to set.
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return Returns the userId.
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            The userId to set.
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return Returns the vat.
	 */
	public int getVat() {
		return vat;
	}

	/**
	 * @param vat
	 *            The vat to set.
	 */
	public void setVat(int vat) {
		this.vat = vat;
	}

	/**
	 * @return Returns the websiteId.
	 */
	public String getWebsiteId() {
		return websiteId;
	}

	/**
	 * @param websiteId
	 *            The websiteId to set.
	 */
	public void setWebsiteId(String websiteId) {
		this.websiteId = websiteId;
	}

	/**
	 * @return Returns the timeStamp.
	 */
	@Column(name = "TimeStamp", nullable = false)
	public long getTimeStamp() {
		return timeStamp;
	}

	@Transient
	public String getTimeStampStr() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(timeStamp * 1000L);
	}

	/**
	 * @param timeStamp
	 *            The timeStamp to set.
	 */
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp.getTime() / 1000;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public int getNoPayout() {
		return NoPayout;
	}

	public void setNoPayout(int noPayout) {
		NoPayout = noPayout;
	}

	public Long getParentRefId() {
		return parentRefId;
	}

	public void setParentRefId(Long parentRefId) {
		this.parentRefId = parentRefId;
	}

	public Long getReportLogId() {
		return reportLogId;
	}

	public void setReportLogId(Long reportLogId) {
		this.reportLogId = reportLogId;
	}

	@Column(name = "VeragentBuild")
	public int getDebitTypeOrg() {
		return debitTypeOrg;

	}

	public void setDebitTypeOrg(int debitTypeOrg) {
		// Use unused veragentuild db column
		this.debitTypeOrg = debitTypeOrg;
	}

	@Column(name = "KeyIndex")
	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	@Transient
	public static ChargeLog getChargeLog(long chargeLogId) {
		ChargeLog chargeLog = (ChargeLog) HibernateUtil.currentSession().get(ChargeLog.class, new Long(chargeLogId));
		if (chargeLog == null) {
			throw new NoSuchChargeLogException("ChargeLog[" + chargeLogId + "] not found.");
		}
		return chargeLog;
	}

	@Transient
	public static ArrayList<ChargeLog> getChargeLogs(String websiteId, Date fomDate, Date tomDate, boolean isTest) {

		ArrayList<ChargeLog> chargeLogs = (ArrayList<ChargeLog>) HibernateUtil.currentSession()
				.createQuery("FROM ChargeLog WHERE WebSiteId=:wid AND TimeStamp BETWEEN :fom AND :tom")
				.setString("wid", websiteId)
				.setLong("fom", fomDate.getTime()/1000)
				.setLong("tom", tomDate.getTime()/1000)
				.list();
		logger.debug("Size:" + chargeLogs.size());
		return chargeLogs;
	}

	@Transient
	public static ChargeLog getChargeLog_NoExeption(long chargeLogId) {
		ChargeLog chargeLog = (ChargeLog) HibernateUtil.currentSession().get(ChargeLog.class, new Long(chargeLogId));
		return chargeLog;
	}

	@Transient
	public static ChargeLog getChargeLogByChargeLogIdAndWebsite_NoException(Long chargeLogId, String websiteId) {
		ChargeLog chargeLog = (ChargeLog) HibernateUtil.currentSession()
				.createQuery("FROM ChargeLog WHERE Id=:id AND WebSiteId=:wid")
				.setParameter("id", chargeLogId)
				.setParameter("wid", websiteId)
				.uniqueResult();
		return chargeLog;
	}
	
	@Transient
	public static ChargeLog getChargeLogByChargeLogIdAndWebsite(Long chargeLogId, String websiteId) {
		ChargeLog chargeLog = (ChargeLog) HibernateUtil.currentSession()
				.createQuery("FROM ChargeLog WHERE Id=:id AND WebSiteId=:wid")
				.setParameter("id", chargeLogId)
				.setParameter("wid", websiteId)
				.uniqueResult();
		if (chargeLog == null) {
			throw new NoSuchChargeLogException("ChargeLog[" + chargeLogId + "] not found.");
		}
		return chargeLog;
	}

	@Transient
	public static ChargeLog getChargeLogByWebsiteIdAndSessionId_NOT_DEBITED_YET(String websiteId, String sessionId) {

		final Logger logger = Logger.getLogger("ChargeLog");
		logger.debug("ChargeLog::getChargeLogByWebsiteIdAndSessionId_NOT_DEBITED_YET(websiteId:'" + websiteId + "'sessionId:'" + sessionId + ")");

		ChargeLog chargeLog = (ChargeLog) HibernateUtil.currentSession()
				.createQuery("FROM ChargeLog WHERE WebSiteId=:wid AND KeyIndex=:id AND IsDebited=0")
				.setParameter("wid", websiteId)
				.setParameter("id", sessionId)
				.uniqueResult();
		return chargeLog;
	}

	@Transient
	public static ChargeLog getChargeLogBySessionIdAndType(String sessionId, int type) {

		final Logger logger = Logger.getLogger("ChargeLog");
		logger.debug("ChargeLog::getChargeLogBySessionIdAndType(sessionId:'" + sessionId + "', type:" + type + ")");

		ChargeLog chargeLog = (ChargeLog) HibernateUtil.currentSession()
				.createQuery("FROM ChargeLog WHERE KeyIndex=:id AND DebitType=:type")
				.setParameter("id", sessionId)
				.setParameter("type", type)
				.uniqueResult();
		return chargeLog;
	}

	@Transient
	public static List<ChargeLog> getRefundChargeLogs100(String websiteId, boolean isTest) {
		final Logger logger = Logger.getLogger("ChargeLog");
		logger.debug("ChargeLog::getRefundChargeLogs(" + websiteId + ", " + isTest + ")...");

		int testAsInt = isTest ? 1 : 0;

		List<ChargeLog> chargeLogs = (List<ChargeLog>) HibernateUtil.currentSession().createQuery("FROM ChargeLog WHERE WebsiteId=:wid AND TestDebit=:istest AND CreditRefId <> null ORDER BY Id DESC").setParameter("wid", websiteId).setParameter("istest", testAsInt).setMaxResults(100).list();
		return chargeLogs;
	}

	@Transient
	public static List<ChargeLog> getRefundChargeLogs100(long maxId, boolean isTest) {
		final Logger logger = Logger.getLogger("ChargeLog");
		logger.debug("ChargeLog::getRefundChargeLogs(" + maxId + ", " + isTest + ")...");

		int testAsInt = isTest ? 1 : 0;

		List<ChargeLog> chargeLogs = (List<ChargeLog>) HibernateUtil.currentSession().createQuery("FROM ChargeLog WHERE Id<=:maxid AND TestDebit=:istest AND CreditRefId <> null ORDER BY Id DESC").setParameter("maxid", maxId).setParameter("istest", testAsInt).setMaxResults(100).list();
		return chargeLogs;
	}

	@Transient
	public static void purgeByWebsiteId(String websiteId) {
		logger.debug("purgeByWebsiteId(String websiteId="+websiteId+")...");
		HibernateUtil.currentSession()
		.createQuery("DELETE ChargeLog WHERE WebsiteId = :wid")
		.setParameter("wid", websiteId)
		.executeUpdate();
	}


	@Transient
	public static ChargeLog getChargeLogBySessionIdAndTypeAndLimitNotRefund(String sessionid, int type, Long limit) {
		ChargeLog chargeLog = (ChargeLog) HibernateUtil.currentSession()
				.createQuery("FROM ChargeLog WHERE KeyIndex=:id AND DebitType=:type AND Id BETWEEN :m1 AND :m2 AND CreditRefId IS NULL")
				.setParameter("id", sessionid)
				.setParameter("type", type)
				.setParameter("m1", limit - 20)
				.setParameter("m2", limit + 20)
				.uniqueResult();
		return chargeLog;
	}

	@Transient
	public static ChargeLog getChargeLogByParentRefIdAndType(Long parentRefId, int type) {
		ChargeLog chargeLog = (ChargeLog) HibernateUtil.currentSession()
				.createQuery("FROM ChargeLog WHERE ParentRefId=:prid AND DebitType=:type")
				.setParameter("prid", parentRefId)
				.setParameter("type", type)
				.uniqueResult();
		return chargeLog;
	}

	@Transient
	public static ChargeLog getChargeLogByParentRefIdAndTypeNotRefund(Long parentRefId, int type) {
		ChargeLog chargeLog = (ChargeLog) HibernateUtil.currentSession()
				.createQuery("FROM ChargeLog WHERE ParentRefId=:prid AND DebitType=:type AND CreditRefId IS NULL")
				.setParameter("prid", parentRefId)
				.setParameter("type", type)
				.uniqueResult();
		return chargeLog;
	}

	@Transient
	public static List<ChargeLog> getLastNdaysByWebsiteId(String websiteId, int nDays, int maxResultRows) {
		return (List<ChargeLog>) HibernateUtil.currentSession()
				.createQuery("FROM ChargeLog WHERE WebSiteId=:wid AND TimeStamp > (UNIX_TIMESTAMP()-3600*24*"+nDays+") ORDER BY Id DESC")
				.setParameter("wid", websiteId)
				.setMaxResults(maxResultRows)
				.list();
	}

	@Transient
	public Double getSettledAmount() {
		return Math.round(getCost() / 100.0) / 100.0;
	}

	@Transient
	public long commitChargeLog() {

		logger.debug("commitChargeLog => Reset chargeLog from temporary to final and create new...");

		if (isIsDebited() == false) {

			/* Create Commit ChargeLog */
			ChargeLog newClg = new ChargeLog(getWebsiteId(), getUserId(), getUrl(), getDetails(), getDebitTypeOrg(), getCost(), getVat(), getCurrencyId(), getClientAddress(), getDebiTechLogId(), getDistToCustomer(), getDistToPayRead(),
					isTestDebit(), getSessionId());

			newClg.setSessionId(getSessionId());

			HibernateUtil.currentSession().saveOrUpdate(newClg);
			long id = newClg.getId();

			/* Update the Reserve ChargeLog to be hidden */

			Date date = new Date();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			String details = String.format("Committed@%s:%d, Cost=%.2f, DistToCustomer=%.2f, DistToPR=%.2f", dateFormat.format(date), id, getCost() / 10000.0, getDistToCustomer() / 10000.0, getDistToPayRead() / 10000.0);
			setDetails(details);

			setUrl("[" + id + "]:" + getUrl());

			setIsDebited(true);
			setReportLogId(0L);
			setNoPayout(1);
			setCost(0);
			setDistToCustomer(0);
			setDistToPayRead(0);

			HibernateUtil.currentSession().saveOrUpdate(this);

			logger.debug("New ChargeLog was created and stored:" + id);
			return id;
		} else {
			logger.debug("commitChargeLog => already committed.");
		}
		return -1;
	}

	public String toString() {
		return 
			"["+this.getClass().getSimpleName()+"]:" 
			+ "Id=" + getId() 
			+ ",Date=" + getTimeStampStr() 
			+ ",WebsiteId=" + getWebsiteId()
			+ ",UserId=" + getUserId() 
			+ ",Cost=" + getCost() + "";
	}
}
