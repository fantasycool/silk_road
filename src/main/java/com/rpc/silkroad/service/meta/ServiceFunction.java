package com.rpc.silkroad.service.meta;

import java.lang.reflect.Method;

/**
 * Created by frio on 2017/9/19.
 */
public class ServiceFunction {
    private ServiceParams serviceParams;
    private ServiceReturnType serviceReturnType;

    public ServiceFunction(Method method) {
        this.serviceParams = new ServiceParams(method);
    }
}
