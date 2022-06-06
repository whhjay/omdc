package com.angus.omdc.message;

/**
 * Stock Connect Market Turnover (81)
 * The Stock Connect Market Turnover message provides aggregate turnover under Shanghai-Hong Kong Stock Connect and
 * Shenzhen-Hong Kong Stock Connect (“the Stock Connect programs”). The aggregate turnover is provided for the
 * Northbound trading and the Southbound trading separately under each of the Stock Connect programs. Under normal
 * circumstances, the updates are disseminated around every one minute during the trading hours.
 * @author wangjia
 *
 */
public class StockConnectMarketTurnover extends OmdMessage {
	/**
	 * SH Shanghai Stock Exchange
	 * SZ ShenZhen Stock Exchange
	 */
	String stockConnectMarket; // string2
	/**
	 * NB Northbound trading
	 * SB Southbound trading
	 */
	String tradingDirection; // string2
	/**
	 * Turnover in RMB for Northbound
	 * trading and HKD for Southbound
	 * trading
	 */
	long buyTurnover; // int64
	/**
	 * Turnover in RMB for Northbound
	 * trading and HKD for Southbound
	 * trading
	 */
	long sellTurnover; // int64
	/**
	 * Sum of the values of BuyTurnover and SellTurnover rounded down to integer
	 * Turnover in RMB for Northbound trading and HKD for Southbound trading
	 */
	long totalTurnover; // int64
	
	
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
	public long getBuyTurnover() {
		return buyTurnover;
	}
	public void setBuyTurnover(long buyTurnover) {
		this.buyTurnover = buyTurnover;
	}
	public long getSellTurnover() {
		return sellTurnover;
	}
	public void setSellTurnover(long sellTurnover) {
		this.sellTurnover = sellTurnover;
	}
	public long getTotalTurnover() {
		return totalTurnover;
	}
	public void setTotalTurnover(long totalTurnover) {
		this.totalTurnover = totalTurnover;
	}
}
