package com.eas.service;

import static org.assertj.core.api.Assertions.assertThat;
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
import com.eas.entity.Product;
import com.eas.entity.ReviewStatus;
import com.eas.entity.User;
import com.eas.entity.UserType;
import com.eas.exception.InvalidInputDataException;
import com.eas.repository.AuctionRepository;

@SpringBootTest
public class AuctionServiceJpaImplTest {

	@Autowired
	private AuctionService auctionService;

	@MockBean
	AuctionRepository auctionRepository;

	User user;
	Address address;
	BankDetails bankDetails;
	Product product;
	Auction auction;
	
	@Test
	void initiateAuctionTest() throws ParseException {
		user = new User();
		address = new Address();
		bankDetails = new BankDetails();
		product = new Product();

		bankDetails.setAccountHolderName("chris");
		bankDetails.setAccountNumber("12345784569765");
		bankDetails.setBankName("hdfc");
		bankDetails.setBranchName("mgpr");

		address.setDoorNumber("23");
		address.setStreetName("park road");
		address.setLocality("mogappair");
		address.setCity("Chennai");
		address.setState("TN");
		address.setCountry("India");
		address.setZip(600067);

		user.setFirstName("Chris");
		user.setPassword("california");
		user.setUserType(UserType.SELLER);
		user.setLastName("Brooke");
		user.setEmail("chris.brooke@gmail.com");
		user.setAadharNumber("123456787656");
		user.setContactNumber("1234567872");
		user.setAddress(address);
		user.setBankDetails(bankDetails);

		product.setProductName("pen");
		product.setCategory("stationary");
		product.setProductDescription("stationary");
		product.setSeller(user);
		product.setReviewStatus(ReviewStatus.APPROVED);

		Auction auction = new Auction();

		String startDate = "23-11-2020";
        String endDate = "27-11-2020";
       
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date start = sdf.parse(startDate);
        Date end = sdf.parse(endDate);
		auction.setAuctionId(10);
		auction.setBidWinner(0);
		auction.setMaxBidPrice(0.0);
		auction.setBasePrice(10.0);
		auction.setStartDate(start);
		auction.setEndDate(end);
		
		product.setAuction(auction);
		
		Mockito.when(auctionRepository.save(auction)).thenReturn(auction);
		assertSame(auctionService.initateAuction(auction), auction);
		
	}
	
	
	
	
}
