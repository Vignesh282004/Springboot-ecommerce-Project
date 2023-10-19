package com.project.project.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.project.dto.CustomerDto;
import com.project.project.model.Customer;
import com.project.project.service.serviceimpl.Customerservimpl;

import jakarta.validation.Valid;

@Controller
public class AuthController {

	@Autowired
	private Customerservimpl customerservimpl;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@GetMapping("/customerlogin")
	public String login()
	{
		return "cuslogin";
	}
	@GetMapping("/cusregister")
	public String register(Model model)
	{
		model.addAttribute("customerDto",new CustomerDto());
		
		return "cusregister";
	}
	
    @PostMapping("/do-cusregister")
    public String processRegister(@Valid @ModelAttribute("customerDto") CustomerDto customerDto,
                                  BindingResult result,
                                  Model model) {
        try {
            if (result.hasErrors()) {
                model.addAttribute("customerDto", customerDto);
                return "cusregister";
            }
            Customer customer = customerservimpl.findByUsername(customerDto.getUsername());
            if(customer != null){
                model.addAttribute("username", "Email Exists Already !! Try With New One");
                model.addAttribute("customerDto",customerDto);
                return "cusregister";
            }
            if(customerDto.getPassword().equals(customerDto.getConfirmPassword())) {
                customerDto.setPassword(passwordEncoder.encode(customerDto.getPassword()));
                customerservimpl.save(customerDto);
                model.addAttribute("success", "Register successfully");
                return "cusregister";
            }else{
                model.addAttribute("password", "Password is not same");
                model.addAttribute("customerDto",customerDto);
                return "cusregister";
            }
        }catch (Exception e){
            model.addAttribute("error", "Server have ran into problem");
            model.addAttribute("customerDto",customerDto);
        }
        return "cusregister";
    }
    @GetMapping("/go")
    public String go()
    {
    	return "index";
    }
   
}

