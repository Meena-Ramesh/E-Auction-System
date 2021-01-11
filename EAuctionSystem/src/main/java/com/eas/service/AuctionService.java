package com.eas.service;

import java.util.List;

import com.eas.entity.Auction;

public interface AuctionService {
	Auction initateAuction(Auction auction);

	double calculateMaxBidPrice(int productId);

	Auction getAuctionById(int auctionId);

	List<Auction> getAllAuctions();
}
