package com.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.support.AbstractMultipartHttpServletRequest;

import com.cms.entity.Course;
import com.cms.services.CourseService;

@Controller
public class CourseController {

	@Autowired
	CourseService courseServcie ; 
	
	@RequestMapping("/welcome")
	public String welcome(AbstractMultipartHttpServletRequest request){
		request.setAttribute("mode","MODE_HOME");
		return "welcomepage";
	}
	
	@GetMapping("/show-courses-to-users/{id}")
	public String  getCourseDetails(@PathVariable("id") int id , AbstractMultipartHttpServletRequest request)
	{
		request.setAttribute("mode","MODE_COURSE_DETAILS");
		request.setAttribute("courseDetails", courseServcie.getCourse(id));
		return "coursedetails" ;
	}
	
	@RequestMapping("/addCourse")
	public String addcourse(AbstractMultipartHttpServletRequest request) {
		request.setAttribute("mode", "MODE_ADDCOURSE");
		return "addcoursepage";
	}
	          
	@PostMapping("/course-saved")
	public String saveCourseIndataBase(@ModelAttribute Course course , BindingResult binding , AbstractMultipartHttpServletRequest request ){
		System.out.println("mostafaaaa");
		courseServcie.addCourse(course);
		System.out.println("mosaaaaaa");
		request.setAttribute("mode", "MODE_HOME");
		return "welcomepage" ; 
	}
	
	@GetMapping("/show-courses")
	public String showAllCourses(AbstractMultipartHttpServletRequest request){
		request.setAttribute("courses", courseServcie.getAllCourses());
		request.setAttribute("mode", "ALL_COURSES");
		return "allcoursestoadminpage" ; 
	}
	
	@GetMapping("/show-courses/{year}")
	public String showAllCourses(@PathVariable("year") String year , AbstractMultipartHttpServletRequest request){
		request.setAttribute("courses", courseServcie.getAllCoursesByYear(year));
		request.setAttribute("mode", "ALL_COURSES");
		return "allcoursestoadminpage" ; 
	}
	
	
	@GetMapping("/show-courses-to-users")
	public String showAllCoursesToUsers(AbstractMultipartHttpServletRequest request){
		request.setAttribute("courses", courseServcie.getAllCourses());
		request.setAttribute("mode", "ALL_COURSES_TO_USERS");
		return "welcomecoursepage" ; 
	}
	
	@GetMapping("/show-courses-to-user/{year}")
	public String showAllCoursesToUsers(@PathVariable("year") String year ,AbstractMultipartHttpServletRequest request){
		request.setAttribute("courses", courseServcie.getAllCoursesByYear(year));
		request.setAttribute("mode", "ALL_COURSES_TO_USERS");
		return "welcomecoursepage" ; 
	}
	

	@RequestMapping("/delete-course")
	public String deleteCourse(@RequestParam int id, AbstractMultipartHttpServletRequest request) {
		courseServcie.deleteCourse(id);
		request.setAttribute("courses", courseServcie.getAllCourses());
		request.setAttribute("mode", "ALL_COURSES");
		return "allcoursestoadminpage";
	}
	
	@RequestMapping("/edit-course")
	public String editCourse(@RequestParam int id,AbstractMultipartHttpServletRequest request) {
		request.setAttribute("course",courseServcie.editCourse(id));
		request.setAttribute("mode", "MODE_UPDATE");
		return "addcoursepage";
	}
}