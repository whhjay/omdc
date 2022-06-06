package com.angus.omdc.handler;

import com.angus.omdc.message.*;

public interface QouteMsgTypeHandler {

    /**
     * 处理经纪席位队列消息
     *
     * @param message
     */
    void processBrokerQueue(BrokerQueueMessage message);

    /**
     * 处理报价消息
     *
     * @param message
     */
    void processNominalPrice(NominalPriceMessage message);

    /**
     * 处理统计消息
     *
     * @param message
     */
    void processStatistics(StatisticsMessage message);

    /**
     * 处理收盘价消息
     *
     * @param message
     */
    void processClosingPrice(ClosingPriceMessage message);

    /**
     * 处理摆盘消息
     *
     * @param message
     */
    void processAggregateOrderBookUpdate(AggregateOrderBookUpdateMessage message);

    /**
     * 处理逐笔消息
     *
     * @param message
     */
    void processTradeTicker(TradeTickerMessage message);

    /**
     * 处理指数消息
     *
     * @param message
     */
    void processIndexData(IndexDataMessage message);

    /**
     * 处理市场交易状态消息
     *
     * @param message
     */
    void processTradingSessionStatus(TradingSessionStatusMessage message);

    /**
     * 处理市场成交额消息
     *
     * @param message
     */
    void processMarketTurnover(MarketTurnoverMessage message);

    /**
     * 每日行情额度消息
     *
     * @param message
     */
    void processStockConnectDailyQuotaBalance(StockConnectDailyQuotaBalanceMessage message);

    /**
     * 码表消息
     *
     * @param message
     */
    void processSecurityDefinition(SecurityDefinitionMessage message);

    /**
     * OrderImbalance
     *
     * @param message
     */
    void processOrderImbalance(OrderImbalanceMessage message);

    /**
     * 个股状态消息
     *
     * @param message
     */
    void processSecurityStatus(SecurityStatusMessage message);
}
