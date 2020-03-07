package com.bridgelabz.fundoonotes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoonotes.model.Lables;
import com.bridgelabz.fundoonotes.model.Notes;
@Repository
public interface LableRepository extends CrudRepository<Lables, Long>{
	
	@Query(value=" select * from  lables where lable_id=?",nativeQuery = true)
	 Lables findLableById(long LableId);
	@Query(value="select * from lables where user_id =?",nativeQuery = true)
	List<Lables> findLableByUserId(int id);
	@Query(value="select * from notes  where info_user_id=?",nativeQuery=true)
	List<Notes> getAllNotes(long lableId);	

}
