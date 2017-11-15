// package com.project.BeltReviewerLOTR.models;

// import java.util.Date;
// import java.util.List;

// import javax.persistence.CascadeType;
// import javax.persistence.Column;
// import javax.persistence.Entity;
// import javax.persistence.FetchType;
// import javax.persistence.GeneratedValue;
// import javax.persistence.Id;
// import javax.persistence.JoinColumn;
// import javax.persistence.JoinTable;
// import javax.persistence.ManyToMany;
// import javax.persistence.OneToMany;
// import javax.persistence.PrePersist;
// import javax.persistence.PreUpdate;
// import javax.persistence.Table;
// import javax.persistence.Transient;
// import javax.validation.constraints.Size;

// import org.hibernate.annotations.Type;
// import org.hibernate.validator.constraints.Email;
// import org.springframework.format.annotation.DateTimeFormat;

// @Entity
// @Table(name="roles")
// public class Role{
// 	@Id
// 	@GeneratedValue
// 	private Long id;

// 	private String name;
	
// 	@ManyToMany(mappedBy="roles", cascade=CascadeType.ALL)
// 	private List<User> users;

// 	@Column(updatable=false)
// 	@DateTimeFormat(pattern="MM:dd:yyyy HH:mm:ss")
// 	private Date createdAt;
	
// 	@DateTimeFormat(pattern="MM:dd:yyyy HH:mm:ss")
// 	private Date updatedAt;

// 	@PrePersist
// 	public void onCreate(){this.createdAt = new Date();}
// 	@PreUpdate
// 	public void onUpdate(){this.updatedAt = new Date();}
	
// 	public Long getId() {
// 		return id;
// 	}
// 	public void setId(Long id) {
// 		this.id = id;
// 	}
// 	public Date getCreatedAt() {
// 		return createdAt;
// 	}
// 	public void setCreatedAt(Date createdAt) {
// 		this.createdAt = createdAt;
// 	}
// 	public Date getUpdatedAt() {
// 		return updatedAt;
// 	}
// 	public void setUpdatedAt(Date updatedAt) {
// 		this.updatedAt = updatedAt;
// 	}
// 	/**
// 	 * @return the name
// 	 */
// 	public String getName() {
// 		return name;
// 	}
// 	/**
// 	 * @param name the name to set
// 	 */
// 	public void setName(String name) {
// 		this.name = name;
// 	}
	
// 	// Setters and Getters go here

	
	
	
// }
