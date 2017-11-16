package com.project.BeltReviewerLOTR.controllers;

import java.security.Principal;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
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
import com.project.BeltReviewerLOTR.models.Guild;
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
	public String index(@Valid @ModelAttribute("user") User user, @ModelAttribute("success") String success, @ModelAttribute("emailError") String emailError, @ModelAttribute("usernameError") String usernameError, @RequestParam(value="error", required=false) String error, @RequestParam(value="logout", required=false) String logout, Model model, Principal principal){
		// if(principal != null){
		// 	return "redirect:/dashboard";
		// }
		if(error != null) {
			model.addAttribute("errorMessage", "Invalid Credentials, Please try again.");
		}
		if(logout != null) {
			model.addAttribute("logoutMessage", "Logout Successful!");
		}
		model.addAttribute("success", success);
		model.addAttribute("emailError", emailError);
		model.addAttribute("usernameError", usernameError);
		return "index";
	}	
	@PostMapping("/register")
	public String registration(@Valid @ModelAttribute("user") User user, BindingResult res, Model model, RedirectAttributes rAttributes) {
		System.out.println(user.getEmail());
		if(uService.findByEmail(user.getEmail()) != null) {
			rAttributes.addFlashAttribute("dupError", "Email is already in use! Login or try another email");
			return "redirect:/";
		}
		if(uService.findByUsername(user.getUsername()) != null) {
			rAttributes.addFlashAttribute("dupError", "Username is already in use! Try another Username!");
			return "redirect:/";
		}
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

	@RequestMapping("/admin/guild")
	public String guilds(Principal principal, Model model, @ModelAttribute("guild") Guild guild, @ModelAttribute("userGuildError") String userGuildError) {
		String email = principal.getName();
		User curUser = uService.findByEmail(email);
		// System.out.println(email);
		model.addAttribute("currentUser", curUser);
		List<User> users = uService.findAllUsersForGuild();
		model.addAttribute("users", users);
		model.addAttribute("guilds", uService.getAllGuilds());
		return "showGuild";
	}
	
	@PostMapping("/admin/addguild") 
	public String addGuild(@Valid @ModelAttribute("guild") Guild guild, BindingResult res, Model model){
		if(res.hasErrors()) {
			return "showGuild";
		}
		uService.createGuild(guild);
		return "redirect:/admin/guild";
	}

	@PostMapping("/admin/addusertoguild")
	public String addUserToGuild(Model model, @RequestParam("user_id") Long userId, @RequestParam("guild_id") Long guildId, RedirectAttributes rAttributes) {
		if(uService.checkUserInGuild(userId, guildId) == true) {
			rAttributes.addFlashAttribute("userGuildError", "User is already in the guild!");
			return "redirect:/admin/guild";
		}
		if(uService.findGuildById(guildId).getSize() == 0) {
			rAttributes.addFlashAttribute("userGuildError", "Guild is full choose another guild or increase max size!");
			return "redirect:/admin/guild";
		}
		uService.addUserToGuild(userId, guildId);
		return "redirect:/admin/guild";

	}
}