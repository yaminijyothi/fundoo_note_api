package com.bridgelabz.fundoonotes.service;

import java.util.List;

import com.bridgelabz.fundoonotes.dto.LableDto;
import com.bridgelabz.fundoonotes.model.Lables;

public interface LableService {
	
	Lables createLable(LableDto data,String token);
	Lables updateLable(LableDto data,String token);
	Lables deleteLable(long LableId,String token);
	List<Lables> getAllLables();
	List<Lables> getLableByUserId(String token);
	Lables getLable(long LableId);

}
