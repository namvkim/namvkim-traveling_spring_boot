package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.NewEntity;

public interface NewRepository extends JpaRepository<NewEntity, Long> {

}
