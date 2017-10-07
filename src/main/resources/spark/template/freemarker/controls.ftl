<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<div class="panel panel-default">
				<div class="panel-body">
					<div id="game-toolbar">
						<legend>Controls</legend>
						<div class="panel panel-default padding-10">
							<div class="toolbar flex flex-row flex-space-between flex-align-center">
								<button type="button"
								class="btn btn-primary margin-bottom-20"
								title="Remove the last move with your current turn."
								id="backupLink" disabled=disabled>Backup one move</button>
								
								<button type="button"
								class="btn btn-warning margin-bottom-20"
								title="Remove all moves within your current turn."
								id="resetLink" disabled=disabled>Reset turn</button>
								
								<button type="button"
								class="btn btn-success margin-bottom-20"
								title="Rtitle="Commit your current turn to the server."
								id="submitLink" disabled=disabled>Submit turn</button>
								
								<button type="button"
								class="btn btn-danger margin-bottom-20"
								title="End the game by resigning."
								id="backupLink" disabled=disabled>Resign from game</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</nav>