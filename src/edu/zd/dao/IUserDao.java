package edu.zd.dao;

import edu.zd.entity.User;

public interface IUserDao extends IBaseDao<User>{
	User findByUserNameAndPassword(String name,String passwd);
}
