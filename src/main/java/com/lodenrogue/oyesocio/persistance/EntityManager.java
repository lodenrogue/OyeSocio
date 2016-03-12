package com.lodenrogue.oyesocio.persistance;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class EntityManager<T> {
	private Class<T> entityClass;
	private SessionFactory factory;

	public EntityManager(Class<T> entityClass) {
		factory = Database.getInstance().getSessionFactory();
		this.entityClass = entityClass;
	}

	public T create(T t) {
		Session session = factory.openSession();
		session.beginTransaction();
		session.persist(t);
		session.getTransaction().commit();
		session.close();
		return t;
	}

	public T getUnique(String query) {
		Session session = factory.openSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		T object = (T) session.createQuery(query).uniqueResult();
		session.getTransaction().commit();
		session.close();
		return object;
	}

	public List<T> getAll() {
		Session session = factory.openSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<T> objectList = session.createCriteria(entityClass).list();
		session.getTransaction().commit();
		session.close();
		return objectList;
	}

	public T get(long id) {
		Session session = factory.openSession();
		session.beginTransaction();
		T t = (T) session.get(entityClass, id);
		session.getTransaction().commit();
		session.close();
		return t;
	}

	public void delete(long id) {
		Session session = factory.openSession();
		session.beginTransaction();
		T t = session.get(entityClass, id);
		if (t != null) {
			session.delete(t);
		}
		session.getTransaction().commit();
		session.close();
	}

	public void doQuery(String query) {
		Session session = factory.openSession();
		session.beginTransaction();
		session.createQuery(query).executeUpdate();
		session.getTransaction().commit();
		session.close();
	}

	public List<T> findAllFromQuery(String query) {
		Session session = factory.openSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<T> list = session.createQuery(query).list();
		session.getTransaction().commit();
		session.close();
		return list;
	}

	public Session openSession() {
		return factory.openSession();
	}

}
