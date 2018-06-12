package se.payer.persistence;

import java.util.*;
import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "OrderTokenKeyValue", indexes = {
		@Index(name = "index_uniq_parentId_key", columnList = "OrderTokenId,Keyname", unique = true) })
public class OrderTokenKeyValue extends PersistenceBase {
/*
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "Id")
	private Long id;
*/
	@ManyToOne
	@JoinColumn(name = "OrderTokenId")
	private OrderToken orderToken;

	@Column(name = "Keyname", length = 64)
	private String keyname;

	@Column(name = "Value", length = 255)
	private String value;

	public OrderTokenKeyValue() {
		super();
	}

	public OrderTokenKeyValue(OrderToken orderToken, String keyname, String value) {
		super();
		this.keyname = keyname;
		this.value = value;
		this.orderToken = orderToken;
	}

	public String getKeyname() {
		return keyname;
	}

	public void setKeyname(String keyname) {
		this.keyname = keyname;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}