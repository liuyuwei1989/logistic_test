package edu.zd.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import edu.zd.dao.IBaseDao;


public class BaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T>{
	
	//实体类型
		private Class<T> entityClass;
		
		//使用注解方式进行依赖注入
		@Resource
		public void setMySessionFactory(SessionFactory sessionFactory){
			super.setSessionFactory(sessionFactory);
		}
		
		/**
		 * 在构造方法中动态获得操作的实体类型
		 */
		public BaseDaoImpl() {
			//获得父类（BaseDaoImpl<T>）类型
			ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
			//获得父类上的泛型数组
			Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
			entityClass = (Class<T>) actualTypeArguments[0];
		}
		
		public void save(T entity) {
			this.getHibernateTemplate().save(entity);
		}

		public void delete(T entity) {
			this.getHibernateTemplate().delete(entity);
		}

		public void update(T entity) {
			this.getHibernateTemplate().update(entity);
		}

		public T findById(Serializable id) {
			return this.getHibernateTemplate().get(entityClass, id);
		}

		public List<T> findAll() {//FROM User
			String hql = "FROM  " + entityClass.getSimpleName();
			return this.getHibernateTemplate().find(hql);
		}
		/**
		 * 通用更新方法
		 */
		public void executeUpdate(String queryName, Object... objects) {
			Session session = this.getSession();// 从本地线程中获得session对象
			
			// 使用命名查询语句获得一个查询对象
			Query query = session.getNamedQuery(queryName);
			// 为HQL语句中的？赋值
			int i = 0;
			for (Object arg : objects) {
				query.setParameter(i++, arg);
			}
			query.executeUpdate();// 执行更新
		}

	
		public void saveOrUpdate(T entity) {
			this.getHibernateTemplate().saveOrUpdate(entity);
			
		}
		public List<T> findByCriteria(DetachedCriteria detachedCriteria) {
			return this.getHibernateTemplate().findByCriteria(detachedCriteria);
		}
		
	
		
		//根据模板对象查询多个用户
		public List<T> search(T condition) 
		{
			return search(condition,true);
		}

		//根据模板对象查询多个用户(模糊查询)
		public List search(final T condition,final boolean isExact)
		{
			List list = super.getHibernateTemplate().executeFind(new HibernateCallback()
			{
				public Object doInHibernate(Session s) throws HibernateException, SQLException 
				{
					Criteria c = s.createCriteria(entityClass);
					if(condition != null)
					{
						if(isExact)
						{
							c.add(Example.create(condition));
						}
						else
						{
							c.add(Example.create(condition).enableLike(MatchMode.ANYWHERE));
						}
					}			
					return c.list();
				}
				
			});
			return list;	
		}
		
}
