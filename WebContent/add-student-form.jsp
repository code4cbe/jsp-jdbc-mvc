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
				<form action="StudentControllerServlet" method="get">
					<input type="hidden" name="command" value="add" />
					<table>
						<tr>
							<td><label>First Name: </label></td>
							<td><input type="text" name="firstName" value="" /></td>
						</tr>
						<tr>
							<td><label>Last Name: </label></td>
							<td><input type="text" name="lastName" value="" /></td>
						</tr>
						<tr>
							<td><label>Email: </label></td>
							<td><input type="text" name="email" value="" /></td>
						</tr>
						<tr >
							
							<td ><input class="add-student-button" type="submit" value="Save" /></td>
							<td></td>
						</tr>
					</table>
				</form>
			</div>
			<p><a href="StudentControllerServlet">Go Back to List</a></p>
		</div>
	</body>
</html>