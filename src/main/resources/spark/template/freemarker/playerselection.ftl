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
<div class="home flex flex-row flex-center">

    <div class="content flex flex-row flex-center">
        <div class="logo-content flex flex-column">
            <div class="logo">
                <img src="../img/checkers.svg" />
            </div>
        </div>
        <div class="login-form flex flex-column flex-center">
            <h1 class="home-title">${title}</h1>
            <#include "humanbutton.ftl">
            <#include "computerbutton.ftl">
        </div>
    </div>
</div>
</body>
</html>