package com.masai.Dao;

import java.util.List;

import com.masai.dto.Stocks;
import com.masai.dto.User;

public interface UserDAO {
	void addUser(User user);

	User getUserByUsername(String username);

	void addStock(Stocks stock);

	Stocks getStockByStockName(String stockName);

	void editStock(Stocks stock);

	void deleteStock(Stocks stock);

	List<Stocks> getAllStocks();

	void deleteUser(User user);

	void editUser(User user);

	List<User> viewAllUsers();
	
	void addBalanceToWallet(User user, double amountToAdd);
	void changePassword(User user, String newPassword);
	
}
