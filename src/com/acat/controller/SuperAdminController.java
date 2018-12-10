package com.acat.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.acat.pojo.Admin;
import com.acat.pojo.SuperAdmin;
import com.acat.service.SuperAdminService;

@Controller
@RequestMapping("/superadmin")
public class SuperAdminController {
	
	@Autowired
	private SuperAdminService sas;
	
	//登录
	@RequestMapping("/login")
	public String login(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		request.getSession().setAttribute("pass",password);
		System.out.println(username);
		System.out.println(password);
		
		SuperAdmin superAdmin = sas.login(username, password);
		if(superAdmin==null){
			model.addAttribute("uu","<script type='text/javascript'>alert('账号密码错误，请重新输入')</script>");
			return "/WEB-INF/superadmin/login.jsp";
		}else{
			request.getSession().setAttribute("superAdmin", superAdmin);
			List<Admin> cs= sas.adminAll();
			model.addAttribute("cs",cs);
			return "/WEB-INF/superadmin/welcome.jsp";
		}
	}
	
	//安全退出
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		HttpSession session = request.getSession();
		session.removeAttribute("superAdmin");
		return "/index.jsp";
	}
	
	//寻找超级管理员接口
	@RequestMapping("/findSuperAdmin")
	public String findSuperAdmin(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		int id = Integer.parseInt(request.getParameter("id"));
		SuperAdmin superAdmin = sas.findSuperAdmin(id);
		model.addAttribute("superAdmin", superAdmin);
		return "/WEB-INF/superadmin/welcome.jsp";
	}
	
	//超级管理员修改信息接口
	@RequestMapping("/updateSuperAdmin")
	public String updateSuperAdmin(SuperAdmin superAdmin,HttpServletRequest request,HttpServletResponse response,ModelMap model){
		String nick = request.getParameter("nick");
		superAdmin.setNick(nick);
		sas.updateInfor(superAdmin);
		model.addAttribute("uu","<script type='text/javascript'>alert('修改信息成功')</script>");
		return "/WEB-INF/superadmin/welcome.jsp";
	}
	
	//超级管理员修改密码接口
	@RequestMapping("/updateSuperAdminPassword")
	public String updateSuperAdminPassword(SuperAdmin superAdmin,HttpServletRequest request,HttpServletResponse response,ModelMap model) throws IOException{
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		String reqnewPassword = request.getParameter("reqnewPassword");
		
		if(oldPassword.equals(request.getSession().getAttribute("pass"))){
			if(newPassword.equals(reqnewPassword)){
				superAdmin.setPassword(newPassword);
				sas.updateInfor(superAdmin);
				model.addAttribute("uu","<script type='text/javascript'>alert('修改密码成功')</script>");
				return "/WEB-INF/superadmin/welcome.jsp";
				
				//response.getWriter().write("修改密码成功");
			}else{
				model.addAttribute("uu","<script type='text/javascript'>alert('两次输入密码匹配不成功')</script>");
				return "/WEB-INF/superadmin/welcome.jsp";
				//response.getWriter().write("两次输入密码匹配不成功");
			}
		}else{
			model.addAttribute("uu","<script type='text/javascript'>alert('旧密码匹配不成功')</script>");
			return "/WEB-INF/superadmin/welcome.jsp";
			//response.getWriter().write("旧密码匹配不成功");
		}
	}
	
	//超级管理员添加管理员接口
	@RequestMapping("/addAdmin")
	public String addAdmin(@ModelAttribute Admin admin,HttpServletRequest request,HttpServletResponse response,ModelMap model){
		sas.addAdmin(admin);
		model.addAttribute("uu","<script type='text/javascript'>alert('添加管理员成功')</script>");
		return "/WEB-INF/superadmin/welcome.jsp";
	}
	
	//超级管理员删除管理员接口
	@RequestMapping("/deleteAdmin")
	public String deleteAdmin(@ModelAttribute Admin admin,HttpServletRequest request,HttpServletResponse response,ModelMap model) throws IOException{
		int id = Integer.parseInt(request.getParameter("id"));
		sas.deleteAdmin(id);
//		model.addAttribute("uu","<script type='text/javascript'>alert('删除管理员成功')</script>");
//		return "/WEB-INF/superadmin/welcome.jsp";
		response.getWriter().write("删除成功");
		return null;
	}
}
