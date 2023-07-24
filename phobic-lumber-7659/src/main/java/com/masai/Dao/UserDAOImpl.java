package com.masai.Dao;

import java.util.ArrayList;
import java.util.List;

import com.masai.dto.Stocks;
import com.masai.dto.User;
import com.masai.utility.EMUtils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class UserDAOImpl implements UserDAO {

	@Override
	public void addUser(User user) {
		EntityManager em = EMUtils.getEntityManager();
		EntityTransaction transaction = null;

		try {
			transaction = em.getTransaction();
			transaction.begin();
			em.persist(user);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	@Override
	public User getUserByUsername(String username) {
		EntityManager em = EMUtils.getEntityManager();

		try {
			String hql = "FROM User u WHERE u.username = :username";
			return em.createQuery(hql, User.class).setParameter("username", username).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.close();
		}
	}

	@Override
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

	@Override
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

	@Override
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

	@Override
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

	@Override
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

	@Override
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

	@Override
	public void editUser(User user) {
		EntityManager entityManager = EMUtils.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();

		try {
			transaction.begin();
			entityManager.merge(user);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
	}

	@Override
	public List<User> viewAllUsers() {
		EntityManager entityManager = EMUtils.getEntityManager();
		try {
			return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		} finally {
			entityManager.close();
		}
	}

	@Override
	public void addBalanceToWallet(User user, double amountToAdd) {
		EntityManager entityManager = EMUtils.getEntityManager();
		EntityTransaction transaction = null;

		try {
			transaction = entityManager.getTransaction();
			transaction.begin();

			User updatedUser = entityManager.find(User.class, user.getUserId());
			double currentBalance = updatedUser.getWalletBalance();
			double newBalance = currentBalance + amountToAdd;
			updatedUser.setWalletBalance(newBalance);

			entityManager.merge(updatedUser);
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

	@Override
	public void changePassword(User user, String newPassword) {
		EntityManager entityManager = EMUtils.getEntityManager();
		EntityTransaction transaction = null;

		try {
			transaction = entityManager.getTransaction();
			transaction.begin();
			user.setPassword(newPassword);
			entityManager.merge(user);
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
