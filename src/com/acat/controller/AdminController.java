package com.acat.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.enterprise.inject.Model;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.acat.pojo.Admin;
import com.acat.pojo.Count;
import com.acat.pojo.File;
import com.acat.pojo.FileVo;
import com.acat.pojo.SuperAdmin;
import com.acat.service.AdminService;
import com.acat.service.CountService;
import com.acat.util.GetNowTime;

@Controller
@RequestMapping("/admin")
public class AdminController{
	
	@Autowired
	private AdminService ;
	
	@Autowired
	private CountService cs;
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		request.getSession().setAttribute("pass",password);
//		System.out.println(username);
//		System.out.println(password);
		
		System.out.println("-----------1");
		
		Admin admin = as.login(username, password);
		System.out.println("-----------2");
		if(admin==null){
			model.addAttribute("uu","<script type='text/javascript'>alert('账号密码错误，请重新输入')</script>");
			return "/WEB-INF/superadmin/login.jsp";
		}else{
			System.out.println("----------3");
			request.getSession().setAttribute("admin", admin);
			List<FileVo> cs = as.findFileByAdmin(admin.getId());
			
//			Map<String, String> map = new HashMap<String, String>();//key：UUID文件名；value：老文件名
//			//得到存储文件的根目录
//			String storePath = request.getSession().getServletContext().getRealPath("/files");
//			//递归遍历其中文件
//			java.io.File file = new java.io.File(storePath);
//			treeWalk(file,map);
//			//交给JSP去显示：如何封装数据.用Map封装。key：UUID文件名；value：老文件名
//			model.addAttribute("cs",cs);
			model.addAttribute("cs",cs);
			return "/WEB-INF/admin/success.jsp";
		}
	}
