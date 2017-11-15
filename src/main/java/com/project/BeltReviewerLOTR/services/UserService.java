package com.project.BeltReviewerLOTR.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.project.BeltReviewerLOTR.models.Ring;
import com.project.BeltReviewerLOTR.models.User;
import com.project.BeltReviewerLOTR.repositories.RingRepository;
import com.project.BeltReviewerLOTR.repositories.UserRepository;

@Service
public class UserService {
	private UserRepository uRepository;
	// private RoleRepository roRepository;
	private RingRepository riRepository;
	private BCryptPasswordEncoder bCrypt;
		
	public UserService(UserRepository uRepository, RingRepository riRepository, BCryptPasswordEncoder bCrypt){
		this.uRepository = uRepository;
		this.riRepository = riRepository;
		this.bCrypt = bCrypt;
	}
	//sets user with role user(3)
	public void saveWithUserRole(User user) {
		user.setPassword(bCrypt.encode(user.getPassword()));
		user.setPermissionLevel(3);
		uRepository.save(user);
	}
	//sets user with role admin(2)
	public void saveWithAdminRole(User user) {
		user.setPassword(bCrypt.encode(user.getPassword()));
		user.setPermissionLevel(2);
		uRepository.save(user);
	}
	//sets user with role super_admin(1)
	public void saveWithSuperAdminRole(User user) {
		user.setPassword(bCrypt.encode(user.getPassword()));
		user.setPermissionLevel(1);
		uRepository.save(user);
	}
	//find user by email
	public User findByEmail(String email) {
		return uRepository.findByEmail(email);
	}
	//find all users
	public List<User> findAllUsers() {
		return uRepository.findAll();
	}
	//create ring
	public void createRing(Ring ring, String username) {
		ring.setCreator(username);
		riRepository.save(ring);
	}
	//available rings to pick up
	public List<Ring> availableRings() {
		List<Ring> rings = riRepository.findAll();
		List<Ring> availableRings = new ArrayList<Ring>();
		for(Ring ring : rings) {
			if(ring.getUser() == null) {
				availableRings.add(ring);
			}
		}
		return availableRings;
	}
	//rings created by a user
	public List<Ring> createdRings(String username) {
		return riRepository.findByCreator(username);
	}
	//get one ring
	public Ring getRing(Long id) {
		return riRepository.findById(id);
	}
	//update User
	public void updateUser(User user) {
		uRepository.save(user);
	}
	// public void bindRing(String ringName, User user) {

	// }
}
