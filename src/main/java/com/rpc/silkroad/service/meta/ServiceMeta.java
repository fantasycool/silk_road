package com.rpc.silkroad.service.meta;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Rpc Service Meta Info class
 * Created by frio on 2017/9/19.
 */
public class ServiceMeta {
    private List<ServiceFunction> serviceFunctionList = new ArrayList<>();
    private String serviceName;
    private String serviceVersion;

    /**
     * Rpc Service is always a interface, we analyze this interface to get Service meta info
     *
     * @param serviceInterface
     */
    public ServiceMeta(Class<?> serviceInterface) {
        this.serviceFunctionList = anlyzeGetFunctionList(serviceInterface);
    }

    /**
     * get ServiceFunction from serviceInterface
     *
     * @param serviceInterface
     * @return
     */
    private List<ServiceFunction> anlyzeGetFunctionList(Class<?> serviceInterface) {
        Method[] interfaceMethods = serviceInterface.getMethods();
        Arrays.stream(interfaceMethods).forEach(method -> {
            this.serviceFunctionList.add(new ServiceFunction(method));
        });
        return serviceFunctionList;
    }

}
