package com.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pojo.Student;

@Controller
public class UpdateAndDeleteController {

	@RequestMapping(value = "/unlockUser", method = RequestMethod.POST)
	public String updateStudent(@ModelAttribute("studForm") Student stud, Model model) throws ClassNotFoundException, SQLException{
		
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test1", "root", "raju");
		PreparedStatement stmt = con.prepareStatement("UPDATE student SET count = ?, locked = ? where email=?");
		stmt.setInt(1, 3);
		stmt.setString(2, "off");
		stmt.setString(3, stud.getEmail());
		
		int i = stmt.executeUpdate();	
		model.addAttribute("message", "Succefully Update the record!!");
		
		return "unlock";
	}
	
}
