package com.neu.pojo;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;



@Entity
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private Long Account_Id;
	
	
	@Column(name="User_Name")
private String User_Name	;
	
	@Column(name="Password")
	private String Password;
	
	@ManyToOne
	@JoinColumn(name="User_ID")
	private User user1;
	
	
	@Column(name="reset")
	private String reset; 

	@Column(name="Type")
	private String Type;
	
	
	

	

	public Account()
	{
		
	}
	
	
	public Account(String User_Name, String Password,User user1,String Type,String reset)
	{
		super();
		this.User_Name = User_Name;
		this.Password = Password;
		this.Type = Type;
		this.user1 = user1;
		this.reset = reset;
	}

	public String getReset() {
		return reset;
	}


	public void setReset(String reset) {
		this.reset = reset;
	}


	public Long getAccount_Id() {
		return Account_Id;
	}

	public void setAccount_Id(Long account_Id) {
		Account_Id = account_Id;
	}

	public String getUser_Name() {
		return User_Name;
	}

	public void setUser_Name(String user_Name) {
		User_Name = user_Name;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}


	public User getUser1() {
		return user1;
	}


	public void setUser1(User user1) {
		this.user1 = user1;
	}


	
	public String getType() {
		return Type;
	}


	public void setType(String type) {
		Type = type;
	}


	
	
	
	
	
	
	
	
	
	
	

}
