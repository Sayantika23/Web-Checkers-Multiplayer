<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<div class="panel panel-default">
				<div class="panel-body">
					<div id="game-toolbar">
						<legend>Controls</legend>
						<div class="panel panel-default">
							<div class="toolbar flex flex-row flex-space-between flex-align-center">
								<button type="button"
								class="btn btn-primary margin-10"
								title="Remove the last move with your current turn."
								id="backupLink" disabled=disabled>Backup one move</button>
								
								<button type="button"
								class="btn btn-warning margin-10"
								title="Remove all moves within your current turn."
								id="resetLink" disabled=disabled>Reset turn</button>
								
								<button type="button"
								class="btn btn-success margin-10"
								title="Rtitle="Commit your current turn to the server."
								id="submitLink" disabled=disabled>Submit turn</button>
								
								<button  onclick="Resign()" type="button"
								class="btn btn-danger margin-10"
								title="End the game by resigning."
								id="Resign" >Resign from game</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</nav>