package com.project.project.Controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.project.dto.ProductDto;
import com.project.project.model.Customer;
import com.project.project.model.Product;
import com.project.project.model.ShoppingCart;
import com.project.project.service.serviceimpl.Customerservimpl;
import com.project.project.service.serviceimpl.ProductServiceImpl;
import com.project.project.service.serviceimpl.ShoppingCartservimpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CartController {

	@Autowired
	private Customerservimpl customerservimpl;
	
	@Autowired
	private ProductServiceImpl productServiceImpl;
	
	@Autowired
	private ShoppingCartservimpl shoppingCartservimpl;

	  @GetMapping("/cart")
	    public String cart(Model model, Principal principal, HttpSession session){
	        if(principal == null){
	            return "redirect:/login";
	        }
	        String username = principal.getName();
	        Customer customer = customerservimpl.findByUsername(username);
	        if(customer == null)
	        {
	        	System.out.println("customer is null ....................");
	        }
	        ShoppingCart shoppingCart = customer.getShoppingCart();
	        if(shoppingCart == null){
	        	System.out.println("shopping cart is null  ....................................");
	            model.addAttribute("check", "No item in your cart");
	        }
	        session.setAttribute("totalItems", shoppingCart.getTotalItems());
	        model.addAttribute("subTotal", shoppingCart.getTotalPrices());
	        model.addAttribute("shoppingCart", shoppingCart);
	        return "cart";
	    }

	  
	  @PostMapping("/add-to-cart")
	    public String addItemToCart(
	            @RequestParam("id") Long productId,
	            @RequestParam(value = "quantity", required = false, defaultValue = "1") int quantity,
	            Principal principal,
	            HttpSession session,Model model,HttpServletRequest request){

		  ProductDto productDto = productServiceImpl.getById(productId);
	        if(principal == null){
	            return "redirect:/login";
	        }
	        Product product = productServiceImpl.getProductById(productId);
	        String username = principal.getName();
	        Customer customer = customerservimpl.findByUsername(username);

	        ShoppingCart cart = shoppingCartservimpl.addItemToCart(product, quantity, customer);
	        return "redirect:" + request.getHeader("Referer");
	  }
	  

	    @RequestMapping(value = "/update-cart", method = RequestMethod.POST, params = "action=update")
	    public String updateCart(@RequestParam("quantity") int quantity,
	                             @RequestParam("id") Long productId,
	                             Model model,
	                             Principal principal
	                             ){

	        if(principal == null){
	            return "redirect:/login";
	        }else{
	            String username = principal.getName();
	            Customer customer = customerservimpl.findByUsername(username);
	            Product product = productServiceImpl.getProductById(productId);
	            ShoppingCart cart = shoppingCartservimpl.updateItemInCart(product, quantity, customer);

	            model.addAttribute("shoppingCart", cart);
	            return "redirect:/cart";
	        }
	        
	    }
	    
	    @RequestMapping(value = "/update-cart", method = RequestMethod.POST, params = "action=delete")
	    public String deleteItemFromCart(@RequestParam("id") Long id,
	                                     Model model,
	                                     Principal principal){
	        if(principal == null){
	            return "redirect:/login";
	        }else{
	        	  String username = principal.getName();
	              Customer customer = customerservimpl.findByUsername(username);
	              Product product = productServiceImpl.getProductById(id);
	              ShoppingCart cart = shoppingCartservimpl.deleteItemFromCart(product, customer);
	              model.addAttribute("shoppingCart", cart);
	              return "redirect:/cart";
	        }

	    }
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
}













