<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="user.UserDAO" %>
<%@ page import="java.io.PrintWriter" %>
<% request.setCharacterEncoding("UTF-8"); %>

<jsp:useBean id="user" class="user.User" scope="page" />
<jsp:setProperty name="user" property="userID" />
<jsp:setProperty name="user" property="userPassword" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP 웹사이트 게시판</title>
</head>
<body>
	<%
	String userID = null;
	if(session.getAttribute("userID") != null){
		userID = (String) session.getAttribute("userID");
	}
	if(userID != null){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('you are already logged in')");
		script.println("location.href = 'main.jsp'");
		script.println("</script>");
	}
	UserDAO userDAO = new UserDAO();
	int result = userDAO.login(user.getUserID(), user.getUserPassword());
	
	out.println(result);
	
	if (result  ==0){
		session.setAttribute("userID", user.getUserID());
		
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('success')");
		script.println("location.href = 'main.jsp'");
		script.println("</script>");
	}  else if (result  == -2){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('DB error')");
		script.println("history.back()");
		script.println("</script>");
	} else if (result  == -1){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('ID doesnt exist')");
		script.println("history.back()");
		script.println("</script>");
	} else if (result == -3){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('password is not correct')");
			script.println("history.back()");
			script.println("</script>");
	}
	%>
</body>
</html>