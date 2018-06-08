package com.eoi.slick.util;

import org.dozer.DozerBeanMapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * <p> 类属性复制
 * </p>
 *
 * @author tao.zeng.
 * @since 2018/3/23.
 */
@SuppressWarnings("all")
public class BeanMapper {

    private BeanMapper() {
    }

    private volatile static DozerBeanMapper dozerBeanMapper;

    /**
     * 持有Dozer单例, 避免重复创建DozerMapper消耗资源.
     */
    public static DozerBeanMapper getDozer() {
        if (dozerBeanMapper == null) {
            synchronized (BeanMapper.class) {
                if (dozerBeanMapper == null) {
                    dozerBeanMapper = new DozerBeanMapper();
                }
            }
        }
        return dozerBeanMapper;
    }

    /**
     * 基于Dozer转换对象的类型.
     */
    public static <T> T map(Object source, Class<T> destinationClass) {
        return getDozer().map(source, destinationClass);
    }

    /**
     * 基于Dozer转换Collection中对象的类型.
     */
    public static <T> List<T> mapList(Collection sourceList, Class<T> destinationClass) {
        List<T> destinationList = new ArrayList<>();
        for (Object sourceObject : sourceList) {
            T destinationObject = getDozer().map(sourceObject, destinationClass);
            destinationList.add(destinationObject);
        }
        return destinationList;
    }

    /**
     * 基于Dozer将对象A的值拷贝到对象B中.
     */
    public static void copy(Object source, Object destinationObject) {
        getDozer().map(source, destinationObject);
    }
}
