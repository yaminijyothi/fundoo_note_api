package com.bridgelabz.fundoonotes.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import com.bridgelabz.fundoonotes.model.Note;

public class NoteRepoImpl implements NoteRepo {
   @Autowired
   private  EntityManager manager;
	@Override
	public Note save(Note data) {
		Session session=manager.unwrap(Session.class);
		session.save(data);
		return data;
	}

	@Override
	public Note findById(long id) {
		Session session=manager.unwrap(Session.class);
		Query<Note> query=session.createQuery("from Note where id=:id");
		query.setParameter("userId",id);
		return (Note)query.uniqueResult();
	}

	@Override
	public boolean delete(long id, long userId) {
		Session session=manager.unwrap(Session.class);
		Query<Note> query=session.createQuery("delete Note where id=:id");
		query.setParameter("id",userId);
		return  true;
	}

	@Override
	public List<Note> notes() {
		Session session=manager.unwrap(Session.class);
		List<Note> list=session.createQuery("from Note").getResultList();
		return list;
	}

	@Override
	public List<Note> restore(long userId) {
		Session session=manager.unwrap(Session.class);
		List<Note> list=session.createQuery("from Note where userId=:userId & isTrashed=1").getResultList();
	   return list;
	}

	@Override
	public List<Note> getNotes(long userId) {
		
		return null;
	}

	@Override
	public List<Note> getArchieved(long userId) {
		
		return null;
	}

	@Override
	public List<Note> getPinned(long userId) {
		
		
		return null;
	}

	@Override
	public boolean updateColour(long id, long userId, String colour) {
		Session session=manager.unwrap(Session.class);
		Query<Note> query=session.createQuery("update Note where colour=:colour");
		int i=query.executeUpdate();
		if(i>0) {
			return true;
		}
		return false;
	}

}
