package com.angus.omdc.message;

/**
 * Stock Connect Daily Quota Balance (80)
 * The Stock Connect Daily Quota Balance message provides updates on the Northbound Daily Quota Balance (DQB) for
 * Shanghai-Hong Kong Stock Connect and Shenzhen-Hong Kong Stock Connect separately. Under normal circumstances,
 * the updates are disseminated around every 5 seconds during the trading hours.
 * @author wangjia
 *
 */
public class StockConnectDailyQuotaBalanceMessage extends OmdMessage {
	/**
	 * SH Shanghai Stock Exchange
	 * SZ Shenzhen Stock Exchange
	 */
	String stockConnectMarket; // string2
	/**
	 * NB Northbound trading
	 */
	String tradingDirection; // string2
	/**
	 * DQB in Renminbi (RMB)
	 * 0 when the respective DQB is used
	 * up
	 */
	long dailyQuotaBalance; // int64
	/**
	 * The number of nanoseconds elapsed
	 * since midnight Coordinated Universal
	 * Time (UTC) of January 1, 1970
	 * DailyQuotaBalanceTime precision is
	 * currently provided to the nearest
	 * second.
	 */
	long dailyQuotaBalanceTime; // uint64
	
	
	public String getStockConnectMarket() {
		return stockConnectMarket;
	}
	public void setStockConnectMarket(String stockConnectMarket) {
		this.stockConnectMarket = stockConnectMarket;
	}
	public String getTradingDirection() {
		return tradingDirection;
	}
	public void setTradingDirection(String tradingDirection) {
		this.tradingDirection = tradingDirection;
	}
	public long getDailyQuotaBalance() {
		return dailyQuotaBalance;
	}
	public void setDailyQuotaBalance(long dailyQuotaBalance) {
		this.dailyQuotaBalance = dailyQuotaBalance;
	}
	public long getDailyQuotaBalanceTime() {
		return dailyQuotaBalanceTime;
	}
	public void setDailyQuotaBalanceTime(long dailyQuotaBalanceTime) {
		this.dailyQuotaBalanceTime = dailyQuotaBalanceTime;
	}
	
}
