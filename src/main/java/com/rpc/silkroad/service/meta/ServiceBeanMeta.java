package com.rpc.silkroad.service.meta;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by frio on 2017/9/21.
 */
public class ServiceBeanMeta {
    private Class rawClass;
    private String typeName;
    private List<ServiceBeanMeta> actualTypeArgumentArray = new ArrayList<>();

    public ServiceBeanMeta(ParameterizedType parameterizedType){
        this.typeName = parameterizedType.getRawType().getTypeName();
        Type[] actualTypeArgs = parameterizedType.getActualTypeArguments();
        for(int i = 0; i < actualTypeArgs.length; i++){
            actualTypeArgumentArray.add(new ServiceBeanMeta(actualTypeArgs[i].getClass()));
        }
        parameterizedType.getActualTypeArguments();
        parameterizedType.getClass().getTypeParameters();
    }


    public ServiceBeanMeta(Class cls){

    }
}
