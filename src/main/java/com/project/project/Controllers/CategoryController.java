package com.project.project.Controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.project.model.Category;
import com.project.project.repository.CategoryRepo;
import com.project.project.service.serviceimpl.CAtegoryservimpl;

@Controller
public class CategoryController {

	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private CAtegoryservimpl cAtegoryservimpl;

	@GetMapping("/categories")
	public String adminController(Model model, Principal principal) {
		if (principal == null) {
			return "redirect:/login";
		}
		try {
			List<Category> allCategories = cAtegoryservimpl.findAll();
			model.addAttribute("allCategories", allCategories);
			model.addAttribute("size", allCategories.size());
			model.addAttribute("categoryNew", new Category());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "categories";
	}
	 @PostMapping("/add-category")
	    public String add(@ModelAttribute("categoryNew") Category category, RedirectAttributes attributes){
	        try {
	            cAtegoryservimpl.save(category);
	            attributes.addFlashAttribute("success", "Added successfully");
	        }catch (DataIntegrityViolationException e){
	            System.out.println(e);
	            attributes.addFlashAttribute("failed", "Failed to add because duplicate name");
	        }
	        catch (Exception e){
	            e.printStackTrace();
	            attributes.addFlashAttribute("failed", "Error server");
	        }
	        return "redirect:/categories";

	    }
	@RequestMapping(value = "/findById/",method = {RequestMethod.PUT,RequestMethod.GET})
	@ResponseBody
	 public Category findById(Long id)
	{
		//System.out.println);
		System.out.println("came inside find byid");
		return cAtegoryservimpl.findById(id);
	}
	
	@GetMapping("/update-category")
    public String update(Category category, RedirectAttributes attributes){
        try {
            cAtegoryservimpl.Update(category);
            attributes.addFlashAttribute("success","Updated successfully");
        }catch (DataIntegrityViolationException e){
            e.printStackTrace();
            attributes.addFlashAttribute("failed", "Failed to update because duplicate name");
        }catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("failed", "Error server");
        }
        return "redirect:/categories";
    } 
	
	@RequestMapping(value = "/delete-category/",method = {RequestMethod.PUT,RequestMethod.GET})
	public String delete(Long id,RedirectAttributes attributes)
	{
		  try {
	            cAtegoryservimpl.deleteById(id);
	            attributes.addFlashAttribute("success","Deleted successfully");
	        }catch (DataIntegrityViolationException e){
	            e.printStackTrace();
	            attributes.addFlashAttribute("failed", "Failed to update because duplicate name");
	        }catch (Exception e){
	            e.printStackTrace();
	            attributes.addFlashAttribute("failed", "Error server");
	        }
	        return "redirect:/categories";
		
	}
	
	@RequestMapping(value = "/enable-category/",method = {RequestMethod.PUT,RequestMethod.GET})
	public String enabled(Long id,RedirectAttributes attributes)
	{
		  try {
	            cAtegoryservimpl.enableById(id);
	            attributes.addFlashAttribute("success","Enabled successfully");
	        }catch (DataIntegrityViolationException e){
	            e.printStackTrace();
	            attributes.addFlashAttribute("failed", "Failed to update because duplicate name");
	        }catch (Exception e){
	            e.printStackTrace();
	            attributes.addFlashAttribute("failed", "Error server");
	        }
	        return "redirect:/categories";
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}