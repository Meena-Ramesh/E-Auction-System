package com.eas.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eas.entity.Auction;
import com.eas.entity.Bid;
import com.eas.entity.Product;
import com.eas.entity.User;
import com.eas.entity.UserType;
import com.eas.exception.InvalidInputDataException;
import com.eas.service.AuctionService;
import com.eas.service.BidService;
import com.eas.service.ProductService;
import com.eas.service.UserService;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("eas/bid")
public class BidController {

	@Autowired
	BidService bidService;

	@Autowired
	UserService userService;

	@Autowired
	AuctionService auctionService;

	@Autowired
	ProductService productService;

	@PostMapping("")
	public ResponseEntity<Bid> createBid(@RequestBody Bid bid) {
		Auction auction = auctionService.getAuctionById(bid.getAuction().getAuctionId());
		bid.setAuction(auction);
		if (auction == null)
			throw new InvalidInputDataException("There is no auction for this product yet");
		Date date = new Date();
		if (date.compareTo(auction.getStartDate()) < 0)
			throw new InvalidInputDataException("Auction has not yet started");
		if (date.compareTo(auction.getEndDate()) > 0)
			throw new InvalidInputDataException("Auction has already ended");
		if (bid.getBidPrice() < bid.getAuction().getBasePrice()) {
			throw new InvalidInputDataException("The bidding price must be greater than the base price");
		}
		User bidder = userService.findUserById(bid.getBuyer().getUserId())
				.orElseThrow(() -> new InvalidInputDataException("The user doesn't exist"));
		if (bidder.getUserType() != UserType.BUYER && bidder.getUserType() != UserType.BOTH)
			throw new InvalidInputDataException("You don't have access to bid on this product");
		bid.setBuyer(bidder);
		Bid addedBid = bidService.createBid(bid);
		Product biddingProduct = productService.getProductByAuction(auction);
		auctionService.calculateMaxBidPrice(biddingProduct.getProductId());
		return new ResponseEntity<Bid>(addedBid, HttpStatus.CREATED);
	}
	
	@GetMapping("/{auctionId}")
	public ResponseEntity<List<Bid>> getBidsByAuction(@PathVariable int auctionId){
		Auction auction = auctionService.getAuctionById(auctionId);
		List<Bid> bidList = bidService.getBidsByAuction(auction);
		if(bidList.isEmpty())
			throw new InvalidInputDataException("There are no bids for this product yet");
		return new ResponseEntity<List<Bid>>(bidList, HttpStatus.OK);
	}
	
	@DeleteMapping("/{userId}/{auctionId}")
	public ResponseEntity<String> removeAllBid(@PathVariable int userId, @PathVariable int auctionId){
		User buyer = userService.findUserById(userId).orElseThrow(()-> new InvalidInputDataException("Invalid user id"));
		Auction auction = auctionService.getAuctionById(auctionId);
		bidService.removeAllBid(auction, buyer);
		auctionService.calculateMaxBidPrice(productService.getProductByAuction(auction).getProductId());
		return new ResponseEntity<String>("Bids deleted successfully", HttpStatus.OK);
	}

}
