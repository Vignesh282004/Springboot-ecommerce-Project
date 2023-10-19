package com.project.project.dto;

import com.project.project.model.City;

import jakarta.validation.constraints.Size;

public class CustomerDto {

	 @Size(min = 3, max = 10, message = "First name contains 3-10 characters")
	    private String firstName;

	    @Size(min = 3, max = 10, message = "Last name contains 3-10 characters")
	    private String lastName;
	    private String username;
	    @Size(min = 3, max = 15, message = "Password contains 3-10 characters")
	    private String password;

	    @Size(min = 10, max = 15, message = "Phone number contains 10-15 characters")
	    private String phoneNumber;

	    private String address;
	    private String confirmPassword;
	    private String city;
	    private String image;
	    private String country;
		public CustomerDto() {
			super();
			// TODO Auto-generated constructor stub
		}
		public CustomerDto(@Size(min = 3, max = 10, message = "First name contains 3-10 characters") String firstName,
				@Size(min = 3, max = 10, message = "Last name contains 3-10 characters") String lastName,
				String username,
				@Size(min = 3, max = 15, message = "Password contains 3-10 characters") String password,
				@Size(min = 10, max = 15, message = "Phone number contains 10-15 characters") String phoneNumber,
				String address, String confirmPassword, String city, String image, String country) {
			super();
			this.firstName = firstName;
			this.lastName = lastName;
			this.username = username;
			this.password = password;
			this.phoneNumber = phoneNumber;
			this.address = address;
			this.confirmPassword = confirmPassword;
			this.city = city;
			this.image = image;
			this.country = country;
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getPhoneNumber() {
			return phoneNumber;
		}
		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getConfirmPassword() {
			return confirmPassword;
		}
		public void setConfirmPassword(String confirmPassword) {
			this.confirmPassword = confirmPassword;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getImage() {
			return image;
		}
		public void setImage(String image) {
			this.image = image;
		}
		public String getCountry() {
			return country;
		}
		public void setCountry(String country) {
			this.country = country;
		}
	
}
