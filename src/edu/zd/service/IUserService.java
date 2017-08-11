package edu.zd.service;

import java.util.List;

import edu.zd.entity.User;

public interface IUserService {
	User login(String name,String password);

	List queryAll();

	void insert(User model);

	void insertOrUpdate(User model);

	User queryById(String id);

	void update(User model);

	void deleteById(String string);
}
