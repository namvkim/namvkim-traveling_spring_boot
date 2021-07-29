package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.entity.NewEntity;
import com.repository.NewJDBCRepository;

@CrossOrigin
@RestController
public class NewApiJbdc {
	@Autowired
	protected NewJDBCRepository newJDBCRepository;
	
	@GetMapping(value = "/api/jdbc/new")
	public List<NewEntity> findAll(){
		return newJDBCRepository.findAll();
	}
	
	@GetMapping(value = "/api/jdbc/new/{id}")
	public  List<NewEntity> findById(@PathVariable Long id){
		return newJDBCRepository.findById(id);
	}
	
	@PostMapping(value = "/api/jdbc/new")
	public int insert(@RequestBody NewEntity model) {
		return newJDBCRepository.insert(model);
	}
	
	@PutMapping(value="/api/jdbc/new/{id}")
	public int update(@RequestBody NewEntity model,@PathVariable("id") Long id) {
		model.setId(id);
		return newJDBCRepository.update(model);
	}
	
	@DeleteMapping(value="/api/jdbc/new/{id}")
	public int delete(@PathVariable Long id) {
		return newJDBCRepository.deleteById(id);
	}
}
