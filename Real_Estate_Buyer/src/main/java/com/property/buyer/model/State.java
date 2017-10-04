package com.property.buyer.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "state")
public class State implements java.io.Serializable {

	/** long Short Description */
	private static final long serialVersionUID = -5872567539267170495L;
	private Set<Address> addresses = new HashSet<Address>(0);
	private Long id;
	private String state;

	public State() {
	}

	public State(String state) {
		this.state = state;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof State)) {
			return false;
		}
		State other = (State) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (state == null) {
			if (other.state != null) {
				return false;
			}
		} else if (!state.equals(other.state)) {
			return false;
		}
		return true;
	}

	/**
	 * @return addresses of Set<Address> Type
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "state")
	public final Set<Address> getAddresses() {
		return addresses;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	@Column(name = "STATE", length = 100)
	public String getState() {
		return this.state;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		return result;
	}

	/**
	 * @param Set<Address>
	 *            type set into addresses
	 */
	public final void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setState(String state) {
		this.state = state;
	}
}
