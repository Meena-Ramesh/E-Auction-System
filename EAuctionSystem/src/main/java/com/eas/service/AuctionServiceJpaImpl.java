package com.eas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eas.entity.Auction;
import com.eas.entity.Bid;
import com.eas.entity.Product;
import com.eas.exception.InvalidInputDataException;
import com.eas.repository.AuctionRepository;
import com.eas.repository.BidRepository;
import com.eas.repository.ProductRepository;

@Service
public class AuctionServiceJpaImpl implements AuctionService {

	@Autowired
	private AuctionRepository auctionRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private BidRepository bidRepository;

	@Override
	public Auction initateAuction(Auction auction) {
		return auctionRepository.save(auction);
	}

	@Override
	public double calculateMaxBidPrice(int productId) {

		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new InvalidInputDataException("Invalid product id"));
		Auction productAuction = product.getAuction();
		if(productAuction == null) {
			throw new InvalidInputDataException("There is no auction for the product yet");
		}
		List<Bid> bidList = bidRepository.findByAuction(productAuction);
		if (bidList.isEmpty()) {
			return productAuction.getBasePrice();
		}

		Bid highestBid = bidList.stream().max((bid1, bid2) -> Double.compare(bid1.getBidPrice(), bid2.getBidPrice()))
				.get();
		Auction auction = highestBid.getAuction();

		Auction auctionToBeUpdated = auctionRepository.findById(auction.getAuctionId()).orElse(null);
		auctionToBeUpdated.setMaxBidPrice(highestBid.getBidPrice());
		auctionToBeUpdated.setBidWinner(highestBid.getBuyer().getUserId());
		auctionRepository.save(auctionToBeUpdated);

		return highestBid.getBidPrice();

	}

	@Override
	public Auction getAuctionById(int auctionId) {

		return auctionRepository.findById(auctionId)
				.orElseThrow(() -> new InvalidInputDataException("Auction id is invalid"));
	}

	@Override
	public List<Auction> getAllAuctions() {
		List<Auction> auctionList = auctionRepository.findAll();
		if(auctionList.isEmpty()) 
			throw new InvalidInputDataException("No auction to be displayed");
		else
			return auctionList;
	}

}
