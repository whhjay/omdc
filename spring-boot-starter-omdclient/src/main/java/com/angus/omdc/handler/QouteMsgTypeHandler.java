package com.angus.omdc.handler;

import com.angus.omdc.message.*;

public interface QouteMsgTypeHandler {

    void processOmdBrokerQueue(BrokerQueueMessage message);

    void processNominalPrice(NominalPriceMessage message);

    void processStatistics(StatisticsMessage message);

    void processPrevClosingPrice(SecurityDefinitionMessage message);

    void processTodayClosingPrice(ClosingPriceMessage message);

    void processAggregateOrderBookUpdate(AggregateOrderBookUpdateMessage message);

    void processTradeMessage(TradeTickerMessage message);

    void processIndexData(IndexDataMessage message);

    void processTradingSessionStatus(TradingSessionStatusMessage message);

    void processMarketTurnover(MarketTurnoverMessage message);

    void processStockConnect(StockConnectDailyQuotaBalanceMessage message);
}
