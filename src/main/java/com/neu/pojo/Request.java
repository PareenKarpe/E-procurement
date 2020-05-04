package com.neu.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Request {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Request_ID")
	private Long Request_ID;
	
	@ManyToOne(optional = true)
	@JoinColumn(name="User_Id")
	private User user1;
	
	@ManyToOne(optional = true)
	@JoinColumn(name="Post_ID")
private Product product;
	@Column(name="quantity")
	private int quantity;
	
	
	@Column(name="Totalprice")
	private int Totalprice;
	@Column(name="Status")
	private String Status;
	@Column(name="Req_Date")
	private Date Req_Date;
	
	

	public Date getReq_Date() {
		return Req_Date;
	}


	public void setReq_Date(Date req_Date) {
		Req_Date = req_Date;
	}


	public String getStatus() {
		return Status;
	}


	public void setStatus(String status) {
		Status = status;
	}


	public Long getRequest_ID() {
		return Request_ID;
	}


	public void setRequest_ID(Long request_ID) {
		Request_ID = request_ID;
	}


	public User getUser1() {
		return user1;
	}


	public void setUser1(User user1) {
		this.user1 = user1;
	}


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public int getTotalprice() {
		return Totalprice;
	}


	public void setTotalprice(int totalprice) {
		Totalprice = totalprice;
	}
	

}
