package com.project.project.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cart_item")
public class CartItem {

	   @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "order_detail_id")
	    private Long id;
	    private int quantity;
	    private double totalPrice;

	    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	    @JoinColumn(name = "shopping_cart_id", referencedColumnName = "shopping_cart_id")
	    private ShoppingCart cart;

	    @OneToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
	    private Product product;

		public CartItem() {
			super();
			// TODO Auto-generated constructor stub
		}

		public CartItem(Long id, int quantity, double totalPrice, ShoppingCart cart, Product product) {
			super();
			this.id = id;
			this.quantity = quantity;
			this.totalPrice = totalPrice;
			this.cart = cart;
			this.product = product;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public int getQuantity() {
			return quantity;
		}

		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}

		public double getTotalPrice() {
			return totalPrice;
		}

		public void setTotalPrice(double totalPrice) {
			this.totalPrice = totalPrice;
		}

		public ShoppingCart getCart() {
			return cart;
		}

		public void setCart(ShoppingCart cart) {
			this.cart = cart;
		}

		public Product getProduct() {
			return product;
		}

		public void setProduct(Product product) {
			this.product = product;
		}
	    
}
