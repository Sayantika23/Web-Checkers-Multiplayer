<!DOCTYPE html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
		<meta http-equiv="refresh" content="10">
		<title>${title} | Web Checkers</title>
		<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
		<link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="/css/style.css">
		<link rel="stylesheet" type="text/css" href="/css/flex.css">
	</head>
	<body>
		<div class="home flex flex-center">

			<div class="content flex flex-column flex-center">

				<h2>${title}</h2>
				
				<div class="logo">
					<img src="../img/checkers.svg" />
				</div>

				<h3>Signin</h3>
				
				<div class="panel panel-default padding-5">
					or <a href="/getsignup">Signup</a>
				</div>

				<#if loginFail>
					<p style="color:red">${message}</p>
				</#if>
				<#if newUserSignup>
					<p style="color:red">${SignUpMessage}</p>
				</#if>
				
				<#include "form.ftl">
			
			</div>
		</div>
	</body>
</html>