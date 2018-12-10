<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<title>所有客户信息</title>
	</head>
	<body style="text-align: center">
		<br/>
		<h1>客户信息列表</h1>
		<form id="ff" action="${pageContext.request.contextPath }/servlet/DelAllServlet" method="post">
		 <table align="center" width="88%" border="0"> 
			<tr>	
				<td>
					<a href="${pageContext.request.contextPath }/manager/addUser.jsp">添加</a>&nbsp;&nbsp;
					<a href="javascript:delAll()">删除</a>&nbsp;&nbsp;
				</td>
			</tr>
			<tr>
				<td>	
					<c:if test="${empty cs }">
						<h1>抱歉，没有客户信息</h1>
					</c:if>
					<c:if test="${!empty cs }">
						<table width="100%" border="1">
							<tr>
								<th>选择</th>
								<th>姓名</th>
								<th>昵称</th>
								<th>修改</th>
								<th>删除</th>
								<th>查看详情</th>
							</tr>
							<c:forEach items="${cs }" var="c" varStatus="vs">
								<tr bgcolor="${vs.index%2==0?'#CCCCCC':'#6081A3'}">
									<td align="center">
										<input type="checkbox" name="ids" value="${c.id }">
									</td>
						
									<td align="center">${c.username }</td>
									<td align="center">${c.nick}</td>
									<td align="center">
										<a href="${pageContext.request.contextPath }/superadmin/deleteAdmin?id=${c.id}">删除</a>&nbsp;&nbsp;
									</td>
								
								</tr>
							</c:forEach>
						 </table>
					</c:if>
				</td>
			</tr>
			</table> 
		</form>
		
	</body>
</html>