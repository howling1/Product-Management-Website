<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
		<title></title>

		<script type="text/javascript">
			if ("${registerMsg}" != "") {
				alert("${registerMsg}");
			}
		</script>
		
	</head>

	<body>
		<div id="login">
			<div id="top">
				<img src="${pageContext.request.contextPath}/images/cloud.jpg" /><span>REGIST</span>
			</div>
			<div id="bottom">
				<form action="${pageContext.request.contextPath}/admin/register.action" method="post">
					<table border="0px" id="table">
						<tr>
							<td class="td1">username：</td>
							<td><input type="text" placeholder="Username" class="td2" name="name"></td>
						</tr>
						<tr>
							<td></td>
							<td><span id="nameerr"></span></td>
						</tr>
						<tr>
							<td class="td1">password：</td>
							<td><input type="password" placeholder="Password" class="td2" name="pwd"></td>
						</tr>
						<tr>
							<td></td>
							<td><span id="pwderr"></span></td>
						</tr>
						<tr>
							<td></td>
							<td><input type="submit" value="Register" class="td3">
								<a href="${pageContext.request.contextPath}/admin/login.jsp"><input type="button" value="Back to login" class="td3"></a>
							</td>
						</tr>
					</table>
				</form>
			</div>

		</div>
	</body>

</html>