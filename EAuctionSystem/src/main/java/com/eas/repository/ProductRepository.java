package com.eas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.eas.entity.Auction;
import com.eas.entity.Product;
import com.eas.entity.User;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	List<Product> findBySeller(User user);

	@Query("Select DISTINCT(product.category) FROM Product product")
	List<String> getAllCategory();

	List<Product> findByCategory(String selectedCategory);

	@Query("Select product FROM Product product WHERE product.reviewStatus = 'PENDING'")
	List<Product> getAllPendingProducts();

	Product findByAuction(Auction auction);

	@Query("Select product FROM Product product WHERE product.reviewStatus = 'APPROVED'")
	List<Product> getAllApprovedProducts();

}
