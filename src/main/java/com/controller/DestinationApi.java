package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.DestinationDTO;
import com.service.IDestinationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin  //tăng bảo mật
@RestController  // định nghĩa là controler viết api
@Api(value="onlinestore", description="Operations pertaining to products in Online Store") // mô tả nhóm api
@RequestMapping("/api")
public class DestinationApi {
	@Autowired
	private IDestinationService destinationService;
	
	@ApiOperation(value = "View a list of available destination", response = Iterable.class) // mô tả phương thức get
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved list"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	} //chú thích lỗi
	)
	@GetMapping(value = "/destination")
	@PreAuthorize("hasRole('USER')")
	public List<DestinationDTO> getDestination() {
		return destinationService.get();
	}
	
	@GetMapping(value = "/destination/{id}")
	@PreAuthorize("hasRole('USER')")
	public DestinationDTO getOneDestination(@PathVariable("id") Long id) {
		return destinationService.get(id);
	}
	
	@PostMapping(value = "/destination")
	@PreAuthorize("hasRole('USER')")
	public List<DestinationDTO> createDestination(HttpServletRequest request, @RequestBody DestinationDTO model) {
		destinationService.save(request,model);
		return destinationService.getMyDestination(request);
	}
	
	@PutMapping(value="/destination/{id}")
	@PreAuthorize("hasRole('USER')")
	public List<DestinationDTO> updateDestination(HttpServletRequest request, @RequestBody DestinationDTO model,@PathVariable("id") Long id) {
		model.setId(id);
		destinationService.save(request,model);
		return destinationService.get();
	}
	
	@DeleteMapping(value="/destination")
	@PreAuthorize("hasRole('USER')")
	public List<DestinationDTO> deleteDestination(HttpServletRequest request,@RequestBody Long[] ids) {
		destinationService.delete(ids);
		return destinationService.getMyDestination(request);
	}
	
	@GetMapping(value = "/destination/mydestination")
	@PreAuthorize("hasRole('USER')")
	public List<DestinationDTO> getMyDestination(HttpServletRequest request) {
		return destinationService.getMyDestination(request);
	}
}
