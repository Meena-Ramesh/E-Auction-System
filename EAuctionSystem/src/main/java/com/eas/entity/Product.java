package com.eas.entity;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Vismitha
 * This product entity is persisted into the database by entityManager
 */
@Entity
@Table(name = "product")
public class Product implements Serializable {
	
/*
 * Default constructor	
 */
	public Product() {

	}

/*
 * @param productId auto-generated id in the database for each product
 * @param productName the name of the product
 * @param category the name of the product category
 * @param basePrice the price fixed for a product by the seller
 * @param seller the User who is a seller
 * @param productDescription the information about the product
 * @param availabilityStatus the status of the product availability. If it is available for auction, it is true, else it is false
 * @param reviewStatus the status of the product if it is approved by admin
 * @param auction the auction entity
 */
	public Product(int productId, String productName, String category, double basePrice, User seller,
			String productDescription, boolean availabilityStatus, ReviewStatus reviewStatus, Auction auction) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.category = category;
		this.seller = seller;
		this.productDescription = productDescription;
		this.reviewStatus = reviewStatus;
		this.auction = auction;
	}

/*
 * Overloaded constructor	
 */
	public Product(String productName, String category, User seller, String productDescription) {
		super();
		this.productName = productName;
		this.category = category;
		
		this.seller = seller;
		this.productDescription = productDescription;
	}

	@Id
	@GeneratedValue
	@Column(name = "product_id")
	private int productId;
	
	@NotBlank(message = "Product name should not be empty")
	@Column(name = "product_name")
	private String productName;
	
	@NotBlank(message = "Product category should not be empty")
	private String category;
	
	
	
	@Valid
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "seller_id")
	private User seller;
	
	@NotBlank(message = "Product Description should not be empty")
	@Column(name = "product_description")
	private String productDescription;
	
	

	@Enumerated(EnumType.STRING)
	@Column(name = "review_status")
	private ReviewStatus reviewStatus;
	
	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "auction_id", nullable = true)
	private Auction auction;

/*
 * Getters and Setters	
 */
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public User getSeller() {
		return seller;
	}

	public void setSeller(User seller) {
		this.seller = seller;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	

	public ReviewStatus getReviewStatus() {
		return reviewStatus;
	}

	public void setReviewStatus(ReviewStatus reviewStatus) {
		this.reviewStatus = reviewStatus;
	}

	public Auction getAuction() {
		return auction;
	}

	public void setAuction(Auction auction) {
		this.auction = auction;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", category=" + category
				+ ", seller=" + seller + ", productDescription=" + productDescription
				+ ", availabilityStatus=" + ", reviewStatus=" + reviewStatus + ", auction="
				+ auction + "]";
	}
	
	

}
