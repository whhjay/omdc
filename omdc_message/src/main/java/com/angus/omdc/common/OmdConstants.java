package com.angus.omdc.common;

import com.angus.omdc.message.*;

import java.util.HashMap;
import java.util.Map;

/**
 * omdc 消息常量
 *
 * @author wangjia
 */
public class OmdConstants {
    public static final String HSI_ASSETID = "HSI.IDX.HK"; // 恒指

    /**
     * From time to time certain fields cannot be populated and specific values are used to represent null. This is currently used
     * within Int64 fields of the Index Data (71) message.
     * The Int64 null representation is 0x8000000000000000 (Hex 2’s complement) or -9223372036854775808 (Decimal).
     */
    public static final long NULL_VALUE_LONG = 0x8000000000000000L;

    /**
     * 定义Index Code到AssetId的映射表
     */
    private static final Map<String, String> indexCode2AssetIdMap = new HashMap<String, String>() {
        private static final long serialVersionUID = -8912106737911360148L;

        {
            put("0000100", "HSI.IDX.HK"); // 恒生指数，Hang Seng Index
            put("0001400", "HSCEI.IDX.HK"); // 国企指数，Hang Seng China Enterprises Index
            put("0001500", "HSCACI.IDX.HK"); // 红筹指数，Hang Seng China Affiliated Corporations Index
            put("0000101", "HSIFI.IDX.HK"); // 恒生金融分类指数 HSI Sub Indices – Finance
            put("0201100", "HSITHI.IDX.HK"); // 恒生国内IT硬件指数 Hang Seng IT Hardware Index
//			put("", "HSIUI.IDX.HK"); // 恒生公用分类指数
            put("0200700", "HSMBI.IDX.HK"); // 恒生国内银行指数 Hang Seng Mainland Banks Index
            put("0200900", "HSMHI.IDX.HK"); // 恒生国内健康护理指数 Hang Seng Mainland Healthcare Index
            put("0201000", "HSMOGI.IDX.HK"); // 恒生国内能源指数 Hang Seng Mainland Oil and Gas Index
            put("0200800", "HSMPI.IDX.HK"); // 恒生国内物业指数 Hang Seng Mainland Properties Index
            put("0201200", "HSSSI.IDX.HK"); // 恒生国内软件及服务指数 Hang Seng Software & Services Index
            put("0001500", "HSCCI.IDX.HK"); // 中资指数成份股  Hang Seng China Affiliated Corporations Index
            //
//			put("", "SSE180.IDX.HK"); // 上证180指数
//			put("", "SSE380.IDX.HK"); // 上证380指数
//			put("", "SSE50.IDX.HK"); // 上证50指数
//			put("", "SSECOMP.IDX.HK"); // 上证综合指数
//			put("", "SSEDIV.IDX.HK"); // 上证红利指数
//			put("", "SSEMCAP.IDX.HK"); // 上证中盘指数
//			put("", ""); // 
        }
    };

    /**
     * 指数编码转换成对应的股票编码
     *
     * @param indexCode
     * @return
     */
    public static String indexCode2AssetId(String indexCode) {
        String assetId = indexCode2AssetIdMap.get(indexCode);
        return assetId;
    }

    public interface MSG_TYPE {
        int MARKET_DEFINITION = 10; // Market Definition (10)
        int SECURITY_DEFINITION = 11; // Security Definition (11)
        int LIQUIDITY_PROVIDER = 13; // Liquidity Provider (13)
        int CURRENCY_RATE = 14; // Currency Rate (14)
        int TRADING_SESSION_STATUS = 20; // Trading Session Status (20)
        int SECURITY_STATUS = 21; // Security Status (21)
        int NEWS = 22; // News (22)
        int VCM_TRIGGER = 23; // VCM Trigger (23)
        int ADD_ODD_LOT_ORDER = 33; // Add Odd Lot Order (33)
        int DELETE_ODD_LOT_ORDER = 34; // Delete Odd Lot Order (34)
        int NOMINAL_PRICE = 40; // Nominal Price (40)
        int INDICATIVE_EQUILIBRIUM_PRICE = 41;  // Indicative Equilibrium Price (41)
        int REFERENCE_PRICE = 43; // Reference Price (43)
        int YIELD = 44; // Yield (44)
        int TRADE_TICKER = 52; // Trade Ticker (52)
        int AGGREGATE_ORDER_BOOK_UPDATE = 53; // Aggregate Order Book Update (53)
        int BROKER_QUEUE = 54; // Broker Queue (54)
        int ORDER_IMBALANCE = 56; // Order Imbalance (56)

        int STATISTICS = 60; // Statistics (60)
        int MARKET_TURNOVER = 61; // Market Turnover (61)
        int CLOSING_PRICE = 62; // Closing Price (62)

        int INDEX_DEFINITION = 70; // Index Definition (70)
        int INDEX_DATA = 71; // Index Data (71)

