<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
    <meta http-equiv="refresh" content="10">
    <title>${title} | Web Checkers</title>
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/css/webcheckers.css">
    <link rel="stylesheet" type="text/css" href="/css/gui.css">
    <link rel="stylesheet" type="text/css" href="/css/flex.css">
</head>
<body>
  <div class="page">
  
  
  <!-- move this template to the menu when the page is rendering -->
  
	<#include "menu.ftl">

    <div class="container">
     
    <h1>${title}</h1>
  
    <h1>Web Checkers</h1>
    
    <div class="navigation">
      <a href="/">my home</a>
    </div>
    <#if loginFail>
        <p style="color:red">${message}</p>
    </#if>
    <#if newUserSignup>
        <p style="color:red">${SignUpMessage}</p>
    </#if>
    <#include "form.ftl">
    <a href="/getsignup"><button class="btn btn-lg btn-primary btn-block">Signup</button></a>
    </div>
    
  </div>
</body>
</html>
