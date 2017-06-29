package com.interfacedao;

import org.springframework.ui.Model;

import com.pojo.Student;

public interface InterfaceDao {
public String StudentRegister(Student s);

public String loginStudent(Student s, Model model);
public String unlockUser(Student s,Model model);


}
