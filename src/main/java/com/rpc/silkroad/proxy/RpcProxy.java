package com.rpc.silkroad.proxy;

import com.rpc.silkroad.utils.IniterContext;
import com.rpc.silkroad.domain.RemoteCall;
import com.rpc.silkroad.invoker.IRpcInvoker;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * remote interface proxy
 * Created by frio on 16/6/23.
 */
public class RpcProxy implements InvocationHandler {
    IRpcInvoker rpcInvoker;

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Class<?> serve = method.getDeclaringClass();
        RemoteCall remoteCall = RemoteCall.createRemoteCall(serve.getName(),
                method.getName(), args, IniterContext.getServeVersion(serve));
        Object object = IniterContext.get();
        if(object instanceof Map){
            rpcInvoker.invokeMethod(remoteCall, (Map<String, Object>) object);
        }
        return rpcInvoker.invokeMethod(remoteCall, new HashMap<String, Object>());
    }
}
