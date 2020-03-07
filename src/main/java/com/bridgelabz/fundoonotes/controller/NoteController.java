package com.bridgelabz.fundoonotes.controller;
/**
 * purpose : crud opearations on notes
 * File    : NoteController.java
 * author  : yamini
 */
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

import com.bridgelabz.fundoonotes.dto.NoteDto;
import com.bridgelabz.fundoonotes.dto.NoteUpdate;
import com.bridgelabz.fundoonotes.model.Notes;
import com.bridgelabz.fundoonotes.response.StatusRes;
import com.bridgelabz.fundoonotes.service.NoteService;

@RestController
public class NoteController {
	@Autowired
	private NoteService service;

	// API for create note
	@PostMapping("/note/createnote/{token}")
	public ResponseEntity<StatusRes> create(@RequestBody NoteDto data,@PathVariable("token") String token) {
		Notes note =service.createNote(data, token);
			return ResponseEntity.status(HttpStatus.CREATED).body(new StatusRes("successfully created notes", 200, note));

	}
	// API for update note
	@PutMapping("/note/update/{token}")
	public ResponseEntity<StatusRes> update(@RequestBody NoteUpdate data,@PathVariable("token") String token ) {
		Notes note =service.updateNote(data, token);
		return ResponseEntity.status(HttpStatus.CREATED).body(new StatusRes("successfully updated notes", 200,note));
	}
	// API for delete note
	@DeleteMapping("/note/deletenote/{id}/{token}")
	public ResponseEntity<StatusRes> delete(@PathVariable long id,@PathVariable("token") String token) {
		Notes note =service.deleteNote(id, token);		
			return ResponseEntity.status(HttpStatus.OK).body(new StatusRes("successfully deleted notes", 200));
	}
	//API for getting all notes
	@GetMapping("/note/allnotes")
	public ResponseEntity<StatusRes> getAllnotes() {
		List<Notes> list =service.getAllNotes();
		return ResponseEntity.status(HttpStatus.OK).body(new StatusRes("all notes", 200,list));
	}
	//API for get notes by userid
	@GetMapping("/note/getNotebyUserId/{token}")
	public ResponseEntity<StatusRes> getNoteByUserId(@PathVariable("token") String token) {
		List<Notes> list=service.getNoteByUserId(token);
		return ResponseEntity.status(HttpStatus.OK).body(new StatusRes("notes of particular user", 200,list));
	}
	//API for getting singlenote
	@GetMapping("/note/getNote/{id}")
	public ResponseEntity<StatusRes> getNote(@PathVariable("id") long noteId) {
		Notes note=service.getNote(noteId);
			return ResponseEntity.status(HttpStatus.OK).body(new StatusRes("notes based on note id", 200,note));
	}
	//API for getting trashed notes
	@GetMapping("note/trashednotes/{token}")
	public ResponseEntity<StatusRes> getTrashedNotes(@PathVariable("token")String token){
		List<Notes> note=service.getTrashed(token);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new StatusRes("trashed notes", 200,note));
		
	} 





}
