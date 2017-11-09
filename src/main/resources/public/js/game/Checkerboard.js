var Checkerboard = (function() {
	var pieces = null;
	var checkerVectorArray = [];

	function update() {
		pieces = document.getElementsByClassName("Piece");
		for (var i = 0; i < pieces.length; i++) {

			var checkerId = pieces[i].id;
			var checkerVector = getCheckerPieceVector(checkerId);
			if (pieces[i].getAttribute("data-color") === "WHITE") {
				checkerVectorArray.push([ checkerVector, "WHITE" ]);
			}
			if (pieces[i].getAttribute("data-color") === "RED") {
				checkerVectorArray.push([ checkerVector, "RED" ]);
			}
			if (pieces[i].getAttribute("data-color") === "TRANSPARENT") {
				checkerVectorArray.push([ checkerVector, "TRANSPARENT" ]);
			}
		}
	}

	function updateModel() {
		var updatedSpaceArray = [];
		updatedSpaceArray.push(getStartingCheckerPos());
		updatedSpaceArray.push(getEndingCheckerPos());
		var isValid = null
		$.post( "/updateBoardModel", {"model" : JSON.stringify(updatedSpaceArray)}, function(data) {
			var response = JSON.parse(data);
			var board = response.board;
			var valid = response.valid;
	    	console.log("board: " + board);
	    	console.log("valid: " + valid);
	        for (var key in board) {
	            var jumps = key;
	            var attrValue = board[key];
		    	for (var v in attrValue) {
			    	var jump = attrValue[v];
			    	for (var j in jump) {
				    	console.log("key: "  + j + " value: " + jump[j]);
				    	switch(j) {
				    		case: "currRow": setCurrentRow(jump[j]); 
				    		break;
				    		case: "currCol": setCurrentColumn(jump[j]);
				    		break;
				    		case: "movRow": setMoveRow(jump[j]);
				    		break;
				    		case: "movCol": setMoveColumn(jump[j]);
				    		break;
				    	}
			    	}
		    	}
	        }
		}, "json");
	}

	return {
		update : update,
		updateModel : updateModel
	};

})();