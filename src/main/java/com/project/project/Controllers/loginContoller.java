package com.project.project.Controllers;
import java.security.Principal;
import java.util.Random;

import org.hibernate.bytecode.internal.bytebuddy.PrivateAccessorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.event.PublicInvocationEvent;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.project.dto.AdminDto;
import com.project.project.model.Admin;
import com.project.project.model.Customer;
import com.project.project.repository.CustomerRepo;
import com.project.project.service.AdminService;
import com.project.project.service.serviceimpl.MailServImpl;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import scala.annotation.elidable;

@Controller
public class loginContoller {

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	@Autowired
	private MailServImpl mailServImpl;
	
	@Autowired
	private CustomerRepo customerRepo;

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

	@PostMapping("/send-otp")
	public String sendotp(@RequestParam("email")String email ,Model model,RedirectAttributes attributes,HttpSession session)
	{
		System.out.println(email);
		
		Random random = new Random(); 
		int otp = random.nextInt(999999);
		System.out.println(otp);
		String x_email = email;
		String subject = "This is Otp From Spring-boot App";
		String message = "Your Otp of Spirng boot Application-Login is : "+otp+"";
	boolean flag = this.mailServImpl.SendEmail(x_email,subject,message);
	if(flag)
	{
		session.setAttribute("oldotp", otp);
		session.setAttribute("email", email);
		model.addAttribute("success","Otp Was Sent Suucessfully ! Check your Mail");
		return "otp-check";
	}
	else 
	{
		attributes.addFlashAttribute("failed","Otp Was Not Sent ___ Server Down <===========>");
		session.setAttribute("message", "OOPS ?* Server Runtime Error ------- Otp Not Sent");
		return "Cforgot-password";
	}
	}
	
	@PostMapping("/otp-check")
	public String verify_otp(@RequestParam("otp") int otp,HttpSession session,Model model)
	{
		int oldotp  = (int)session.getAttribute("oldotp");
		String email = (String)session.getAttribute("email");
		if(otp == oldotp)
		{
			Customer customer = this.customerRepo.findByUsername(email);
			if(customer==null)
			{
				model.addAttribute("failed","No User Existed..Please Go back and Register First *");
				return "otp-check";
			}
			else {
				
				return "change_password";
			}
		}
		else 
		{
			model.addAttribute("failed","You Have Entered Wrong Otp !! Please Enter Latest Otp");
			return "otp-check";
		}
		
	}
	
	
	@PostMapping("/change-pass")
	public String changepass(@RequestParam("password")String password,Model model,HttpSession session)
	{
		try {
			String email =(String)session.getAttribute("email");
			Customer customer = this.customerRepo.findByUsername(email);
			customer.setPassword(this.passwordEncoder.encode(password));
			this.customerRepo.save(customer);
			model.addAttribute("success","Password Changed Successfully.");
			return "change_password";
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("failed","Password Didn't Change");
			System.out.println(e);
		}
		return "change_password";
	
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

