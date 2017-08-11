package edu.zd.service;

import java.util.List;

import edu.zd.entity.Staff;

public interface IStaffService {

	List queryAll();

	void deleteById(String string);

	void add(Staff model);

	void update(Staff model);

	Staff queryById(String id);

}
