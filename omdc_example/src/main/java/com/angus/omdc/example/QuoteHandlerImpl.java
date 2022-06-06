package com.angus.omdc.example;

import com.angus.omdc.common.OmdConstants;
import com.angus.omdc.handler.QuoteHandler;
import com.angus.omdc.message.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class QuoteHandlerImpl implements QuoteHandler {

    private long lastTime = System.currentTimeMillis();
    private long counts = 0;

    @Override
    public void processOmdMessage(OmdMessage message) {
        counts++;
        switch (message.getMsgType()) {
            case OmdConstants.MSG_TYPE.SECURITY_DEFINITION:
                SecurityDefinitionMessage securityDefinitionMessage = (SecurityDefinitionMessage) message;
                if (System.currentTimeMillis() - securityDefinitionMessage.getSendTime().getTime() > 20) {
                    log.info("SECURITY_DEFINITION time out " + (System.currentTimeMillis() - securityDefinitionMessage.getSendTime().getTime()));
                }
                break;
            case OmdConstants.MSG_TYPE.NOMINAL_PRICE:
                NominalPriceMessage nominalPriceMessage = (NominalPriceMessage) message;
                if (System.currentTimeMillis() - nominalPriceMessage.getSendTime().getTime() > 20) {
                    log.info("NOMINAL_PRICE time out " + (System.currentTimeMillis() - nominalPriceMessage.getSendTime().getTime()));
                }
                break;
            case OmdConstants.MSG_TYPE.TRADE_TICKER:
                TradeTickerMessage tradeTickerMessage = (TradeTickerMessage) message;
                if (System.currentTimeMillis() - tradeTickerMessage.getSendTime().getTime() > 20) {
                    log.info("TRADE_TICKER time out " + (System.currentTimeMillis() - tradeTickerMessage.getSendTime().getTime()));
                }
                break;
            case OmdConstants.MSG_TYPE.AGGREGATE_ORDER_BOOK_UPDATE:
                AggregateOrderBookUpdateMessage aggregateOrderBookUpdateMessage = (AggregateOrderBookUpdateMessage) message;
                if (System.currentTimeMillis() - aggregateOrderBookUpdateMessage.getSendTime().getTime() > 20) {
                    log.info("AGGREGATE_ORDER_BOOK_UPDATE time out " + (System.currentTimeMillis() - aggregateOrderBookUpdateMessage.getSendTime().getTime()));
                }
                break;
            case OmdConstants.MSG_TYPE.BROKER_QUEUE:
                BrokerQueueMessage brokerQueueMessage = (BrokerQueueMessage) message;
                if (System.currentTimeMillis() - brokerQueueMessage.getSendTime().getTime() > 20) {
                    log.info("BROKER_QUEUE time out " + (System.currentTimeMillis() - brokerQueueMessage.getSendTime().getTime()));
                }
                break;
            case OmdConstants.MSG_TYPE.INDEX_DATA:
                IndexDataMessage indexDataMessage = (IndexDataMessage) message;
                if (System.currentTimeMillis() - indexDataMessage.getSendTime().getTime() > 20) {
                    log.info("INDEX_DATA time out " + (System.currentTimeMillis() - indexDataMessage.getSendTime().getTime()));
                }
                break;
            case OmdConstants.MSG_TYPE.INDEX_DEFINITION:
                IndexDefinitionMessage indexDefinitionMessage = (IndexDefinitionMessage) message;
                if (System.currentTimeMillis() - indexDefinitionMessage.getSendTime().getTime() > 20) {
                    log.info("INDEX_DEFINITION time out " + (System.currentTimeMillis() - indexDefinitionMessage.getSendTime().getTime()));
                }
                break;
            case OmdConstants.MSG_TYPE.MARKET_TURNOVER:
                MarketTurnoverMessage marketTurnoverMessage = (MarketTurnoverMessage) message;
                if (System.currentTimeMillis() - marketTurnoverMessage.getSendTime().getTime() > 20) {
                    log.info("MARKET_TURNOVER time out " + (System.currentTimeMillis() - marketTurnoverMessage.getSendTime().getTime()));
                }
                break;
            case OmdConstants.MSG_TYPE.MARKET_DEFINITION:
                MarketDefinitionMessage marketDefinitionMessage = (MarketDefinitionMessage) message;
                if (System.currentTimeMillis() - marketDefinitionMessage.getSendTime().getTime() > 20) {
                    log.info("MARKET_DEFINITION time out " + (System.currentTimeMillis() - marketDefinitionMessage.getSendTime().getTime()));
                }
                break;
            case OmdConstants.MSG_TYPE.INDICATIVE_EQUILIBRIUM_PRICE:
                IndicativeEquilibriumPriceMessage indicativeEquilibriumPriceMessage = (IndicativeEquilibriumPriceMessage) message;
                if (System.currentTimeMillis() - indicativeEquilibriumPriceMessage.getSendTime().getTime() > 20) {
                    log.info("INDICATIVE_EQUILIBRIUM_PRICE time out " + (System.currentTimeMillis() - indicativeEquilibriumPriceMessage.getSendTime().getTime()));
                }
                break;
            case OmdConstants.MSG_TYPE.ORDER_IMBALANCE:
                OrderImbalanceMessage orderImbalanceMessage = (OrderImbalanceMessage) message;
                if (System.currentTimeMillis() - orderImbalanceMessage.getSendTime().getTime() > 20) {
                    log.info("ORDER_IMBALANCE time out " + (System.currentTimeMillis() - orderImbalanceMessage.getSendTime().getTime()));
                }
                break;
            case OmdConstants.MSG_TYPE.CLOSING_PRICE:
                ClosingPriceMessage closingPriceMessage = (ClosingPriceMessage) message;
                if (System.currentTimeMillis() - closingPriceMessage.getSendTime().getTime() > 20) {
                    log.info("CLOSING_PRICE time out " + (System.currentTimeMillis() - closingPriceMessage.getSendTime().getTime()));
                }
                break;
            case OmdConstants.MSG_TYPE.STATISTICS:
                StatisticsMessage statisticsMessage = (StatisticsMessage) message;
                if (System.currentTimeMillis() - statisticsMessage.getSendTime().getTime() > 20) {
                    log.info("STATISTICS time out " + (System.currentTimeMillis() - statisticsMessage.getSendTime().getTime()));
                }
                break;
            case OmdConstants.MSG_TYPE.YIELD:
                YieldMessage yieldMessage = (YieldMessage) message;
                if (System.currentTimeMillis() - yieldMessage.getSendTime().getTime() > 20) {
                    log.info("YIELD time out " + (System.currentTimeMillis() - yieldMessage.getSendTime().getTime()));
                }
                break;
            case OmdConstants.MSG_TYPE.TRADING_SESSION_STATUS:
                TradingSessionStatusMessage tradingSessionStatusMessage = (TradingSessionStatusMessage) message;
                if (System.currentTimeMillis() - tradingSessionStatusMessage.getSendTime().getTime() > 20) {
                    log.info("TRADING_SESSION_STATUS time out " + (System.currentTimeMillis() - tradingSessionStatusMessage.getSendTime().getTime()));
                }
                break;
            case OmdConstants.MSG_TYPE.SECURITY_STATUS:
                SecurityStatusMessage securityStatusMessage = (SecurityStatusMessage) message;
                if (System.currentTimeMillis() - securityStatusMessage.getSendTime().getTime() > 20) {
                    log.info("SECURITY_STATUS time out " + (System.currentTimeMillis() - securityStatusMessage.getSendTime().getTime()));
                }
                break;
            default:
                log.info("msgType not found:" + message.getMsgType());
        }
        if (System.currentTimeMillis() - lastTime > 60 * 1000l) {
            lastTime = System.currentTimeMillis();
            log.info("every mins handler message counts：" + counts);
            counts = 0;
        }
    }
}
