<div class="score-container">
	<div class="panel panel-default padding-10 flex flex-row flex-space-between">
		<div class="player-turn flex flex-row flex-center flex-space-between" data-color="${playerOneDivName}" name="${playerOneDivName}">
			<div class="score ${scoreClass1}"></div>
			<div class="name">${playerName}</div>
			<div id="player-score-${playerOneDivName}" class="score-count badge primary badge-margin-right">0</div>
		</div>
		<h3>Score</h3>
		<div class="player-turn flex flex-row flex-space-between flex-center player-turn" data-color="${playerTwoDivName}" name="${playerTwoDivName}">
			<div class="score ${scoreClass2}"></div>
			<div class="name">${opponentName}</div>
			<div id="player-score-${playerTwoDivName}" class="score-count badge primary badge-margin-right">0</div>
		</div>
	</div>
</div>