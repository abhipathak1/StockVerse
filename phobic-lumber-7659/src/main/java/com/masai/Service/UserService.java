package com.masai.Service;

import java.util.List;

import com.masai.Dao.UserDAO;
import com.masai.Dao.UserPortfolioDAO;
import com.masai.dto.Stocks;
import com.masai.dto.User;
import com.masai.dto.UserPortfolio;

public class UserService {
	private UserDAO userDAO;
	private UserPortfolioDAO userPortfolioDAO;

	public UserService(UserDAO userDAO, UserPortfolioDAO userPortfolioDAO) {
		this.userDAO = userDAO;
		this.userPortfolioDAO = userPortfolioDAO;
	}

	public void addUser(User user) {
		userDAO.addUser(user);
	}

	public User getUserByUsername(String username) {
		return userDAO.getUserByUsername(username);
	}

	public void addStock(Stocks stock) {
		userDAO.addStock(stock);
	}

	public Stocks getStockByStockName(String stockName) {
		return userDAO.getStockByStockName(stockName);
	}

	public void editStock(Stocks stock) {
		userDAO.editStock(stock);
	}

	public void deleteStock(Stocks stock) {
		userDAO.deleteStock(stock);
	}

	public List<Stocks> getAllStocks() {
		return userDAO.getAllStocks();
	}

	public void deleteUser(User user) {
		userDAO.deleteUser(user);
	}

	public List<UserPortfolio> getUserPortfolioByUser(User user) {
		return userPortfolioDAO.getUserPortfolioByUser(user);
	}

	public void addUserPortfolio(UserPortfolio userPortfolio) {
		userPortfolioDAO.addUserPortfolio(userPortfolio);
	}

	public void editUser(User user) {
		userDAO.editUser(user);
	}

	public List<User> viewAllUsers() {
		return userDAO.viewAllUsers();
	}
	
	public void addBalanceToWallet(User user, double amountToAdd) {
        userDAO.addBalanceToWallet(user, amountToAdd);
    }
	
	 public void changePassword(User user, String newPassword) {
	        userDAO.changePassword(user, newPassword);
	    }

}
