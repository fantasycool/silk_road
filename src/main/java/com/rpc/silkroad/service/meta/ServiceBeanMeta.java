package com.rpc.silkroad.service.meta;

import com.rpc.silkroad.exception.RpcException;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.*;

/**
 * Created by frio on 2017/9/21.
 */
public class ServiceBeanMeta {
    private Class rawClass;
    private ServiceBeanMeta typeClass;
    private String rawTypeName;
    private boolean isPrimitive = false;
    private List<ServiceBeanMeta> fields = new ArrayList<>();
    private Map<String, Type> contextTypeClass;
    private Class[] primitiveCls = new Class[]{
            String.class,
            Void.class, void.class,
            long.class, Long.class,
            Double.class, double.class,
            float.class, Float.class,
            Integer.class, int.class
    };

    public ServiceBeanMeta(ParameterizedType parameterizedType) {
        this.rawTypeName = parameterizedType.getRawType().getTypeName();
        contextTypeClass = fillGenericInfo(parameterizedType);
        // 数组List需要单独处理
        if ((parameterizedType.getRawType() instanceof Class) && (parameterizedType.getRawType().equals(List.class))) {
            Type typeClass = parameterizedType.getActualTypeArguments()[0];
            if (typeClass instanceof ParameterizedType) {
                this.typeClass = new ServiceBeanMeta((ParameterizedType) typeClass);
            } else if (typeClass instanceof TypeVariable) {
                this.typeClass = retrieveServiceBeanMeta((TypeVariable) typeClass);
            }
        } else {
            parseFields(parameterizedType);
        }

    }

    private void parseFields(ParameterizedType parameterizedType) {
        if (!(parameterizedType.getRawType() instanceof Class)) {
            throw new RpcException("not supported rpc service defined");
        }
        Class cls = (Class) parameterizedType.getRawType();
        Field[] fields = cls.getDeclaredFields();
        Arrays.stream(fields).forEach(field -> {
            Type type = field.getGenericType();
            if (type instanceof ParameterizedType) {
                this.fields.add(new ServiceBeanMeta((ParameterizedType) type));
            } else if (type instanceof TypeVariable) {
                this.fields.add(retrieveServiceBeanMeta((TypeVariable) type));
            } else {
                this.fields.add(new ServiceBeanMeta((Class) type));
            }
        });
    }

    private ServiceBeanMeta retrieveServiceBeanMeta(TypeVariable type) {
        Type typeVariable = contextTypeClass.get(type.getTypeName());
        if (typeVariable instanceof ParameterizedType) {
            return new ServiceBeanMeta((ParameterizedType) typeVariable);
        } else if (typeVariable instanceof Class) {
            return new ServiceBeanMeta((Class) typeVariable);
        } else {
            throw new RpcException("not supported service defined format");
        }
    }

    private Map<String, Type> fillGenericInfo(ParameterizedType parameterizedType) {
        Class rawClass = (Class) parameterizedType.getRawType();
        TypeVariable[] typeVariablesAry = rawClass.getTypeParameters();
        Map<String, Type> contextTypeClass = new HashMap<String, Type>();
        for (int i = 0; i < parameterizedType.getActualTypeArguments().length; i++) {
            contextTypeClass.put(typeVariablesAry[i].getName(), parameterizedType.getActualTypeArguments()[i]);
        }
        return contextTypeClass;
    }

    public ServiceBeanMeta(Class cls) {
        rawTypeName = cls.getTypeName();
        rawClass = cls;
        if (!Arrays.asList(primitiveCls).contains(cls)) {
            Field[] fields = rawClass.getDeclaredFields();
            Arrays.stream(fields).forEach(field -> {
                this.fields.add(new ServiceBeanMeta(field.getType()));
            });
        }
    }


}
