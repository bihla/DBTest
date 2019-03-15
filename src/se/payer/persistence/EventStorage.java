package se.payer.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "EventStorage")
public class EventStorage extends PersistenceBase {
	@Column(name="Data")
	String data;

}
