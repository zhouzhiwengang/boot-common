package com.zzg.mybatis.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @ClassName: ReflectUtil
 * @Description: MyBatis 单表查询 反射工具封装
 * @author: 世纪伟图 -zzg
 * @date: 2019年4月18日 上午9:55:25
 * 
 * @Copyright: 2019 www.digipower.cn 注意：本内容仅限于深圳市世纪伟图科技开发有限公司内部使用，禁止用于其他的商业目的
 */
public class ReflectUtil {
	/**
	 * 
	 * @Title: setFieldValue   
	 * @Description: 反射为指定对象复制  
	 * @param: @param 对象
	 * @param: @param 属性名称
	 * @param: @param 属性所属数据类型
	 * @param: @param 属性值      
	 * @return: void      
	 * @throws
	 */
	public static void setFieldValue(Object target, String fname, Class ftype, Object fvalue) {
		if (target == null || fname == null || "".equals(fname)
				|| (fvalue != null && !ftype.isAssignableFrom(fvalue.getClass()))) {
			return;
		}
		Class clazz = target.getClass();
		try {
			Method method = clazz.getDeclaredMethod("set" + Character.toUpperCase(fname.charAt(0)) + fname.substring(1),
					ftype);
			if (!Modifier.isPublic(method.getModifiers())) {
				method.setAccessible(true);
			}
			method.invoke(target, fvalue);

		} catch (Exception me) {
			// if (logger.isDebugEnabled()) {
			// logger.debug(me);
			// }
			try {
				Field field = clazz.getDeclaredField(fname);
				if (!Modifier.isPublic(field.getModifiers())) {
					field.setAccessible(true);
				}
				field.set(target, fvalue);
			} catch (Exception fe) {
				// if (logger.isDebugEnabled()) {
				// logger.debug(fe);
				// }
			}
		}
	}

	/**
	 * 用Reflection机制得到指定对象所有属性的Map形式
	 * 
	 * @return
	 * @author xiali2
	 * @since 2008-10-14
	 */
	public static Map<String, Object> getMapFieldData(Object target) {
		Map<String, Object> map = new HashMap<String, Object>();
		Class clazz = target.getClass();
		Field[] fields = clazz.getDeclaredFields();
		Method[] methods = clazz.getDeclaredMethods();
		for (Field field : fields) {
			String fieldName = field.getName();
			if ("messageTypeId".equals(fieldName)) {
				continue;
			}
			String getMethod = "get" + StringUtils.capitalize(fieldName);
			for (Method method : methods) {
				if (method.getName().equals(getMethod)) {
					try {
						Object ret = method.invoke(target, null);
						map.put(fieldName, ret);
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return map;
	}

	public static Class getGenericClass(Class clazz) {
		Object genericClazz = clazz.getGenericSuperclass();
		if (genericClazz instanceof ParameterizedType) {
			Class c = (Class) ((ParameterizedType) genericClazz).getActualTypeArguments()[0];
			return c;
		}
		return null;
	}

}
