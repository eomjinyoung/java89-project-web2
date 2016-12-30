<%@page import="java.util.List"%>
<%@page import="bitcamp.java89.ems2.domain.Student"%>
<%@ page 
    language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>학생관리-목록</title>
</head>
<body>

<jsp:include page="../header.jsp"></jsp:include>

<h1>학생 정보</h1>
<a href='form.html'>추가</a><br>
<table border='1'>
<tr>
  <th>회원번호</th>
  <th>이름</th>
  <th>전화</th>
  <th>재직</th>
  <th>최종학력</th>
  <th>학교명</th>
</tr>
<%
List<Student> students = (List<Student>)request.getAttribute("students");
for (Student student : students) {%>
<tr> 
  <td>1</td>
  <td><a href='detail?memberNo=1'>학생1</a></td>
  <td>1111-1111</td>
  <td>true</td>
  <td>학사</td>
  <td>비트대학교</td>
</tr>
<%}%>
</table>

<jsp:include page="../footer.jsp"></jsp:include>

</body>
</html>
    