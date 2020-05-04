package com.neu.pojo;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;




@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="User_ID")
	private Long User_Id;
	
	

	@Column(name="FIRST_NAME")
	private String First_name;
	
	@Column(name = "Last_Name")
	private String Last_name;
	
	@Column(name = "Phone_number")
	private String Phone_Number;
	
	@Column(name = "Email_Id")
	private String Email_Id;
	
	@Column(name = "Apartment_Name")
	private String Apartment_Name;
	
	@Column(name = "Street_Name")
	private String Street_Name;
	
	@Column(name = "Unit_Number")
	private String Unit_Number;
	
	@Column(name = "City")
	private String City;
	
	@Column(name = "State")
	private String state;
	
	@Column(name = "Postal")
	private String postal;	
	
	@Column(name = "Country")
	private String Country;

	
	
	
//@Column(name="User_Type")
//	private String User_Type;
//	
//	



@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="user1")
	
	private Set<Account> phoneNumber;
     
     
public User() {
		
	}
	
	
public Long getUser_Id() {
	return User_Id;
}

public void setUser_Id(Long user_Id) {
	User_Id = user_Id;
}

	public String getFirst_name() {
		return First_name;
	}


//	public String getUser_Type() {
//		return User_Type;
//	}
//
//
//	public void setUser_Type(String user_Type) {
//		User_Type = user_Type;
//	}
	public void setFirst_name(String first_name) {
		First_name = first_name;
	}



	public String getLast_name() {
		return Last_name;
	}



	public void setLast_name(String last_name) {
		Last_name = last_name;
	}



	public String getPhone_Number() {
		return Phone_Number;
	}



	public void setPhone_Number(String phone_Number) {
		Phone_Number = phone_Number;
	}



	public String getApartment_Name() {
		return Apartment_Name;
	}



	public void setApartment_Name(String apartment_Name) {
		Apartment_Name = apartment_Name;
	}



	public String getStreet_Name() {
		return Street_Name;
	}



	public void setStreet_Name(String street_Name) {
		Street_Name = street_Name;
	}



	public String getUnit_Number() {
		return Unit_Number;
	}



	public void setUnit_Number(String unit_Number) {
		Unit_Number = unit_Number;
	}



	public String getCity() {
		return City;
	}



	public void setCity(String city) {
		City = city;
	}



	public String getState() {
		return state;
	}



	public void setState(String state) {
		this.state = state;
	}



	public String getPostal() {
		return postal;
	}



	public void setPostal(String postal) {
		this.postal = postal;
	}



	public String getCountry() {
		return Country;
	}



	public void setCountry(String country) {
		Country = country;
	}



	

	

	public String getEmail_Id() {
		return Email_Id;
	}

	public void setEmail_Id(String email_Id) {
		Email_Id = email_Id;
	}


	public Set<Account> getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(Set<Account> phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	


	
	


	
}
