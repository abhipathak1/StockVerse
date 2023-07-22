package com.masai.dto;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userId;

	@Column(name = "name")
	private String name;

	@Column(name = "username", unique = true, nullable = false)
	private String username;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "wallet_balance")
	private double walletBalance;

	public User() {
		super();
	}

	public User(Long userId, String name, String username, String password, double walletBalance,
			List<UserPortfolio> userPortfolioList) {
		super();
		this.userId = userId;
		this.name = name;
		this.username = username;
		this.password = password;
		this.walletBalance = walletBalance;
		this.userPortfolioList = userPortfolioList;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getWalletBalance() {
		return walletBalance;
	}

	public void setWalletBalance(double walletBalance) {
		this.walletBalance = walletBalance;
	}

	public List<UserPortfolio> getUserPortfolioList() {
		return userPortfolioList;
	}

	public void setUserPortfolioList(List<UserPortfolio> userPortfolioList) {
		this.userPortfolioList = userPortfolioList;
	}

	// One-to-Many relationship with UserPortfolio
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<UserPortfolio> userPortfolioList;

	
}
