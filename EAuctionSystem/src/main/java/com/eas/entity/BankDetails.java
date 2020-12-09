package com.eas.entity;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;


/**
 * @author Charishma
 *This entity BankDetails is persisted into the database using entityManager
 */

@Entity
@Table(name = "bank_details")
public class BankDetails implements Serializable {
	
	private static final long serialVersionUID = 1L;

/*
 * Default Constructor	
 */
	public BankDetails() {

	}

/*
 * @param bankDetailsId the auto-generated Id for a particular user in the database
 * @param accountHolderName the name of the user as per bank account
 * @param accountNumber the accountNumber of the user
 * @param bankName the name of the bank
 * @param branchName the name of the branch 
 */
	
	public BankDetails(int bankDetailsId, String accountHolderName, String accountNumber, String bankName,
			String branchName) {
		super();
		this.bankDetailsId = bankDetailsId;
		this.accountHolderName = accountHolderName;
		this.accountNumber = accountNumber;
		this.bankName = bankName;
		this.branchName = branchName;
	}

/*
 * Overloaded constructor
 */
	
	public BankDetails(String accountHolderName, String accountNumber, String bankName, String branchName) {
		super();
		this.accountHolderName = accountHolderName;
		this.accountNumber = accountNumber;
		this.bankName = bankName;
		this.branchName = branchName;
	}

	@Id
	@GeneratedValue
	@Column(name = "bank_details_id")
	private int bankDetailsId;

	@NotBlank(message = "Account Holder Name must not be empty")
	@Column(name = "account_holder_name")
	private String accountHolderName;

	@NotBlank(message = "Account number must not be empty")
	@Pattern(regexp = "[0-9]{14}", message = "Account number must have 14 digits")
	@Column(name = "account_number")
	private String accountNumber;

	@NotBlank(message = "Bank name must not be empty")
	@Column(name = "bank_name")
	private String bankName;

	@NotBlank(message = "Branch name must not be empty")
	@Column(name = "branch_name")
	private String branchName;
	
/*
 * Getters and Setters	
 */

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	
	

}