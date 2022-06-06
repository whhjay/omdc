package com.angus.omdc.message;

import com.angus.omdc.common.OmdConstants;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class OmdPacketParser {

    private static final Map<Integer, OmdMessageParser<?>> MessageParserMapping = new HashMap<>();

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
        //
        MessageParserMapping.put(OmdConstants.MSG_TYPE.ORDER_IMBALANCE, new OrderImbalanceMessageParser());
        MessageParserMapping.put(OmdConstants.MSG_TYPE.REFERENCE_PRICE, new ReferencePriceMessageParser());
        MessageParserMapping.put(OmdConstants.MSG_TYPE.SECURITY_DEFINITION, new SecurityDefinitionMessageParser());
        MessageParserMapping.put(OmdConstants.MSG_TYPE.SECURITY_STATUS, new SecurityStatusMessageParser());
        MessageParserMapping.put(OmdConstants.MSG_TYPE.STATISTICS, new StatisticsMessageParser());
        MessageParserMapping.put(OmdConstants.MSG_TYPE.TRADE_TICKER, new TradeTickerMessageParser());
        MessageParserMapping.put(OmdConstants.MSG_TYPE.TRADING_SESSION_STATUS, new TradingSessionStatusMessageParser());
        MessageParserMapping.put(OmdConstants.MSG_TYPE.VCM_TRIGGER, new VCMTriggerMessageParser());
        MessageParserMapping.put(OmdConstants.MSG_TYPE.YIELD, new YieldMessageParser());
        //
        MessageParserMapping.put(OmdConstants.MSG_TYPE.STOCK_CONNECT_MARKET_TURNOVER, new StockConnectMarketTurnoverParser());
        MessageParserMapping.put(OmdConstants.MSG_TYPE.STOCK_CONNECT_DAILY_QUOTA_BALANCE, new StockConnectDailyQuotaBalanceMessageParser());
        //
        MessageParserMapping.put(OmdConstants.MSG_TYPE.SEQUENCE_RESET, new SequenceResetMessageParser());
    }

    private long unixNanoPower = 1000000;
    private long startTimer = System.currentTimeMillis() * unixNanoPower;

    public OmdPacket parse(ByteBuf buf) {
        buf = buf.order(ByteOrder.LITTLE_ENDIAN);
        OmdPacket packet = new OmdPacket();
        packet.pktSize = buf.readUnsignedShort();
        packet.msgCount = buf.readUnsignedByte();
        packet.filler = buf.readByte();
        packet.seqNum = buf.readUnsignedInt();
        packet.sendTime = buf.readLong();
        parseMessages(packet, buf);
        return packet;
    }

    public byte[] parseToBytes(ByteBuf buf) {
        buf = buf.order(ByteOrder.LITTLE_ENDIAN);
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        return req;
    }

    private Date lastSendTime;

    private void parseMessages(OmdPacket packet, ByteBuf buf) {
        if (packet.getMsgCount() <= 0) {
            // 没有包含消息，可能是心跳包
            return;
        }
        Date sendTime;
        try {
            if (packet.sendTime > startTimer) {//UTC纳秒
                sendTime = new Date(packet.sendTime / unixNanoPower);
            } else {
                //由于过来的时间不是时间戳，而是一个时间字符串，所以将字符串转换成时间戳
                sendTime = new SimpleDateFormat("yyyyMMddHHmmssSSS").parse(packet.sendTime + "");
            }
            lastSendTime = sendTime;
        } catch (ParseException e) {
            System.err.println("time parse err:" + packet.sendTime + "  " + e.getMessage());
            if (lastSendTime == null) {
                return;
            }
            sendTime = lastSendTime;
        }

        List<OmdMessage> messages = new ArrayList<>(packet.getMsgCount());
        long seqNum = packet.getSeqNum();
        OmdMessage msg;
        ByteBuf msgBodyBuf;
        for (int i = 0; i < packet.getMsgCount(); i++) {
            msg = new OmdMessage();
            msg.setSendTime(sendTime);
            msg.msgSize = buf.readUnsignedShort();
            msg.msgType = buf.readUnsignedShort();
            msg.setSeqNum(seqNum); // 第一个消息的序列号就是封包的序列号
            seqNum++;
            int msgBodySize = msg.msgSize - 4;
            if (msgBodySize > 0) {
                // 读取消息内容
                msgBodyBuf = buf.readBytes(msgBodySize);
                msgBodyBuf.markReaderIndex();
                try {
                    msg = parseMessageBody(msg, msgBodyBuf);
                } catch (Exception ex) {
                    msgBodyBuf.resetReaderIndex();
                    continue;
                }
            }
            messages.add(msg);
        }
        packet.setMessages(messages);
    }

    private OmdMessage parseMessageBody(OmdMessage msg, ByteBuf msgBodyBuf) throws Exception {
        OmdMessageParser<?> parser = MessageParserMapping.get(msg.getMsgType());
        if (parser == null) {
            return msg;
        }
        return parser.parseMessageBody(msg, msgBodyBuf);
    }

}
