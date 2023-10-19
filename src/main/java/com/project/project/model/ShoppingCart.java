package com.project.project.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
@Entity
@Table(name = "shopping_cart")
public class ShoppingCart {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "shopping_cart_id")
	    private Long id;
	    private int totalItems;
	    private double totalPrices;
	    @OneToOne(fetch = FetchType.EAGER)          
	   //cascade All for getting cust_id on shopping_cart .id;  --> shopping,cart - customer_id -> Save 
	    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
	    private Customer customer;

	    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cart")
	    private Set<CartItem> cartItem;

		public ShoppingCart() {
			this.cartItem = new HashSet<>();
	        this.totalItems = 0;
	        this.totalPrices = 0.0;
		}

		public ShoppingCart(Long id, int totalItems, double totalPrices, Customer customer, Set<CartItem> cartItem) {
			super();
			this.id = id;
			this.totalItems = totalItems;
			this.totalPrices = totalPrices;
			this.customer = customer;
			this.cartItem = cartItem;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public int getTotalItems() {
			return totalItems;
		}

		public void setTotalItems(int totalItems) {
			this.totalItems = totalItems;
		}

		public double getTotalPrices() {
			return totalPrices;
		}

		public void setTotalPrices(double totalPrices) {
			this.totalPrices = totalPrices;
		}

		public Customer getCustomer() {
			return customer;
		}

		public void setCustomer(Customer customer) {
			this.customer = customer;
		}

		public Set<CartItem> getCartItem() {
			return cartItem;
		}

		public void setCartItem(Set<CartItem> cartItem) {
			this.cartItem = cartItem;
		}
	    
}
