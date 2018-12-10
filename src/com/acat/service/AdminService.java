package com.acat.service;

import java.util.List;

import com.acat.pojo.Admin;
import com.acat.pojo.File;
import com.acat.pojo.FileVo;

public interface AdminService {
	Admin login(String username,String password);
////	void updateAdmin(Admin admin);
//	Admin findAdmin(int id);
	void addFile(File file);
//	void deleteFile(int id);
//	List<File> findAll();
	List<FileVo> findFileByAdmin(int id);
	int findIdByFilename(String filename);
}
