<%@page import="com.ServiceUser"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>user management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/user.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-6">
				<h1>User Management</h1>
				<form id="formuser" name="formuser" method="post" action="user.jsp">
					User ID: <input id="UserID" name="UserID" type="text"
						class="form-control form-control-sm"> <br> User First
					Name: <input id="U_Fname" name="U_Fname" type="text"
						class="form-control form-control-sm"> <br> User Last
					Name <input id="U_Lname" name="U_Lname" type="text"
						class="form-control form-control-sm"> <br> User email
					<input id="Uemail" name="Uemail" type="text"
						class="form-control form-control-sm"> <br> User
					address: <input id="Uaddress" name="Uaddress" type="text"
						class="form-control form-control-sm"> <br> User User
					NIC: <input id="U_NIC" name="U_NIC" type="text"
						class="form-control form-control-sm"> <br> User
					gender: <input id="U_gender" name="U_gender" type="text"
						class="form-control form-control-sm"> <br> U_DoB: <input
						id="U_DoB" name="U_DoB" type="text"
						class="form-control form-control-sm"> <br> <input
						id="btnSave" name="btnSave" type="button" value="Save"
						class="btn btn-primary"> <input type="hidden"
						id="hidbidSave" name="hidbidSave" value="">

				</form>

				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>

				<div id="divbillGrid">
					<%
						ServiceUser userobj = new ServiceUser();
						out.print(userobj.readUser());
					%>
				</div>
			</div>
		</div>
	</div>
</body>
</html>