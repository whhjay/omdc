package com.angus.omdc.message;

import java.util.Date;

/**
 * 消息头
 *
 * @author:WangJia
 */
public class OmdMessage {

    int msgSize; // 消息长度，包括当前字段(uint16)
    int msgType; // 消息类型(uint16)
    //
    long seqNum; // 消息序列号
    // 自定义属性
    private long sendTime; // 消息封包的sentTime属性

    public long getSendTime() {
        return sendTime;
    }

    public void setSendTime(long sendTime) {
        this.sendTime = sendTime;
    }

    public long getSeqNum() {
        return seqNum;
    }

    public void setSeqNum(long seqNum) {
        this.seqNum = seqNum;
    }

    public int getMsgSize() {
        return msgSize;
    }

    public void setMsgSize(int msgSize) {
        this.msgSize = msgSize;
    }

    public int getMsgType() {
        return msgType;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }

}
