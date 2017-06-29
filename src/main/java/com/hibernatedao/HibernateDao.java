package com.hibernatedao;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.ui.Model;

import com.interfacedao.InterfaceDao;
import com.pojo.Student;

public class HibernateDao implements InterfaceDao{
	static SessionFactory sessionFactory;
    static Session session;

	public String StudentRegister(Student s) {
		Session session = new AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory().openSession();
        session.save(s);
        session.beginTransaction().commit();
		return "success";
	}
	
	public String loginStudent(Student s, Model model){
		
		System.out.println("Entered into Hibernate Dao");
		Session session = new AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory().openSession();
		Criteria criteria = session.createCriteria(Student.class);
		criteria.add(Restrictions.eq("username", s.getUsername()));
//		criteria.add(Restrictions.eq("password", s.getPassword()));
		List<Student> result = criteria.list();
		session.beginTransaction().commit();
		if (s.getPassword().equals(result.get(0).getPassword())) 
		     {
			      System.out.println("----" + result.get(0).getRole());
			      if(result.get(0).getRole().equals("admin")) 
			      {
			    	  Query query = session.createQuery("from Student where locked=?");
						query.setParameter(0,"on");
						List<Student> result1 = query.list();
						 model.addAttribute("listoflockedstudents" , result1);
						
				        return "adminlogin";
			      } else 
			           {
				          if(result.get(0).getLocked().equals("on"))
				            {
					            model.addAttribute("m", "Your Account Is Locked");
					            return "login";
					        }
				          else
			                 {
				                  result.get(0).setCount(3);
				                  session.beginTransaction().commit();
				                  System.out.println("Db password : " + result.get(0).getPassword());
				                  System.out.println("Jsp password : " + s.getPassword());
				model.addAttribute("message","successfully login");
				model.addAttribute("Name", result.get(0).getUsername());
				model.addAttribute("Email", result.get(0).getEmail());
				model.addAttribute("Mobile", result.get(0).getMobile());
				return "loginsuccess";
			}
		}
		     }
		 else {
			Integer count=result.get(0).getCount();
			if(count>0)
			count=--count;
			
			result.get(0).setCount(count);
			session.beginTransaction().commit();
			System.out.println(count);
			
			System.out.println("Db password : " + result.get(0).getPassword());
			System.out.println("Jsp password : " + s.getPassword());
			
			model.addAttribute("message","login failed : Remained "+count+"times" );
			if(count==0){
				System.out.println(result.get(0).getLocked());
				result.get(0).setLocked("on");
				session.beginTransaction().commit();
				System.out.println(result.get(0).getLocked());
				model.addAttribute("message","Your Account is Locked" );
				
			}
			return "login";
		}
	}
	public String unlockUser(Student s,Model model) {
		System.out.println("--------");
		Session session = new AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory().openSession();
		Query query = session.createQuery("UPDATE Student set locked = :locked,count=:count WHERE username = :username");
		query.setParameter("locked","off");
		query.setParameter("count",3);
		query.setParameter("username",s.getUsername());
		int result = query.executeUpdate();
		System.out.println("result:"+result);
		Transaction transaction = session.beginTransaction();
		transaction.commit();
		model.addAttribute("message","Succesfully Unlocked the account");
		return "unlock";

	}
}
	
	


