package com.project.BeltReviewerLOTR.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.BeltReviewerLOTR.models.User;

@Repository 												
public interface UserRepository extends CrudRepository<User,Long>{
	List<User> findAll();
	User findByEmail(String email);
}
