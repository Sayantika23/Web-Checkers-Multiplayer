define(function(require) {
	'use strict';

	var pieces = document.getElementsByClassName("Piece");
	var checkerVectorArray = [];
	for (var i = 0; i < pieces.length; i++) {
		var checkerId = pieces[i].id;
		var checkerVector = checkerId.replace('piece-', '');
		checkerVector = checkerVector.replace('-', ',');
		if (pieces[i].getAttribute("data-color") === "WHITE") {
			checkerVectorArray.push([checkerVector, "white" ]);
		}
		if (pieces[i].getAttribute("data-color") === "RED") {
			checkerVectorArray.push([checkerVector, "red"]);
		}
	}
	$.post("/updateBoardModel", {"model": JSON.stringify(checkerVectorArray)});
});