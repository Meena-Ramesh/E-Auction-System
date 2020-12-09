package com.eas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eas.entity.Auction;

public interface AuctionRepository extends JpaRepository<Auction, Integer> {

}
