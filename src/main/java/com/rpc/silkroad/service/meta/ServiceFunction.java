package com.rpc.silkroad.service.meta;

import com.rpc.silkroad.exception.RpcException;

import javax.lang.model.type.TypeVariable;
import javax.lang.model.type.TypeVisitor;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by frio on 2017/9/19.
 */
public class ServiceFunction {
    private ServiceParams serviceParams;
    private ServiceBeanMeta returnType;

    public ServiceFunction(Method method) {
        this.serviceParams = new ServiceParams(method);
        Type type = method.getGenericReturnType();
        if(type instanceof ParameterizedType){
            this.returnType = new ServiceBeanMeta((ParameterizedType) method.getGenericReturnType());
        }else if(type instanceof TypeVariable){
            throw new RpcException("not supported return type");
        }else {
            this.returnType = new ServiceBeanMeta((Class)type);
        }
    }

    public ServiceParams getServiceParams() {
        return serviceParams;
    }

    public void setServiceParams(ServiceParams serviceParams) {
        this.serviceParams = serviceParams;
    }

    public ServiceBeanMeta getReturnType() {
        return returnType;
    }

    public void setReturnType(ServiceBeanMeta returnType) {
        this.returnType = returnType;
    }
}
