<nav class="navbar navbar-default">
	<form id="gameForm" action="/submitTurn" method="POST">
		<div class="game-board flex flex-center">
		<script>
		function allowDrop(ev) {
		    ev.preventDefault();
		}

		function drag(ev) {
		    ev.dataTransfer.setData("text", ev.target.id);
		}

		function drop(ev) {
		    ev.preventDefault();
		    var data = ev.dataTransfer.getData("text");
		    ev.target.appendChild(document.getElementById(data));
		}
		</script>
			<table id="game-board">
				<tbody>
					<#list board.iterator() as row>
					<tr data-row="${row.getRowNumber()}">
						<#list row.iterator() as space>
						<td data-cell="${space.getCellId()}"
							<#if space.isValid()>
							class="Space"
							ondrop="drop(event)"
							ondragover="allowDrop(event)"
							</#if>>
							<#if space.piece??>
								<#if space.piece.getType() == "checker">
									<div class="Piece ${space.piece.getColorClass()}"
										id="piece-${row.getRowNumber()}-${space.getCellId()}"
										data-type="${space.piece.getType()}"
										data-color="${space.piece.getDataColor()}"
										draggable="true"
										ondragstart="drag(event)">
									</div>
								</#if>
							</#if>
						</td>
						</#list>
					</tr>
					</#list>
				</tbody>
			</table>
		</div>
	</form>
</nav>