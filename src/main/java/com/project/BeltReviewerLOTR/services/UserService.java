package com.project.BeltReviewerLOTR.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.project.BeltReviewerLOTR.models.Guild;
import com.project.BeltReviewerLOTR.models.Ring;
import com.project.BeltReviewerLOTR.models.User;
import com.project.BeltReviewerLOTR.repositories.GuildRepository;
import com.project.BeltReviewerLOTR.repositories.RingRepository;
import com.project.BeltReviewerLOTR.repositories.UserRepository;

@Service
public class UserService {
	private UserRepository uRepository;
	// private RoleRepository roRepository;
	private RingRepository riRepository;
	private GuildRepository gRepository;
	private BCryptPasswordEncoder bCrypt;
		
	public UserService(UserRepository uRepository, RingRepository riRepository, BCryptPasswordEncoder bCrypt, GuildRepository gRepository){
		this.uRepository = uRepository;
		this.riRepository = riRepository;
		this.bCrypt = bCrypt;
		this.gRepository = gRepository;
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
	//find user by id
	public User findByUserId(Long id) {
		return uRepository.findById(id);
	}
	//find user by username
	public User findByUsername(String username) {
		return uRepository.findByUsername(username);
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
	//find ring by name
	public Ring findRingByName(String name){
		return riRepository.findByName(name);
	}
	//update User
	public void updateUser(User user) {
		uRepository.save(user);
	}
	//update Ring
	public void updateRing(Ring ring) {
		riRepository.save(ring);
	}
	//unbind Ring
	public void unbindRing(Long id) {
		Ring ring = riRepository.findById(id);
		ring.setUser(null);
		riRepository.save(ring);
	}
	//find all users for Guild table and update Age
	public List<User> findAllUsersForGuild() {
		List<User> users = uRepository.findAll();
		for(User user : users) {
			LocalDate today = LocalDate.now();
			LocalDate createdAt = user.getCreatedAt().toLocalDate();
			int days = Period.between(createdAt, today).getDays();
			user.setAge(days);
			uRepository.save(user);
		}
		return users;
	}
	//find guild by id
	public Guild findGuildById(Long id) {
		return gRepository.findById(id);
	}
	//create a guild or update a guild
	public void createGuild(Guild guild) {
		Guild g = gRepository.findByName(guild.getName());
		if(g != null) {
			guild.setId(g.getId());
			guild.setUpdatedAt(LocalDateTime.now());
			gRepository.save(guild);
		} else {
			gRepository.save(guild);
		}
		
	}
	//get all the guilds
	public List<Guild> getAllGuilds() {
		return gRepository.findAll();
	}
	//check if a user exists in guild list
	public boolean checkUserInGuild(Long userId, Long guildId) {
		Guild guild = gRepository.findById(guildId);
		
		List<User> users = guild.getUsers();
		for(User user : users) {
			if(user.getId() == userId) {
				System.out.println("hit");
				return true;
			}
		}
		return false;
	}
	//add user to guild
	public void addUserToGuild(Long userId, Long guildId){
		Guild guild = gRepository.findById(guildId);
		User user = uRepository.findById(userId);
		int size = guild.getSize() - 1;
		guild.getUsers().add(user);
		guild.setSize(size);
		gRepository.save(guild);
	}
}
