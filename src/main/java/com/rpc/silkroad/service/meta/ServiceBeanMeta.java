package com.rpc.silkroad.service.meta;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by frio on 2017/9/21.
 */
public class ServiceBeanMeta {
    private Class rawClass;
    private String typeName;
    private boolean isPrimitive;
    public ServiceBeanMeta(ParameterizedType parameterizedType){
        this.typeName = parameterizedType.getRawType().getTypeName();
        Type[] actualTypeArgs = parameterizedType.getActualTypeArguments();
        parseFields(parameterizedType);
        parameterizedType.getActualTypeArguments();
        parameterizedType.getClass().getTypeParameters();
    }

    private void parseFields(ParameterizedType parameterizedType) {

    }


    public ServiceBeanMeta(Class cls){
        isPrimitive = true;
        typeName = cls.getTypeName();
        rawClass = cls;
    }


}
