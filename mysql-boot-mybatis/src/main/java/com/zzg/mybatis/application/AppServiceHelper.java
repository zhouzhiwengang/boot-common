package com.zzg.mybatis.application;

import java.util.Iterator;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import com.zzg.mybatis.exception.GlobalRuntimeException;
import com.zzg.mybatis.exception.ServiceException;

@Component
public class AppServiceHelper implements ApplicationContextAware {
	// 引入日志
	private final static Logger logger = LoggerFactory.getLogger(AppServiceHelper.class);

	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		if (AppServiceHelper.applicationContext == null) {
			AppServiceHelper.applicationContext = applicationContext;
		}
		System.out.println("---------------com.zzg.mybatis.application.AppServiceHelper---------------");
	}

	// 获取applicationContext
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * 根据beanId返回Spring管理的Bean实例对象.
	 * 
	 * @param beanId
	 *            id of bean object
	 * @return spring bean object
	 * @author xiali2
	 * @since May 26, 2010
	 */
	public static Object findBean(String beanId) {
		Object service = null;

		if (applicationContext != null) {
			try {
				if (beanId.indexOf(".") >= -1) {
					beanId = beanId.substring(beanId.lastIndexOf(".") + 1, beanId.length());
				}
				beanId = StringUtils.uncapitalize(beanId);
				if (logger.isDebugEnabled()) {
					logger.debug("Finding bean=" + beanId);
				}
				service = applicationContext.getBean(beanId);
			} catch (NoSuchBeanDefinitionException ex) {
				throw new GlobalRuntimeException("no such bean for[" + beanId + "]", ex);
			} catch (BeansException ex) {
				throw new GlobalRuntimeException("bean exception for[" + beanId + "]", ex);
			}

		}
		return service;
	}

	/**
	 * find the Spring Bean object by Class type.
	 * 
	 * @param clz
	 *            the clz
	 * @return the object
	 * @throws ServiceException
	 *             the service exception
	 */
	@SuppressWarnings(value = "all")
	public static Object findBeanOfType(Class clz) throws ServiceException {
		if (logger.isDebugEnabled()) {
			logger.debug("findBeanOfType=" + (clz == null ? "Empty Class Name" : clz.getName()));
		}
		if (clz == null) {
			return null;
		}
		Object service = null;
		// 建议优化：采用本地缓存 查询实体对象
		// Object service = CacheManager.getFromCache(clz.getName());
		// if (service == null) {
		try {
			Map<String, Object> serviceMap = applicationContext.getBeansOfType(clz);
			Iterator<String> beanNames = serviceMap.keySet().iterator();
			while (beanNames.hasNext()) {
				Object instance = serviceMap.get(beanNames.next());
				if (instance.getClass().equals(clz)) {
					service = instance;
				} else if (AopUtils.isAopProxy(instance)) {
					service = instance;
					break;
				}
			}
			// CacheManager.putInCache(clz.getName(), service);
		} catch (NoSuchBeanDefinitionException ex) {
			throw new GlobalRuntimeException("no such bean for[" + clz + "]", ex);
		} catch (BeansException ex) {
			throw new GlobalRuntimeException("bean exception for[" + clz + "]", ex);
		}
		// }

		return service;
	}

}
