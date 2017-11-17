<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<div class="panel panel-default">
				<div class="panel-body">
					<div id="game-controls" class="flex flex-row flex-center">
						<div id="game-info">
							<p>You are playing a game of checkers with ${opponentName}.</p>
							<p>
								<div id="current-turn" class="hidden">
									It's your turn.  Move your piece.
								</div>
								<div id="opponent-turn" class="hidden">
									It's <span id="turn-badge" class="badge">${opponentName}'s</span> turn.
								</div>
							</p>
							<legend>Info</legend>
							<#if message??>
							<div id="message" class="${message.type}">${message.text}</div>
							<#else>
							<div id="message" class="info" style="display:none">
								<!-- keep here for Client-side messages -->
							</div>
							</#if>
							<#include "score.ftl">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</nav>