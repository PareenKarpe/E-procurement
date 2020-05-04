package com.neu.dao;

import org.hibernate.HibernateException;
import java.util.*;
import org.hibernate.Query;

import com.neu.exception.UserException;
//import com.neu.edu.exception.UserException;
import com.neu.pojo.*;

public class AccountDao extends DAO {

	public AccountDao() {
	}

	public Account get(String user_name,String password) throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from Account where User_Name = :user_name and Password = :password");
			q.setString("user_name",user_name);
			q.setString("password",password);
			Account acc = (Account) q.uniqueResult();
			commit();
			return acc;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get user " + user_name, e);
		}
	}
	
	
	
	
	public Account get(String user_name) throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from Account where User_Name = :user_name");
			q.setString("user_name",user_name);
			//q.setString("password",password);
			Account acc = (Account) q.uniqueResult();
			commit();
			return acc;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get user " + user_name, e);
		}
	}
	
	public void updateToken(Account acc)throws UserException 
	{
		acc.setReset(UUID.randomUUID().toString());
		try
		{
		begin();
		getSession().update(acc);
		
		
		commit();
			
			
		}catch(HibernateException e)
		{
			
			rollback();
			throw new UserException("could not change token");
		}
		}
		
		
		
		
	
	
	

	public int validToken(String user_name,String password,String reset) throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("update from Account set Password =:password where User_Name =:user_name and reset=:reset");
			q.setString("user_name",user_name);
			q.setString("password",password);
			q.setString("reset", reset);
			//q.setString("password",password);
			int n = q.executeUpdate();
			commit();
			return n;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get user " + user_name, e);
		}
	}
	
	
	public User register(User u) throws UserException {
		try {
			begin();
			getSession().save(u);
			commit();
			return u;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Exception while creating user: " + e.getMessage());
		}
	}
	

	

	public void delete(User user) throws UserException {
		try {
			begin();
			getSession().delete(user);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not delete user ");
		}
	}
	
//	public void done() throws UserException {
//		String 
//	Query query = getSession().createQuery("from Stock where stockCode = :code ");
//	query.setParameter("code", "7277");
//	List list = query.list();
//	}
	
}
