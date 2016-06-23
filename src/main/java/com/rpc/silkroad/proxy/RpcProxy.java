package com.rpc.silkroad.proxy;

import com.rpc.silkroad.invoker.IRpcInvoker;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * remote interface proxy
 * Created by frio on 16/6/23.
 */
public class RpcProxy implements InvocationHandler {
    IRpcInvoker rpcInvoker;

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null;
    }


}
