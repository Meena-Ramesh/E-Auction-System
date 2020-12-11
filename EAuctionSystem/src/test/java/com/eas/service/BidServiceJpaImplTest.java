package com.eas.service;

import static org.junit.jupiter.api.Assertions.assertSame;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.eas.entity.Address;
import com.eas.entity.Auction;
import com.eas.entity.BankDetails;
import com.eas.entity.Bid;
import com.eas.entity.Product;
import com.eas.entity.ReviewStatus;
import com.eas.entity.User;
import com.eas.entity.UserType;
import com.eas.repository.BidRepository;

@SpringBootTest
public class BidServiceJpaImplTest {

	@Autowired
	BidService bidService;

	@MockBean
	BidRepository bidRepository;
	
	User user;
	Address address;
	BankDetails bankDetails;
	Product product;
	Auction auction;
	Bid bid;

	@Test
	void createBidTest() throws ParseException {
		user = new User();
		address = new Address();
		product = new Product();
		bankDetails = new BankDetails();

		bankDetails.setAccountHolderName("ABC");
		bankDetails.setAccountNumber("12345789765");
		bankDetails.setBankName("ICICI");
		bankDetails.setBranchName("mgpr");

		address.setDoorNumber("23");
		address.setStreetName("park road");
		address.setLocality("mogappair");
		address.setCity("Trichy");
		address.setState("TN");
		address.setZip(600067);

		user.setFirstName("ABC");
		user.setLastName("Brooke");
		user.setPassword("california");
		user.setUserType(UserType.SELLER);
		user.setEmail("chris.brooke@gmail.com");
		user.setAadharNumber("123456787656");
		user.setContactNumber("1234567872");
		user.setAddress(address);
		user.setBankDetails(bankDetails);

		User buyer = new User();
		Address buyerAddress = new Address();
		BankDetails buyerBank = new BankDetails();

		buyerBank.setAccountHolderName("ABC");
		buyerBank.setAccountNumber("12345789765");
		buyerBank.setBankName("ICICI");
		buyerBank.setBranchName("mgpr");

		buyerAddress.setDoorNumber("23");
		buyerAddress.setStreetName("park road");
		buyerAddress.setLocality("mogappair");
		buyerAddress.setCity("Trichy");
		buyerAddress.setState("TN");
		buyerAddress.setZip(600067);

		buyer.setFirstName("BUYER");
		buyer.setLastName("Brooke");
		buyer.setPassword("california");
		buyer.setUserType(UserType.BUYER);
		buyer.setEmail("chris.brooke@gmail.com");
		buyer.setAadharNumber("123456787656");
		buyer.setContactNumber("1234567872");
		buyer.setAddress(buyerAddress);
		buyer.setBankDetails(buyerBank);

		product.setProductName("Mobile");
		product.setCategory("stationary");
		product.setProductDescription("stationary");
		product.setSeller(user);
		product.setReviewStatus(ReviewStatus.APPROVED);

		Auction newAuction = new Auction();

		String startDate = "23-11-2020";
		String endDate = "27-11-2020";

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date start = sdf.parse(startDate);
		Date end = sdf.parse(endDate);

		newAuction.setBidWinner(0);
		newAuction.setMaxBidPrice(0.0);
		newAuction.setStartDate(start);
		newAuction.setEndDate(end);
		newAuction.setBasePrice(10.0);

		bid = new Bid();
		bid.setAuction(newAuction);
		bid.setBidPrice(17.0);
		bid.setBuyer(buyer);

		Mockito.when(bidRepository.save(bid)).thenReturn(bid);
		assertSame(bidService.createBid(bid), bid);
	}

}
