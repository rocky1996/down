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
	
	//��¼
	@RequestMapping("/login")
	public String login(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		request.getSession().setAttribute("pass",password);
		System.out.println(username);
		System.out.println(password);
		
		SuperAdmin superAdmin = sas.login(username, password);
		if(superAdmin==null){
			model.addAttribute("uu","<script type='text/javascript'>alert('�˺������������������')</script>");
			return "/WEB-INF/superadmin/login.jsp";
		}else{
			request.getSession().setAttribute("superAdmin", superAdmin);
			List<Admin> cs= sas.adminAll();
			model.addAttribute("cs",cs);
			return "/WEB-INF/superadmin/welcome.jsp";
		}
	}
	
	//��ȫ�˳�
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		HttpSession session = request.getSession();
		session.removeAttribute("superAdmin");
		return "/index.jsp";
	}
	
	//Ѱ�ҳ�������Ա�ӿ�
	@RequestMapping("/findSuperAdmin")
	public String findSuperAdmin(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		int id = Integer.parseInt(request.getParameter("id"));
		SuperAdmin superAdmin = sas.findSuperAdmin(id);
		model.addAttribute("superAdmin", superAdmin);
		return "/WEB-INF/superadmin/welcome.jsp";
	}
	
	//��������Ա�޸���Ϣ�ӿ�
	@RequestMapping("/updateSuperAdmin")
	public String updateSuperAdmin(SuperAdmin superAdmin,HttpServletRequest request,HttpServletResponse response,ModelMap model){
		String nick = request.getParameter("nick");
		superAdmin.setNick(nick);
		sas.updateInfor(superAdmin);
		model.addAttribute("uu","<script type='text/javascript'>alert('�޸���Ϣ�ɹ�')</script>");
		return "/WEB-INF/superadmin/welcome.jsp";
	}
	
	//��������Ա�޸�����ӿ�
	@RequestMapping("/updateSuperAdminPassword")
	public String updateSuperAdminPassword(SuperAdmin superAdmin,HttpServletRequest request,HttpServletResponse response,ModelMap model) throws IOException{
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		String reqnewPassword = request.getParameter("reqnewPassword");
		
		if(oldPassword.equals(request.getSession().getAttribute("pass"))){
			if(newPassword.equals(reqnewPassword)){
				superAdmin.setPassword(newPassword);
				sas.updateInfor(superAdmin);
				model.addAttribute("uu","<script type='text/javascript'>alert('�޸�����ɹ�')</script>");
				return "/WEB-INF/superadmin/welcome.jsp";
				
				//response.getWriter().write("�޸�����ɹ�");
			}else{
				model.addAttribute("uu","<script type='text/javascript'>alert('������������ƥ�䲻�ɹ�')</script>");
				return "/WEB-INF/superadmin/welcome.jsp";
				//response.getWriter().write("������������ƥ�䲻�ɹ�");
			}
		}else{
			model.addAttribute("uu","<script type='text/javascript'>alert('������ƥ�䲻�ɹ�')</script>");
			return "/WEB-INF/superadmin/welcome.jsp";
			//response.getWriter().write("������ƥ�䲻�ɹ�");
		}
	}
	
	//��������Ա��ӹ���Ա�ӿ�
	@RequestMapping("/addAdmin")
	public String addAdmin(@ModelAttribute Admin admin,HttpServletRequest request,HttpServletResponse response,ModelMap model){
		sas.addAdmin(admin);
		model.addAttribute("uu","<script type='text/javascript'>alert('��ӹ���Ա�ɹ�')</script>");
		return "/WEB-INF/superadmin/welcome.jsp";
	}
	
	//��������Աɾ������Ա�ӿ�
	@RequestMapping("/deleteAdmin")
	public String deleteAdmin(@ModelAttribute Admin admin,HttpServletRequest request,HttpServletResponse response,ModelMap model) throws IOException{
		int id = Integer.parseInt(request.getParameter("id"));
		sas.deleteAdmin(id);
//		model.addAttribute("uu","<script type='text/javascript'>alert('ɾ������Ա�ɹ�')</script>");
//		return "/WEB-INF/superadmin/welcome.jsp";
		response.getWriter().write("ɾ���ɹ�");
		return null;
	}
}
