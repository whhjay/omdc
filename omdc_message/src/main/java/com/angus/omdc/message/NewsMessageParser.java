package com.angus.omdc.message;

import com.angus.omdc.common.OmdConstants;
import io.netty.buffer.ByteBuf;

import java.util.ArrayList;

public class NewsMessageParser extends OmdMessageParser<NewsMessage> {
	private static final String NEWS_TYPE_ENG = "EXN";
	private static final String NEWS_TYPE_CHN = "EXC";

	@Override
	protected NewsMessage createMessageInstance() {
		return new NewsMessage();
	}

	@Override
	protected int getMsgType() {
		return OmdConstants.MSG_TYPE.NEWS;
	}

	@Override
	protected NewsMessage doParseMessageBody(ByteBuf msgBodyBuf, NewsMessage msg) throws Exception {
		msg.newsType = readAsciiString(msgBodyBuf, 3); // string3
		boolean isEng = NEWS_TYPE_ENG.equals(msg.newsType);
		
		msg.newsId = readAsciiString(msgBodyBuf, 3); // string3
		msg.headline = isEng ? readAsciiString(msgBodyBuf, 320) : readUtf16LEString(msgBodyBuf, 320); // string320
		msg.cancelFlag = readAsciiString(msgBodyBuf, 1); // sting1
		msg.lastFragment = readAsciiString(msgBodyBuf, 1); // string1
		msgBodyBuf.readBytes(msg.filler4); // byte4
		msg.releaseTime = msgBodyBuf.readLong(); // uint64
		msgBodyBuf.readBytes(msg.filler2); // byte2
		
		msg.noMarketCodes = msgBodyBuf.readUnsignedShort(); // uint16
		if (msg.noMarketCodes > 0) {
			msg.marketCode = new ArrayList<>(msg.noMarketCodes);
			for (int i = 0; i < msg.noMarketCodes; i++) {
				String s = readAsciiString(msgBodyBuf, 4); // string4
				msg.marketCode.add(s);
			}
		}
		//
		msgBodyBuf.readBytes(msg.filler2b); // byte2
		msg.noSecurityCodes = msgBodyBuf.readUnsignedShort(); // uint16
		if (msg.noSecurityCodes > 0) {
			msg.securityCode = new ArrayList<>(msg.noSecurityCodes);
			for (int i = 0; i < msg.noSecurityCodes; i++) {
				int securityCode = msgBodyBuf.readInt(); // uint32
				msg.securityCode.add(securityCode);
			}
		}
		//
		msgBodyBuf.readBytes(msg.filler2c); // byte2
		msg.noNewsLines = msgBodyBuf.readUnsignedShort(); // uint16
		if (msg.noNewsLines > 0) {
			msg.newsLine = new ArrayList<>(msg.noNewsLines);
			for (int i = 0; i < msg.noNewsLines; i++) {
				String s = isEng ? readAsciiString(msgBodyBuf, 160) : readUtf16LEString(msgBodyBuf, 160); // string160
				msg.newsLine.add(s);
			}
		}
		//
		return msg;
	}

}
