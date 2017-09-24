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
    private List<ServiceBeanMeta> params;

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
                params.add(new ServiceBeanMeta(p.getClass()));
            }else{
                throw new RpcException("rpc service param analyze failed!");
            }
        });
    }

    public void test(){

    }
    public static void main(String[] args) {
        Method[] methods = ServiceParams.class.getMethods();
        System.out.println(JSON.toJSONString(methods[0].getDeclaringClass().getTypeParameters()));
    }
}
