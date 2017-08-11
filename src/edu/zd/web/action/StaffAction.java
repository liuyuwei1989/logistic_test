package edu.zd.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import edu.zd.entity.Staff;
import edu.zd.service.IStaffService;
import edu.zd.web.action.base.BaseAction;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff> {
	//查询所有员工
	public String queryAll(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		try {
			PrintWriter out = response.getWriter();
			List list = staffService.queryAll();
			out.print(JSONArray.fromObject(list));
		} catch (IOException e) {
			e.printStackTrace();
			return "error";
		}
		return null;
	}
	//删除员工
	public String deleteStaff(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String[] ids = request.getParameter("ids").split(":");
		for(String id:ids){
			staffService.deleteById(id);
		}
		return null;
	}
	//增加员工
	public String add(){
		staffService.add(model);
		return null;
	}
	//更改员工
	public String update(){
		staffService.update(model);
		return null;
	}
	//根据ID查找员工
	public String queryById(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		model=staffService.queryById(model.getId());
		try {
			PrintWriter out = response.getWriter();
			out.print(JSONObject.fromObject(model));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private IStaffService staffService;
	
	@Resource
	public void setStaffService(IStaffService staffService) {
		this.staffService = staffService;
	}
}
