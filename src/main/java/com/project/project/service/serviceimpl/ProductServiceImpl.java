package com.project.project.service.serviceimpl;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.project.dto.ProductDto;
import com.project.project.model.Product;
import com.project.project.repository.ProductRepo;
import com.project.project.service.ProductService;
import com.project.project.utils.ImageUpload;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepo productRepo;
	
	@Autowired
	private ImageUpload imageUpload; 

	@Autowired
	private ProductServiceImpl productServiceImpl;
	 @Override
	    public List<ProductDto> findAll() {
	        List<Product> products = productRepo.findAll();
	        List<ProductDto> productDtoList = transfer(products);
	        return productDtoList;
	    }

	private List<ProductDto> transfer(List<Product> products) {
		  List<ProductDto> productDtoList = new ArrayList<>();
	        for(Product product : products){
	            ProductDto productDto = new ProductDto();
	            productDto.setId(product.getId());
	            productDto.setName(product.getName());
	            productDto.setDecription(product.getDecription());
	            productDto.setCurrentQuantity(product.getCurrentQuantity());
	            productDto.setCategory(product.getCategory());
	            productDto.setSaleprice(product.getSaleprice());
	            productDto.setCostprice(product.getCostprice());
	            productDto.setImage(product.getImage());
	            productDto.setDeleted(product.isIs_deleted());
	            productDto.setActivated(product.isIs_activated());
	            productDtoList.add(productDto);
	        }
	        return productDtoList;
	}

	@Override
	public Product saveProduct(MultipartFile imageProduct,ProductDto productDto) {
		try {
			Product product = new Product();
			if(imageProduct == null) 
			{
				product.setImage(null);
			}
			else {
				
			if(imageUpload.uploadImage(imageProduct)) {
				System.out.println("Upload Success");
			}
				product.setImage(Base64.getEncoder().encodeToString(imageProduct.getBytes()));
			}
			product.setName(productDto.getName());
			product.setCategory(productDto.getCategory());
			product.setDecription(productDto.getDecription());
			product.setCostprice(productDto.getCostprice());
			product.setCurrentQuantity(productDto.getCurrentQuantity());
			product.setIs_activated(true);
			product.setIs_deleted(false);
			//System.out.println(product.toString());
			return productRepo.save(product);

		} catch (Exception e) {
		 System.out.println(e);
		 return null;
		}
	}

	//@SuppressWarnings("deprecation")
	@Override
	public Product updateProduct(MultipartFile imageProduct,ProductDto productDto) {
		try {
			Product product = productRepo.getById(productDto.getId());
			if(imageProduct == null)
			{
				product.setImage(product.getImage());
			}
			else {
				if(imageUpload.checkexisted(imageProduct) ==false)
				{
					imageUpload.uploadImage(imageProduct);
				}
				product.setImage(Base64.getEncoder().encodeToString(imageProduct.getBytes()));
			}
			product.setName(productDto.getName());
			product.setDecription(productDto.getDecription());
			product.setSaleprice(productDto.getSaleprice());
			product.setCostprice(productDto.getCostprice());
			product.setCurrentQuantity(productDto.getCurrentQuantity());
			product.setCategory(productDto.getCategory());
			return productRepo.save(product);
			
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	@Override
	public void deleteById(Long id) {
		Product product = productRepo.getById(id);
		product.setIs_deleted(true);
		product.setIs_activated(false);
		productRepo.save(product);
	}

	@Override
	public void enableById(Long id) {
	Product product = productRepo.getById(id);
	product.setIs_deleted(false);
		product.setIs_activated(true);
		productRepo.save(product);
	}

	@Override
	public ProductDto getById(Long id) {
		Product product = productRepo.getById(id);
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDecription(product.getDecription());
        productDto.setCurrentQuantity(product.getCurrentQuantity());
        productDto.setCategory(product.getCategory());
        productDto.setSaleprice(product.getSaleprice());
        productDto.setCostprice(product.getCostprice());
        productDto.setImage(product.getImage());
        productDto.setDeleted(product.isIs_deleted());
        productDto.setActivated(product.isIs_activated());
        return productDto;
	}

	@Override
	public Page<Product> findPaginated(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
		return this.productRepo.findAll(pageable);
	}

	@Override
	public List<Product> getByKeyword(String keyword) {
		return productRepo.findByKeyword(keyword);
	}

	@Override
	public List<Product> getAllProducts() {
		return productRepo.getAllProducts();
	}

	@Override
	public List<Product> getProductsInCategory(Long categoryId) {
		
		return productRepo.getProductsInCategory(categoryId);
	}

	@Override
	public List<ProductDto> listViewProducts() {
		
		return transfer(productRepo.listViewProducts());
	}

	@Override
	public Product getProductById(Long id) {
		return productRepo.findById(id).get();
	}

	@Override
	public List<Product> getRelatedProducts(Long categoryId) {
		   return productRepo.getRelatedProducts(categoryId);
	}

	@Override
	public List<ProductDto> filterHighProducts() {
		// TODO Auto-generated method stub
		return transfer(productRepo.filterHighProducts());
	}

	@Override
	public List<ProductDto> filterLowerProducts() {
		// TODO Auto-generated method stub
		return transfer(productRepo.filterLowerProducts());
	}

	@Override
	public List<ProductDto> randomProduct() {
		return transfer(productRepo.randomProduct());
	}

	

	

	

	

	

	

	


	
	
	

}

	


	


	

