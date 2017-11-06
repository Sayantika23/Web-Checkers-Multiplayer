var Checkerboard = (function() {
	var pieces = null;
	var checkerVectorArray = [];

	function update() {
		pieces = document.getElementsByClassName("Piece");
		for (var i = 0; i < pieces.length; i++) {

			var checkerId = pieces[i].id;
			var checkerVector = getCheckerSpace(checkerId);
			if (pieces[i].getAttribute("data-color") === "WHITE") {
				checkerVectorArray.push([ checkerVector, "white" ]);
			}
			if (pieces[i].getAttribute("data-color") === "RED") {
				checkerVectorArray.push([ checkerVector, "red" ]);
			}
			if (pieces[i].getAttribute("data-color") === "TRANSPARENT") {
				checkerVectorArray.push([ checkerVector, "transparent" ]);
			}
		}
	}

	function updateModel() {
		var updatedSpaceArray = [];
		updatedSpaceArray.push(getStartingCheckerPos());
		updatedSpaceArray.push(getEndingCheckerPos())
		$.post("/updateBoardModel", {"model" : JSON.stringify(updatedSpaceArray)});
	}

	return {
		update : update,
		updateModel : updateModel
	};

})();