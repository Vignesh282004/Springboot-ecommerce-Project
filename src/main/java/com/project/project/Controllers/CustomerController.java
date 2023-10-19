package com.project.project.Controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.project.dto.CustomerDto;
import com.project.project.model.City;
import com.project.project.model.Country;
import com.project.project.service.serviceimpl.Customerservimpl;
import com.project.project.service.serviceimpl.cityservimpl;
import com.project.project.service.serviceimpl.countryservimpl;

import jakarta.validation.Valid;

@Controller
public class CustomerController {

	
	private final PasswordEncoder passwordEncoder;
	
	public CustomerController(PasswordEncoder passwordEncoder) {
		super();
		this.passwordEncoder = passwordEncoder;
	}

	@Autowired
	private Customerservimpl customerservimpl;
	
	@Autowired
	private countryservimpl countryservimpl;
	
	@Autowired
	private cityservimpl cityservimpl;
	   @PostMapping("/update-profile")
	    public String updateProfile(@Valid @ModelAttribute("customer") CustomerDto customerDto,
	                                BindingResult result,
	                                RedirectAttributes attributes,
	                                Model model,
	                                Principal principal) {
	        if (principal == null) {
	            return "redirect:/login";
	        } else {
	            String username = principal.getName();
	            CustomerDto customer = customerservimpl.getCustomer(username);
	            List<Country> countryList = countryservimpl.findAll();
	            List<City> cities = cityservimpl.findAll();
	            model.addAttribute("countries", countryList);
	            model.addAttribute("cities", cities);
	            if (result.hasErrors()) {
	                return "customer-information";
	            }
	            customerservimpl.update(customerDto);
	            CustomerDto customerUpdate = customerservimpl.getCustomer(principal.getName());
	            attributes.addFlashAttribute("success", "Update successfully!");
	            model.addAttribute("customer", customerUpdate);
	            return "redirect:/shop";
	        }
}
	   
	   @GetMapping("/change-password")
	    public String changePassword(Model model, Principal principal) {
	        if (principal == null) {
	            return "redirect:/login";
	        }
	        model.addAttribute("title", "Change password");
	        model.addAttribute("page", "Change password");
	        return "change-password";
	    }

	    @PostMapping("/change-password")
	    public String changePass(@RequestParam("oldPassword") String oldPassword,
	                             @RequestParam("newPassword") String newPassword,
	                             @RequestParam("repeatNewPassword") String repeatPassword,
	                             RedirectAttributes attributes,
	                             Model model,
	                             Principal principal) {
	        if (principal == null) {
	            return "redirect:/login";
	        } else {
	            CustomerDto customer = customerservimpl.getCustomer(principal.getName());
	            if (passwordEncoder.matches(oldPassword, customer.getPassword())
	                    && !passwordEncoder.matches(newPassword, oldPassword)
	                    && !passwordEncoder.matches(newPassword, customer.getPassword())
	                    && repeatPassword.equals(newPassword) && newPassword.length() >= 5) {
	                customer.setPassword(passwordEncoder.encode(newPassword));
	                customerservimpl.changePass(customer);
	                attributes.addFlashAttribute("success", "Your password has been changed successfully!");
	                return "redirect:/profile";
	            } else {
	                model.addAttribute("message", "Your password is wrong");
	                return "change-password";
	            }
	        }
	   
	   
	    }
	    
	    
	    @GetMapping("/cust-profile")
	    public String profile(Model model, Principal principal) {
	        if (principal == null) {
	            return "redirect:/login";
	        } else {
	            String username = principal.getName();
	            CustomerDto customer = customerservimpl.getCustomer(username);
	            List<Country> countryList = countryservimpl.findAll();
	            List<City> cities = cityservimpl.findAll();
	            model.addAttribute("customer", customer);
	            model.addAttribute("cities", cities);
	            model.addAttribute("countries", countryList);
	            model.addAttribute("title", "Profile");
	            model.addAttribute("page", "Profile");
	            return "customer-information";
	        }
}
}
