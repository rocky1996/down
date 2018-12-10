package com.acat.service.impl;

import com.acat.pojo.File;
import com.acat.pojo.FileVo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acat.mapper.AdminMapper;
import com.acat.pojo.Admin;
import com.acat.service.AdminService;
import com.acat.util.GetNowTime;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminMapper am;

	@Override
	public Admin login(String username, String password) {
		return am.login(username, password);
	}

////	@Override
////	public void updateAdmin(Admin admin) {
////		am.updateAdmin(admin);		
////	}
////
//	@Override
//	public Admin findAdmin(int id) {
//		return am.findAdmin(id);
//	}
//
	@Override
	public void addFile(File file) {
		file.setTime(GetNowTime.getTime());
		am.addFile(file);
	}
//
//	@Override
//	public void deleteFile(int id) {
//		am.deleteFile(id);
//	}
//
//	@Override
//	public List<File> findAll() {
//		return am.findAll();
//	}

	@Override
	public List<FileVo> findFileByAdmin(int id) {
		return am.findFileByAdmin(id);
	}

	@Override
	public int findIdByFilename(String filename) {
		return am.findIdByFilename(filename);
	}
}
