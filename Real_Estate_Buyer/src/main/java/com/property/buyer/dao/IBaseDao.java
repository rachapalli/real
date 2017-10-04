/* 
 * ===========================================================================
 * File Name IBaseDao.java
 * 
 * Created on 15-Jun-2017
 *
 * This code contains copyright information which is the proprietary property
 * of GirasolPayments-Admin. No part of this code may be reproduced, stored or transmitted
 * in any form without the prior written permission of GirasolPayments-Admin.
 *
 * Copyright (C) GirasolPayments-Admin. 2017
 * All rights reserved.
 *
 * Modification history:
 * $Log: IBaseDao.java,v $
 * ===========================================================================
 */

package com.property.buyer.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * This is a base dao interface ,it is used to perform the basic operation on
 * entity
 * 
 * @author prabinar
 * @version 1.0 - 17-Jun-2017
 */
public interface IBaseDao<T> {

	/**
	 * Find and retrieve list of object as per provided sql param string and
	 * parameter with maximum records size define.
	 * 
	 * @author prabinar
	 * @date 20-Jun-2017
	 * @return <code>List<X></code>
	 * @param clazz
	 * @param ids
	 * @throws HibernateException
	 */
	 <X> List<X> findById(final Class<T> clazz, final List<Long> ids) throws HibernateException;

	/**
	 * Find and retrieve list of object as per provided sql param string and
	 * parameter with maximum records size define.
	 * 
	 * @author prabinar
	 * @date 20-Jun-2017
	 * @return <code>List<X></code>
	 * @param clazz
	 * @param column
	 * @param value
	 * @throws HibernateException
	 */
	 <X> List<X> findByColumn(final Class<T> clazz, final String column, final Object value) throws HibernateException;

	/**
	 * Find and retrieve unique object as per provided sql param string and
	 * parameter with maximum records size define.
	 * 
	 * @author prabinar
	 * @date 20-Jun-2017
	 * @return <code>X</code>
	 * @param clazz
	 * @param column
	 * @param value
	 * @throws HibernateException
	 */
	 <X> X findUniqueByColumn(final Class<T> clazz, final String column, final Object value) throws HibernateException;

	/**
	 * Find all details of a entity
	 * 
	 * @author prabinar
	 * @date 20-Jun-2017
	 * @return <code>List<X></code>
	 * @param clazz
	 * @throws HibernateException
	 */
	 <X> List<X> findAll(final Class<T> clazz) throws HibernateException;

	/**
	 * Delete entity object based on the id that is related to a specific bean
	 * 
	 * @author prabinar
	 * @date 20-Jun-2017
	 * @return <code>void</code>
	 * @param clazz
	 * @throws HibernateException
	 */
	 void deleteById(final Class<T> clazz, final Long objId) throws HibernateException;

	/**
	 * Save if not persit or update if already persist entity object in db.
	 * 
	 * @author prabinar
	 * @date 20-Jun-2017
	 * @return <code>T</code>
	 * @param paramT
	 * @throws HibernateException
	 */
	 T merge(final T paramT) throws HibernateException;

	/**
	 * This method id used to get the session object
	 * 
	 * @author prabinar
	 * @date 20-Jun-2017
	 * @return <code>Session</code>
	 */
	 Session getCurrentSession();

	/**
	 * This method will get list of class by two column value..
	 * 
	 * @author prabinar
	 * @date 20-Jun-2017
	 * @return <code>List<X></code>
	 * @param clazz
	 * @param email
	 * @param cellphone
	 * @param emailValue
	 * @param cellPhoneValue
	 * @throws HibernateException
	 */
	 <X> List<X> findByTwoOredColumn(final Class<T> clazz, String emailValue,
			String cellPhoneValue) throws HibernateException;

	/**
	 * This method return list of clazz object by and of two column value.
	 * 
	 * @author prabinar
	 * @date 20-Jun-2017
	 * @return <code>List<X></code>
	 * @param clazz
	 * @param idColumn
	 * @param column
	 * @param idValue
	 * @param columnValue
	 * @throws HibernateException
	 */
	 <X> List<X> findByIdAndColumn(final Class<T> clazz, final String idColumn, final String column,final Long idValue,
			String columnValue) throws HibernateException;

	/**
	 * Save entity object related to specified bean object passed as method
	 * argument.
	 * 
	 * @author prabinar
	 * @date 17-Jun-2017
	 * @return <code>Serializable</code>
	 * @param paramT
	 * @throws HibernateException
	 */
	Serializable save(T paramT) throws HibernateException;

	/**
	 * Delete entity object related to specified bean object passed as method
	 * argument.
	 * 
	 * @author prabinar
	 * @date 17-Jun-2017
	 * @return <code>void</code>
	 * @param paramT
	 * @throws HibernateException
	 * @throws SQLException
	 */
	void delete(final T paramT) throws HibernateException, SQLException;

	/**
	 * Delete entity using some parameter of specified object.
	 * 
	 * @author prabinar
	 * @date 17-Jun-2017
	 * @return <code>void</code>
	 * @param paramString
	 * @param paramMap
	 * @throws HibernateException
	 * @throws SQLException
	 */
	void delete(final String paramString,final Map<String, Object> paramMap) throws HibernateException, SQLException;

	/**
	 * Find unique object using param query and parameter.
	 * 
	 * @author prabinar
	 * @date 17-Jun-2017
	 * @return <code>X</code>
	 * @param paramString
	 * @param paramMap
	 * @throws HibernateException
	 */
	<X> X findUnique(final String paramString, final Map<String, Object> paramMap) throws HibernateException;

