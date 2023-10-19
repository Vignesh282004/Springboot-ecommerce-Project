package com.project.project.service;

import com.project.project.model.Customer;
import com.project.project.model.Product;
import com.project.project.model.ShoppingCart;

public interface ShoppingCartService {


	
	ShoppingCart addItemToCart(Product product, int quantity, Customer customer);
    ShoppingCart updateItemInCart(Product product, int quantity, Customer customer);

    ShoppingCart deleteItemFromCart(Product product, Customer customer);
    void deleteCartById(Long id);
   
}
