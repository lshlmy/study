<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${resources}/css/common.css" rel="stylesheet" type="text/css" />
<title>测试</title>
</head>
<body>
	<% session.toString(); %>
	<br>
	<p>测试</p>
	${path}
	<form:form action="logout.html" method="post">
		<input type="submit" value="提交">
	</form:form>
	
	<!-- 加载编辑器的容器 -->
    <script id="container" name="content" type="text/plain" class="UEditorContainer">
      	  这里写你的初始化内容
    </script>
	
	<!-- 配置文件 -->
    <script type="text/javascript" src="${resources}/plugin/UEditor/ueditor.config.js"></script>
    <!-- 编辑器源码文件 -->
    <script type="text/javascript" src="${resources}/plugin/UEditor/ueditor.all.js"></script>
    <!-- 实例化编辑器 -->
    <script type="text/javascript">
        var ue = UE.getEditor('container');
    </script>
</body>
</html>