	/**
	 * Find unique object using param query and parameter.
	 * 
	 * @author prabinar
	 * @date 17-Jun-2017
	 * @return <code>X</code>
	 * @param paramString
	 * @param paramMap
	 * @param clazz
	 * @throws HibernateException
	 */
	<X> X findUnique(final String paramString, final Map<String, Object> paramMap, final Class<T> clazz) throws HibernateException;

	/**
	 * Save if not persist or update if already persist entity object in db.
	 * 
	 * @author prabinar
	 * @date 17-Jun-2017
	 * @return <code>void</code>
	 * @param paramT
	 * @throws HibernateException
	 */
	void saveOrUpdate(final T paramT) throws HibernateException;

	/**
	 * This method is used to get session factory object.
	 * 
	 * @author prabinar
	 * @date 17-Jun-2017
	 * @return <code>SessionFactory</code>
	 * @throws HibernateException
	 */
	SessionFactory getSessionFactory() throws HibernateException;

	/**
	 * Find and retrieve list of object as per provided sql param string and
	 * parameter with maximum records size define.
	 * 
	 * @author prabinar
	 * @date 17-Jun-2017
	 * @return <code>List<X></code>
	 * @param paramString
	 * @param paramMap
	 * @param maxResults
	 * @throws HibernateException
	 */
	<X> List<X> find(final String paramString, final Map<String, Object> paramMap, final int maxResults) throws HibernateException;

	/**
	 * Find and retrieve list of object as per provided sql param string and
	 * parameter with maximum records size define.
	 * 
	 * @author prabinar
	 * @date 17-Jun-2017
	 * @return <code>List<X></code>
	 * @param paramString
	 * @param paramMap
	 * @throws HibernateException
	 */
	<X> List<X> find(final String paramString, final Map<String, Object> paramMap) throws HibernateException;

	/**
	 * Find and retrieve list of object as per provided sql param string.
	 * 
	 * @author prabinar
	 * @date 17-Jun-2017
	 * @return <code>List<X></code>
	 * @param paramString
	 * @throws HibernateException
	 */
	<X> List<X> findAll(final String paramString) throws HibernateException;

	/**
	 * Find and retrieve list of object as per provided sql param string.
	 * 
	 * @author prabinar
	 * @date 17-Jun-2017
	 * @return <code>List<X></code>
	 * @param paramString
	 * @param map
	 * @throws HibernateException
	 */
	<X> List<X> findAll(final String paramString, final Map<String, Object> map) throws HibernateException;

	/**
	 * Find and retrieve list of bean object as per provided sql param string.
	 * 
	 * @author prabinar
	 * @date 17-Jun-2017
	 * @return <code>List<X></code>
	 * @param paramString
	 * @param className
	 * @throws HibernateException
	 */
	<X> List<X> findAll(final String paramString, final Class<T> className) throws HibernateException;

	/**
	 * Find and retrieve list of bean object as per provided sql param string.
	 * 
	 * @author prabinar
	 * @date 17-Jun-2017
	 * @return <code>List<X></code>
	 * @param paramString
	 * @param className
	 * @param map
	 * @throws HibernateException
	 */
	<X> List<X> findAll(final String paramString,final Class<T> className, final Map<String, Object> map) throws HibernateException;

	/**
	 * Execute sql query pass as an argument to database.
	 * 
	 * @author prabinar
	 * @date 17-Jun-2017
	 * @return <code>Object</code>
	 * @param sql
	 * @throws Exception
	 */
	Object executSQL(final String sql) throws Exception;

	/**
	 * Get a transaction object bind with hibernate context and initialize the
	 * transaction a pair of initializeTransaction and commitTransaction or
	 * rollBackTransaction must have to use
	 * 
	 * @author prabinar
	 * @date 17-Jun-2017
	 * @return <code>Transaction</code>
	 * @throws HibernateException
	 */
	Transaction initializeTransaction() throws HibernateException;

	/**
	 * commit using transaction object passed as an argument a pair of
	 * initializeTransaction and commitTransaction or rollBackTransaction must
	 * have to use
	 * 
	 * @author prabinar
	 * @date 17-Jun-2017
	 * @return <code>void</code>
	 * @param tx
	 */
	void commitTransaction(final Transaction tranasaction);

	/**
	 * rollback using transaction object passed as an argument a pair of
	 * initializeTransaction and commitTransaction or rollBackTransaction must
	 * have to use
	 * 
	 * @author prabinar
	 * @date 17-Jun-2017
	 * @return <code>void</code>
	 * @param tx
	 */
	void rollBackTransaction(final Transaction transaction);

	/**
	 * Find and retrieve list of bean object as per provided sql param string,
	 * paramMap and listMap.
	 * 
	 * @author prabinar
	 * @date 17-Jun-2017
	 * @return <code>List<Long></code>
	 * @param string
	 * @param paramMap
	 * @param listMap
	 * @return
	 */
	List<Long> find(final String string, final Map<String, Object> paramMap, final Map<String, List<Long>> listMap);

	/**This method fetch details of object on basis of criteria.
	 * @author prabinar
	 * @date 07-Sep-2017
	 * @return <code>List<X></code>
	 * @param queryString
	 * @param map
	 * @param start
	 * @param amount
	 * @param className
	 * @throws HibernateException
	 */
	<X> List<X> findAll(final String queryString, final Map<String, Object> map, final int start, final int amount, final Class<T> className)
			throws HibernateException;

	/**
	 * This method is used to get list of objects on basis of list of column
	 * value
	 * 
	 * @author shravans
	 * @date Jul 27, 2017
	 * @return <code>List<X></code>
	 * @param clazz
	 * @param fieldName
	 * @param idList
	 * @return
	 * @throws HibernateException
	 */
	<X> List<X> findAll(final Class<T> clazz, String fieldName, final List<Long> idList) throws HibernateException;

}
