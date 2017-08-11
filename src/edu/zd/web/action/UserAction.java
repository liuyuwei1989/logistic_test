package edu.zd.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import edu.zd.entity.User;
import edu.zd.service.IUserService;
import edu.zd.web.action.base.BaseAction;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {

	public String login(){
		model=userService.login(model.getUsername(), model.getPassword());
		if(model==null){
			return "error";
		}
		return "index";
	}
	
	public String queryAll(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		List list =userService.queryAll();
		try {
			PrintWriter out = response.getWriter();
			
			JSONArray array =JSONArray.fromObject(list);
			
			out.print(array);
		} catch (IOException e) {
			e.printStackTrace();
			return "error";
		}
		return null;
	}
	
	public String addOrUpdate(){
		userService.insertOrUpdate(model);
		return null;
	}
	
	public String add(){
		userService.insert(model);
		return null;
	}
	
	public String update(){
		userService.update(model);
		return null;
	}
	
	public String queryById(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		model=userService.queryById(model.getId());
		try {
			PrintWriter out = response.getWriter();
			out.print(JSONObject.fromObject(model));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String deleteUser(){
		userService.deleteById(model.getId());
		return null;
	}
	
	private IUserService userService;
	
	@Resource
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
}
