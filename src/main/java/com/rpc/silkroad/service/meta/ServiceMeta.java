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
    private List<ServiceFunction> serviceFunctionList;

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
     * @param serviceInterface
     * @return
     */
    private List<ServiceFunction> anlyzeGetFunctionList(Class<?> serviceInterface) {
        List<ServiceFunction> serviceFunctions = new ArrayList<>(10);
        Method[] interfaceMethods = serviceInterface.getMethods();
        Arrays.stream(interfaceMethods).forEach(method -> {
            serviceFunctions.add(new ServiceFunction(method));
        });
        return null;
    }

}
