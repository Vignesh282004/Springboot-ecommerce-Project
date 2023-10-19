package com.project.project.service.serviceimpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.project.dto.ProductDto;
import com.project.project.model.CartItem;
import com.project.project.model.Customer;
import com.project.project.model.Product;
import com.project.project.model.ShoppingCart;
import com.project.project.repository.CartItemRepo;
import com.project.project.repository.ShoppingCartRepo;
import com.project.project.service.ShoppingCartService;
@Service
public class ShoppingCartservimpl implements ShoppingCartService {

	@Autowired
	private ShoppingCartRepo shoppingCartRepo;
	
	@Autowired
	private CartItemRepo cartItemRepo;
	
	
	
	 private CartItem findCartItem(Set<CartItem> cartItems, Long productId) {
	        if (cartItems == null) {
	            return null;
	        }
	        CartItem cartItem = null;

	        for (CartItem item : cartItems) {
	            if (item.getProduct().getId() == productId) {
	                cartItem = item;
	            }
	        }
	        return cartItem;
	    }
	 
	 private int totalItems(Set<CartItem> cartItems){
	        int totalItems = 0;
	        for(CartItem item : cartItems){
	            totalItems += item.getQuantity();
	        }
	        return totalItems;
	    }

	    private double totalPrice(Set<CartItem> cartItems){
	        double totalPrice = 0.0;

	        for(CartItem item : cartItems){
	            totalPrice += item.getTotalPrice();
	        }

	        return totalPrice;
	    }

	    
		@Override
		public ShoppingCart updateItemInCart(Product product, int quantity, Customer customer) {
			ShoppingCart cart = customer.getShoppingCart();

	        Set<CartItem> cartItems = cart.getCartItem();

	        CartItem item = findCartItem(cartItems, product.getId());

	        item.setQuantity(quantity);
	        item.setTotalPrice(quantity * product.getCostprice());

	        cartItemRepo.save(item);

	        int totalItems = totalItems(cartItems);
	        double totalPrice = totalPrice(cartItems);

	        cart.setTotalItems(totalItems);
	        cart.setTotalPrices(totalPrice);

	        return shoppingCartRepo.save(cart);
		}

		@Override
		public ShoppingCart deleteItemFromCart(Product product, Customer customer) {
			ShoppingCart cart = customer.getShoppingCart();
			try {
	        
				Set<CartItem> cartItems = cart.getCartItem();
		        CartItem item = findCartItem(cartItems, product.getId());
		        cartItems.remove(item);
		        cartItemRepo.delete(item);
		        
		        double totalPrice = totalPrice(cartItems);
		        int totalItems = totalItems(cartItems);

		        cart.setCartItem(cartItems);
		        cart.setTotalItems(totalItems);
		        cart.setTotalPrices(totalPrice);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e);
				e.printStackTrace();
			}

	        return shoppingCartRepo.save(cart);

		}

		@Override
		public void deleteCartById(Long id) {
			ShoppingCart shoppingCart = shoppingCartRepo.getById(id);
	        for (CartItem cartItem : shoppingCart.getCartItem()) {
	            cartItemRepo.deleteById(cartItem.getId());
	        }
	        shoppingCart.setCustomer(null);
	        shoppingCart.getCartItem().clear();
	        shoppingCart.setTotalPrices(0);
	        shoppingCart.setTotalItems(0);
	        shoppingCartRepo.save(shoppingCart);
			
		}
		

		
	
	    private Product transfer(ProductDto productDto) {
	        Product product = new Product();
	        product.setId(productDto.getId());
	        product.setName(productDto.getName());
	        product.setCurrentQuantity(productDto.getCurrentQuantity());
	        product.setCostprice(productDto.getCostprice());
	        product.setSaleprice(productDto.getSaleprice());
	        product.setDecription(productDto.getDecription());
	        product.setImage(productDto.getImage());
	        product.setIs_activated(productDto.isActivated());
	        product.setIs_deleted(productDto.isDeleted());
	        product.setCategory(productDto.getCategory());
	        return product;
	    }
		

		@Override
		public ShoppingCart addItemToCart(Product product, int quantity, Customer customer) {
			  ShoppingCart cart = customer.getShoppingCart();

		        if (cart == null) {
		            cart = new ShoppingCart();
		        }
		        Set<CartItem> cartItems = cart.getCartItem();
		        CartItem cartItem = findCartItem(cartItems, product.getId());
		        if (cartItems == null) {
		            cartItems = new HashSet<>();
		            if (cartItem == null) {
		                cartItem = new CartItem();
		                cartItem.setProduct(product);
		                cartItem.setTotalPrice(quantity * product.getCostprice());
		                cartItem.setQuantity(quantity);
		                cartItem.setCart(cart);
		                cartItems.add(cartItem);
		                cartItemRepo.save(cartItem);
		            }
		        } else {
		            if (cartItem == null) {
		                cartItem = new CartItem();
		                cartItem.setProduct(product);
		                cartItem.setTotalPrice(quantity * product.getCostprice());
		                cartItem.setQuantity(quantity);
		                cartItem.setCart(cart);
		                cartItems.add(cartItem);
		                cartItemRepo.save(cartItem);
		            } else {
		                cartItem.setQuantity(cartItem.getQuantity() + quantity);
		                cartItem.setTotalPrice(cartItem.getTotalPrice() + ( quantity * product.getCostprice()));
		                cartItemRepo.save(cartItem);
		            }
		        }
		        cart.setCartItem(cartItems);

		        int totalItems = totalItems(cart.getCartItem());
		        double totalPrice = totalPrice(cart.getCartItem());

		        cart.setTotalPrices(totalPrice);
		        cart.setTotalItems(totalItems);
		        cart.setCustomer(customer);

		        return shoppingCartRepo.save(cart);
		}

		

		}

