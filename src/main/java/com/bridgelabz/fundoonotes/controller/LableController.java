package com.bridgelabz.fundoonotes.controller;
/*
 * purpose : creating lables 
 * File    : LableController.java
 * author  :yamini
 * */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoonotes.dto.LableDto;
import com.bridgelabz.fundoonotes.model.Lables;
import com.bridgelabz.fundoonotes.response.StatusRes;
import com.bridgelabz.fundoonotes.service.LableService;
@RestController
public class LableController {
	@Autowired
	private LableService service;
	
	
	//API for creating lables
	@PostMapping("lable/addLable/{token}")
	public ResponseEntity<StatusRes> addLable(@RequestBody LableDto data,@PathVariable("token") String token){
		Lables lab=service.createLable(data, token);
			return ResponseEntity.status(HttpStatus.CREATED).body(new StatusRes("lable created",200,lab));
	}


	//API for update the lable
	@PutMapping("lable/updateLable/{token}")
	public ResponseEntity<StatusRes> updateLable(@RequestBody LableDto data,@PathVariable("token")String token){
		Lables lab=service.updateLable(data, token);
		
			return ResponseEntity.status(HttpStatus.CREATED).body(new StatusRes("lable updated",200,lab));
	}

	//API for deleting lable
	@DeleteMapping("lable/deleteLable/{LableId}/{token}")
	public ResponseEntity<StatusRes> deleteLable(@PathVariable("LableId")long LableId,@PathVariable("token")String token){
		Lables lab=service.deleteLable(LableId, token);
			return ResponseEntity.status(HttpStatus.OK).body(new StatusRes("lable deleted",200,lab));
	}

	//API for getting all lables
	@GetMapping("lable/getAllLables")
	public  ResponseEntity<StatusRes> getAllLables() {
		List<Lables> lab=service.getAllLables();
			return ResponseEntity.status(HttpStatus.OK).body(new StatusRes("all Lables are",200,lab));
	}

	
	//API for getting lable by userId
  @GetMapping("lable/getLablebyUserId/{token}")
	public ResponseEntity<StatusRes> getLableUserId(@PathVariable("token")String token){
		List<Lables> lab=service.getLableByUserId(token);
			return ResponseEntity.status(HttpStatus.CREATED).body(new StatusRes("lables of given user",200,lab));
	}
  
  //API for getting single lable
  @GetMapping("lable/getLable/{LableId}")
  public ResponseEntity<StatusRes> getLable(@PathVariable("LableId")long LableId){
	  Lables lab=service.getLable(LableId);
	  	return ResponseEntity.status(HttpStatus.CREATED).body(new StatusRes("single lable",200,lab));
  }
}



