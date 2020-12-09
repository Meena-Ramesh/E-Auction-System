package com.eas.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author madhu
 *
 */
@Entity
@Table(name = "auction")
public class Auction implements Serializable {
	
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
	
	public Auction(int auctionId,double basePrice, double maxBidPrice, Date startDate, Date endDate, User bidWinner) {
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
	
	public Auction( Date startDate, Date endDate, AuctionStatus auctionStatus) {
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
	private User bidWinner;


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

	public User getBidWinner() {
		return bidWinner;
	}

	public void setBidWinner(User bidWinner) {
		this.bidWinner = bidWinner;
	}
	
	

}