/*
$Id: PersistenceBase.java,v 1.9.10.2.4.2.4.4.2.1 2016/12/15 12:33:53 bihla Exp $ 
$Header: /usr/local/cvsroot/PayReadJavaEngine/WEB-INF/src/se/payread/persistent/PersistenceBase.java,v 1.9.10.2.4.2.4.4.2.1 2016/12/15 12:33:53 bihla Exp $ 
$Log: PersistenceBase.java,v $
Revision 1.9.10.2.4.2.4.4.2.1  2016/12/15 12:33:53  bihla
new lines

Revision 1.9.10.2.4.2.4.4  2015/09/16 12:46:34  bihla
use wildcard

Revision 1.9.10.2.4.2.4.3  2015/04/21 14:48:52  bihla
added serial

Revision 1.9.10.2.4.2.4.2  2015/04/10 16:06:21  bihla
changed the logger to be transient (and private/protected static and final)

Revision 1.9.10.2.4.2.4.1  2014/11/05 16:13:44  bihla
adding the latest

Revision 1.9.10.2.4.2  2011/11/15 08:30:20  bihla
= cleaned up the comments

Revision 1.9.10.2.4.1  2011/11/08 10:03:34  bihla
added CVS header

Revision 1.9.10.2  2011/02/28 16:21:56  admin
reviewed by: kchai
*/

package se.payer.persistence;

import java.io.Serializable;
import javax.persistence.*;

import org.apache.log4j.Logger;

@MappedSuperclass
public class PersistenceBase implements Serializable {
	protected static final transient Logger logger = Logger.getLogger(PersistenceBase.class);
	private static final long serialVersionUID = 7446798237474L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "Id")
	
	protected Long id;

	public Long getId() {
		return this.id;
	}

	private void setId(final Long id) {
		this.id = id;
	}
}