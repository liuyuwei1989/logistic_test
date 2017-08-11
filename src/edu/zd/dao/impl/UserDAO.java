package edu.zd.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import edu.zd.dao.IUserDao;
import edu.zd.entity.User;
@Repository
public class UserDAO extends BaseDaoImpl<User> implements IUserDao{
	@Override
	public User findByUserNameAndPassword(String name, String passwd) {
		String hql = "from User where username=? and password=?";
		@SuppressWarnings({ "rawtypes" })
		List list = super.getHibernateTemplate().find(hql, name,passwd);
		if(list!=null&&!list.isEmpty())
			return (User)list.get(0);
		else
			return null;
	}
	
}
