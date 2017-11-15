package com.project.BeltReviewerLOTR.controllers;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.project.BeltReviewerLOTR.models.Ring;
import com.project.BeltReviewerLOTR.models.User;
import com.project.BeltReviewerLOTR.services.UserService;

@Controller
public class Rings{
	private UserService uService;

	public Rings(UserService uService){
		this.uService = uService;
	}
	
	@RequestMapping("/ring")
	public String addRing(@Valid @ModelAttribute("ring") Ring ring, Principal principal, Model model){
		String email = principal.getName();
		User curUser = uService.findByEmail(email);
		model.addAttribute("currentUser", curUser);
		return "addRing";
	}	

	@PostMapping("/ring")
	public String forgeRing(@Valid @ModelAttribute("ring") Ring ring, Principal principal, Model model, BindingResult res) {
		if(res.hasErrors()) {
			return "addRing";
		}
		String email = principal.getName();
		User curUser = uService.findByEmail(email);
		uService.createRing(ring, curUser.getUsername());
		return "redirect:/dashboard";
	}

	@PostMapping("/addRing")
	public String bindRing(@RequestParam(value="rings") Long ringId, Principal principal) {
		String email = principal.getName();
		User curUser = uService.findByEmail(email);
		List<Ring> bindedRings = curUser.getRings();
		bindedRings.add(uService.getRing(ringId));
		uService.updateUser(curUser);
		return "redirect:/dashboard";
		}

}
