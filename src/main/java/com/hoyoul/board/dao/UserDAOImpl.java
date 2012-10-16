package com.hoyoul.board.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hoyoul.board.dto.User;

@Repository
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	SessionFactory sessionFactory;
	
	public List<User> listUser() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<User> list = session.createQuery("from User").list();
		session.getTransaction().commit();
		session.close();
		return list;
	}

	public int addUser(User user) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		int id = ((Integer) session.save(user)).intValue();
		session.getTransaction().commit();
		session.close();
		return id;
	}

	public User getUser(int id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		//User user = (User) session.load(User.class,id);
		//user.getId();
		User user = (User)session.get(User.class,id);
		session.getTransaction().commit();
		session.close();
		return user;
	}

	public void updateUser(User user) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(user);
		session.getTransaction().commit();
		session.close();
	}

	public void removeUser(int id) {
		User user = getUser(id);
		if(null != user){
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.delete(user);
			session.getTransaction().commit();
			session.close();
		}
	}

	public List<User> listUserByName(String name) {
		String hql="from User e where e.name = :name";
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Query query = session.createQuery(hql);
		query.setParameter("name", name);
		@SuppressWarnings("unchecked")
		List<User> list = query.list();
		
		session.getTransaction().commit();
		session.close();
		return list;
	}
	
	public List<User> listUserByEmail(String email){
		String hql="from User e where e.email = :email";
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Query query = session.createQuery(hql);
		query.setParameter("email", email);
		@SuppressWarnings("unchecked")
		List<User> list = query.list();
		
		session.getTransaction().commit();
		session.close();
		return list;
	}
	
}
