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
				
				<#if signinPage>
					<h3>Signin</h3>
					
					<div class="panel panel-default padding-10">
						or <a href="/getsignup">Signup</a>
					</div>
				<#else>
					<h3>Signup</h3>
					
					<div class="panel panel-default padding-10">
						or <a href="/">Signin</a>
					</div>
				</#if>
				
				<#if loginFail>
					<p class="alert alert-danger error home-message flex flex-center" role="alert">${message}</p>
				</#if>
				<#if signupFail>
					<p class="alert alert-danger error home-message flex flex-center" role="alert">${message}</p>
				</#if>
				<#if newUserSignup>
					<p class="alert alert-success success home-message flex flex-center" role="alert">${SignUpMessage}</p>
				<#else>
				</#if>
				
				<#if signinPage>
					<#include "signinform.ftl">
				<#else>
					<#include "signupform.ftl">
				</#if>
			
			</div>
		</div>
	</body>
</html>