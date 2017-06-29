package com.test;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.interfacedao.InterfaceDao;
import com.pojo.Student;






@Controller
public class TestController {
	
	@Autowired
	private InterfaceDao dao;
	
	
	
	@RequestMapping(value="register" ,method=RequestMethod.POST)
	public String StudentRegister(Student s){
		s.setCount(3);
		s.setLocked("off");
		s.setRole("user");
		
	     String result = dao.StudentRegister(s);
		 return result;
	}
	
	
	@RequestMapping(value="loginStudent",method=RequestMethod.GET)
	public String loginStudent(Student s,Model model){
		System.out.println("Entered into database");
		System.out.println(s.getRole());
		String response = dao.loginStudent(s, model);
		return response;
}
	/*@RequestMapping(value = "unlockUser", method = RequestMethod.POST)
	public String unlockUser(Student s,Model model) {
		String result = dao.unlockUser(s, model);
		return result;
	}*/
}

	
	
	
	

