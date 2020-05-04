package com.neu.dao;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;

import com.neu.exception.UserException;
import com.neu.pojo.*;

public class RequestDao extends DAO{
	

	 public RequestDao() {
		// TODO Auto-generated constructor stub
	}
	
	 
	 public Request sendReq(Request r) throws UserException
	 {
		 try {
				begin();
				getSession().save(r);
				commit();
				return r;
			} catch (HibernateException e) {
				rollback();
				throw new UserException("Exception while creating product: " + e.getMessage());
			}
		 
	 }
	 
	 public List<Request> RequestCriteriaonStatus(String status,Long id)
	 {
		 
		 Criteria cReq = getSession().createCriteria(Request.class);
		// Disjunction d = Restrictions.disjunction();
		 cReq.add(Restrictions.eq("Status","status"));
		 cReq.add(Restrictions.eq("Request_ID",id));
		
		// cReq.add(d);
		List<Request> listReq = cReq.list();
		return listReq;
		 
		 
		 
	 }
	 
	 
	 public List<Request> RequestCriteriaonVendor(Long id,User u)
	 {
		 
		 
		 Criteria cReq = getSession().createCriteria(Request.class);
		Disjunction d = Restrictions.disjunction();
		 d.add(Restrictions.and(Restrictions.eq("Status","Shipped"),Restrictions.eq("Request_ID",id)));
		 d.add(Restrictions.and(Restrictions.eq("Status","Order"),Restrictions.eq("Request_ID",id)));
		 d.add(Restrictions.and(Restrictions.eq("Status","Delivered"),Restrictions.eq("Request_ID",id)));
		// d.add(Restrictions.eq("Request_ID",id));)
		 cReq.add(d);
		 Criteria cVen = cReq.createCriteria("product");
		 cVen.add(Restrictions.eq("user1",u));
		 List<Request> reqList = cReq.list();
		 return reqList;
		 
		 
		 
	 }
	 
	 public List<Request> RequestCriteriaonEmp(User u,Long id)
	 {
		 
		 Criteria cReq = getSession().createCriteria(Request.class);
		 //Disjunction d = Restrictions.disjunction();
		 cReq.add(Restrictions.eq("user1",u));
		 cReq.add(Restrictions.eq("Request_ID",id));
		
		 //cReq.add(d);
		List<Request> listReq = cReq.list();
		return listReq;
		 
		 
		 
	 }
	 
	 
	 public int updateReq(Long id,String val) throws UserException
	 {
		 int prd ;
		 try {
	            begin();
	            Query q = getSession().createQuery("update Request set Status=:status where Request_ID=:id");
	         q.setParameter("id",id);
	         q.setParameter("status", val);
	             prd = q.executeUpdate();
	            commit();
	         //   return category;
	        } catch (HibernateException e) {
	            rollback();
	            throw new UserException("Exception while creating user: " + e.getMessage());
	        }
		 return prd;
	 }
	 
	 public List<Request> getProductUser(String f1,String f2,String f3)throws UserException
		{
			 List<Request> prd = new ArrayList<Request>() ;
		        
			  try {
				  Query q =null;
		            begin();
		            if(f2.length()==0)
		            {
		            	System.out.print("here");
		            	  q = getSession().createQuery("from Request where Status=:f1");	
		            	  q.setString("f1", f1);
		            }
		            else if(f2.length()>1)
		            {System.out.print("hereff");
		            	  q = getSession().createQuery("from Request where Status=:f1 OR Status=:f2 OR Status=:f3");
		            	  q.setString("f1", f1);
		            	  q.setString("f2", f2);
		            	  q.setString("f3", f3);
		            }
		       
		            else
		            	{
		            	
		            	}
		             prd = q.list();
		            commit();
		         //   return category;
		        } catch (HibernateException e) {
		            rollback();
		            throw new UserException("Exception while creating user: " + e.getMessage());
		        }
			 
			 
			 return prd;
		
	}
	 
	 public List<Request> getAllreq()throws UserException
		{
			 List<Request> prd = new ArrayList<Request>() ;
		        
			  try {
				  Query q =null;
		            begin();
		            
		            	  q = getSession().createQuery("from Request");	
		            	 
		           
		             prd = q.list();
		            commit();
		         //   return category;
		        } catch (HibernateException e) {
		            rollback();
		            throw new UserException("Exception while creating user: " + e.getMessage());
		        }
			 
			 
			 return prd;
		
	}
	 public List<Request> getReg_forVendor(User u)throws UserException
	 {
		 
		 
		 Criteria cReq = getSession().createCriteria(Request.class);
		 Disjunction d = Restrictions.disjunction();
		 d.add(Restrictions.eq("Status","Shipped"));
		 d.add(Restrictions.eq("Status","Order"));
		 d.add(Restrictions.eq("Status","Delivered"));
		 cReq.add(d);
		 Criteria cVen = cReq.createCriteria("product");
		 cVen.add(Restrictions.eq("user1",u));
		 List<Request> reqList = cReq.list();
		 return reqList;
		 
		 
		 
		 
		 
		 
		 
	 }
	 
	 
	 public Request getreq(Long id) throws UserException
	 {Request prd  ;
	        
		  try {
			  Query q =null;
	            begin();
	            
	            	  q = getSession().createQuery("from Request where Request_ID=:id");	
	            	 q.setParameter("id", id);
	           
	             prd = (Request)q.uniqueResult();
	            commit();
	         //   return category;
	        } catch (HibernateException e) {
	            rollback();
	            throw new UserException("Exception while creating user: " + e.getMessage());
	        }
		 
		 
		 return prd;
	 }
	 
	 
	 
	 public List<Request> getProductEmp(Long userid)throws UserException
		{
			 List<Request> prd = new ArrayList<Request>() ;
		        
			  try {
				  Query q =null;
		            begin();
		            
		            
		            	  q = getSession().createQuery("from Request where User_Id=:userid");	
		            	  q.setParameter("userid", userid);
		            
		             prd = q.list();
		            commit();
		         //   return category;
		        } catch (HibernateException e) {
		            rollback();
		            throw new UserException("Exception while creating user: " + e.getMessage());
		        }
			 
			 
			 return prd;
		
	}
	 
	 public Request getRequestStatus(Long id)throws UserException
		{
		 Request req;
		        
			  try {
				  Query q ;
		            begin();
		           
		            	  q = getSession().createQuery("from Request where Request_ID=:id");
		            	  q.setParameter("id", id);
		            	
		          
		       
		             req =(Request) q.uniqueResult();
		            commit();
		         //   return category;
		        } catch (HibernateException e) {
		            rollback();
		            throw new UserException("Exception while creating user: " + e.getMessage());
		        }
			 
			 
			 return req;
		
	} 
	 

}
