package com.project.project.service.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.project.model.CartItem;
import com.project.project.model.Customer;
import com.project.project.model.Order;
import com.project.project.model.OrderDetail;
import com.project.project.model.ShoppingCart;
import com.project.project.repository.CustomerRepo;
import com.project.project.repository.OrderDetailRepo;
import com.project.project.repository.OrderRepo;
import com.project.project.service.orderService;

@Service
public class Orderservimpl implements orderService {

	@Autowired
	  private CustomerRepo customerRepo;
	   
	@Autowired
	private ShoppingCartservimpl shoppingCartservimpl;
	    
	@Autowired
	    private OrderRepo orderRepo;
	    
	    @Autowired
	    private OrderDetailRepo orderDetailRepo;
	    
	    

	@Override
	public Order save(ShoppingCart shoppingCart) {
	     Order order = new Order();
	        order.setOrderDate(new Date());
	        order.setCustomer(shoppingCart.getCustomer());
	        order.setTax(2);
	        order.setTotalPrice(shoppingCart.getTotalPrices());
	        order.setAccept(false);
	        order.setPaymentMethod("Cash");
	        order.setOrderStatus("Pending");
	        order.setQuantity(shoppingCart.getTotalItems());
	        List<OrderDetail> orderDetailList = new ArrayList<>();
	        for (CartItem item : shoppingCart.getCartItem()) {
	            OrderDetail orderDetail = new OrderDetail();
	            orderDetail.setOrder(order);
	            orderDetail.setProduct(item.getProduct());
	            orderDetailRepo.save(orderDetail);
	            orderDetailList.add(orderDetail);
	        }
	        order.setOrderDetailList(orderDetailList);
	        shoppingCartservimpl.deleteCartById(shoppingCart.getId());
	        return orderRepo.save(order);
	}

	@Override
	public List<Order> findAll(String username) {
		Customer customer = customerRepo.findByUsername(username);
		List<Order> orders = customer.getOrders();
		return orders;
	}

	@Override
	public List<Order> findALlOrders() {
		// TODO Auto-generated method stub
		return orderRepo.findAll();
	}

	@Override
	public 	Order acceptOrder(Long id) {
		// TODO Auto-generated method stub
		Order order = orderRepo.getById(id);
		order.setAccept(true);
		order.setDeliveryDate(new Date());
		return orderRepo.save(order);
	}

	@Override
	public void cancelOrder(Long id) {
		// TODO Auto-generated method stub
		orderRepo.deleteById(id);
		
	}
	 
	
}
