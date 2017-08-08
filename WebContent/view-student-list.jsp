<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
	<title>FooBar University</title>
	<link type="text/css" rel="stylesheet" href="css/style.css">
	</head>
	<body>
		<div id="wrapper">
			<div id="header">
				<h2>FooBar University</h2>	
			</div>
		</div>
		<div id="container">
			<div id="content">
				<form method="get" action="add-student-form.jsp">
					<input type="submit" value="Add Student" />
				</form>
				
			</div>
			<div id="content">
				<table>
					<tr>
						<th>ID</th>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Email</th>
						<th>Action</th>
					</tr>
					<c:forEach var="tempStudent" items="${student_list }">
						<c:url var="updateUrl" value="StudentControllerServlet">
							<c:param name="command" value="load" />
							<c:param name="id" value="${tempStudent.getId()}" />
						</c:url>

						<c:url var="deleteUrl" value="StudentControllerServlet">
							<c:param name="command" value="delete" />
							<c:param name="id" value="${tempStudent.getId()}" />
						</c:url>
						
						<tr>
							<td>${tempStudent.getId()}</td>
							<td>${tempStudent.getFirstName()}</td>
							<td>${tempStudent.getLastName()}</td>
							<td>${tempStudent.getEmail()}</td>
							<td>
								<a href="${updateUrl}">Update</a>
								|
								<a href="${deleteUrl}" onclick="if (!confirm('Are you sure want to delete this record?')) return false;">Delete</a>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			
		</div>
	</body>
</html>