package com.eas.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.eas.entity.Product;
import com.eas.entity.ReviewStatus;
import com.eas.entity.User;
import com.eas.entity.UserType;
import com.eas.exception.DetectErrors;
import com.eas.exception.InvalidInputDataException;
import com.eas.service.ProductService;
import com.eas.service.UserService;

@RestController
@RequestMapping("eas/product")
public class ProductController {	

		@Autowired
		ProductService productService;
		
		@Autowired
		UserService userService;

		@PostMapping("/{userId}")
		public ResponseEntity<Product> addProduct(@PathVariable int userId,@Valid @RequestBody Product product, Errors errors) {
			
			DetectErrors.detectErrors(errors);
			
			
			User productSeller = userService.findUserById(userId).orElseThrow(() -> new InvalidInputDataException("Sorry! The seller doesn't exist!"));
			if(productSeller.getUserType()!=UserType.SELLER && productSeller.getUserType()!=UserType.BOTH) {
				throw new InvalidInputDataException("Sorry! You don't have access to add a product!");
			}
			product.setSeller(productSeller);
			product.setReviewStatus(ReviewStatus.PENDING);
			return new ResponseEntity<Product>(productService.addProduct(product), HttpStatus.CREATED);

		}
		
		@GetMapping("/{id}")
		public ResponseEntity<Product> getProductById(@PathVariable("id") int productId){
			Product product = productService.getProductById(productId).orElseThrow(() -> new InvalidInputDataException("Sorry! No Products Found with the given ID " + productId));
			return new ResponseEntity<Product>(product, HttpStatus.OK);
			
		}
		
		@PutMapping("")
		public ResponseEntity<Product> updateProduct(@Valid	@RequestBody Product product, Errors errors) throws InvalidInputDataException {
			
			DetectErrors.detectErrors(errors);
	        Product productToBeUpdated = productService.getProductById(product.getProductId()).orElseThrow(
					() -> new InvalidInputDataException("Sorry! No Products Found with the given ID " + product.getProductId()));
			productToBeUpdated.setProductName(product.getProductName());
			productToBeUpdated.setProductDescription(product.getProductDescription());
			Product updatedProduct = productService.updateProduct(productToBeUpdated);
			return new ResponseEntity<Product>(updatedProduct, HttpStatus.ACCEPTED);

		}
		
		@DeleteMapping("/{id}")
		public String deleteProduct(@PathVariable("id") int productId) {
			Product productToBeDeleted = productService.getProductById(productId).orElseThrow(()-> new InvalidInputDataException("Sorry! No Products Found with the given ID "+productId));
			productService.deleteProduct(productToBeDeleted);		
			return "Product Deleted";
			
		}
		
		@GetMapping("/seller/{id}")
		public ResponseEntity<List<Product>> getProductsOwnedByUser( @PathVariable("id") int userId){
			User productSeller = userService.findUserById(userId).orElseThrow(() -> new InvalidInputDataException("Sorry! The seller doesn't exist!"));
			
			List<Product> productList = new ArrayList<>();
			productList = productService.getProductsOwnedByUser(productSeller);
			if(productList.isEmpty()) {
				throw new InvalidInputDataException("There are no products to be shown");
			}
			return new ResponseEntity<List<Product>>(productList,HttpStatus.OK);
			
		}
		
		@GetMapping("/{productId}/{sellerId}")
		public ResponseEntity<String> validateSeller(@PathVariable int productId, @PathVariable int sellerId){
			User productSeller = userService.findUserById(sellerId).orElseThrow(() -> new InvalidInputDataException("Sorry! The seller doesn't exist!"));
			Product product = productService.getProductById(productId).orElseThrow(() -> new InvalidInputDataException("Sorry! No Products Found with the given ID " + productId));
			if(productService.validateSeller(productSeller, product)) {
				return new ResponseEntity<String>("The given user is the seller of this product", HttpStatus.OK);
			}
			else {
				return new ResponseEntity<String>("The given user is not the seller of this product", HttpStatus.OK);
			}
		}
		
		@GetMapping("/category")
		public ResponseEntity<List<String>> getAllCategory(){
			List<String> categoryList = productService.getAllCategory();
			if(categoryList.isEmpty())
				throw new InvalidInputDataException("There are no categories found!");
			return new ResponseEntity<List<String>>(categoryList, HttpStatus.OK);
		}
		
		@GetMapping("/category/{categoryName}")
		public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String categoryName){
			List<Product> productList = productService.getProductsByCategory(categoryName);
			if(productList.isEmpty())
				throw new InvalidInputDataException("There are no categories with this category name!");
			return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
		}
		
		@GetMapping("/pending")
		public ResponseEntity<List<Product>> getAllPendingProducts(){
			List<Product> pendingProductList = productService.getAllPendingProducts();
			if(pendingProductList.isEmpty())
				throw new InvalidInputDataException("There are no pending products");
			return new ResponseEntity<List<Product>>(pendingProductList, HttpStatus.OK);
		}
		
		@PutMapping("/review/{productId}/{status}")
		public ResponseEntity<Product> reviewProduct(@PathVariable int productId,@PathVariable String status){
			Product productToBeReviewed = productService.getProductById(productId).orElseThrow(()-> new InvalidInputDataException("Sorry! No Products Found with the given ID "+productId));
			 ReviewStatus reviewStatus;
			 if(status.equals("approved"))
				 reviewStatus = ReviewStatus.APPROVED;
			 else if(status.equals("rejected"))
				 reviewStatus = ReviewStatus.REJECTED;
			 else
				 throw new InvalidInputDataException("Invalid Review Status");
			 
			 return new ResponseEntity<Product>(productService.reviewProduct(productToBeReviewed, reviewStatus), HttpStatus.OK);
		}
		
		
	}



