package com.masai.Service;

import java.util.List;

import com.masai.dto.Stocks;
import com.masai.dto.User;
import com.masai.dto.UserPortfolio;

public interface UserService {

    void addUser(User user);

    User getUserByUsername(String username);

    void addStock(Stocks stock);

    Stocks getStockByStockName(String stockName);

    void editStock(Stocks stock);

    void deleteStock(Stocks stock);

    List<Stocks> getAllStocks();

    void deleteUser(User user);

    List<UserPortfolio> getUserPortfolioByUser(User user);

    void addUserPortfolio(UserPortfolio userPortfolio);

    void editUser(User user);

    List<User> viewAllUsers();

    void addBalanceToWallet(User user, double amountToAdd);

    void changePassword(User user, String newPassword);
}
