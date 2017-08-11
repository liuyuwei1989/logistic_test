package edu.zd.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.zd.dao.IStaffDao;
import edu.zd.entity.Staff;
import edu.zd.service.IStaffService;
@Service
public class StaffService implements IStaffService {

	@Override
	@Transactional(readOnly=false)
	public void deleteById(String id) {
		staffDao.delete(staffDao.findById(id));
	}
	@Override
	@Transactional(readOnly=false)
	public void add(Staff model) {
		staffDao.save(model);
	}
	@Override
	@Transactional(readOnly=false)
	public void update(Staff model) {
		staffDao.update(model);
	}
	@Override
	public List queryAll() {
		return staffDao.findAll();
	}
	@Override
	public Staff queryById(String id) {
		return staffDao.findById(id);
	}
	private IStaffDao staffDao;
	@Resource
	public void setStaffDao(IStaffDao staffDao) {
		this.staffDao = staffDao;
	}



}
