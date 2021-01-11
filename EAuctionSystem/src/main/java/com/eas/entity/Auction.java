package com.eas.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author madhu
 *
 */
@Entity
@Table(name = "auction")
public class Auction implements Serializable {
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/*
 * Default constructor	
 */
	public Auction() {

	}

/*
 * @param auctionId auto-generated in the database
 * @param product entity
 * @param maxBidPrice  the maximum bid price of all the bids for a product
 * @param startDate the starting date of the auction
 * @param endDate the ending date of the auction
 * @bidWinner the User with the highest bid
 * @param auctionStatus the status of the auction.If the auction is over, it returns completed
 */
	
	public Auction(int auctionId,double basePrice, double maxBidPrice, Date startDate, Date endDate, int bidWinner) {
		super();
		this.auctionId = auctionId;
		this.basePrice = basePrice;
		this.maxBidPrice = maxBidPrice;
		this.startDate = startDate;
		this.endDate = endDate;
		this.bidWinner = bidWinner;
		
	}

/*
 * @param product the product entity
 */
	
	public Auction( Date startDate, Date endDate) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		
	}

	@Id
	@GeneratedValue
	@Column(name = "auction_id")
	private int auctionId;

	@Column(name = "base_price")
	private double basePrice;
	
	@Column(name = "max_bid_price")
	private double maxBidPrice;
	
	@Column(name = "start_date")
	private Date startDate;
	
	@Column(name = "end_date")
	private Date endDate;
	
	@Column(name = "bid_winner")
	private int bidWinner;


/*
 * Getters and Setters	
 */
	
	

	public int getAuctionId() {
		return auctionId;
	}

	public void setAuctionId(int auctionId) {
		this.auctionId = auctionId;
	}

	public double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

	public double getMaxBidPrice() {
		return maxBidPrice;
	}

	public void setMaxBidPrice(double maxBidPrice) {
		this.maxBidPrice = maxBidPrice;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getBidWinner() {
		return bidWinner;
	}

	public void setBidWinner(int bidWinner) {
		this.bidWinner = bidWinner;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + auctionId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Auction other = (Auction) obj;
		if (auctionId != other.auctionId)
			return false;
		return true;
	}

	

	
	

}