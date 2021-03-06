package com.rpc.silkroad.service.meta;

import com.rpc.silkroad.exception.RpcException;
import com.rpc.silkroad.service.annotation.RpcService;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Rpc RpcService Meta Info class
 * Created by frio on 2017/9/19.
 */
public class ServiceMeta {
    private List<ServiceFunction> serviceFunctionList = new ArrayList<>();
    private String serviceName;
    private String serviceVersion;

    /**
     * Rpc RpcService is always a interface, we analyze this interface to get RpcService meta info
     *
     * @param serviceInterface
     */
    public ServiceMeta(Class<?> serviceInterface) {
        this.serviceFunctionList = anlyzeGetFunctionList(serviceInterface);
        RpcService rpcService = AnnotationUtils.findAnnotation(serviceInterface, RpcService.class);
        if (null == rpcService) {
            throw new RpcException("this type " + serviceInterface.getTypeName() + " is not rpc service");
        }
        String[] values = rpcService.value().split(":");
        if(values.length < 2){
            throw new RpcException("incorrect rpc service annotation config for interface:" + serviceInterface.getTypeName());
        }
        this.serviceName = values[0];
        this.serviceVersion = values[1];
    }

    /**
     * get ServiceFunction from serviceInterface
     *
     * @param serviceInterface
     * @return
     */
    private List<ServiceFunction> anlyzeGetFunctionList(Class<?> serviceInterface) {
        Method[] interfaceMethods = serviceInterface.getMethods();
        Arrays.stream(interfaceMethods).forEach(method ->
                this.serviceFunctionList.add(new ServiceFunction(method))
        );
        return serviceFunctionList;
    }

    /**
     * dump this service info to save in zk for subscribe
     *
     * @return
     */
    public String dumpServiceInfo() {
        return null;
    }

    public List<ServiceFunction> getServiceFunctionList() {
        return serviceFunctionList;
    }

    public void setServiceFunctionList(List<ServiceFunction> serviceFunctionList) {
        this.serviceFunctionList = serviceFunctionList;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceVersion() {
        return serviceVersion;
    }

    public void setServiceVersion(String serviceVersion) {
        this.serviceVersion = serviceVersion;
    }
}
