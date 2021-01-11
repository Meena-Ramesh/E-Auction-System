package com.eas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eas.entity.Auction;
import com.eas.entity.Product;
import com.eas.entity.ReviewStatus;
import com.eas.entity.User;
import com.eas.exception.InvalidInputDataException;
import com.eas.repository.ProductRepository;

@Service
public class ProductServiceJpaImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product addProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public boolean validateSeller(User seller, Product product) {
		User actualSeller = product.getSeller();
		if (actualSeller.getUserId() == seller.getUserId())
			return true;
		return false;
	}

	@Override
	public Optional<Product> getProductById(int productId) {
		return productRepository.findById(productId);
	}

	@Override
	public Product updateProduct(Product productToBeUpdated) {
		return productRepository.save(productToBeUpdated);
	}

	@Override
	public void deleteProduct(Product productToBeDeleted) {
		productRepository.delete(productToBeDeleted);
	}

	@Override
	public List<Product> getProductsOwnedByUser(User user) {
		return productRepository.findBySeller(user);
	}

	@Override
	public List<String> getAllCategory() {
		return productRepository.getAllCategory();
	}

	@Override
	public List<Product> getProductsByCategory(String selectedCategory) {
		return productRepository.findByCategory(selectedCategory);
	}

	@Override
	public List<Product> getAllPendingProducts() {
		return productRepository.getAllPendingProducts();
	}

	@Override
	public Product reviewProduct(Product product, ReviewStatus reviewStatus) {
		product.setReviewStatus(reviewStatus);
		Product reviewedProduct = productRepository.save(product);
		return reviewedProduct;

	}

	@Override
	public Product getProductByAuction(Auction auction) {
		return productRepository.findByAuction(auction);
	}

	@Override
	public List<Product> getAllApprovedProducts() {
		List<Product> productList = productRepository.getAllApprovedProducts();
		if(productList.isEmpty()) {
			throw new InvalidInputDataException("No approved products to be displayed yet");
		}
		return productList;
		
	}

}
