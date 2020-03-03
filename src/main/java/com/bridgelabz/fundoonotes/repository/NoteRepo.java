package com.bridgelabz.fundoonotes.repository;

import java.util.List;

import com.bridgelabz.fundoonotes.model.Note;

public interface NoteRepo {
  Note save(Note data);
  Note findById(long id);
  boolean delete(long id,long userId);
  List<Note>  notes();
  List<Note> restore(long userId);
  List<Note> getNotes(long userId);
  List<Note> getArchieved(long userId);
  List<Note> getPinned(long userId);
  boolean updateColour(long id,long userId,String colour);
  
  
}
