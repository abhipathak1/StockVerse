package com.masai.Service;

import java.util.List;

import com.masai.Dao.UserDAO;
import com.masai.dto.Stocks;
import com.masai.dto.User;

public class UserService {
	private UserDAO userDAO;

	public UserService(UserDAO userDAO) {
		this.userDAO = userDAO;
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

}
