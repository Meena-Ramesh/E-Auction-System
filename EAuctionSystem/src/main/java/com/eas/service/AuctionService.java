package com.eas.service;

import com.eas.entity.Auction;

public interface AuctionService {
	Auction initateAuction(Auction auction);

	double calculateMaxBidPrice(int productId);

	Auction getAuctionById(int auctionId);
}
