package com.project.project.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.project.dto.CategoryDto;
import com.project.project.dto.ProductDto;
import com.project.project.model.Category;
import com.project.project.model.Contact;
import com.project.project.model.Product;
import com.project.project.repository.CategoryRepo;
import com.project.project.service.serviceimpl.CAtegoryservimpl;
import com.project.project.service.serviceimpl.MailServImpl;
import com.project.project.service.serviceimpl.ProductServiceImpl;



@Controller
public class HomeController {

	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private ProductServiceImpl productServiceImpl;
	@Autowired
	private CAtegoryservimpl cAtegoryservimpl;
	@Autowired
	private MailServImpl mailServImpl;
	@GetMapping("/index")
	public String index(Model model)
	{
		List<Category> categories = categoryRepo.findAllByActivated();
		List<ProductDto> productDtos = productServiceImpl.findAll();
		model.addAttribute("categories",categories);
		model.addAttribute("products",productDtos);
		return "index";
	}
	
	@GetMapping("/shop")
	public String shop(Model model)
	{
		List<Product> products = productServiceImpl.getAllProducts();
		List<ProductDto> listViewProducts = productServiceImpl.listViewProducts();
		List<CategoryDto> categoryDtos = cAtegoryservimpl.getCategoryAndProduct();
		List<Category> categories = categoryRepo.findAllByActivated();
		model.addAttribute("categories",categoryDtos);
		model.addAttribute("products",products);
		model.addAttribute("listViewProducts",listViewProducts);
		return "shop";
	}
@GetMapping("/contact")
	public String contact(Model model)
	{
		model.addAttribute("contact",new Contact());
		return "contact";
	}

@PostMapping("/contactform")
public String sendContact(@ModelAttribute("contact")Contact contact,Model model,RedirectAttributes attributes)
{
	
	try {
		mailServImpl.SendEmail(contact.getName(),contact.getEmail(),contact.getSubject(),contact.getMessage());
		attributes.addFlashAttribute("message","Email Sent Succesfully");
		System.out.println("Email sent....");
	} catch (Exception e) {
		System.out.println(e);
		System.out.println("Email not sent");
		attributes.addFlashAttribute("message","Email Not Sent");
	}
	return "contact";
}
}
	 
	 

