<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
    <title>${title} | Web Checkers Signup</title>
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/css/webcheckers.css">
    <link rel="stylesheet" type="text/css" href="/css/gui.css">
    <link rel="stylesheet" type="text/css" href="/css/flex.css">
</head>
<body>
  <div class="page">
  
  
  <!-- move this template to the menu when the page is rendering -->
  

    <div class="container">
     
    <h1>${title}</h1>
        <form class="form-signup" action="/signup" method="POST">
            <label for="inputUsername" class="sr-only">Username</label>
            <#--<input type="text" name = "inputFirstName" id="inputFirstName" class="form-control" placeholder="First Name" required>-->
            <#--<input type="text" name = "inputLastName" id="inputLastName" class="form-control" placeholder="Last Name" required>-->
            <#--<input type="email" name = "inputEmail" id="inputEmail" class="form-control" placeholder="Email" required>-->
            <input type="text" name = "inputUsername" id="inputUsername" class="form-control" placeholder="Username" required>
            <input type="password" name = "inputPassword" id="inputPassword" class="form-control" placeholder="Password" required>
        <#include "button.ftl">
        </form>
    </div>
    
  </div>
</body>
</html>
