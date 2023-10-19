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
@Table(name = "order_detail")
public class OrderDetail {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "order_detail_id")
	    private Long id;

	    private int quantity;
	    private double totalPrice;
	    private double unitPrice;

	    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
	    private Order order;

	    @OneToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
	    private Product product;

		public OrderDetail() {
			super();
			// TODO Auto-generated constructor stub
		}

		public OrderDetail(Long id, int quantity, double totalPrice, double unitPrice, Order order, Product product) {
			super();
			this.id = id;
			this.quantity = quantity;
			this.totalPrice = totalPrice;
			this.unitPrice = unitPrice;
			this.order = order;
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

		public double getUnitPrice() {
			return unitPrice;
		}

		public void setUnitPrice(double unitPrice) {
			this.unitPrice = unitPrice;
		}

		public Order getOrder() {
			return order;
		}

		public void setOrder(Order order) {
			this.order = order;
		}

		public Product getProduct() {
			return product;
		}

		public void setProduct(Product product) {
			this.product = product;
		}
	    
}
