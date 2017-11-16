package com.project.BeltReviewerLOTR.repositories;

import com.project.BeltReviewerLOTR.models.Guild;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository 												
public interface GuildRepository extends CrudRepository<Guild,Long>{
	List<Guild> findAll();
	Guild findByName(String name);
	Guild findById(Long id);
}
