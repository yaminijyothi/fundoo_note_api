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
import org.springframework.web.bind.annotation.RequestHeader;
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
	@PostMapping("/note/createnote")
	public ResponseEntity<StatusRes> create(@RequestBody NoteDto data,@RequestHeader("token") String token) {
		Notes note =service.createNote(data, token);
		if (note!=null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(new StatusRes("successfully created notes", 200, data));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new StatusRes("failed to created notes", 400, data));
	}
	// API for update note
	@PutMapping("/note/update")
	public ResponseEntity<StatusRes> update(@RequestBody NoteUpdate data,@RequestHeader("token") String token ) {
		Notes note =service.updateNote(data, token);
		if (note!=null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(new StatusRes("successfully updated notes", 200, data));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new StatusRes("failed to updated notes", 400, data));
	}
	// API for delete note
	@DeleteMapping("/note/deletenote{id}")
	public ResponseEntity<StatusRes> delete(@PathVariable long id,@RequestHeader("token") String token) {
		Notes note =service.deleteNote(id, token);		
		if (note!=null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(new StatusRes("successfully deleted notes", 200));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new StatusRes(" notes are not deleted", 400));
	}
	//API for getting all notes
	@GetMapping("/note/allnotes")
	public ResponseEntity<StatusRes> getAllnotes() {
		List<Notes> list =service.getAllNotes();
		return ResponseEntity.status(HttpStatus.CREATED).body(new StatusRes("all notes", 200,list));
	}
	//API for get notes by userid
	@GetMapping("/note/getNotebyUserId{token}")
	public ResponseEntity<StatusRes> getNoteByUserId(@PathVariable("token") String token) {
		List<Notes> list=service.getNoteByUserId(token);
		if (list!=null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(new StatusRes("notes of particular user", 200,list));
		}
		return null;
	}
	//API for getting singlenote
	@GetMapping("/note/getNote{id}")
	public ResponseEntity<StatusRes> getNote(@PathVariable("id") long noteId) {
		Notes note=service.getNote(noteId);
		if (note!=null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(new StatusRes("notes based on note id", 200,note));
		}
		return null;
	}





}
