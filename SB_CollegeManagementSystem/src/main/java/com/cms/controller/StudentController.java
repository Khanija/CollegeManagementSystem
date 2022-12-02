package com.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.support.AbstractMultipartHttpServletRequest;

import com.cms.services.StudentService;


@Controller
public class StudentController {

	
	@Autowired
	StudentService  studentServcie ; 
	
	@GetMapping("/showstudents")
	public String showAllCourses(AbstractMultipartHttpServletRequest request){
		request.setAttribute("students", studentServcie.getAllStudents());
		request.setAttribute("mode", "ALL_STUDENTS");
		System.out.println("mostafaaaaaaaaa");
		return "allstudentspage" ; 
	}
	
	
	
	@GetMapping("/showstudents/{id}")
	public String  getStudentProfile(@PathVariable("id") int id , AbstractMultipartHttpServletRequest request)
	{
		request.setAttribute("mode","MODE_STUDENT_PROFILE");
		request.setAttribute("student", studentServcie.getStudent(id));
		return "studentprofilepage" ;
	}
}