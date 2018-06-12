package se.payer.persistence;

import java.util.*;
import javax.persistence.*;

@Entity
@Table(name = "OrderToken", indexes = {
		@Index(name = "index_uniq_tokenIdentifier", columnList = "TokenIdentifier", unique = true),
		@Index(name = "index_createDate", columnList = "CreateDate", unique = false),
		@Index(name = "index_modificationDate", columnList = "ModificationDate", unique = false) })
public class OrderToken extends PersistenceBase {
	public OrderToken() {
		super();
	}
/*
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "Id")
	private Long id;
*/	
	@Column(name = "Description", length = 255)
	private String description;
	
	@Column(name = "TokenType", length = 64)
	private String tokenType;
	
	@Column(name = "TokenIdentifier", length = 128)
	private String tokenIdentifier = UUID.randomUUID().toString();
	
	@Column(name = "CreateDate")
	private Date createDate = new Date();
	
	@Column(name = "ModificationDate")
	private Date modificationDate = new Date();
	
	@OneToMany(mappedBy = "orderToken", cascade = CascadeType.ALL, orphanRemoval = true)
	@MapKey(name = "keyname")
	private Map<String, OrderTokenKeyValue> keyValuePairs = new LinkedHashMap<String, OrderTokenKeyValue>();
/*
	@ElementCollection
	@MapKeyColumn(name = "keyName", length = 128)
	@Column(name = "value", length = 1024)
	@CollectionTable(name = "OrderTokenMetaData", joinColumns = @JoinColumn(name = "orderTokenId", referencedColumnName = "id"))
	private Map<String, String> metaData = new LinkedHashMap<String, String>();
*/	
		
	public OrderToken(String description, String tokenType) {
		super();
		this.description = description;
		this.tokenType = tokenType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}

	public String getTokenIdentifier() {
		return tokenIdentifier;
	}

	@Transient
	public void setTokenIdentifier(String tokenIdentifier) {
		this.tokenIdentifier = tokenIdentifier;
	}

	public Map<String, OrderTokenKeyValue> getKeyValuePairs() {
		return keyValuePairs;
	}

	public void setKeyValuePairs(Map<String, OrderTokenKeyValue> keyValuePairs) {
		this.keyValuePairs = keyValuePairs;
	}

	@Transient
	public void createMetaData(String keyname, String value) {
		keyValuePairs.put(keyname, new OrderTokenKeyValue(this, keyname, value));
	}

	@Transient
	public String updateMetaData(String keyname, String value) {
		OrderTokenKeyValue node = keyValuePairs.get(keyname);
		if (node != null) {
			String org = node.getValue();
			node.setValue(value);
			return org;
		}
		return null;
	}

	@Transient
	public void deleteMetaData(String keyname) {
		keyValuePairs.remove(keyname);
	}

	@Transient
	public String getMetaData(String keyname) {
		OrderTokenKeyValue node = keyValuePairs.get(keyname);
		if (node != null)
			return node.getValue();
		return null;
	}

}