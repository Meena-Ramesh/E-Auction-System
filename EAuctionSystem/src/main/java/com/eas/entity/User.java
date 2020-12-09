package com.eas.entity;

import java.io.Serializable;

//import java.util.Date;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;


/**
 * @author jamuna
 *
 */
@Entity
@Table(name = "user_auction")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
/*
 * Default constructor
 */
	public User() {

	}

/*
 * @param userId the auto-generated Id in the database for the user
 * @param firstName the first name of the user 
 * @param lastName the last name of the user
 * @param email the email id of the user
 * @param aadharNumber the aadharNumber of the user for identification proof
 * @param contactNumber the mobile number of the user
 * @param address the address entity which gives the address details of the user
 * @param bankDetails the bankDetails entity which gives the bank account details of the user
 * @param password the password to be used by the user for logging in
 * @param userType the enum constant which specifies the type of the user. The user can register as seller, buyer or both
 */
	public User(int userId, String firstName, String lastName, String email, String aadharNumber, String contactNumber,
			Address address, BankDetails bankDetails, String password, UserType userType) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.aadharNumber = aadharNumber;
		this.contactNumber = contactNumber;
		this.address = address;
		this.bankDetails = bankDetails;
		this.password = password;
		this.userType = userType;
	}
/*
 * Overloaded constructor
 */
	public User(String firstName, String lastName, String email, String aadharNumber, String contactNumber,
			Address address, BankDetails bankDetails, String password, UserType userType) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.aadharNumber = aadharNumber;
		this.contactNumber = contactNumber;
		this.address = address;
		this.bankDetails = bankDetails;
		this.password = password;
		this.userType = userType;
	}

	@Id
	@GeneratedValue
	@Column(name = "user_id")
	private int userId;

	@NotBlank(message = "First name should not be empty")
	@Size(min = 3, max = 20, message = "First name should be between 3 and 20 characters")
	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;
	
	@NotNull(message = "Email cannot be null") 
	@Pattern(regexp = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$" , message = "Email is invalid")
	private String email;

	@NotNull(message = "Aadhar number cannot be null") 
	@Pattern(regexp = "[0-9]{12}" , message = "Aadhar number should have 12 numbers")
	@Column(name = "aadhar_number")
	private String aadharNumber;

	@NotNull(message = "Contact number cannot be null") 
	@Pattern(regexp = "[0-9]{10}" , message = "Contact number should have 10 digits")
	@Column(name = "contact_number")
	private String contactNumber;

	@Valid
	@NotNull(message = "Address cannot be null")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	private Address address;

	@Valid
	@NotNull(message = "Bank Details cannot be null")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "bank_details_id")
	private BankDetails bankDetails;

	@NotBlank(message = "Password cannot be null") 
	@Size(min = 5, message = "Password must have minimum 5 characters")
	@Column(name = "password")
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(name = "user_type")
	private UserType userType;

/*
 * Getters and setters	
 */
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAadharNumber() {
		return aadharNumber;
	}

	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public BankDetails getBankDetails() {
		return bankDetails;
	}

	public void setBankDetails(BankDetails bankDetails) {
		this.bankDetails = bankDetails;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "User Details" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", aadharNumber=" + aadharNumber + ", contactNumber=" + contactNumber + ", address=" + address
				+ ", bankDetails=" + bankDetails + ", password=" + password + ", userType=" + userType + "]";
	}
	
	

}

