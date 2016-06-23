package com.rpc.silkroad.coder;

import com.rpc.silkroad.RemoteCall;

/**
 * Created by frio on 16/6/24.
 */
public interface IDecoder {
    /**
     * en..decode here
     * @param object
     * @return
     */
    RemoteCall decode(Object object);
}
