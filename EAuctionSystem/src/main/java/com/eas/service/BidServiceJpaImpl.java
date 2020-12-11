package com.eas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eas.entity.Auction;
import com.eas.entity.Bid;
import com.eas.entity.User;
import com.eas.exception.BidNotFoundException;
import com.eas.repository.AuctionRepository;
import com.eas.repository.BidRepository;
import com.eas.repository.ProductRepository;

import ch.qos.logback.classic.spi.PackagingDataCalculator;

@Service
public class BidServiceJpaImpl implements BidService {

	@Autowired
	private BidRepository bidRepository;
	
	@Autowired
	private AuctionRepository auctionRepository;
	
	@Autowired
	private ProductRepository productRepository;
	@Override
	public List<Bid> getBidsByAuction(Auction auction) {
		return bidRepository.findByAuction(auction);
	}

	@Override
	public void removeAllBid(Auction auction, User user) {

		List<Bid> bidList = bidRepository.getBidsByUser(auction.getAuctionId(), user.getUserId());
		if (bidList.isEmpty()) {
			throw new BidNotFoundException("You haven't made any bids for this auction yet");
		}
		else
		  bidRepository.deleteAll(bidList);

	}

	@Override
	public Bid createBid(Bid bid) {
		return bidRepository.save(bid);
	}

}
