<%@ page import="java.sql.*" %>
<%@ page import="com.exam.DBConnection" %>

<html>
<head>
<title>View Questions</title>
</head>

<body>

<h2>All Questions</h2>

<table border="1" cellpadding="10">
<tr>
<th>ID</th>
<th>Question</th>
<th>Delete</th>
</tr>

<%
Connection con=DBConnection.getConnection();
Statement st=con.createStatement();
ResultSet rs=st.executeQuery("select * from questions");

while(rs.next()){
%>

<tr>
<td><%=rs.getInt("qid")%></td>
<td><%=rs.getString("question")%></td>
<td>
<a href="/ExamPortal/DeleteQuestionServlet?qid=<%=rs.getInt("qid")%>">
Delete
</a>
</td>
</tr>

<%
}
%>

</table>

</body>
</html>
