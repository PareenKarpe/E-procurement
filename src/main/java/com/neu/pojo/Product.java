package com.neu.pojo;

import java.sql.Blob;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import java.io.*;



import org.apache.commons.codec.binary.*;

import com.neu.pojo.User;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Post_ID")
	private Long Post_ID;
	
	@Column(name="Name")
	private String Name;
	

	@Column(name = "IMAGE")
	   private Blob image;
	@Column(name="Price")
	private int Price;
	
	@Column(name="Description")
	private String description;
	
	public int getPrice() {
		return Price;
	}

	@Column(name="Prd_Date")
	private Date Prd_Date;

	public Date getPrd_Date() {
		return Prd_Date;
	}



	public void setPrd_Date(Date prd_Date) {
		Prd_Date = prd_Date;
	}



	public void setPrice(int price) {
		Price = price;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	@Column(name="file1")
	private String   file1;
	
	@ManyToOne
	@JoinColumn(name="User_Id")
	private User user1;
	


	

	public User getUser1() {
		return user1;
	}



	public void setUser1(User user1) {
		this.user1 = user1;
	}



	public String getFile1() {
		return file1;
	}



	public void setFile1(String file1) {
		this.file1 = file1;
	}



	public Blob getImage() {
		return image;
	}



	public void setImage(Blob image) {
		this.image = image;
	}



	public Long getPost_ID() {
		return Post_ID;
	}



	public void setPost_ID(Long post_ID) {
		Post_ID = post_ID;
	}



	public String getName() {
		return Name;
	}



	public void setName(String name) {
		Name = name;
	}



	

	

	
	
	

}
