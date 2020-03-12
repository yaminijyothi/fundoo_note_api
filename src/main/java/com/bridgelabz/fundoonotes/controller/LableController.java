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
	@PostMapping("lable/createLable/{token}")
	public ResponseEntity<StatusRes> createLable(@RequestBody LableDto data,@PathVariable String token){
		Lables lab=service.createLable(data, token);
		return ResponseEntity.status(HttpStatus.CREATED).body(new StatusRes("lable created",200,lab));
	}
	//API for deleting lable
	@DeleteMapping("lable/deleteLable/{LableId}/{token}")
	public ResponseEntity<StatusRes> deleteLable(@PathVariable long lableId,@PathVariable("token")String token){
		service.deleteLable(lableId, token);
		return ResponseEntity.status(HttpStatus.OK).body(new StatusRes("lable deleted",200));
	}
	//API for getting all lables
	@GetMapping("lable/getAllLables")
	public  ResponseEntity<StatusRes> getAllLables() {
		List<Lables> lab=service.getAllLables();
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new StatusRes("all Lables are",200,lab));
	}
	//API for getting lable by userId
	@GetMapping("lable/getLablebyUserId/{token}")
	public ResponseEntity<StatusRes> getLableUserId(@PathVariable String token){
		List<Lables> lab=service.getLableByUserId(token);
		return ResponseEntity.status(HttpStatus.CREATED).body(new StatusRes("lables of given user",200,lab));
	}
	//API for getting single lable
	@GetMapping("lable/getSingleLable/{LableId}")
	public ResponseEntity<StatusRes> getLable(@PathVariable long LableId){
		Lables lab=service.getLable(LableId);
		return ResponseEntity.status(HttpStatus.CREATED).body(new StatusRes("single lable",200,lab));
	}
	//API for mappping the notes and lables
	@GetMapping("lable/LableMapwithNotes/{lableId}/{noteId}/{token}")
	public ResponseEntity<StatusRes> addLable(@PathVariable long lableId,@PathVariable long  noteId,@PathVariable String token){
		Lables lab=service.addLable(lableId,noteId,token);
		return ResponseEntity.status(HttpStatus.CREATED).body(new StatusRes("mapped lable with note",200,lab));
	}
	//API for sorting lables in ascending order
	@GetMapping("lable/ascendingSort")
	public ResponseEntity<StatusRes> ascendingSort(){
		List<String> list=service.ascendingSort();
		if(list!=null) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new StatusRes("all lables in ascending sort order",200,list));
		}
		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(new StatusRes("sorting failed",400,list));
	}
	//API for sorting lables in descending order
	@GetMapping("lable/descendingSort")
	public ResponseEntity<StatusRes> descendingSort(){
		List<String> list=service.descendingSort();
		if(list!=null) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new StatusRes("all lables in descending sort order",200,list));
		}
		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(new StatusRes("sorting failed",400,list));
	}
	
}



