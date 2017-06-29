<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
}
th, td {
    padding: 5px;
    text-align: left;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2>Welcome to admin Page</h2>
<!-- <form action="getdetails" method="get">
<input type="submit" value="Get Details"> 
</form> -->
<table>
<%-- <c:if test = "${adminDetails}"> --%>
        <tr>
         <th>username</th>
         <th>password</th>
         <th>email</th>
         <th>mobile</th>
         <th>count</th>
         <th>locked</th>
         </tr>
<c:forEach items="${listoflockedstudents}" var="StudentDetails">
   
      <form action="unlockUser" name="studForm" method="post">
		<tr>
        <td>${StudentDetails.username}</td>
        <td>${StudentDetails.password}</td>
        <td>${StudentDetails.email}</td>
        <td>${StudentDetails.mobile}</td>
        <td>${StudentDetails.locked}</td>
        <td>${StudentDetails.count}</td>
       <td>
       <button type="submit" value="${StudentDetails.email}" name="email">unlock</button>
       </td>
        </tr>
        </form>
    </c:forEach>
   <%--   </c:if> --%>
<a href="register.jsp">Home</a>
</table>
</body>
</html>