package com.acat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.acat.mapper.SuperAdminMapper;
import com.acat.pojo.Admin;
import com.acat.pojo.SuperAdmin;
import com.acat.service.SuperAdminService;

@Service
public class SuperAdminServiceImpl implements SuperAdminService{
	
	@Autowired
	private SuperAdminMapper sam;

	@Override
	public SuperAdmin login(String username, String password) {
		return sam.login(username, password);
	}

	@Override
	public void updateInfor(SuperAdmin superAdmin) {
		sam.updateInfor(superAdmin);
	}

	@Override
	public void addAdmin(Admin admin) {
		sam.addAdmin(admin);
		
	}

	@Override
	public void deleteAdmin(int id) {
		sam.deleteAdmin(id);
	}

	@Override
	public List<Admin> adminAll() {
		return sam.adminAll();
	}

	@Override
	public SuperAdmin findSuperAdmin(int id) {
		return sam.findSuperAdmin(id);
	}

	@Override
	public List<SuperAdmin> superAdminAll() {
		return sam.superAdminAll();
	}
	
	
	
}
