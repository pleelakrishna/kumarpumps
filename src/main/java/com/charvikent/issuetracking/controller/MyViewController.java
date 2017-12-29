package com.charvikent.issuetracking.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.charvikent.issuetracking.dao.UserDao;
import com.charvikent.issuetracking.model.User;
import com.charvikent.issuetracking.service.ReportIssueService;
@Controller
public class MyViewController {
	@Autowired
	HttpSession session;
	@Autowired
	private ReportIssueService reportIssueService;
	
	@Autowired
	private UserDao userService;
	
	
	@RequestMapping("/myView")
	public String myview(Model model) {
		User objuserBean = (User) session.getAttribute("cacheUserBean");
		
	         if(objuserBean!=null)
	         {
	        	//userService.getUsersUnderReportTo();
	        	//reportIssueService.getIssuesByAssignToUnderMonitor();
		//System.out.println(reportIssueService.getIssuesByAsorysignBy(String.valueOf(objuserBean.getId())));
		
		model.addAttribute("reportedByMe", reportIssueService.getIssuesByAssignBy(String.valueOf(objuserBean.getId())));
		
		model.addAttribute("assignToMe", reportIssueService.getIssuesByAssignTo(String.valueOf(objuserBean.getId())));
		model.addAttribute("assignToMeResolved", reportIssueService.getIssuesByAssignToResolved(String.valueOf(objuserBean.getId())));
		model.addAttribute("gapAndCount", reportIssueService.getGapAndCount());
		//System.out.println("Problem here occured");
		//System.out.println(reportIssueService.getGapAndCount());
		model.addAttribute("recentlyModified", reportIssueService.getRecentlyModified(String.valueOf(objuserBean.getId())));
		
	     model.addAttribute("statusCount" ,reportIssueService.getCountByStatusWise());
	     model.addAttribute("monitoryBy",reportIssueService.getIssuesByAssignToUnderMonitor(String.valueOf(objuserBean.getId())));
		
		return "myView";
	         }
	         
	         else 
	        	 return "redirect:/"; 
		
	}

}