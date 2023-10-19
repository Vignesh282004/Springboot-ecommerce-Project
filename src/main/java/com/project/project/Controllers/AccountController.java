package com.project.project.Controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.project.model.Customer;
import com.project.project.service.serviceimpl.Customerservimpl;

@Controller
public class AccountController {

	@Autowired
	private Customerservimpl customerservimpl;
	
    @GetMapping("/account")
    public String accountHome(Model model , Principal principal){
        if(principal == null){
            return "redirect:/login";
        }
        String username = principal.getName();
        Customer customer = customerservimpl.findByUsername(username);
        model.addAttribute("customer", customer);
        return "account";
    }
    
    @RequestMapping(value = "/update-infor", method = {RequestMethod.GET, RequestMethod.PUT})
    public String updateCustomer(
            @ModelAttribute("customer") Customer customer,
            Model model,
            RedirectAttributes redirectAttributes,
            Principal principal){
        if(principal == null){
            return "redirect:/login";
        }
        Customer customerSaved = customerservimpl.saveInfor(customer);

        redirectAttributes.addFlashAttribute("success", customerSaved);

        return "redirect:/account";
    }

    
}
