package com.rpc.silkroad.invoker;


import com.rpc.silkroad.RemoteCall;

/**
 * Created by frio on 16/6/24.
 */
public interface IRpcInvoker {
    /**
     * call remote server to execute method
     * @param remoteCall
     * @return
     */
    Object invokeMethod(RemoteCall remoteCall);
}
