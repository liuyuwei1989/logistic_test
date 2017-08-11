package edu.zd.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.zd.dao.IUserDao;
import edu.zd.entity.User;
import edu.zd.service.IUserService;
@Service

public class UserService implements IUserService {
	
	@Override
	public User login(String name, String password) {
		return userDao.findByUserNameAndPassword(name, password);
	}
	@Override
	public List queryAll() {
		return userDao.findAll();
	}
	private IUserDao userDao;
	@Resource
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}
	@Transactional(readOnly = false) 
	@Override
	public void insert(User model) {
		this.userDao.save(model);
	}
	@Transactional(readOnly = false)
	@Override
	public void insertOrUpdate(User model) {
		this.userDao.saveOrUpdate(model);
	}
	@Override
	public User queryById(String id) {
		return userDao.findById(id);
	}
	@Transactional(readOnly = false)
	@Override
	public void update(User model) {
		this.userDao.update(model);
	}
	@Transactional(readOnly = false)
	@Override
	public void deleteById(String id) {
		userDao.delete(userDao.findById(id));
	}


}
