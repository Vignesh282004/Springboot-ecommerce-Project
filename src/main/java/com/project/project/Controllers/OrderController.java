package com.project.project.Controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.project.dto.CustomerDto;
import com.project.project.model.City;
import com.project.project.model.Country;
import com.project.project.model.Customer;
import com.project.project.model.Order;
import com.project.project.model.Product;
import com.project.project.model.ShoppingCart;
import com.project.project.repository.OrderRepo;
import com.project.project.service.Cityservice;
import com.project.project.service.orderService;
import com.project.project.service.serviceimpl.Customerservimpl;
import com.project.project.service.serviceimpl.Orderservimpl;
import com.project.project.service.serviceimpl.ProductServiceImpl;
import com.project.project.service.serviceimpl.cityservimpl;
import com.project.project.service.serviceimpl.countryservimpl;

import jakarta.servlet.http.HttpSession;

@Controller
public class OrderController {

	@Autowired
	private Customerservimpl customerservimpl;
	
	@Autowired
	private ProductServiceImpl productServiceImpl;
	@Autowired
	private Orderservimpl  orderservimpl;
	
	@Autowired
	private countryservimpl countryservimpl;
	
	@Autowired
	private cityservimpl cityservimpl;
	
	  @GetMapping("/check-out")
	    public String checkout(Model model, Principal principal){
		  if (principal == null) {
	            return "redirect:/login";
	        } else {
	            CustomerDto customer = customerservimpl.getCustomer(principal.getName());
	            if (customer.getAddress() == null || customer.getCity() == null || customer.getPhoneNumber() == null) {
	                model.addAttribute("information", "You need update your information before check out");
	                List<Country> countryList = countryservimpl.findAll();
	                List<City> cities = cityservimpl.findAll();
	                model.addAttribute("customer", customer);
	                model.addAttribute("cities", cities);
	                model.addAttribute("countries", countryList);
	                return "customer-information";
	            } else {
	                ShoppingCart cart = customerservimpl.findByUsername(principal.getName()).getShoppingCart();
	                model.addAttribute("customer", customer);
	                model.addAttribute("title", "Check-Out");
	                model.addAttribute("page", "Check-Out");
	                model.addAttribute("shoppingCart", cart);
	                model.addAttribute("grandTotal", cart.getTotalItems());
	                return "checkout";
	            }
	        }
	    }

	    @GetMapping("/orders")
	    public String getOrders(Principal principal,Model model){
	    	if(principal == null)
	    	{
	    		return "redirect:/customerlogin";
	    	}
	    	Customer customer = customerservimpl.findByUsername(principal.getName());
	    	List<Order> orders = customer.getOrders();
	    	model.addAttribute("orders",orders);
	        return "order";
	    }
	    
	    
	    @RequestMapping(value = "/cancel-order", method = {RequestMethod.PUT, RequestMethod.GET})
	    public String cancelOrder(Long id, RedirectAttributes attributes) {
	        orderservimpl.cancelOrder(id);
	        attributes.addFlashAttribute("success", "Cancel order successfully!");
	        return "redirect:/orders";
	    }


	/*    @RequestMapping(value = "/add-order", method = {RequestMethod.POST})
	    public String createOrder(Principal principal,
	                              Model model,
	                              HttpSession session) {
	        if (principal == null) {
	            return "redirect:/login";
	        } else {
	            Customer customer = customerservimpl.findByUsername(principal.getName());
	            ShoppingCart cart = customer.getShoppingCart();
	            Order order = null;
	            if(order==null)
	            {
	            		return "order-detail";
	            }
	            session.removeAttribute("totalItems");
	            model.addAttribute("order", order);
	            model.addAttribute("title", "Order Detail");
	            model.addAttribute("page", "Order Detail");
	            model.addAttribute("success", "Add order successfully");
	            return "order-detail";
	        }
	    }      */
}
