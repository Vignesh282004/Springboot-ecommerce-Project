package com.project.project.utils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ImageUpload {

	private final String path= "E:\\Springboot-Project\\project\\src\\main\\resources\\static";
	
	public boolean uploadImage(MultipartFile imageProduct)
	{
		boolean uploaded = false;
		try {
			
			Files.copy(imageProduct.getInputStream(),
					Paths.get(path + File.separator,
					imageProduct.getOriginalFilename()),
					StandardCopyOption.REPLACE_EXISTING );
			uploaded = true;
		} catch (Exception e) {
			System.out.println(e);
			
		}
		return uploaded;
	}
	
	public boolean checkexisted(MultipartFile imageProduct)
	{
		boolean imageexists = false;
		try {
			File file = new File(path + "\\" + imageProduct.getOriginalFilename());
			imageexists = file.exists();
		} catch (Exception e) {
			System.out.println(e);		
		}
		return imageexists;
	}
}
