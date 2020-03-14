package com.bridgelabz.fundoonotes.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoonotes.model.Notes;
import com.bridgelabz.fundoonotes.response.StatusRes;
import com.bridgelabz.fundoonotes.service.CollabratorService;

@RestController
public class CollabratorController {
	@Autowired
	private CollabratorService service;
	
	//API for adding collabrator
	@PostMapping("collabrator/addCollabrator/{token}/{noteId}")
   public ResponseEntity<StatusRes> addCollabrator(@RequestParam("email") String email,@RequestHeader("token") String token,@RequestParam("noteId")long noteId)
   {
		Optional<Notes> note=service.addCollabrator(email,token,noteId);
		if(note!=null) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new StatusRes("colabrator added",200,note));
			
		}
		return null;
	}
	
	//API for get all collabrator
	@GetMapping("collabrator/getAllCollabrators/{token}")
	public ResponseEntity<StatusRes> getCollabrators(@PathVariable("token")String token){
		List<Notes> note=service.getAllCollabrator(token);
		if(note!=null) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new StatusRes("list of all collabrators",200,note));
				}
		return null;
		}
	
	//API for remove collabrator
	@DeleteMapping("collabrator/removeCollabrators/{token}/{email}/{noteId}")
	public ResponseEntity<StatusRes> removeCollabrators(@PathVariable("email")String email,@PathVariable("token")String token,@PathVariable("noteId")long noteId){
		service.removeCollabrator(email,token,noteId);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new StatusRes("removed collabrator successfully",200));
		}
	
}
