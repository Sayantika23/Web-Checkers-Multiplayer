<!DOCTYPE html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
		<!-- <meta http-equiv="refresh" content="10"> -->
		<title>${title} | Web Checkers</title>
		<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
		<link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
		<link rel="stylesheet" href="/css/style.css">
		<link rel="stylesheet" href="/css/game.css">
		<link rel="stylesheet" href="/css/flex.css">
		<script src="/js/jquery-3.1.1.js"></script>
		<script src="/js/game/Checkerboard.js"></script>
		<script src="/js/game/CheckerEvents.js"></script>
		<script>
		window.gameState = {
			'player' : {
			'name' : '${playerName}',
			'color' : '${playerColor}'
			},
			'opponent' : {
			'name' : '${opponentName}',
			'color' : '${opponentColor}'
			}
		};
		</script>
	</head>
	<body>
		<div class="page">
			
		<#if accepted>
			<div class="game-container flex flex-row flex-center flex-space-between">
				<div class="info-menu flex flex-column">
					<#include "game-menu.ftl">
					<#include "info.ftl">
					<#include "controls.ftl">
				</div>
	            <div class="body flex">
					<#include "board.ftl">
	            </div>
            </div>
		<#else>
			Waiting for Opponent to accept your request
		</#if>

		</div>
		<audio id="audio" src="http://www.soundjay.com/button/beep-07.mp3" autostart="false" ></audio>
		<script data-main="js/game/index" src="js/require.js"></script>
		<script src="/js/game/websocket.js"></script>
	</body>
</html>