//	
//	//安全退出
//	@RequestMapping("/logout")
//	public String logout(HttpServletRequest request,HttpServletResponse response,ModelMap model){
//		HttpSession session = request.getSession();
//		session.removeAttribute("admin");
//		return "/index.jsp";
//	}
////	
//	//寻找超级管理员接口
//	@RequestMapping("/findAdmin")
//	public String findSuperAdmin(HttpServletRequest request,HttpServletResponse response,ModelMap model){
//		int id = Integer.parseInt(request.getParameter("id"));
//		Admin admin = as.findAdmin(id);
//		model.addAttribute("admin", admin);
//		return "/WEB-INF/superadmin/welcome.jsp";
//	}
//	
//
////	//超级管理员修改信息接口
////	@RequestMapping("/updateAdmin")
////	public String updateSuperAdmin(Admin admin,HttpServletRequest request,HttpServletResponse response,ModelMap model){
////		String nick = request.getParameter("nick");
////		admin.setNick(nick);
////		as.updateAdmin(admin);
////		model.addAttribute("uu","<script type='text/javascript'>alert('修改信息成功')</script>");
////		return "/WEB-INF/superadmin/welcome.jsp";
////	}
////	
////	//超级管理员修改密码接口
////	@RequestMapping("/updateAdminPassword")
////	public String updateSuperAdminPassword(Admin admin,HttpServletRequest request,HttpServletResponse response,ModelMap model) throws IOException{
////		String oldPassword = request.getParameter("oldPassword");
////		String newPassword = request.getParameter("newPassword");
////		String reqnewPassword = request.getParameter("reqnewPassword");
////		
////		if(oldPassword.equals(request.getSession().getAttribute("pass"))){
////			if(newPassword.equals(reqnewPassword)){
////				admin.setPassword(newPassword);
////				as.updateAdmin(admin);
////				model.addAttribute("uu","<script type='text/javascript'>alert('修改密码成功')</script>");
////				return "/WEB-INF/superadmin/welcome.jsp";
////				
////				//response.getWriter().write("修改密码成功");
////			}else{
////				model.addAttribute("uu","<script type='text/javascript'>alert('两次输入密码匹配不成功')</script>");
////				return "/WEB-INF/superadmin/welcome.jsp";
////				//response.getWriter().write("两次输入密码匹配不成功");
////			}
////		}else{
////			model.addAttribute("uu","<script type='text/javascript'>alert('旧密码匹配不成功')</script>");
////			return "/WEB-INF/superadmin/welcome.jsp";
////			//response.getWriter().write("旧密码匹配不成功");
////		}
////	}
//	
//	//超级管理员添加管理员接口
//	@RequestMapping("/addFile")
//	public String addAdmin(@ModelAttribute File file,HttpServletRequest request,HttpServletResponse response,ModelMap model){
//		as.addFile(file);
//		model.addAttribute("uu","<script type='text/javascript'>alert('添加管理员成功')</script>");
//		return "/WEB-INF/superadmin/welcome.jsp";
//	}
//	
//	//超级管理员删除管理员接口
//	@RequestMapping("/deleteFile")
//	public String deleteAdmin(@ModelAttribute File file,HttpServletRequest request,HttpServletResponse response,ModelMap model) throws IOException{
//		int id = Integer.parseInt(request.getParameter("id"));
//		as.deleteFile(id);
//		model.addAttribute("uu","<script type='text/javascript'>alert('删除管理员成功')</script>");
//		return "/WEB-INF/superadmin/welcome.jsp";
//		//response.getWriter().write("删除成功");
//		//return null;
//	}
//	
	@RequestMapping("/upload")
	public String upload(@RequestParam(value="file1") MultipartFile files,@ModelAttribute File file,HttpServletRequest request,HttpServletResponse response,ModelMap model) throws IOException{
		Admin admin = (Admin)request.getSession().getAttribute("admin");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pout = response.getWriter();
		try {
			String storePath = request.getSession().getServletContext().getRealPath("/files");

			// 设置环境
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 判断一下form是否是enctype=multipart/form-data类型的
			ServletFileUpload upload = new ServletFileUpload(factory);
			// 解析
			List<FileItem> items = upload.parseRequest(request);
			String fileName = files.getOriginalFilename();
			
			// Settings\wzhting\妗岄潰\a.txt
			// a.txt
			fileName = fileName
					.substring(fileName.lastIndexOf("\\") + 1);// a.txt
			fileName = UUID.randomUUID() + "_" + fileName;
			
			String newStorePath = makeStorePath(storePath, fileName);
			String storeFile = newStorePath + "\\" + fileName;// WEB-INF/files/1/2/sldfdslf.txt
			System.out.println(fileName);
			file.setFile(fileName);
			file.setAdmin_id(admin.getId());
			
			InputStream in = files.getInputStream();
			OutputStream out = new FileOutputStream(storeFile);
			byte b[] = new byte[1024];
			int len = -1;
			while((len=in.read(b))!=-1){
				out.write(b, 0, len);
			}
			out.close();
			in.close();
			as.addFile(file);
		} catch (org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException e) {
			// 单个文件超出大小时的异常
			pout.write("单个文件大小不能超出4M");
		} catch (org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException e) {
			// 总文件超出大小时的异常
			pout.write("总文件大小不能超出6M");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.getWriter().write("lala");
		return null;
	}
	
	// 根据 /WEB-INF/files和文件名，创建一个新的存储路径 /WEB-INF/files/1/12
	private String makeStorePath(String storePath, String fileName) {
		int hashCode = fileName.hashCode();
		int dir1 = hashCode & 0xf;// 0000~1111：整数0~15共16个
		int dir2 = (hashCode & 0xf0) >> 4;// 0000~1111：整数0~15共16个
		
		String path = storePath + "\\" + dir1 + "\\" + dir2; // WEB-INF/files/1/12
		java.io.File file = new java.io.File(path);
		if (!file.exists())
			file.mkdirs();
		
		return path;
	}
	
	private void treeWalk(java.io.File file, Map<String, String> map) {
		if(file.isFile()){
			//是文件
			String uuidName = file.getName();//  UUID_a_a.txt//真实文件名
			String oldName = uuidName.substring(uuidName.indexOf("_")+1);
			map.put(uuidName, oldName);
		}else{
			//是一个目录
			java.io.File[] fs = file.listFiles();
			for(java.io.File f:fs){
				treeWalk(f,map);
			}
		}
	}
	
	@RequestMapping("/findFileByAdmin")
	public String findFileByAdmin(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println(id);
		
		List<FileVo> cs = as.findFileByAdmin(id);
		model.addAttribute("cs",cs);
		return "/WEB-INF/admin/success.jsp";
	}
	
	@RequestMapping("/download")
	public String download(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		response.setContentType("text/html;charset=UTF-8");
		OutputStream out = response.getOutputStream();
		
		String filename = request.getParameter("filename");//get请求方式
		
		int id = as.findIdByFilename(filename);
		
		System.out.println(id+"----");
		
		filename = new String(filename.getBytes("ISO-8859-1"),"UTF-8");//中文编码
		
		//截取老文件名
		String oldFileName = filename.split("_")[1];
		//得到存储路径
		String storePath = request.getSession().getServletContext().getRealPath("/files");
		//得到文件的全部路径
		String filePath = makeStorePath(storePath, filename)+"\\"+filename;
		
		//判断文件是否存在
		java.io.File file = new java.io.File(filePath);
		
		InputStream in = new FileInputStream(file);
		//通知客户端以下载的方式打开
		response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(oldFileName, "UTF-8"));
		
		byte[] b = new byte[1024];
		int len = -1;
		while((len=in.read(b))!=-1){
			out.write(b, 0, len);
		}
		in.close();
	
		System.out.println("^^^^^^^^^^'");
		
		Count count = new Count();
		int cou = cs.findCountById(id);
		
		System.out.println(cou);
		
		count.setCount(cou+1);
		
		System.out.println("!!!!!!!!!!!!!1");
		
		cs.updateCount(count);
		
		System.out.println("@@@@@@@@@@");
		
		out.write("下载成功".getBytes("UTF-8"));
		return "";
	}
}
