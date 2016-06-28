package com.rpc.silkroad.domain;

import java.util.Map;

/**
 * Created by frio on 16/6/23.
 */
public class Request {
    private String transactionId; //transactionId to permit exactly once
    private long threadId;  //caller thread id
    private byte[] data;
    private long length;
    private Map<String, Object> contextParams;

    private Request() {

    }

    public static Request createRpcObject(String transactionId, long threadId,
                                          byte[] data, long length, Map<String, Object> params) {
        Request rpcObject = new Request();
        rpcObject.setTransactionId(transactionId);
        rpcObject.setThreadId(threadId);
        rpcObject.setData(data);
        rpcObject.setLength(length);
        rpcObject.setContextParams(params);
        return rpcObject;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public long getThreadId() {
        return threadId;
    }

    public void setThreadId(long threadId) {
        this.threadId = threadId;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public Map<String, Object> getContextParams() {
        return contextParams;
    }

    public void setContextParams(Map<String, Object> contextParams) {
        this.contextParams = contextParams;
    }
}
