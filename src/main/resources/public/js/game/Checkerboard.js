<<<<<<< HEAD
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
//		update();
		$.post("/updateBoardModel", {"model" : JSON.stringify(updatedSpaceArray)});
	}

	return {
		update : update,
		updateModel : updateModel
	};

})();
=======
define(function(require) {
	'use strict';
	
	var checkerboard = (function () {
		var pieces = null;
		var checkerVectorArray = [];
		
		function update() {
			pieces = document.getElementsByClassName("Piece");
			for (var i = 0; i < pieces.length; i++) {

//				pieces[i].setAttribute("draggable", false);
				var checkerId = pieces[i].id;
				var checkerVector = checkerId.replace('piece-', '');
				checkerVector = checkerVector.replace('-', ',');
				if (pieces[i].getAttribute("data-color") === "WHITE") {
					checkerVectorArray.push([checkerVector, "white" ]);
//					pieces[i].setAttribute("draggable", true);
				}
				if (pieces[i].getAttribute("data-color") === "RED") {
					checkerVectorArray.push([checkerVector, "red"]);
//					pieces[i].setAttribute("draggable", true);
				}
				if (pieces[i].getAttribute("data-color") === "TRANSPARENT") {
					checkerVectorArray.push([checkerVector, "transparent"]);
				}
			}
			$.post("/updateBoardModel", {"model": JSON.stringify(checkerVectorArray)});
		}
 
        return {
        	update: update
        };
 
    })();
	
	checkerboard.update();
});
>>>>>>> master
