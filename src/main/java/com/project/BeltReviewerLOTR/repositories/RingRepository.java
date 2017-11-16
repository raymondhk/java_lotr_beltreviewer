package com.project.BeltReviewerLOTR.repositories;

import com.project.BeltReviewerLOTR.models.Ring;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository 												
public interface RingRepository extends CrudRepository<Ring,Long>{
	List<Ring> findAll();
	List<Ring> findByCreator(String username);
	Ring findByName(String ring);
	Ring findById(Long id);
}
