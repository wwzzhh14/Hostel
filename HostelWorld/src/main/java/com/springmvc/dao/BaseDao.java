package com.springmvc.dao;


import org.hibernate.*;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class BaseDao {
	private static final Logger log = LoggerFactory.getLogger(BaseDao.class);

	@Resource
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.openSession();
	}

	public boolean save(Object entity) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(entity);
			tx.commit();
			session.clear();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			return false;
		} finally {
			session.close();
		}
	}

	public boolean update(Object entity) {
		Session session = getSession();
		try {
			session.beginTransaction();
			session.update(entity);
			session.getTransaction().commit();
			session.clear();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			if (session!=null) {
				session.getTransaction().rollback();
			}
			return false;
		} finally {
			session.close();
		}

	}

	public boolean delete(Object entity) {
		Session session = getSession();
		try {
			session.beginTransaction();
			session.delete(entity);
			session.getTransaction().commit();
			session.clear();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			if (session!=null) {
				session.getTransaction().rollback();
			}
			return false;
		} finally {
			session.close();
		}
	}

	public boolean deleteById(Class<?> className, int id) {
		Session session = getSession();
		try {
			session.beginTransaction();
			Object instance = session.get(className, id);
			session.delete(instance);
			session.getTransaction().commit();
			session.clear();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			if (session!=null) {
				session.getTransaction().rollback();
			}
			return false;
		} finally {
			session.close();
		}
	}

	public boolean deleteByProperties(Class<?> className, String[] propertyNames,
								  Object[] values) {
		Session session = getSession();
		try {
			session.beginTransaction();
			String hql = "";
			for(int i=0;i<propertyNames.length;i++) {
				if(i<propertyNames.length-1){
					hql+=("model."+propertyNames[i] + "= ?"+" and ");
				}else {
					hql+=("model."+propertyNames[i] + "= ?");
				}
			}

			Query q= session.createQuery("delete from "+className.getSimpleName()+" as model  where "+hql);
			for (int i =0 ;i< values.length;i++){
				q.setParameter(i, values[i]);
			}
			q.executeUpdate();
			session.getTransaction().commit();
			session.clear();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			if (session!=null) {
				session.getTransaction().rollback();
			}
			return false;
		} finally {
			session.close();
		}
	}

	public boolean updateByProperties(Class<?> className, String[] propertyNames,
								  Object[] values,String[] updatePropertyNames,Object[] updateValues) {
		Session session = getSession();
		try {
			session.beginTransaction();
			String hql1 = "";
			for(int i=0;i<updatePropertyNames.length;i++) {
				if(i<updatePropertyNames.length-1){
					hql1+=(updatePropertyNames[i] + "= ?"+" , ");
				}else {
					hql1+=(updatePropertyNames[i] + "= ?");
				}
			}

			String hql2 = "";

			for(int i=0;i<propertyNames.length;i++) {
				if(i<propertyNames.length-1){
					hql2+=(propertyNames[i] + "= ?"+" and ");
				}else {
					hql2+=(propertyNames[i] + "= ?");
				}
			}
			Query q= session.createQuery("update "+className.getSimpleName()+" set "+hql1+" where "+hql2);
			for (int i =0 ;i< updateValues.length;i++){
				q.setParameter(i, updateValues[i]);
			}
			for (int i =updateValues.length ;i< values.length+updateValues.length;i++){
				q.setParameter(i, values[i-updateValues.length]);
			}
			q.executeUpdate();
			session.getTransaction().commit();
			session.clear();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			if (session!=null) {
				session.getTransaction().rollback();
			}
			return false;
		} finally {
			session.close();
		}
	}


	public List<?> getPageAll(Class<?> className, int up, int size) {
		List<?> list = null;
		Session session = getSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(className);
			criteria.addOrder(Order.desc("id"));
			criteria.setFirstResult(up);
			criteria.setMaxResults(size);
			list = criteria.list();
			session.getTransaction().commit();
			session.clear();
		} catch (Exception e) {
			e.printStackTrace();
			if (session!=null) {
				session.getTransaction().rollback();
			}
		} finally {
			session.close();
		}
		return list;
	}
	public List<?> getAll(Class<?> className) {
		List<?> list = null;
		Session session = getSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(className);
			criteria.addOrder(Order.desc("id"));
			list = criteria.list();
			session.getTransaction().commit();
			session.clear();
		} catch (Exception e) {
			e.printStackTrace();
			if (session!=null) {
				session.getTransaction().rollback();
			}
		} finally {
			session.close();
		}
		return list;
	}



	public Object findById(Class<?> className, String id) {
		Session session = getSession();
		try {
			Object instance = session.get(className, id);
			return instance;
		} catch (Exception re) {
			re.printStackTrace();
		return null;
		} finally {
			session.close();
		}
	}

	public List<?> findByExample(Object entity) {
		List<?> list = null;
		Session session = getSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(entity.getClass());
			criteria.addOrder(Order.desc("id"));
			criteria.add(Example.create(entity).enableLike(MatchMode.ANYWHERE));
			list = criteria.list();
			session.getTransaction().commit();
			// List<?> list = getSession().createQuery(
			// "from  " + className.getSimpleName()).list();
			session.clear();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return list;
	}

	public List<?> findByProperty(Class<?> className, String propertyName,
			Object value) {
		log.debug("finding Room instance with property: " + propertyName
				+ ", value: " + value);
		Session session = getSession();
		try {
			String queryString = "from " + className.getSimpleName()
					+ " as model where model." + propertyName + "= ?";
			Query queryObject = session.createQuery(queryString);
			queryObject.setParameter(0, value);

			List<?> list = queryObject.list();
			return list;
		} catch (Exception re) {
			re.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}

	public List<?> findByPropertys(Class<?> className, String[] propertyNames,
								  Object[] values) {

		Session session = getSession();
		try {
			String queryString = "from " + className.getSimpleName()
					+ " as model where ";
			StringBuffer buffer = new StringBuffer(queryString);
			for(int i =0 ;i<propertyNames.length;i++){
				if(i<propertyNames.length-1){
					buffer.append("model." + propertyNames[i] + "= ?"+" and ");
				}else {
					buffer.append("model." + propertyNames[i] + "= ?");
				}

			}
			Query queryObject = session.createQuery(buffer.toString());
			for (int i =0 ;i< values.length;i++){
				queryObject.setParameter(i, values[i]);
			}

			List<?> list = queryObject.list();
			return list;
		} catch (Exception re) {
			re.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}

	public List<Object[]> execSqlQuery(String sql) {
		Session session = getSession();
		try {
			session.beginTransaction();
			List<Object[]> objects = session.createSQLQuery(sql).list();
			session.getTransaction().commit();
			session.clear();
			return objects;
		} catch (Exception re) {
			re.printStackTrace();
			if (session!=null) {
				session.getTransaction().rollback();
			}
			return null;
		} finally {
			session.close();
		}
	}

	public List<?> execQuery(String sql,Object[] values) {
		Session session = getSession();
		try {
			session.beginTransaction();
			Query queryObject = session.createQuery(sql);
			for (int i =0 ;i< values.length;i++){
				queryObject.setParameter(i, values[i]);
			}

			List<?> list = queryObject.list();
			session.getTransaction().commit();
			session.clear();
			return list;
		} catch (Exception re) {
			re.printStackTrace();
			if (session!=null) {
				session.getTransaction().rollback();
			}
			return null;
		} finally {
			session.close();
		}
	}

	public int clearTable(String myTable){
	    String hql = String.format("delete from %s",myTable);
		Session session = getSession();
	    Query query = session.createQuery(hql);
		int res = query.executeUpdate();
		session.close();
	    return res;
	}





}
