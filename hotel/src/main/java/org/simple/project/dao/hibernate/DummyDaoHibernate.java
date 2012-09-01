package org.simple.project.dao.hibernate;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.simple.project.dao.DummyDao;
import org.simple.project.model.Dummy;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

@Repository("dummyDao")
public class DummyDaoHibernate extends GenericDaoHibernate<Dummy, Long> implements DummyDao {

	public DummyDaoHibernate() {
		super(Dummy.class);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Object[]> findNativeQuery(final String sql, final Object... param) {
//		List retList;
//		String x =  " select r.*,ep.price, ep.id_event, t.* from room as r " +
//					" join event_price as ep on ( ep.id_room_type = r.id_room_type ) " +
//					" left join transaction t on ( t.id_room = r.id and date_format(coalesce(check_in_time,sysdate()),'%d%m%Y') = date_format(sysdate(),'%d%m%Y')  ) ";
		
		return (List)getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s) throws HibernateException {
						SQLQuery q = s.createSQLQuery(sql);

						if(param != null) 
							for(int i = 0; i< param.length; i++){
								if(param[i] instanceof String) q.setString(i, (String)param[i]);
								else if(param[i] instanceof Character) q.setCharacter(i, (Character)param[i]);
								else if(param[i] instanceof Boolean) q.setBoolean(i, (Boolean)param[i]);
								else if(param[i] instanceof Byte) q.setByte(i, (Byte)param[i]);
								else if(param[i] instanceof Short) q.setShort(i, (Short)param[i]);
								else if(param[i] instanceof Integer) q.setInteger(i, (Integer)param[i]);
								else if(param[i] instanceof Long) q.setLong(i, (Long)param[i]);
								else if(param[i] instanceof Float) q.setFloat(i, (Float)param[i]);
								else if(param[i] instanceof Double) q.setDouble(i, (Double)param[i]);
								else if(param[i] instanceof byte[]) q.setBinary(i, (byte[])param[i]);
								else if(param[i] instanceof BigDecimal) q.setBigDecimal(i, (BigDecimal)param[i]);
								else if(param[i] instanceof BigInteger) q.setBigInteger(i, (BigInteger)param[i]);
								else if(param[i] instanceof Date) q.setDate(i, (Date)param[i]);
								else if(param[i] instanceof Time) q.setTime(i, (Time)param[i]);
								else if(param[i] instanceof Timestamp) q.setTimestamp(i, (Timestamp)param[i]);
								else if(param[i] instanceof Calendar) q.setCalendar(i, (Calendar)param[i]);
							}
						return q.list();
					}
				}
		);
		
//		Session s = getSessionFactory().getCurrentSession();
//		SQLQuery q = s.createSQLQuery(sql);
//		
//		if(param != null) 
//			for(int i = 0; i< param.length; i++){
//				if(param[i] instanceof String) q.setString(i, (String)param[i]);
//				else if(param[i] instanceof Character) q.setCharacter(i, (Character)param[i]);
//				else if(param[i] instanceof Boolean) q.setBoolean(i, (Boolean)param[i]);
//				else if(param[i] instanceof Byte) q.setByte(i, (Byte)param[i]);
//				else if(param[i] instanceof Short) q.setShort(i, (Short)param[i]);
//				else if(param[i] instanceof Integer) q.setInteger(i, (Integer)param[i]);
//				else if(param[i] instanceof Long) q.setLong(i, (Long)param[i]);
//				else if(param[i] instanceof Float) q.setFloat(i, (Float)param[i]);
//				else if(param[i] instanceof Double) q.setDouble(i, (Double)param[i]);
//				else if(param[i] instanceof byte[]) q.setBinary(i, (byte[])param[i]);
//				else if(param[i] instanceof BigDecimal) q.setBigDecimal(i, (BigDecimal)param[i]);
//				else if(param[i] instanceof BigInteger) q.setBigInteger(i, (BigInteger)param[i]);
//				else if(param[i] instanceof Date) q.setDate(i, (Date)param[i]);
//				else if(param[i] instanceof Time) q.setTime(i, (Time)param[i]);
//				else if(param[i] instanceof Timestamp) q.setTimestamp(i, (Timestamp)param[i]);
//				else if(param[i] instanceof Calendar) q.setCalendar(i, (Calendar)param[i]);
//			}
		
//		retList = q.list();
//		s.close();
		
//		return retList;
	}

	
	
}

