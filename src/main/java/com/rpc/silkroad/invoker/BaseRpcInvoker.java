package com.rpc.silkroad.invoker;

import com.rpc.silkroad.connector.IRpcConnector;
import com.rpc.silkroad.domain.RemoteCall;
import com.rpc.silkroad.exception.RpcException;
import com.rpc.silkroad.domain.Request;
import com.rpc.silkroad.codec.EncodedObject;
import com.rpc.silkroad.codec.IEncoder;
import com.rpc.silkroad.serialize.ISerializer;
import com.rpc.silkroad.utils.Constant;

import java.util.Map;

/**
 * basic invoker
 * Created by frio on 16/6/24.
 */
public class BaseRpcInvoker implements IRpcInvoker{
    IEncoder encoder;
    ISerializer serializer;
    IRpcConnector connector;

    public Object invokeMethod(RemoteCall remoteCall, Map<String, Object> contextParams) {
        //do encode operation
        EncodedObject object = encoder.encode(remoteCall);
        byte[] datas = serializer.serialize(object);
        long threadId = Thread.currentThread().getId();
        String transactionId = String.format("rpc_tid_%d_%d", System.currentTimeMillis(), threadId);
        Request rpcObject = Request.createRpcObject(transactionId, threadId, datas, datas.length, contextParams);
        connector.sendRpcObject(rpcObject, (Integer)contextParams.getOrDefault(Constant.CONNECTOR_SEND_TIME, Constant.CONNECTOR_DEFAULT_TIMEOUT));
        return null;
    }

    public void waitResult(int timeOut, Request rpcObject){
        try {
            rpcObject.wait(timeOut);
        } catch (InterruptedException e) {
            throw new RpcException("rpc caller failed,reason is timeout", e);
        }
    }

    public void notifyResult(Request rpcObject){
        rpcObject.notify();
    }
}
