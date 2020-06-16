package com.hyper.backend.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;

import com.hyper.backend.model.Appuser;

public abstract class NotNullBeanUtils {
	public static void copyProperties(Object source, Object target) throws BeansException, IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        ArrayList<String> nullAttributes = new ArrayList<String>();
        
        BeanInfo beanInfo = Introspector.getBeanInfo(Appuser.class);
        for(PropertyDescriptor propertyDesc : beanInfo.getPropertyDescriptors()) {
        	String propertyName = propertyDesc.getName();
            Object value = propertyDesc.getReadMethod().invoke(source);
            if(value==null) {
            	nullAttributes.add(propertyName);
            }
        }
        String[] res = new String[nullAttributes.size()];
        res = nullAttributes.toArray(res);
		BeanUtils.copyProperties(source, target, res);
    }
}
