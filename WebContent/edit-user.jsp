<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" >
<title>Edit User Page</title>
</head>
<body>
	<form action="UpdateServlet" method="get">
		<div class="container">
			<h1>Edit User Details</h1>
			<hr>
			<div class="form-group">
				<label for="username"><b>User Name</b></label> 
				<input type="text" class="form-control" name="username" value="${user.name}" required>
			</div>
			<div class="form-group">
				<label for="email"><b>Email</b></label> 
				<input type="email" class="form-control" name="email" value="${user.email }" required>
			</div>
			<div class="form-group">
				<label for="psw"><b>Password</b></label>
				<input type="password" class="form-control" name="psw" value="${user.password }" required>
			</div>
			<div class="form-group">
				<label for="mobile"><b>Mobile Number</b></label> 
				<input type="number" class="form-control" name="mobile" value="${user.mobile}"required>
				<input type="hidden" name="id" value="${user.id }">
			</div>
			<hr>
			<button type="submit" class="btn btn-primary">Update</button>
		</div>
	</form>

</body>
</html>