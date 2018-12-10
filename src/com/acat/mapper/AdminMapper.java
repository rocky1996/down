package com.acat.mapper;

import com.acat.pojo.File;
import com.acat.pojo.FileVo;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.acat.pojo.Admin;

public interface AdminMapper extends SqlMapper{
	Admin login(@Param("username")String username,@Param("password")String password);
////	void updateAdmin(Admin admin);
//	Admin findAdmin(int id);
	void addFile(File file);
//	void deleteFile(int id);
//	List<File> findAll();
	List<FileVo> findFileByAdmin(int id);
	int findIdByFilename(String filename);
}
