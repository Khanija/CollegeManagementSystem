package com.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.support.AbstractMultipartHttpServletRequest;

import com.cms.entity.Schedule;
import com.cms.services.ScheduleService;


@Controller
public class ScheduleController {

	
	@Autowired
	private ScheduleService scheduleService ;
//	
//	@RequestMapping("/schedule")
//	public String welcome(HttpServletRequest request){
//		request.setAttribute("mode","MODE_SCHEDULE");
//		return "schedule";
//	}
	
	
	@RequestMapping("/add-shedule-data")
	public String addcourse(AbstractMultipartHttpServletRequest request) {
		request.setAttribute("mode", "MODE_ADDSCHEDULEDATA");
		return "schedule";
	}
	
	

	@PostMapping("/schedule-saved")
	public String saveCourseIndataBase(@ModelAttribute Schedule schedule, BindingResult binding , AbstractMultipartHttpServletRequest request ){
		System.out.println("mostafaaaa");
		scheduleService.addSchedule(schedule);
		System.out.println("mosaaaaaa");
		request.setAttribute("mode", "MODE_HOME");
		return "welcomecoursepage" ; 
	}
	
//	
//	@RequestMapping("/show-schedule-to-users")
//	public String showScheduletoUsers(HttpServletRequest request) {
//		request.setAttribute("schedule", scheduleService.getSchedule());
//		request.setAttribute("mode", "MODE_SHOWSCHEDULEDATA");
//		return "NewFile";
//	}
	
	@RequestMapping("/show-schedule")
	public String showSchedule(AbstractMultipartHttpServletRequest request) {
		request.setAttribute("schedule", scheduleService.getSchedule());
		request.setAttribute("mode", "MODE_SHOWSCHEDULEDATA");
		return "schedule";
	}
	
	
}