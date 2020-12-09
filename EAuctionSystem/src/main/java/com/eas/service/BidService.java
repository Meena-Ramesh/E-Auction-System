package com.eas.service;

import java.util.List;

import com.eas.entity.Auction;
import com.eas.entity.Bid;
import com.eas.entity.User;

public interface BidService {
	List<Bid> getBidsByAuction(Auction auction);

	void removeAllBid(Auction auction, User user);

	Bid createBid(Bid bid);
}
