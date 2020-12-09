package com.eas.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author chitra
 * This Bid entity is persisted into the database by the entityManager
 */
@Entity
@Table(name = "bid")
public class Bid implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public Bid() {

	}

/*
 * @param bidId the auto-generated id in the database for each bid 
 * @param auction the auction entity
 * @param bidPrice the price bid by the buyer
 * @param buyer the User who makes a bid	
 */
	
	public Bid(int bidId, Auction auction, double bidPrice, User buyer) {
		super();
		this.bidId = bidId;
		this.auction = auction;
		this.bidPrice = bidPrice;
		this.buyer = buyer;
	}
/*
 * Overloaded constructor
 */
	
	public Bid(Auction auction, double bidPrice, User buyer) {
		super();
		this.auction = auction;
		this.bidPrice = bidPrice;
		this.buyer = buyer;
	}

	@Id
	@GeneratedValue
	@Column(name = "bid_id")
	private int bidId;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "auction_id")
	private Auction auction;

	@Column(name = "bid_price")
	private double bidPrice;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "user_id")
	private User buyer;
	
/*
 * Getters and setters	
 */

	public int getBidId() {
		return bidId;
	}

	public Auction getAuction() {
		return auction;
	}

	public void setAuction(Auction auction) {
		this.auction = auction;
	}

	public double getBidPrice() {
		return bidPrice;
	}

	public void setBidPrice(double bidPrice) {
		this.bidPrice = bidPrice;
	}

	public User getBuyer() {
		return buyer;
	}

	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}
	
	

}
