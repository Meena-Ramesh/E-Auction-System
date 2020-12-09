package com.eas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.eas.entity.Auction;
import com.eas.entity.Bid;


public interface BidRepository extends JpaRepository<Bid, Integer>{

	List<Bid> findByAuction(Auction auction);

	@Query("SELECT bid FROM Bid bid WHERE bid.auction.auctionId = :auctionId AND bid.buyer.userId = :userId")
	List<Bid> getBidsByUser(@Param("auctionId") int auctionId, @Param("userId") int userId);

}
