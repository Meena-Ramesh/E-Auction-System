package com.eas.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.eas.repository.ProductRepository;

@SpringBootTest
public class ProductServiceJpaImplTest {
	@Autowired
	private ProductService productService;

	@MockBean
	ProductRepository productRepository;

	User user;
	Address address;
	BankDetails bankDetails;
	Product product;

	@Test
	void contextLoads() {
	}

	@Test
	void addProductTest() {
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
		product.setReviewStatus(ReviewStatus.PENDING);

		Mockito.when(productRepository.save(product)).thenReturn(product);
		assertSame(productService.addProduct(product), product);
	}

	@Test
	void validateSellerTest() {
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
		product.setReviewStatus(ReviewStatus.PENDING);

		assertTrue(productService.validateSeller(user, product));
	}

	@Test
	void getProductByIdTest() {
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

		product.setProductId(10);
		product.setProductName("pen");
		product.setCategory("stationary");
		product.setProductDescription("stationary");
		product.setSeller(user);
		product.setReviewStatus(ReviewStatus.PENDING);

		assertThat(productService.getProductById(10)).isEqualTo(productRepository.findById(10));

	}

	@Test
	void updateProductTest() {
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
		product.setReviewStatus(ReviewStatus.PENDING);

		Mockito.when(productRepository.save(product)).thenReturn(product);
		product.setProductName("Pencil");
		assertSame(productService.updateProduct(product), product);

	}

	@Test
	void getProductsOwnedByUserTest() {
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
		product.setReviewStatus(ReviewStatus.PENDING);

		Product product1 = new Product();

		product1.setProductName("pencil");
		product1.setCategory("stationary");
		product1.setProductDescription("stationary");
		product1.setSeller(user);
		product1.setReviewStatus(ReviewStatus.PENDING);

		List<Product> productList = new ArrayList<>();
		productList.add(product1);
		productList.add(product);

		Mockito.when(productRepository.findBySeller(user)).thenReturn(productList);
		assertThat(productService.getProductsOwnedByUser(user)).isEqualTo(productList);

	}

	@Test
	void getAllCategoryTest() {
		List<String> expectedList = new ArrayList<>();
		expectedList.add("Kitchen ware");
		expectedList.add("stationary");

		Mockito.when(productRepository.getAllCategory()).thenReturn(expectedList);
		assertThat(productService.getAllCategory()).isEqualTo(expectedList);
	}

	@Test
	void getProductsByCategoryTest() {
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

		product.setProductName("Cutlery");
		product.setCategory("Kitchen");
		product.setProductDescription("Kitchen Items");
		product.setSeller(user);
		product.setReviewStatus(ReviewStatus.PENDING);

		Product product1 = new Product();

		product1.setProductName("Utensils");
		product1.setCategory("Kitchen");
		product1.setProductDescription("Strong");
		product1.setSeller(user);
		product1.setReviewStatus(ReviewStatus.PENDING);

		List<Product> expectedList = new ArrayList<>();
		expectedList.add(product);
		expectedList.add(product1);

		Mockito.when(productRepository.findByCategory("Kitchen")).thenReturn(expectedList);
		assertThat(productService.getProductsByCategory("Kitchen")).isEqualTo(expectedList);
	}

	@Test
	void getAllPendingProductsTest() {
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

		product.setProductName("Cutlery");
		product.setCategory("Kitchen");
		product.setProductDescription("Kitchen Items");
		product.setSeller(user);
		product.setReviewStatus(ReviewStatus.PENDING);

		Product product1 = new Product();

		product1.setProductName("Utensils");
		product1.setCategory("Kitchen");
		product1.setProductDescription("Strong");
		product1.setSeller(user);
		product1.setReviewStatus(ReviewStatus.PENDING);

		List<Product> expectedList = new ArrayList<>();
		expectedList.add(product);
		expectedList.add(product1);

		Mockito.when(productRepository.getAllPendingProducts()).thenReturn(expectedList);
		assertThat(productService.getAllPendingProducts()).isEqualTo(expectedList);
	}

	@Test
	void reviewProductTest() {
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
		product.setReviewStatus(ReviewStatus.PENDING);

		Mockito.when(productRepository.save(product)).thenReturn(product);
		Product reviewdProduct = productService.reviewProduct(product, ReviewStatus.APPROVED);
		assertSame(reviewdProduct.getReviewStatus(), ReviewStatus.APPROVED);

	}

	@Test
	void getProductByAuctionTest() throws ParseException {
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
		
		Mockito.when(productRepository.findByAuction(auction)).thenReturn(product);
		assertSame(productService.getProductByAuction(auction), product);
		
		
	}

}
