package com.eas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eas.entity.Auction;
import com.eas.entity.Product;
import com.eas.entity.ReviewStatus;
import com.eas.exception.InvalidInputDataException;
import com.eas.service.AuctionService;
import com.eas.service.BidService;
import com.eas.service.ProductService;
import com.eas.service.UserService;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("eas/auction")
public class AuctionController {
	
	@Autowired
	AuctionService auctionService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	UserService uservice;
	
	@Autowired
	BidService bidService;
	
	@PostMapping("/{productId}")
	public ResponseEntity<Auction> initiateAuction(@PathVariable int productId,@RequestBody Auction auction){
		Product auctionProduct= productService.getProductById(productId).orElseThrow(()->new InvalidInputDataException("The product does not exist"));
		Double basePrice = auction.getBasePrice();
		if (basePrice <= 0.0) {
			throw new InvalidInputDataException("Base price should not be less than 0");
		}
		if(auctionProduct.getAuction()!=null)
			throw new InvalidInputDataException("The product is already in auction");
		if(auctionProduct.getReviewStatus()!=ReviewStatus.APPROVED)
			throw new InvalidInputDataException("The product is not approved");
		Auction initiatedAuction = auctionService.initateAuction(auction);
		auctionProduct.setAuction(initiatedAuction);
		productService.updateProduct(auctionProduct);
		return new ResponseEntity<Auction>( initiatedAuction , HttpStatus.CREATED);
	}
	
	@GetMapping("/maxprice/{productId}")
	public ResponseEntity<Double> calculateMaxBidPrice(@PathVariable int productId){
		return new ResponseEntity<Double>(auctionService.calculateMaxBidPrice(productId), HttpStatus.OK);
	}
	
	@GetMapping("/{auctionId}")
	public ResponseEntity<Auction> getAuctionById(@PathVariable int auctionId){
		return new ResponseEntity<Auction>(auctionService.getAuctionById(auctionId), HttpStatus.OK);
	}
	
	@GetMapping("")
	public ResponseEntity<List<Auction>> getAllAuctions(){
		return new ResponseEntity<List<Auction>>(auctionService.getAllAuctions(), HttpStatus.OK);
	}
	
	
	

}
