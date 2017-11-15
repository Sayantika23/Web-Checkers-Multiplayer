<nav class="navbar navbar-default">
	<form id="gameForm" action="/submitTurn" method="POST">
		<div class="game-board flex flex-center">
			<table id="game-board">
				<tbody>
					<#list board.iterator() as row>
					<tr data-row="${row.getRowNumber()}">
						<#list row.iterator() as space>
						<td data-cell="${space.getCellId()}"
							<#if space.isValid()>
							id="space-${row.getRowNumber()}-${space.getCellId()}"
							class="Space"
							ondrop="drop(event)"
							ondragover="allowDrop(event)"
							</#if>>
							<#if space.piece??>
								<#if space.piece.getType() == "SINGLE">
									<div class="Piece"
										id="piece-${row.getRowNumber()}-${space.getCellId()}"
										data-type="${space.piece.getType()}"
										data-color="${space.piece.getDataColor()}"
										draggable="true"
										ondragstart="drag(event)">
									</div>	
								<#elseif space.piece.getType() == "KING">
									<div class="Piece"
										id="piece-${row.getRowNumber()}-${space.getCellId()}"
										data-type="KING"
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