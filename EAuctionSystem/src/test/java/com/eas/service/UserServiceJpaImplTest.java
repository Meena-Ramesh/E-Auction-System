package com.eas.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.eas.entity.Address;
import com.eas.entity.BankDetails;
import com.eas.entity.User;
import com.eas.entity.UserType;
import com.eas.repository.UserRepository;

@SpringBootTest
public class UserServiceJpaImplTest {

	@Autowired
	private UserService userService;

	@MockBean 
	UserRepository userRepository;
	
	User user;
	Address address;
	BankDetails bankDetails;

	@Test
	void contextLoads() {
	}

	@Test
	void createAccountTest() {
	user = new User();
	address = new Address();
	bankDetails = new BankDetails();
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
	
	Mockito.when(userRepository.save(user)).thenReturn(user);
	assertThat(userService.createAccount(user)).isEqualTo(user);

	}

	@Test
	void findUserByIdTest() {
	user = new User();
	address = new Address();
	bankDetails = new BankDetails();
	bankDetails.setAccountHolderName("chris");
	bankDetails.setAccountNumber("12345785679765");
	bankDetails.setBankName("hdfc");
	bankDetails.setBranchName("mgpr");
	address.setDoorNumber("23");
	address.setStreetName("park road");
	address.setLocality("mogappair");
	address.setCity("Chennai");
	address.setCountry("India");
	address.setState("TN");
	address.setZip(600067);
	user.setFirstName("Chris");
	user.setPassword("california");
	user.setUserType(UserType.SELLER);
	user.setLastName("Brooke");
	user.setEmail("chris.brooke@gmail.com");
	user.setPassword("california");
	user.setAadharNumber("123456787656");
	user.setContactNumber("1234567872");
	user.setAddress(address);
	user.setBankDetails(bankDetails);
	user.setUserId(12);
	assertThat(userService.findUserById(12)).isEqualTo(userRepository.findById(12));
	
	}

}
