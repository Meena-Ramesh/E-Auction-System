package com.eas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.eas.entity.Auction;
import com.eas.entity.Product;
import com.eas.entity.ReviewStatus;
import com.eas.entity.User;

public interface ProductService {
	Product addProduct(Product product);

	boolean validateSeller(User seller, Product product);

	Optional<Product> getProductById(int productId);

	Product updateProduct(Product productToBeUpdated);

	void deleteProduct(Product productToBeDeleted);

	List<Product> getProductsOwnedByUser(User user);

	List<String> getAllCategory();

	List<Product> getProductsByCategory(String selectedCategory);

	List<Product> getAllPendingProducts();

	Product reviewProduct(Product product, ReviewStatus reviewStatus);

	Product getProductByAuction(Auction auction);

	List<Product> getAllApprovedProducts();
}
