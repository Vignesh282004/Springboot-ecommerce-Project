package com.project.project.service;

import java.util.List;

import com.project.project.model.Order;
import com.project.project.model.ShoppingCart;

public interface orderService {

	  Order save(ShoppingCart shoppingCart);

	    List<Order> findAll(String username);

	    List<Order> findALlOrders();

	    Order acceptOrder(Long id);

	    void cancelOrder(Long id);
}
