package com.rpc.silkroad.service.meta;

import com.alibaba.fastjson.JSON;
import com.rpc.silkroad.exception.RpcException;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.*;

/**
 * Rpc Service Params
 * Created by frio on 2017/9/19.
 */
public class ServiceParams {
    /**
     * Args class list
     */
    private List<Class> paramsClass;
    /**
     * Args class name
     */
    private List<String> name;
    private Class<?> declaringClass;
    private List<ServiceBeanMeta> params = new ArrayList<>();

    public ServiceParams(Method method) {
        declaringClass = method.getDeclaringClass();
        TypeVariable[] typeVariable = declaringClass.getTypeParameters();
        Type[] paramGenericTypes = method.getGenericParameterTypes();
        paramsClass = new ArrayList<>(10);
        Arrays.stream(paramGenericTypes).forEachOrdered(p -> {
            if(p instanceof ParameterizedType){
                ParameterizedType parameterizedType = (ParameterizedType)p;
                params.add(new ServiceBeanMeta(parameterizedType));
            }else if(p instanceof TypeVariable){
                throw new RpcException("rpc service interface should not have TypeVariable type");
            }else if(p instanceof Class){
                params.add(new ServiceBeanMeta((Class)p));
            }else{
                throw new RpcException("rpc service param analyze failed!");
            }
        });
    }

    public List<Class> getParamsClass() {
        return paramsClass;
    }

    public void setParamsClass(List<Class> paramsClass) {
        this.paramsClass = paramsClass;
    }

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }

    public Class<?> getDeclaringClass() {
        return declaringClass;
    }

    public void setDeclaringClass(Class<?> declaringClass) {
        this.declaringClass = declaringClass;
    }

    public List<ServiceBeanMeta> getParams() {
        return params;
    }

    public void setParams(List<ServiceBeanMeta> params) {
        this.params = params;
    }
}
