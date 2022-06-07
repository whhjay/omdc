package com.angus.omdc.example;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
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
        long curTime = System.currentTimeMillis();
        long costTime = curTime - message.getSendTime();
        switch (message.getMsgType()) {
            case OmdConstants.MSG_TYPE.SECURITY_DEFINITION:
                SecurityDefinitionMessage securityDefinitionMessage = (SecurityDefinitionMessage) message;
                if (costTime > 20) {
                    log.info(String.format("SECURITY_DEFINITION time out %d   SendTime:%d", (costTime), securityDefinitionMessage.getSendTime()));
                }
                log.info(JSONUtil.parseObj(securityDefinitionMessage).toString());
                break;
            case OmdConstants.MSG_TYPE.NOMINAL_PRICE:
                NominalPriceMessage nominalPriceMessage = (NominalPriceMessage) message;
                if (costTime > 20) {
                    log.info(String.format("NOMINAL_PRICE time out %d   SendTime:%d", (costTime), nominalPriceMessage.getSendTime()));
                }
                break;
            case OmdConstants.MSG_TYPE.TRADE_TICKER:
                TradeTickerMessage tradeTickerMessage = (TradeTickerMessage) message;
                if (costTime > 20) {
                    log.info(String.format("TRADE_TICKER time out %d   SendTime:%d", (costTime), tradeTickerMessage.getSendTime()));
                }
                break;
            case OmdConstants.MSG_TYPE.AGGREGATE_ORDER_BOOK_UPDATE:
                AggregateOrderBookUpdateMessage aggregateOrderBookUpdateMessage = (AggregateOrderBookUpdateMessage) message;
                if (costTime > 20) {
                    log.info(String.format("AGGREGATE_ORDER_BOOK_UPDATE time out %d   SendTime:%d", (costTime), aggregateOrderBookUpdateMessage.getSendTime()));
                }
                break;
            case OmdConstants.MSG_TYPE.BROKER_QUEUE:
                BrokerQueueMessage brokerQueueMessage = (BrokerQueueMessage) message;
                if (costTime > 20) {
                    log.info(String.format("BROKER_QUEUE time out %d   SendTime:%d", (costTime), brokerQueueMessage.getSendTime()));
                }
                break;
            case OmdConstants.MSG_TYPE.INDEX_DATA:
                IndexDataMessage indexDataMessage = (IndexDataMessage) message;
                if (costTime > 20) {
                    log.info("INDEX_DATA time out " + (costTime) + "  " + indexDataMessage.getSendTime());
                }
                break;
            case OmdConstants.MSG_TYPE.INDEX_DEFINITION:
                IndexDefinitionMessage indexDefinitionMessage = (IndexDefinitionMessage) message;
                if (costTime > 20) {
                    log.info("INDEX_DEFINITION time out " + (costTime) + "  " + indexDefinitionMessage.getSendTime());
                }
                log.info(String.format("IndexCode:%s  CurrencyCode:%s  IndexSource:%d", indexDefinitionMessage.getIndexCode(), indexDefinitionMessage.getCurrencyCode(), indexDefinitionMessage.getIndexSource()));
                break;
            case OmdConstants.MSG_TYPE.MARKET_TURNOVER:
                MarketTurnoverMessage marketTurnoverMessage = (MarketTurnoverMessage) message;
                if (costTime > 20) {
                    log.info("MARKET_TURNOVER time out " + (costTime));
                }
                break;
            case OmdConstants.MSG_TYPE.MARKET_DEFINITION:
                MarketDefinitionMessage marketDefinitionMessage = (MarketDefinitionMessage) message;
                if (costTime > 20) {
                    log.info("MARKET_DEFINITION time out " + (costTime));
                }
                log.info(String.format("MarketCode:%s  MarketName:%s  CurrencyCode:%s  NumberOfSecurities:%d", marketDefinitionMessage.getMarketCode(), marketDefinitionMessage.getMarketName(), marketDefinitionMessage.getCurrencyCode(), marketDefinitionMessage.getNumberOfSecurities()));
                break;
            case OmdConstants.MSG_TYPE.INDICATIVE_EQUILIBRIUM_PRICE:
                IndicativeEquilibriumPriceMessage indicativeEquilibriumPriceMessage = (IndicativeEquilibriumPriceMessage) message;
                if (curTime - indicativeEquilibriumPriceMessage.getSendTime() > 20) {
                    log.info("INDICATIVE_EQUILIBRIUM_PRICE time out " + (curTime - indicativeEquilibriumPriceMessage.getSendTime()));
                }
                break;
            case OmdConstants.MSG_TYPE.ORDER_IMBALANCE:
                OrderImbalanceMessage orderImbalanceMessage = (OrderImbalanceMessage) message;
                if (costTime > 20) {
                    log.info(String.format("ORDER_IMBALANCE time out %d   SendTime:%d", (costTime), orderImbalanceMessage.getSendTime()));
                }
                log.info(String.format("SecurityCode:%s  OrderImbalanceDirection:%s  OrderImbalanceQuantity:%d", orderImbalanceMessage.getSecurityCode(), orderImbalanceMessage.getOrderImbalanceDirection(), orderImbalanceMessage.getOrderImbalanceQuantity()));
                break;
            case OmdConstants.MSG_TYPE.CLOSING_PRICE:
                ClosingPriceMessage closingPriceMessage = (ClosingPriceMessage) message;
                if (costTime > 20) {
                    log.info(String.format("CLOSING_PRICE time out %d   SendTime:%d", (costTime), closingPriceMessage.getSendTime()));
                }
                break;
            case OmdConstants.MSG_TYPE.STATISTICS:
                StatisticsMessage statisticsMessage = (StatisticsMessage) message;
                if (costTime > 20) {
                    log.info(String.format("STATISTICS time out %d   SendTime:%d", (costTime), statisticsMessage.getSendTime()));
                }
                break;
            case OmdConstants.MSG_TYPE.YIELD:
                YieldMessage yieldMessage = (YieldMessage) message;
                if (costTime > 20) {
                    log.info(String.format("YIELD time out %d   SendTime:%d", (costTime), yieldMessage.getSendTime()));
                }
                log.info(String.format("SecurityCode:%s  Yield:%d", yieldMessage.getSecurityCode(), yieldMessage.getYield()));
                break;
            case OmdConstants.MSG_TYPE.TRADING_SESSION_STATUS:
                TradingSessionStatusMessage tradingSessionStatusMessage = (TradingSessionStatusMessage) message;
                if (costTime > 20) {
                    log.info(String.format("TRADING_SESSION_STATUS time out %d   SendTime:%d", (costTime), tradingSessionStatusMessage.getSendTime()));
                }
                log.info(String.format("MarketCode:%s  TradingSesStatus:%d TradingSessionId:%d", tradingSessionStatusMessage.getMarketCode(), tradingSessionStatusMessage.getTradingSesStatus(), tradingSessionStatusMessage.getTradingSessionId()));
                break;
            case OmdConstants.MSG_TYPE.SECURITY_STATUS:
                SecurityStatusMessage securityStatusMessage = (SecurityStatusMessage) message;
                if (costTime > 20) {
                    log.info(String.format("SECURITY_STATUS time out %d   SendTime:%d", (costTime), securityStatusMessage.getSendTime()));
                }
                break;
            case OmdConstants.MSG_TYPE.LIQUIDITY_PROVIDER:
                LiquidityProviderMessage liquidityProviderMessage = (LiquidityProviderMessage) message;
                if (curTime - liquidityProviderMessage.getSendTime() > 20) {
                    log.info(String.format("LIQUIDITY_PROVIDER time out %d   SendTime:%d", (curTime - liquidityProviderMessage.getSendTime()), liquidityProviderMessage.getSendTime()));
                }
                log.info(String.format("SecurityCode:%s  NoLiquidityProviders:%d BrokerNumber:%d", liquidityProviderMessage.getSecurityCode(), liquidityProviderMessage.getNoLiquidityProviders(), liquidityProviderMessage.getLpBrokerNumber()));
                break;
            case OmdConstants.MSG_TYPE.REFERENCE_PRICE:
                ReferencePriceMessage referencePriceMessage = (ReferencePriceMessage) message;
                if (costTime > 20) {
                    log.info(String.format("REFERENCE_PRICE time out %d   SendTime:%d", (costTime), referencePriceMessage.getSendTime()));
                }
                log.info(String.format("SecurityCode:%s  ReferencePrice:%d LowerPrice:%d  UpperPrice:%d", referencePriceMessage.getSecurityCode(), referencePriceMessage.getReferencePrice(), referencePriceMessage.getLowerPrice(), referencePriceMessage.getUpperPrice()));
                break;
            default:
                log.info("msgType not found:" + message.getMsgType());
        }
        if (curTime - lastTime > 60 * 1000l) {
            lastTime = curTime;
            log.info("every mins handler message counts：" + counts);
            counts = 0;
        }
    }
}
