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
<#if is_human>
<div class="home flex flex-row flex-center">

    <div class="content flex flex-row flex-center">
    <#if invites?has_content>
        <div style="text-align: center;">Reply to request</div>
        <br/>
        <form action="/game" method="GET">
            <input type="text" hidden name="opponentType" id="opponentType" value="human">
            <input type="text" hidden name="requestType" id="requestType" value="invite">
            <#list invites as player>
                <input type="radio" name="opponentName" value="${player}" id="opponentName"> ${player}<br/>
            </#list>
            <#include "button.ftl">
        </form>
    </#if>
        <#if players?has_content>
            <div style="text-align: center;">Select an opponent</div>
            <br/>
            <form action="/game" method="GET">
                <input type="text" hidden name="opponentType" id="opponentType" value="human">
                <input type="text" hidden name="requestType" id="requestType" value="request">
                <#list players as player>
                    <input type="radio" name="opponentName" value="${player}" id="opponentName"> ${player}<br/>
                </#list>
                <#include "button.ftl">
            </form>
        <#else >
            <div style="text-align: center; color:red; font-size: 20;"><b>There are no opponents online.</b> <br/> Wait for opponent <br/> or <br/> Play against Computer</div>
            <form action="/mode" method="POST">
                <input type="radio" name="player" value="computer" id="opponent"> Computer<br/>
                <#include "button.ftl">
            <#--<#include "humanbutton.ftl">-->
            <#--<#include "computerbutton.ftl">-->
            </form>
        </#if>
        <br/>
    </div>
</div>
</#if>
<#if !is_human>
<div class="home flex flex-row flex-center">
    <div style="text-align: center;">Select difficulty level</div>
    <div class="content flex flex-row flex-center">
        <form action="/game" method="GET">
            <input type="text" hidden name="opponentType" id="opponentType" value="computer">
            <#list levels as level>
                <input type="radio" name="difficulty" value="${level}" id="difficulty"> ${level}<br/>
            </#list>
            <#include "button.ftl">
        </form>
        <br/>
    </div>
</div>
</#if>
</body>
</html>