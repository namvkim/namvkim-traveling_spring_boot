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

import com.dto.NewDTO;
import com.service.INewService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin  //tăng bảo mật
@RestController  // định nghĩa là controler viết api
@Api(value="onlinestore", description="Operations pertaining to products in Online Store") // mô tả nhóm api
public class DestinationApi {
	@Autowired
	private INewService newService;
	
	@ApiOperation(value = "View a list of available products", response = Iterable.class) // mô tả phương thức get
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved list"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	} //chú thích lỗi
	)
	@GetMapping(value = "/api/new")
	public List<NewDTO> getNew() {
		return newService.get();
	}
	
	@PostMapping(value = "/api/new")
	public NewDTO createNew(@RequestBody NewDTO model) {
		return newService.save(model);
	}
	
	@PutMapping(value="/api/new/{id}")
	public NewDTO updateNew(@RequestBody NewDTO model,@PathVariable("id") Long id) {
		model.setId(id);
		return newService.save(model);
	}
	
	@DeleteMapping(value="/api/new")
	public void deleteNew(@RequestBody Long[] ids) {
		newService.delete(ids);
	}
}
