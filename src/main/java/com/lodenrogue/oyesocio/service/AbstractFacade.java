package com.lodenrogue.oyesocio.service;

import java.util.List;

import com.lodenrogue.oyesocio.persistance.EntityManager;

public abstract class AbstractFacade<T> {
	private EntityManager<T> entityManager;

	public AbstractFacade(Class<T> entityClass) {
		entityManager = new EntityManager<T>(entityClass);
	}

	public List<T> findAll() {
		return entityManager.getAll();
	}

	public T find(long id) {
		return entityManager.get(id);
	}

	public T create(T t) {
		return entityManager.create(t);
	}

	public void delete(long id) {
		entityManager.delete(id);
	}

	protected T findUnique(String query) {
		return entityManager.getUnique(query);
	}

	protected void doQuery(String query) {
		entityManager.doQuery(query);
	}

	protected List<T> findAllFromQuery(String query) {
		return entityManager.findAllFromQuery(query);
	}

}
