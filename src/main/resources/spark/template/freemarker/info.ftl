<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<div class="panel panel-default">
				<div class="panel-body">
					<div id="game-controls" class="flex flex-row flex-center">
						<div id="game-info">
							<p>
								You are playing a game of checkers with ${opponentName}.
								<#if isMyTurn>
								It's your turn.  Move your piece and click the Submit link.
								If you want to erase your move click the Reset link.
								<#else>
								It's <span class="badge primary">${opponentName}'s</span> turn.  The page will refresh periodically
								and you will be informed when it is your turn.
								</#if>
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