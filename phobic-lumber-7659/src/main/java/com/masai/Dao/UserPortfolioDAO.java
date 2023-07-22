package com.masai.Dao;

import com.masai.dto.User;
import com.masai.dto.UserPortfolio;

import java.util.List;

public interface UserPortfolioDAO {
    void addUserPortfolio(UserPortfolio userPortfolio);
    List<UserPortfolio> getUserPortfolioByUser(User user);
}
