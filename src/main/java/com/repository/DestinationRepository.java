package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.DestinationEntity;

@Repository
public interface DestinationRepository extends JpaRepository<DestinationEntity, Long>{
//	@Query("select * from ")
	List<DestinationEntity> findByUsers_id(Long id);
}
