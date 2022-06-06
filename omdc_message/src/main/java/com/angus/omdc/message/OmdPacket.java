package com.angus.omdc.message;

import java.util.List;

public class OmdPacket {
	int pktSize; // 封包长度（包括当前字段）(uint16)
	short msgCount; // 封包包含的消息数量(uint8)
	byte filler;
	long seqNum; // 序列号，心跳包的序列号不增加(unint32)
	long sendTime; // 发送时间（纳秒）(uint64)
	
	//
	private List<OmdMessage> messages;
	
	public List<OmdMessage> getMessages() {
		return messages;
	}
	public void setMessages(List<OmdMessage> messages) {
		this.messages = messages;
	}
	public int getPktSize() {
		return pktSize;
	}
	public void setPktSize(int pktSize) {
		this.pktSize = pktSize;
	}
	public short getMsgCount() {
		return msgCount;
	}
	public void setMsgCount(short msgCount) {
		this.msgCount = msgCount;
	}
	public byte getFiller() {
		return filler;
	}
	public void setFiller(byte filler) {
		this.filler = filler;
	}
	public long getSeqNum() {
		return seqNum;
	}
	public void setSeqNum(long seqNum) {
		this.seqNum = seqNum;
	}
	public long getSendTime() {
		return sendTime;
	}
	public void setSendTime(long sendTime) {
		this.sendTime = sendTime;
	}
}
