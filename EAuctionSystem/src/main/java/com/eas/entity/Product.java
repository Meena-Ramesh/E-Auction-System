package com.eas.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Vismitha
 * This product entity is persisted into the database by entityManager
 */
@Entity
@Table(name = "product")
public class Product implements Serializable {
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
			String productDescription, boolean availabilityStatus, ReviewStatus reviewStatus, Auction auction, String productImage) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.category = category;
		this.seller = seller;
		this.productDescription = productDescription;
		this.reviewStatus = reviewStatus;
		this.auction = auction;
		this.productImage = productImage;
	}

/*
 * Overloaded constructor	
 */
	public Product(String productName, String category, User seller, String productDescription, String productImage) {
		super();
		this.productName = productName;
		this.category = category;
		this.productImage = productImage;
		this.seller = seller;
		this.productDescription = productDescription;
	}

	@Id
	@GeneratedValue
	@Column(name = "product_id")
	private int productId;
	
	@Column(name = "product_name")
	private String productName;
	
	private String category;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "seller_id")
	private User seller;
	
	@Column(name = "product_description")
	private String productDescription;

	@Enumerated(EnumType.STRING)
	@Column(name = "review_status")
	private ReviewStatus reviewStatus;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "auction_id", nullable = true)
	private Auction auction;
	
	@Column(name = "product_image")
	private String productImage;

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

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", category=" + category
				+ ", seller=" + seller + ", productDescription=" + productDescription
				+ ", availabilityStatus=" + ", reviewStatus=" + reviewStatus + ", auction="
				+ auction + "]";
	}
	
	

}
