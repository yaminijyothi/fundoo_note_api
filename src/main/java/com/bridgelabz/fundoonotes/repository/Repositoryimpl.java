package com.bridgelabz.fundoonotes.repository;

import java.util.List;

import javax.persistence.EntityManager;


import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoonotes.model.UserInfo;
@Repository
public class Repositoryimpl implements UserRepository{
	
@Autowired
private EntityManager manager;

@Override
public UserInfo register(UserInfo info) {
	Session session=manager.unwrap(Session.class);
	session.saveOrUpdate(info);
	return  info;
}

@Override
public UserInfo getUser(String email) {
	Session session=manager.unwrap(Session.class);
	Query<UserInfo> query=session.createQuery("from UserInfo where email=:email");
	query.setParameter("email",email);
	return (UserInfo)query.uniqueResult();
}

@Override
public UserInfo findUserById(long id) {
	Session session=manager.unwrap(Session.class);
	Query<UserInfo> query=session.createQuery("from UserInfo where id=:id");
	query.setParameter("userId", id);
	return (UserInfo) query.uniqueResult();
		
}

@Override
public List<UserInfo> users() {
	Session session=manager.unwrap(Session.class);
	List<UserInfo> list=session.createQuery("from UserInfo").getResultList();
	return list;
}

@Override
public boolean verify(long userId) {
	Session session=manager.unwrap(Session.class);
	Query<UserInfo> query=session.createQuery("update UserInfo set is_verified=:q where userId=:i");
	query.setParameter("q", true);
	query.setParameter("i", userId);
	int i=query.executeUpdate();
	if(i>0) {
		return true;
	}
	return false;

}
}


	
