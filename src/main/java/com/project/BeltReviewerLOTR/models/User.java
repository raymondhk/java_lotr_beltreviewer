package com.project.BeltReviewerLOTR.models;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="users")
public class User{
	@Id
	@GeneratedValue
	private Long id;
	
	@Size(min=2, message="Username must be at least 2 characters long")
	private String username;

	@Email
	private String email;

	private String password;

	@Transient
	private String passwordConfirmation;

	@Column(updatable=false)
	@DateTimeFormat(pattern="MM:dd:yyyy HH:mm:ss")
	private Date createdAt;
	
	@DateTimeFormat(pattern="MM:dd:yyyy HH:mm:ss")
	private Date updatedAt;

	// @ManyToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	// @JoinTable(
	// 	name="users_roles",
	// 	joinColumns=@JoinColumn(name="user_id"),
	// 	inverseJoinColumns=@JoinColumn(name="role_id")
	// )
	// private List<Role> roles;

	private int permissionLevel;

	@OneToMany(mappedBy="user", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Ring> rings;

	@PrePersist
	public void onCreate(){this.createdAt = new Date();}
	@PreUpdate
	public void onUpdate(){this.updatedAt = new Date();}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	// Setters and Getters go here

	public User(){}
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the passwordConfirmation
	 */
	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}
	/**
	 * @param passwordConfirmation the passwordConfirmation to set
	 */
	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}
	// /**
	//  * @return the roles
	//  */
	// public List<Role> getRoles() {
	// 	return roles;
	// }
	// /**
	//  * @param roles the roles to set
	//  */
	// public void setRoles(List<Role> roles) {
	// 	this.roles = roles;
	// }
	/**
	 * @return the permissionLevel
	 */
	public int getPermissionLevel() {
		return permissionLevel;
	}
	/**
	 * @param permissionLevel the permissionLevel to set
	 */
	public void setPermissionLevel(int permissionLevel) {
		this.permissionLevel = permissionLevel;
	}
	/**
	 * @return the rings
	 */
	public List<Ring> getRings() {
		return rings;
	}
	/**
	 * @param rings the rings to set
	 */
	public void setRings(List<Ring> rings) {
		this.rings = rings;
	}
}
