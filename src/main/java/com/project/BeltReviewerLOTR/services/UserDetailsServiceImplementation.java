package com.project.BeltReviewerLOTR.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.project.BeltReviewerLOTR.models.User;
import com.project.BeltReviewerLOTR.repositories.UserRepository;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService {
	private UserRepository uRepository;
		
	public UserDetailsServiceImplementation(UserRepository uRepository){
		this.uRepository = uRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = uRepository.findByEmail(email);

		if(user == null) {
			throw new UsernameNotFoundException("User not found");
		}

		user.setUpdatedAt(new Date());
		uRepository.save(user);
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthorities(user));
	}

	private List<GrantedAuthority> getAuthorities(User user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		if(user.getPermissionLevel() <= 2) {
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}
		if(user.getPermissionLevel() == 1) {
			authorities.add(new SimpleGrantedAuthority("ROLE_SUPERADMIN"));
		}
		return authorities;
	}
}
