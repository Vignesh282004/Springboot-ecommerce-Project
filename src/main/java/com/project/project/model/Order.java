package com.project.project.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Entity
@Table(name = "orders")
public class Order {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "order_id")
	    private Long id;
	    private Date orderDate;
	    private Date deliveryDate;
	    private double totalPrice;
	    private double shippingFee;
	    private String orderStatus;
	    private String notes;
	    private double tax;
	    private int quantity;
	    private String paymentMethod;
	    private boolean isAccept;
	    
	    @ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
	    private Customer customer;

	    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
	    private List<OrderDetail> orderDetailList;

		public Order() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Order(Long id, Date orderDate, Date deliveryDate, double totalPrice, double shippingFee,
				String orderStatus, String notes, double tax, int quantity, String paymentMethod, boolean isAccept,
				Customer customer, List<OrderDetail> orderDetailList) {
			super();
			this.id = id;
			this.orderDate = orderDate;
			this.deliveryDate = deliveryDate;
			this.totalPrice = totalPrice;
			this.shippingFee = shippingFee;
			this.orderStatus = orderStatus;
			this.notes = notes;
			this.tax = tax;
			this.quantity = quantity;
			this.paymentMethod = paymentMethod;
			this.isAccept = isAccept;
			this.customer = customer;
			this.orderDetailList = orderDetailList;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Date getOrderDate() {
			return orderDate;
		}

		public void setOrderDate(Date orderDate) {
			this.orderDate = orderDate;
		}

		public Date getDeliveryDate() {
			return deliveryDate;
		}

		public void setDeliveryDate(Date deliveryDate) {
			this.deliveryDate = deliveryDate;
		}

		public double getTotalPrice() {
			return totalPrice;
		}

		public void setTotalPrice(double totalPrice) {
			this.totalPrice = totalPrice;
		}

		public double getShippingFee() {
			return shippingFee;
		}

		public void setShippingFee(double shippingFee) {
			this.shippingFee = shippingFee;
		}

		public String getOrderStatus() {
			return orderStatus;
		}

		public void setOrderStatus(String orderStatus) {
			this.orderStatus = orderStatus;
		}

		public String getNotes() {
			return notes;
		}

		public void setNotes(String notes) {
			this.notes = notes;
		}

		public double getTax() {
			return tax;
		}

		public void setTax(double tax) {
			this.tax = tax;
		}

		public int getQuantity() {
			return quantity;
		}

		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}

		public String getPaymentMethod() {
			return paymentMethod;
		}

		public void setPaymentMethod(String paymentMethod) {
			this.paymentMethod = paymentMethod;
		}

		public boolean isAccept() {
			return isAccept;
		}

		public void setAccept(boolean isAccept) {
			this.isAccept = isAccept;
		}

		public Customer getCustomer() {
			return customer;
		}

		public void setCustomer(Customer customer) {
			this.customer = customer;
		}

		public List<OrderDetail> getOrderDetailList() {
			return orderDetailList;
		}

		public void setOrderDetailList(List<OrderDetail> orderDetailList) {
			this.orderDetailList = orderDetailList;
		}

		@Override
		public String toString() {
			return "Order [id=" + id + ", orderDate=" + orderDate + ", deliveryDate=" + deliveryDate + ", totalPrice="
					+ totalPrice + ", shippingFee=" + shippingFee + ", orderStatus=" + orderStatus + ", notes=" + notes
					+ ", tax=" + tax + ", quantity=" + quantity + ", paymentMethod=" + paymentMethod + ", isAccept="
					+ isAccept + ", customer=" + customer + ", orderDetailList=" + orderDetailList + "]";
		}
		
	    
	    
	    
}
