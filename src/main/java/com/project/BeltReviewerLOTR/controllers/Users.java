package com.project.BeltReviewerLOTR.controllers;

import java.security.Principal;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.project.BeltReviewerLOTR.models.User;
import com.project.BeltReviewerLOTR.services.UserDetailsServiceImplementation;
import com.project.BeltReviewerLOTR.services.UserService;
import com.project.BeltReviewerLOTR.validators.UserValidator;

@Controller
public class Users{
	private UserService uService;
	private UserDetailsServiceImplementation uDetails;
	private UserValidator uValidator;

	public Users(UserService uService, UserDetailsServiceImplementation uDetails, UserValidator uValidator){
		this.uService = uService;
		this.uDetails = uDetails;
		this.uValidator = uValidator;
	}
	
	@RequestMapping("")
	public String index(@Valid @ModelAttribute("user") User user, @ModelAttribute("success") String success, @RequestParam(value="error", required=false) String error, @RequestParam(value="logout", required=false) String logout, Model model){
		if(error != null) {
			model.addAttribute("errorMessage", "Invalid Credentials, Please try again.");
		}
		if(logout != null) {
			model.addAttribute("logoutMessage", "Logout Successful!");
		}
		model.addAttribute("success", success);
		return "index";
	}	
	@PostMapping("/register")
	public String registration(@Valid @ModelAttribute("user") User user, BindingResult res, Model model, RedirectAttributes rAttributes) {
		uValidator.validate(user, res);
		if(res.hasErrors()) {
			return "index";
		}
		if(uService.findAllUsers().size() == 0) {
			uService.saveWithSuperAdminRole(user);
		} else {
			uService.saveWithUserRole(user);
		}
		rAttributes.addFlashAttribute("success", "Thank you for registering. I am sorry but please log in now.");
		return "redirect:/";
	}
	@RequestMapping("/dashboard")
	public String dashboard(Principal principal, Model model) {
		String email = principal.getName();
		User curUser = uService.findByEmail(email);
		// System.out.println(email);
		model.addAttribute("currentUser", curUser);
		model.addAttribute("availableRings", uService.availableRings());
		model.addAttribute("adminRings", uService.createdRings(curUser.getUsername()));
		return "dashboard";
	}
	
}
