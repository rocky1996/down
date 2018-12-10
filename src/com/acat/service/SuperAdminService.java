package com.acat.service;

import java.util.List;

import com.acat.pojo.Admin;
import com.acat.pojo.SuperAdmin;

public interface SuperAdminService {
		//��������Ա��¼
		SuperAdmin login(String username,String password);
		
		SuperAdmin findSuperAdmin(int id);
		
		List<SuperAdmin> superAdminAll();
		
		//��������Ա�޸�
		void updateInfor(SuperAdmin superAdmin);
		
		//��������Ա������ͨ����Ա
		void addAdmin(Admin admin);
		
		//��������Աɾ����ͨ����Ա
		void deleteAdmin(int id);
		
		//��ȡ���й���Ա
		List<Admin> adminAll();
}
