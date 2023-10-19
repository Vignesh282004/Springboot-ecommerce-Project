package com.project.project.Controllers;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.project.dto.AdminDto;
import com.project.project.model.Admin;
import com.project.project.service.AdminService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class loginContoller {

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@GetMapping("/login")
	public String login()
	{
		return "Clogin";
	}
	@GetMapping("/register")
	public String register(Model model)
	{

		model.addAttribute("adminDto",new AdminDto());
		return "Cregister";
	}
	
	@GetMapping("/profile")
	public String check(Principal principal)
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication==null || authentication instanceof AnonymousAuthenticationToken)
		{
			return "Clogin";
		}
		if(principal == null)
		{
			return "Clogin";
		}
		return "profile-details";
	}
	@PostMapping("/new-user")
	public String addNew(@Valid @ModelAttribute("adminDto")AdminDto adminDto,BindingResult result,Model model,RedirectAttributes redirectAttributes,HttpSession session)
	{
		try {
			session.removeAttribute("message");
			if(result.hasErrors())
			{
				model.addAttribute("adminDto",adminDto); 
				return "Cregister";
			}
			String username = adminDto.getUsername();
			Admin admin = adminService.findByUsername(username);
			if(admin!=null)
			{
			System.out.println("give all details correctly");
			return "Cregister";
			}
			if(adminDto.getPassword().equals(adminDto.getRepeatpassword()))
			{
				adminDto.setPassword(passwordEncoder.encode(adminDto.getPassword()));
				adminService.saveAdmin(adminDto);
				model.addAttribute("adminDto",adminDto);
				System.out.println("You are registerd successfully");
				session.setAttribute("message","You are registerd successfully");
				model.addAttribute("adminDto",adminDto);
			}
			else 
			{
			System.out.println("passwords not same");
				session.setAttribute("message","Passwords Are Not Same");
				model.addAttribute("adminDto",adminDto);
				return "Cregister";
			}
			return "Cregister";
		} catch (Exception e) {
			System.out.println("You CAnnot be registered Because Some Error is Occured at server side");
			session.setAttribute("message","You CAnnot be registered Because Some Error is Occured at server side");
			e.printStackTrace();
		}
		return "Cregister";
	}
	
	@GetMapping("/forgot-pass")
	public String forgotpass()
	{
		return "Cforgot-password";
	}

	
	
}
