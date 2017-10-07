/* 
 * ===========================================================================
 * File Name IBaseDao.java
 * 
 * Created on Oct 04, 2017
 *
 * This code contains copyright information which is the proprietary property
 * of Real_Estate. No part of this code may be reproduced, stored or transmitted
 * in any form without the prior written permission of Real_Estate.
 *
 * Copyright (C) Real_Estate. 2017
 * All rights reserved.
 *
 * Modification history:
 * $Log: IBaseDao.java,v $
 * ===========================================================================
 */
package com.property.buyer.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public interface IBaseDao<T> {
	
	/**
	 * Delete entity object related to specified bean object passed as method argument
	 * 
	 * @author gauravk
	 * @param     paramT A Object type
	 * @exception HibernateException
	 * @return   Serializable
	 */
	public void delete(T paramT) throws HibernateException;
    
    /**Delete entity object based on the id that is related to a specific bean 
     * 
     * @author gauravk
     * @param clazz
     * @param id
     * @return 
     * @throws HibernateException
     */
    public boolean deleteById(Class<T> clazz,Long id) throws HibernateException;
   
    /**
     * Find and retrieve list of object as per provided sql param string and
     * parameter with maximum records size define
     * @author gauravk
     * @param     paramString A String object contain
     * @param     paramMap A Map<String, Object> object contain
     * @param     maxResults A integer type variable contain
     * @exception HibernateException
     * @return   List of type object
     */
    
   public <X> List<X> findAll(final Class<T> clazz) throws HibernateException;
    
    /**
     * Find and retrieve list of object as per provided sql param string and
     * parameter with maximum records size define
     * @author gauravk
     * @param     paramString A String object contain
     * @param     paramMap A Map<String, Object> object contain
     * @param     maxResults A integer type variable contain
     * @exception HibernateException
     * @return   List of type object
     */
    
    public <X> List<X> findByColumn(final Class<T> clazz, String column, Object value) throws HibernateException;
    
    /**
     * Find and retrieve list of objects as per provided sql param string and
     * parameter with maximum records size define
     * @author vikashk3
     * @date Apr 28, 2017
     * @return <code>List<X></code>
     * @param clazz
     * @param column
     * @param values
     * @throws HibernateException
     */
    public <X> List<X> findByColumn(Class<T> clazz, String column, String... values) throws HibernateException;
    
    /**
     * Find and retrieve list of object as per provided sql param string and
     * parameter with maximum records size define
     * @author gauravk
     * @param     paramString A String object contain
     * @param     paramMap A Map<String, Object> object contain
     * @param     maxResults A integer type variable contain
     * @exception HibernateException
     * @return   List of type object
     */
    
    public <X> List<X> findById(final Class<T> clazz, final List<Long> ids) throws HibernateException;
    
    /**
    * Find unique object using param query and parameter
    * @author gauravk
    * @param     paramString A String object contain
    * @param     paramMap A Map<String, Object> object contain
    * @exception HibernateException
    * @return   Object
    */
    
   public <X> X findById(final Class<T> clazz, final Long id) throws HibernateException;
    
    
    /**
     * Find and retrieve list of object as per provided sql param string and
     * parameter with maximum records size define
     * @author gauravk
     * @param     paramString A String object contain
     * @param     paramMap A Map<String, Object> object contain
     * @param     maxResults A integer type variable contain
     * @exception HibernateException
     * @return   List of type object
     */
    
    public <X> List<X> findById(final Class<T> clazz, final Long...id) throws HibernateException;

    /**
     * Find and retrieve unique object as per provided sql param string and
     * parameter with maximum records size define
     * @author gauravk
     * @param     paramString A String object contain
     * @param     paramMap A Map<String, Object> object contain
     * @param     maxResults A integer type variable contain
     * @exception HibernateException
     * @return   List of type object
     */
    
    public <X> X findUniqueByColumn(final Class<T> clazz, String column, Object value) throws HibernateException;
    /**This method id used to get the session object 
     * 
     * @author gauravk
     * @return session 					session object
     */
    public Session getCurrentSession();
    
    /**
     * Save if not persit or update if already persist entity object in db
     *
     * @author gauravk
     * @param     paramT A Object type
     * @exception HibernateException
     * @return   void
     */
    public T merge(T paramT) throws HibernateException;
    
    /**
     * Save entity object related to specified bean object passed as method argument
     * @author gauravk
     * @param     paramT A Object type
     * @exception HibernateException
     * @return   Serializable
     */
    public Serializable save(T paramT) throws HibernateException;
    
    /**
     * Save if not persit or update if already persist entity object in db
     * @author gauravk
     * @param     paramT A Object type
     * @exception HibernateException
     * @return   void
     */
    public void saveOrUpdate(T paramT) throws HibernateException;
    
	/**Force this session to flush. Must be called at the end of a
	 * unit of work, before committing the transaction and closing the
	 * session 
	 * 
	 * @author vikashk3
	 * @date May 25, 2017
	 * @return <code>void</code>
	 * @throws HibernateException
	 */
	public void flush() throws HibernateException;
}