        int STOCK_CONNECT_DAILY_QUOTA_BALANCE = 80; // Stock Connect Daily Quota Balance (80)
        int STOCK_CONNECT_MARKET_TURNOVER = 81; // Stock Connect Market Turnover (81)

        //
        int SEQUENCE_RESET = 100; // Sequence Reset (100)
    }

    /**
     * Channel自定义标识
     */
    public enum CHANNEL {
        CSI_INDEX(10),
        HSI_INDEX(11),
        LEVEL2(12),
        MARKET_ODD_LOT_ORDER(13),
        NEWS(14),
        REFERENCE(15),
        SP_INDEX(16),
        STATUS(17),
        VALUE_ADDED_DATA(18),
        STOCKCONN_BALANCE(19),
        STOCKCONN_TURNOVER(20);
        //
        private int id;

        CHANNEL(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }
    }

    /**
     * 市场状态
     */
    public interface TRADING_SESSION {
        int STATUS_DAY_CLOSED = 100;
        //
        int SUBID_DAY_CLOSE = 0;
    }


    private static final Map<Integer, OmdMessageParser<?>> MessageParserMapping = new HashMap<>();

    public static OmdMessageParser<?> getOmdMessageParser(int msgType) {
        return MessageParserMapping.get(msgType);
    }

    static {
        MessageParserMapping.put(OmdConstants.MSG_TYPE.ADD_ODD_LOT_ORDER, new AddOddLotOrderMessageParser());
        MessageParserMapping.put(OmdConstants.MSG_TYPE.AGGREGATE_ORDER_BOOK_UPDATE, new AggregateOrderBookUpdateMessageParser());
        MessageParserMapping.put(OmdConstants.MSG_TYPE.BROKER_QUEUE, new BrokerQueueMessageParser());
        MessageParserMapping.put(OmdConstants.MSG_TYPE.CLOSING_PRICE, new ClosingPriceMessageParser());
        MessageParserMapping.put(OmdConstants.MSG_TYPE.CURRENCY_RATE, new CurrencyRateMessageParser());
        MessageParserMapping.put(OmdConstants.MSG_TYPE.DELETE_ODD_LOT_ORDER, new DeleteOddLotOrderMessageParser());
        MessageParserMapping.put(OmdConstants.MSG_TYPE.INDEX_DATA, new IndexDataMessageParser());
        MessageParserMapping.put(OmdConstants.MSG_TYPE.INDEX_DEFINITION, new IndexDefinitionMessageParser());
        MessageParserMapping.put(OmdConstants.MSG_TYPE.INDICATIVE_EQUILIBRIUM_PRICE, new IndicativeEquilibriumPriceMessageParser());
        MessageParserMapping.put(OmdConstants.MSG_TYPE.LIQUIDITY_PROVIDER, new LiquidityProviderMessageParser());
        MessageParserMapping.put(OmdConstants.MSG_TYPE.MARKET_DEFINITION, new MarketDefinitionMessageParser());
        MessageParserMapping.put(OmdConstants.MSG_TYPE.MARKET_TURNOVER, new MarketTurnoverMessageParser());
        MessageParserMapping.put(OmdConstants.MSG_TYPE.NEWS, new NewsMessageParser());
        MessageParserMapping.put(OmdConstants.MSG_TYPE.NOMINAL_PRICE, new NominalPriceMessageParser());
        MessageParserMapping.put(OmdConstants.MSG_TYPE.ORDER_IMBALANCE, new OrderImbalanceMessageParser());
        MessageParserMapping.put(OmdConstants.MSG_TYPE.REFERENCE_PRICE, new ReferencePriceMessageParser());
        MessageParserMapping.put(OmdConstants.MSG_TYPE.SECURITY_DEFINITION, new SecurityDefinitionMessageParser());
        MessageParserMapping.put(OmdConstants.MSG_TYPE.SECURITY_STATUS, new SecurityStatusMessageParser());
        MessageParserMapping.put(OmdConstants.MSG_TYPE.STATISTICS, new StatisticsMessageParser());
        MessageParserMapping.put(OmdConstants.MSG_TYPE.TRADE_TICKER, new TradeTickerMessageParser());
        MessageParserMapping.put(OmdConstants.MSG_TYPE.TRADING_SESSION_STATUS, new TradingSessionStatusMessageParser());
        MessageParserMapping.put(OmdConstants.MSG_TYPE.VCM_TRIGGER, new VCMTriggerMessageParser());
        MessageParserMapping.put(OmdConstants.MSG_TYPE.YIELD, new YieldMessageParser());
        MessageParserMapping.put(OmdConstants.MSG_TYPE.STOCK_CONNECT_MARKET_TURNOVER, new StockConnectMarketTurnoverParser());
        MessageParserMapping.put(OmdConstants.MSG_TYPE.STOCK_CONNECT_DAILY_QUOTA_BALANCE, new StockConnectDailyQuotaBalanceMessageParser());
        MessageParserMapping.put(OmdConstants.MSG_TYPE.SEQUENCE_RESET, new SequenceResetMessageParser());
    }
}
