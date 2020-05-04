package com.neu.dao;
import java.awt.image.BufferedImage;
import java.sql.Blob;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.engine.jdbc.BlobProxy;

import com.neu.exception.UserException;
import com.neu.pojo.Product;
import com.neu.pojo.User;

import net.bytebuddy.agent.builder.AgentBuilder.RawMatcher.Disjunction;

import org.hibernate.HibernateException;
import java.util.*;
import org.hibernate.Query;
public class ProductDao extends DAO {

	
	public ProductDao()
	{
		
	}
	
	
	
	public List<Product> SortCriteriaSearch(String value)throws UserException
	{
		List<Product> prd = new ArrayList<Product>() ;
        
		  try {
	            begin();
	           Criteria c = getSession().createCriteria(Product.class);
	          // Disjunction d = Restrictions.disjunction();
	          Criterion c1=Restrictions.ilike("Name", value, MatchMode.ANYWHERE);
	          Criterion c2 =Restrictions.ilike("description", value, MatchMode.ANYWHERE);
	          c.add(Restrictions.or(c1,c2));
	          // c.addOrder(Order.desc("Prd_Date"));
	             prd = c.list();
	            commit();
	         //   return category;
	        } catch (HibernateException e) {
	            rollback();
	            throw new UserException("Exception while creating user: " + e.getMessage());
	        }
		 
		 
		 return prd;
	
		
	}
	
	public List<Product> SortCriteriaPrice()throws UserException
	{
		List<Product> prd = new ArrayList<Product>() ;
        
		  try {
	            begin();
	           Criteria c = getSession().createCriteria(Product.class);
	          c.addOrder(Order.asc("Price"));
	          // c.addOrder(Order.desc("Prd_Date"));
	             prd = c.list();
	            commit();
	         //   return category;
	        } catch (HibernateException e) {
	            rollback();
	            throw new UserException("Exception while creating user: " + e.getMessage());
	        }
		 
		 
		 return prd;
	
		
	}
	public Product saveProduct(Product p) throws UserException
	{
		try {
			begin();
			getSession().save(p);
			commit();
			return p;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Exception while creating user: " + e.getMessage());
		}
		
	}
	
	
	public Product getProduct(String pid)throws UserException
	{
		 Product prd;
	        
		  try {
	            begin();
	            Query q = getSession().createQuery("from Product where Post_ID =:pid");
	           q.setString("pid", pid);
	             prd = (Product)q.uniqueResult();
	            commit();
	         //   return category;
	        } catch (HibernateException e) {
	            rollback();
	            throw new UserException("Exception while creating user: " + e.getMessage());
	        }
		 
		 
		 return prd;
	
}
	
	public List<Product> getProductUser(Long uid)throws UserException
	{
		 List<Product> prd = new ArrayList<Product>() ;
	        
		  try {
	            begin();
	            Query q = getSession().createQuery("from Product where User_Id =: uid");
	           q.setLong("uid", uid);
	             prd = q.list();
	            commit();
	         //   return category;
	        } catch (HibernateException e) {
	            rollback();
	            throw new UserException("Exception while creating user: " + e.getMessage());
	        }
		 
		 
		 return prd;
	
}
	public List<Product> SortCriteria()throws UserException
	{
		 List<Product> prd = new ArrayList<Product>() ;
	        
		  try {
	            begin();
	           Criteria c = getSession().createCriteria(Product.class);
	          // c.addOrder(Order.asc("Price"));
	           c.addOrder(Order.desc("Prd_Date"));
	             prd = c.list();
	            commit();
	         //   return category;
	        } catch (HibernateException e) {
	            rollback();
	            throw new UserException("Exception while creating user: " + e.getMessage());
	        }
		 
		 
		 return prd;
	
}
	
		
	
	
//	public int UpdatePrd(String pid,String name,String description,int price)throws UserException
//	{//,Blob image
//		int m;
//	        
//		  try {
//	           begin();
//	          Query q = getSession().createQuery("Update Product set Name =:name, Price = :price, description=:description where Post_ID =:pid");
//	         
//	       //    Query q = getSession().createQuery("Update Product set Name = :name,Price = :price, description=:description, image=:image where Post_ID =:pid");
//	            q.setString("pid", pid);
//	           q.setString("name", name);
//	           q.setString("description", description);
//	           q.setInteger("price", price);
//	         // q.setParameter("image",image);
//	           
//	              m = q.executeUpdate();
//	              System.out.print("pareen*****"+m);
//	            commit();
//	         //   return category;
//	        } catch (HibernateException e) {
//	            rollback();
//	            throw new UserException("Exception while creating user: " + e.getMessage());
//	        }
//		 
//		 
//		 return m;
//	
//}
	
	public Product Update1(Product p)throws UserException
	{
		try {
			begin();
			getSession().update(p);
			commit();
			return p;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Exception while creating user: " + e.getMessage());
		}
		
		
	}
	
	public Product Delete(Product p)throws UserException
	{
		try {
			begin();
			getSession().delete(p);
			commit();
			return p;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Exception while creating user: " + e.getMessage());
		}
		
		
	}
	
	public int Delete(Long pid)throws UserException
	{
		 int m ;
		try {
		 begin();
         Query q = getSession().createQuery("delete from Product where Post_ID= :pid");
        q.setParameter("pid", pid);
          m = q.executeUpdate();
         commit();
         getSession().close();
      //   return category;
     } catch (HibernateException e) {
         rollback();
         throw new UserException("Exception while creating user: " + e.getMessage());
     }
		return m;
	}
	
	
	
	
	
	public List<Product> getProduct()throws UserException
	{
		 List<Product> prd = new ArrayList<Product>() ;
	        
		  try {
	            begin();
	            Query q = getSession().createQuery("from Product");
	           
	             prd = q.list();
	            commit();
	         //   return category;
	        } catch (HibernateException e) {
	            rollback();
	            throw new UserException("Exception while creating user: " + e.getMessage());
	        }
		 
		 
		 return prd;
	
}
	
}
