<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" >
<title>Sign Up Page</title>
</head>
<body>
	<form action="RegisterServlet" method="post">
		<div class="container">
			<h1>Register</h1>
			<p>Please fill in this form to create an account.</p>
			<hr>
			<div class="form-group">
				<label for="username"><b>User Name</b></label> 
				<input type="text" class="form-control" name="username" required>
			</div>
			<div class="form-group">
				<label for="email"><b>Email</b></label> 
				<input type="email" class="form-control" name="email" required>
			</div>
			<div class="form-group">
				<label for="psw"><b>Password</b></label>
				<input type="password" class="form-control" name="psw" required>
			</div>
			<div class="form-group">
				<label for="mobile"><b>Mobile Number</b></label> 
				<input type="number" class="form-control" name="mobile" required>
			</div>
			<hr>
			<button type="submit" class="btn btn-primary">Register</button>
		</div>
	</form>

</body>
</html>