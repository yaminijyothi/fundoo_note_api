package com.bridgelabz.fundoonotes.service;

import java.util.List;

import com.bridgelabz.fundoonotes.dto.LableDto;
import com.bridgelabz.fundoonotes.dto.NoteDto;
import com.bridgelabz.fundoonotes.model.Lables;
import com.bridgelabz.fundoonotes.model.Notes;

public interface LableService {
	
	Lables createLable(LableDto data,String token);
	
	Lables deleteLable(long LableId,String token);
	
	List<Lables> getAllLables();
	
	List<Lables> getLableByUserId(String token);
	
	Lables getLable(long LableId);
	
	Lables addLable(long lableId, long noteId, String token);

	List<String> ascendingSort();

	List<String> descendingSort();
	
}
