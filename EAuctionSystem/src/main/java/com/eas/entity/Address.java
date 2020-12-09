package com.eas.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Jamuna
 *Entity Address to be persisted into the database by the entityManager
 */
@Entity
@Table(name = "address")
public class Address implements Serializable {

	private static final long serialVersionUID = 1L;
	
/*
 * Default Constructor
 */
	public Address() {

	}

/*
 * @param addressId auto-generated in the database
 * @param doorNumber the door number of the User
 * @param buildingName the name of the building
 * @param streetName the name of the street of the User
 * @param locality the locality of the User
 * @param city the city of the User
 * @param state the state of the user
 * @param country the residing country of the user
 * @param zip the postal code of the user address
 */

	public Address(int addressId, String doorNumber, String buildingName, String streetName, String locality,
			String city, String state, String country, int zip) {
		super();
		this.addressId = addressId;
		this.doorNumber = doorNumber;
		this.buildingName = buildingName;
		this.streetName = streetName;
		this.locality = locality;
		this.city = city;
		this.state = state;
		this.country = country;
		this.zip = zip;
	}
	
	/*
	 * Overloaded constructor
	 */

	public Address(String doorNumber, String buildingName, String streetName, String locality, String city,
			String state, String country, int zip) {
		super();
		this.doorNumber = doorNumber;
		this.buildingName = buildingName;
		this.streetName = streetName;
		this.locality = locality;
		this.city = city;
		this.state = state;
		this.country = country;
		this.zip = zip;
	}

	@Id
	@GeneratedValue
	@Column(name = "address_id")
	private int addressId;

	@Column(name = "door_number")
	private String doorNumber;

	@Column(name = "building_name")
	private String buildingName;

	@Column(name = "street_name")
	private String streetName;

	@Column
	private String locality;

	@Column
	private String city;

	@Column
	private String state;

	@Column
	private String country;

	@Column
	private int zip;
	
/*
 *  Getters and setters for the fields	
 */

	public String getDoorNumber() {
		return doorNumber;
	}

	public void setDoorNumber(String doorNumber) {
		this.doorNumber = doorNumber;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}
	
	

}