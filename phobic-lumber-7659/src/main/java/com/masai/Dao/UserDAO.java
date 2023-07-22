package com.masai.Dao;

import java.util.ArrayList;
import java.util.List;

import com.masai.dto.Stocks;
import com.masai.dto.User;
import com.masai.utility.EMUtils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class UserDAO {
	
	public void addUser(User user) {
		EntityManager entityManager = EMUtils.getEntityManager();
		EntityTransaction transaction = null;

		try {
			transaction = entityManager.getTransaction();
			transaction.begin();
			entityManager.persist(user);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
	}

	public User getUserByUsername(String username) {
		EntityManager entityManager = EMUtils.getEntityManager();

		try {
			String hql = "FROM User u WHERE u.username = :username";
			return entityManager.createQuery(hql, User.class).setParameter("username", username).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			entityManager.close();
		}
	}

	public void addStock(Stocks stock) {
		EntityManager entityManager = EMUtils.getEntityManager();
		EntityTransaction transaction = null;

		try {
			transaction = entityManager.getTransaction();
			transaction.begin();
			entityManager.persist(stock);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
	}

	public Stocks getStockByStockName(String stockName) {
		EntityManager entityManager = EMUtils.getEntityManager();

		try {
			String hql = "FROM Stocks s WHERE s.stockName = :stockName";
			return entityManager.createQuery(hql, Stocks.class).setParameter("stockName", stockName).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			entityManager.close();
		}
	}

	public void editStock(Stocks stock) {
		EntityManager entityManager = EMUtils.getEntityManager();
		EntityTransaction transaction = null;

		try {
			transaction = entityManager.getTransaction();
			transaction.begin();
			entityManager.merge(stock);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
	}

	public void deleteStock(Stocks stock) {
		EntityManager entityManager = EMUtils.getEntityManager();
		EntityTransaction transaction = null;

		try {
			transaction = entityManager.getTransaction();
			transaction.begin();
			entityManager.remove(entityManager.merge(stock));
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
	}
	
	public List<Stocks> getAllStocks() {
	    EntityManager entityManager = EMUtils.getEntityManager();

	    try {
	        String hql = "FROM Stocks";
	        return entityManager.createQuery(hql, Stocks.class).getResultList();
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new ArrayList<>(); 
	    } finally {
	        entityManager.close();
	    }
	}
	
	public void deleteUser(User user) {
        EntityManager entityManager = EMUtils.getEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.remove(entityManager.merge(user));
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }


}
