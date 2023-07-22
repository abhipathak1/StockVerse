package com.masai.Service;

import com.masai.Dao.StocksDao;

public class StockService {
	
	private StocksDao stocksDao;

	public StockService(StocksDao stocksDao) {
		this.stocksDao = stocksDao;
	}
	
//	public void deleteStock(Stocks stock) {
//		stocksDao.deleteStock(stock);
//	}
	
	

}
