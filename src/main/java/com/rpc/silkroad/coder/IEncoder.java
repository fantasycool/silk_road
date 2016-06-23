package com.rpc.silkroad.coder;

import com.rpc.silkroad.RemoteCall;

/**
 * Created by frio on 16/6/24.
 */
public interface IEncoder {
    /**
     * maybe we do convert
     * @param remoteCall
     * @return
     */
    Object encode(RemoteCall remoteCall);
}
