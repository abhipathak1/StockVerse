package com.masai.Dao;

import com.masai.dto.User;
import com.masai.dto.UserPortfolio;
import com.masai.utility.EMUtils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.Collections;
import java.util.List;

public class UserPortfolioDAOImpl implements UserPortfolioDAO {

    @Override
    public void addUserPortfolio(UserPortfolio userPortfolio) {
        EntityManager entityManager = EMUtils.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(userPortfolio);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<UserPortfolio> getUserPortfolioByUser(User user) {
        EntityManager entityManager = EMUtils.getEntityManager();
        try {
            return entityManager
                    .createQuery("SELECT up FROM UserPortfolio up WHERE up.user = :user", UserPortfolio.class)
                    .setParameter("user", user)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            entityManager.close();
        }
    }
}
