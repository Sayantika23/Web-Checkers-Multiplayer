<!DOCTYPE html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
		<meta http-equiv="refresh" content="10">
		<title>${title} | Web Checkers</title>
		<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
		<link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
		<link rel="stylesheet" href="/css/style.css">
		<link rel="stylesheet" href="/css/game.css">
		<link rel="stylesheet" href="/css/flex.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
		<script>
		window.gameState = {
		'player' : {
		'name' : '${playerName}',
		'color' : '${playerColor}',
		'isMyTurn' : ${isMyTurn?c}
		},
		'opponent' : {
		'name' : '${opponentName}',
		'color' : '${opponentColor}',
		'isMyTurn' : ${(!isMyTurn)?c}
		}
		};
		</script>
	</head>
	<body>
		<div class="page">
			
		<#if !accepted>
			<#include "game-menu.ftl">
			<#include "info.ftl">
			<#include "controls.ftl">
            <div class="body">
				<#include "board.ftl">
            </div>
		<#else>
			Waiting for Opponent to accept your request
		</#if>

		</div>
		<audio id="audio" src="http://www.soundjay.com/button/beep-07.mp3" autostart="false" ></audio>
		<script data-main="js/game/index" src="js/require.js"></script>
	</body>
</html>