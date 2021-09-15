package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, Long>{

//	CommentEntity findOneByCode(String code);
}
