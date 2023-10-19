package com.project.project.Controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.project.dto.CategoryDto;
import com.project.project.dto.ProductDto;
import com.project.project.model.Category;
import com.project.project.model.Product;
import com.project.project.service.serviceimpl.CAtegoryservimpl;
import com.project.project.service.serviceimpl.ProductServiceImpl;

@Controller
public class ProductController {
	
	@Autowired
	private ProductServiceImpl productServiceImpl;
	
	@Autowired
	private CAtegoryservimpl cAtegoryservimpl;
	
	@GetMapping("/products")
	public String products(Model model,Principal principal)
	{
		if(principal == null)
		{
			return "redirect:/login";
		}
		List<ProductDto> productDtosList = productServiceImpl.findAll();
		model.addAttribute("products",productDtosList);
		model.addAttribute("productsize",productDtosList.size());
		return "products";
	}
	 @GetMapping("/shop-detail")
	    public String shopDetail(Model model) {
	        List<CategoryDto> categories = cAtegoryservimpl.getCategoriesAndSize();
	        model.addAttribute("categories", categories);
	        List<ProductDto> products = productServiceImpl.randomProduct();
	        List<ProductDto> listView = productServiceImpl.listViewProducts();
	        model.addAttribute("productViews", listView);
	        model.addAttribute("title", "Shop Detail");
	        model.addAttribute("page", "Shop Detail");
	        model.addAttribute("products", products);
	        return "shop-detail";
	    }
	
	@GetMapping("/add-product")
	public String addProduct(Model model,Principal principal)
	{
		if(principal ==null)
		{
			return "redirect:/login";
		}
		List<Category> categories = cAtegoryservimpl.findAllByActivated();
		model.addAttribute("categories",categories);
		model.addAttribute("product",new ProductDto());
		return "add-product";
	}
	@PostMapping("/save-product")
	public String saveProduct(@ModelAttribute("product")ProductDto productDto,@RequestParam("imageProduct")MultipartFile imageProduct,RedirectAttributes attributes)
	{
		try {
			productServiceImpl.saveProduct(imageProduct, productDto);
			attributes.addFlashAttribute("success","Product Added Successfully");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			attributes.addFlashAttribute("failed","failed To add product");
		}
		return "redirect:/products";
	}
	
	@GetMapping("/update-product/{id}")
	public String updateProduct(@PathVariable("id")Long id,Model model,Principal principal)
	{
		if(principal == null)
		{
			return "redirect:/login";
		}
		List<Category> categories = cAtegoryservimpl.findAllByActivated();
		ProductDto productDto= productServiceImpl.getById(id);
		model.addAttribute("categories",categories);
		model.addAttribute("productDto",productDto);
		
		return "update-product";
	}
	
	@PostMapping("/update-product/{id}")
	private String updateProducts(@PathVariable("id")Long id,@ModelAttribute("productDto")ProductDto productDto,@RequestParam("imageProduct")MultipartFile imageProduct,RedirectAttributes attributes)
	{
			try {
				productServiceImpl.updateProduct(imageProduct, productDto);
				attributes.addFlashAttribute("success","Product Updated Successfully");
			} catch (Exception e) {
				System.out.println(e);
				attributes.addFlashAttribute("failed","Failed To update product");
			}
		return "redirect:/products";
	}
	
	@RequestMapping(value = "/enable-product/{id}",method = {RequestMethod.PUT,RequestMethod.GET})
	public String enabled(@PathVariable("id")Long id,RedirectAttributes attributes)
	{
		try {
			productServiceImpl.enableById(id);
			attributes.addFlashAttribute("success","Enabled Successfully");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			attributes.addFlashAttribute("failed","Failed To enable");
		}
		return "redirect:/products";
	}
	
	@RequestMapping(value = "/delete-product/{id}",method = {RequestMethod.PUT,RequestMethod.GET})
	public String deleted(@PathVariable("id")Long id,RedirectAttributes attributes)
	{
		try {
			productServiceImpl.deleteById(id);
			attributes.addFlashAttribute("success","Deleted Successfully");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			attributes.addFlashAttribute("failed","Failed To Delete");
		}
		return "redirect:/products";
	}
	
	@GetMapping("/search")
	public String search(String keyword,Model model,Product product)
	{
		if(keyword != null)
		{
			List<Product> allproducts = productServiceImpl.getByKeyword(keyword.trim());
			model.addAttribute("ss",allproducts);
		}
		else 
		{
			List<ProductDto> allproducts = productServiceImpl.findAll();
			model.addAttribute("ss",allproducts);
			
		}
		return "search";
	}
	
	 @GetMapping("/products-in-category/{id}")
	    public String getProductsInCategory(@PathVariable("id") Long categoryId ,Model model){
	        Category category = cAtegoryservimpl.findById(categoryId);
	        List<CategoryDto> categories = cAtegoryservimpl.getCategoryAndProduct();
	        List<Product> products = productServiceImpl.getProductsInCategory(categoryId);
	        model.addAttribute("category",category);
	        model.addAttribute("categories", categories);
	        model.addAttribute("products", products);
	        return "products-in-category";
	    }

	  @GetMapping("/find-product/{id}")
	    public String findProductById(@PathVariable("id") Long id, Model model){
	        Product product = productServiceImpl.getProductById(id);
	        model.addAttribute("product", product);	
	    List<Product> products = productServiceImpl.getProductsInCategory(product.getCategory().getId());
	    System.out.println(products);
	    model.addAttribute("products",products);
	        return "product-detail";
	    }
	  @GetMapping("/high-price")
	    public String filterHighPrice(Model model) {
	        List<CategoryDto> categories = cAtegoryservimpl.getCategoriesAndSize();
	        model.addAttribute("categories", categories);
	        List<ProductDto> products = productServiceImpl.filterHighProducts();
	        List<ProductDto> listView = productServiceImpl.listViewProducts();
	        model.addAttribute("title", "Shop Detail");
	        model.addAttribute("page", "Shop Detail");
	        model.addAttribute("productViews", listView);
	        model.addAttribute("products", products);
	        return "shop";
	    }


	    @GetMapping("/lower-price")
	    public String filterLowerPrice(Model model) {
	        List<CategoryDto> categories = cAtegoryservimpl.getCategoriesAndSize();
	        model.addAttribute("categories", categories);
	        List<ProductDto> products = productServiceImpl.filterLowerProducts();
	        List<ProductDto> listView = productServiceImpl.listViewProducts();
	        model.addAttribute("productViews", listView);
	        model.addAttribute("title", "Shop Detail");
	        model.addAttribute("page", "Shop Detail");
	        model.addAttribute("products", products);
	        return "shop";
	    }
}








