<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
    <#--<meta http-equiv="refresh" content="10">-->
    <title>${title} | Web Checkers</title>
    <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/css/style.css">
    <link rel="stylesheet" type="text/css" href="/css/flex.css">
</head>
<body>
<div class="home flex flex-row flex-center">
    <div style="text-align: center;">Select an opponent</div>
    <div class="content flex flex-row flex-center">
        <form action="/game">
        <#list players as player>
            <input type="radio" name="opponent" value="${player}" id="opponent"> ${player}<br/>
        </#list>
        <#include "button.ftl">
        </form>
        <br/>
    </div>

</div>
</body>
</html>