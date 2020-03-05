package com.bridgelabz.fundoonotes.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoonotes.model.UserInfo;
@Repository
public class RepositoryImpl implements UserRepository {

	@Autowired
	private EntityManager manager;
	//**query for save the user data
	@Override
	public UserInfo register(UserInfo info) {
		Session session=manager.unwrap(Session.class);
		session.saveOrUpdate(info);
		return  info;
		
	}
	//**query to find  user information from user email
	@Override
	public UserInfo getUser(String email) {
		Session session=manager.unwrap(Session.class);
		@SuppressWarnings("unchecked")
		Query<UserInfo> query=session.createQuery("from UserInfo where email=:email");
		query.setParameter("email",email);
		return (UserInfo)query.uniqueResult();
		
	}
	//**query to get all users
	@Override
	public List<UserInfo> users() {
		Session session=manager.unwrap(Session.class);
		@SuppressWarnings("unchecked")
		List<UserInfo> list=session.createQuery("from UserInfo").getResultList();
		return list;
	
	}
	//**query to verify user
	@Override
	public boolean verify(int userId) {
		Session session=manager.unwrap(Session.class);
		@SuppressWarnings("unchecked")
		Query<UserInfo> query=session.createQuery("update UserInfo set isVerified=true where userId=:i");
		query.setParameter("i", userId);
		int i=query.executeUpdate();
		if(i>0) {
			return true;
		}
		return false;
		
	}

	@Override
	public UserInfo findUserById(int userId) {
		Session session=manager.unwrap(Session.class);
		@SuppressWarnings("unchecked")
		Query<UserInfo> query=session.createQuery("from UserInfo where userId=:userId");
		query.setParameter("userId", userId);
		return (UserInfo)query.uniqueResult();
	}
}
