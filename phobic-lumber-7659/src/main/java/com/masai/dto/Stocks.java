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
@Table(name = "stocks")
public class Stocks {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "stock_id")
	private Long stockId;

	@Column(name = "stock_name", nullable = false)
	private String stockName;

	@Column(name = "stock_price", nullable = false)
	private double stockPrice;

	public Stocks() {
		super();
	}

	public Stocks(Long stockId, String stockName, double stockPrice, List<UserPortfolio> userPortfolioList) {
		super();
		this.stockId = stockId;
		this.stockName = stockName;
		this.stockPrice = stockPrice;
		this.userPortfolioList = userPortfolioList;
	}

	public Long getStockId() {
		return stockId;
	}

	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public double getStockPrice() {
		return stockPrice;
	}

	public void setStockPrice(double stockPrice) {
		this.stockPrice = stockPrice;
	}

	public List<UserPortfolio> getUserPortfolioList() {
		return userPortfolioList;
	}

	public void setUserPortfolioList(List<UserPortfolio> userPortfolioList) {
		this.userPortfolioList = userPortfolioList;
	}

	// One-to-Many relationship with UserPortfolio
	@OneToMany(mappedBy = "stock", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<UserPortfolio> userPortfolioList;
}
