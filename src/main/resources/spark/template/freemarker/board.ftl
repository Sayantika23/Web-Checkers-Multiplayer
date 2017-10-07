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
							class="Space"
							</#if>>
							<#if space.piece??>
							<#if space.piece.getType() == "checker">
							<div class="Piece ${space.piece.getColorClass()}"
								id="piece-${row.getRowNumber()}-${space.getCellId()}"
								data-type="${space.piece.getType()}"
								data-color="${space.piece.getDataColor()}">
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