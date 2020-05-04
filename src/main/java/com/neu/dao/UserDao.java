package com.neu.dao;

import org.hibernate.HibernateException;
import java.util.*;
import org.hibernate.Query;

import com.neu.exception.UserException;
//import com.neu.edu.exception.UserException;
import com.neu.pojo.*;

public class UserDao extends DAO {

	public UserDao() {
	}

//	public User get(long userId) throws UserException {
//		try {
//			begin();
//			Query q = getSession().createQuery("from User where userId = :userId");
//			q.setLong("userId", userId);
//			User user = (User) q.uniqueResult();
//			commit();
//			return user;
//		} catch (HibernateException e) {
//			rollback();
//			throw new UserException("Could not get user " + userId, e);
//		}
//	}

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
	

//	public User get(String userName) throws UserException
//	{
//		
//		try {
//			begin();
//			Query q = getSession().createQuery("from Account where User_Name = :user_name");
//			q.setString("user_name",userName);
//			//q.setString("password",password);
//			Account acc = (Account) q.uniqueResult();
//			
//			commit();
//			return acc;
//		} catch (HibernateException e) {
//			rollback();
//			throw new UserException("Could not get user " + user_name, e);
//		}
//		
//	}
	
	
	public int userExist(String uname)throws UserException
	{
	int i=0;
		
	try {
		begin();
		Query q = getSession().createQuery("from Account where User_Name =:uname");
		q.setString("uname",uname);
		//i = q.executeUpdate();
		
		//q.setString("password",password);
		List<User> ulist = q.list();
		i=ulist.size();
		commit();
		//return n;
	} catch (HibernateException e) {
		rollback();
		throw new UserException("Could not get user");
	}	
		
	return i;	
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
