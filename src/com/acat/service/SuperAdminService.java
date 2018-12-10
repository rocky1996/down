package com.acat.service;

import java.util.List;

import com.acat.pojo.Admin;
import com.acat.pojo.SuperAdmin;

public interface SuperAdminService {
		//超级管理员登录
		SuperAdmin login(String username,String password);
		
		SuperAdmin findSuperAdmin(int id);
		
		List<SuperAdmin> superAdminAll();
		
		//超级管理员修改
		void updateInfor(SuperAdmin superAdmin);
		
		//超级管理员增加普通管理员
		void addAdmin(Admin admin);
		
		//超级管理员删除普通管理员
		void deleteAdmin(int id);
		
		//获取所有管理员
		List<Admin> adminAll();
}
