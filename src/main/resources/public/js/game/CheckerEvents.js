var startingCheckerPos = null;
var startingCheckerId = null;
var endCheckerPos = null;
var dataColor = null;
var currentSpace = null;
var previousSpace = null;
var startingColumn = null;
var endingColumn = null;
var startingRow = null;
var endingRow = null;
var currentRow = null;
var currentColumn = null;
var moveRow = null;
var moveColumn = null;
var score = 0;
var redScore = 0;
var blackScore = 0;
var scoreCounterId = null;
var jumpCheckerPos = null;
var pieces = null;
var spaces = null;
var endingCheckerVector = null;
var endingCheckerId = null;
var endingCheckerSpaceId = null;

document.addEventListener('DOMContentLoaded', bindButtons, false);

function allowDrop(ev) {
	ev.preventDefault();
}

function drag(ev) {
	ev.dataTransfer.setData("text", ev.target.id);
	dataColor = ev.target.getAttribute("data-color");
	var parent = ev.target.parentNode;
	setPreviousSpace(ev.target.parentNode);
	setStartingCheckerId(ev.target.id);
	setStartingCheckerVector(getCheckerSpaceVector(parent.id));
	setStartingCheckerPos([getCheckerSpaceVector(parent.id), dataColor]);
}

function drop(ev) {
	ev.preventDefault();
	var data = ev.dataTransfer.getData("text");
	ev.target.appendChild(document.getElementById(data));
	setCurrentSpace(document.getElementById(data));
	setEndingCheckerPos([getCheckerSpaceVector(ev.target.id), dataColor]);
	// console.log("ENDING CHECKER ID: " + getCheckerSpaceVector(ev.target.id))
	setEndingCheckerVector(getCheckerSpaceVector(ev.target.id));
	updateCheckerPieceId(ev.target.id);
	setEndingCheckerId(setCheckerPieceIdPrefix(ev.target.id));
	setEndingCheckerSpaceId(ev.target.id);
	Checkerboard.updateModel();
	checkForCapturedPiece();
	kingCheckerPiece();
}

function setEndingCheckerSpaceId(id) {
	endingCheckerSpaceId = id;
}

function getEndingCheckerSpaceId() {
	return endingCheckerSpaceId;
}

function setEndingCheckerId(id) {
	endingCheckerId = id;
}

function getEndingCheckerId() {
	return endingCheckerId;
}

function findCheckerByVector() {
	pieces = document.getElementsByClassName("Piece");
	for (var i = 0; i < pieces.length; i++) {
		if (pieces[i].getAttribute("data-color") != dataColor) {
			var jump = false;
			var array = getEndingCheckerVector().split(",");
			
			var startingRow = parseInt(array[0]);
			var startingColumn = parseInt(array[1]);
			
			var rightRowJump1 = startingRow - 2;
			var rightColJump1 = startingColumn + 2;
			var adjacentCheckerRow1 = startingRow - 1;
			var adjacentCheckerCol1 = startingColumn + 1;
			var adjacentCheckerId1 = setCheckerJumpId(adjacentCheckerRow1, adjacentCheckerCol1);
			var adjecentChecker1 = document.getElementById(adjacentCheckerId1);
			var id1 = setCheckerJumpId(rightRowJump1, rightColJump1);
			var checkerToJump1 = document.getElementById(id1);
			if (rightRowJump1 <= 7 && rightRowJump1 >=0
					&& rightColJump1 <=7 && rightColJump1 >= 0) {
				if (checkerToJump1 == null
						&& setCheckerSpaceIdPrefix(id1) != setCheckerSpaceIdPrefix(getStartingCheckerId())
						&& adjecentChecker1 != null) {
					jump = true;
//					console.log("ID1: " + id1);
//					console.log("STARTING CHECKER ID: " + getStartingCheckerId());
					console.log("CHECKER TO JUMP: " + setCheckerSpaceIdPrefix(id1));
					console.log("STARTING SPACE ID: " + setCheckerSpaceIdPrefix(getStartingCheckerId()));
				} 
			} else {
				jump = false;
			}

			var leftRowJump2 = startingRow - 2;
			var leftColJump2 = startingColumn - 2;
			var adjacentCheckerRow2 = startingRow - 1;
			var adjacentCheckerCol2 = startingColumn - 1;
			var adjacentCheckerId2 = setCheckerJumpId(adjacentCheckerRow2, adjacentCheckerCol2);
			var adjecentChecker2 = document.getElementById(adjacentCheckerId2)
			var id2 = setCheckerJumpId(leftRowJump2, leftColJump2);
			var checkerToJump2 = document.getElementById(id2);
			
			if (leftRowJump2 <= 7 && leftRowJump2 >=0
					&& leftColJump2 <=7 && leftColJump2 >= 0) {
				if (checkerToJump2 == null
						&& setCheckerSpaceIdPrefix(id2) != setCheckerSpaceIdPrefix(getStartingCheckerId())
						&& adjecentChecker2 != null) {
					jump = true;
//					console.log("ID2: " + id2);
//					console.log("STARTING CHECKER ID: " + getStartingCheckerId());
					console.log("CHECKER TO JUMP: " + setCheckerSpaceIdPrefix(id2));
					console.log("STARTING SPACE ID: " + setCheckerSpaceIdPrefix(getStartingCheckerId()));
				} 
			} else {
				jump = false;
			}
			

			var leftRowJump3 = startingRow - 2;
			var leftColJump3 = startingColumn - 2;
			var adjacentCheckerRow3 = startingRow - 1;
			var adjacentCheckerCol3 = startingColumn - 1;
			var adjacentCheckerId3 = setCheckerJumpId(adjacentCheckerRow3, adjacentCheckerCol3);
			var adjecentChecker3 = document.getElementById(adjacentCheckerId3)
			var id3 = setCheckerJumpId(leftRowJump3, leftColJump3);
			var checkerToJump3 = document.getElementById(id3);
			
			if (leftRowJump3 <= 7 && leftRowJump3 >=0
					&& leftColJump3 <=7 && leftColJump3 >= 0) {
				if (checkerToJump3 == null
						&& setCheckerSpaceIdPrefix(id3) != setCheckerSpaceIdPrefix(getStartingCheckerId())
						&& adjecentChecker3 != null) {
					jump = true;
//					console.log("ID3: " + id3);
//					console.log("STARTING CHECKER ID: " + getStartingCheckerId());
					console.log("CHECKER TO JUMP: " + setCheckerSpaceIdPrefix(id3));
					console.log("STARTING SPACE ID: " + setCheckerSpaceIdPrefix(getStartingCheckerId()));
				} 
			} else {
				jump = false;
			}
			

			var rightRowJump4 = startingRow + 2;
			var rightColJump4 = startingColumn + 2;
			var adjacentCheckerRow4 = startingRow + 1;
			var adjacentCheckerCol4 = startingColumn + 1;
			var adjacentCheckerId4 = setCheckerJumpId(adjacentCheckerRow4, adjacentCheckerCol4);
			var adjecentChecker4 = document.getElementById(adjacentCheckerId4)
			var id4 = setCheckerJumpId(rightRowJump4, rightColJump4);
			var checkerToJump4 = document.getElementById(id4);
			

			if (rightRowJump4 <= 7 && rightRowJump4 >=0
					&& rightColJump4 <=7 && rightColJump4 >= 0) {
				if (checkerToJump4 == null
						&& setCheckerSpaceIdPrefix(id4) != setCheckerSpaceIdPrefix(getStartingCheckerId())
						&& adjecentChecker4 != null) {
					jump = true;
//					console.log("ID4: " + id4);
//					console.log("STARTING CHECKER ID: " + getStartingCheckerId());
					console.log("CHECKER TO JUMP: " + setCheckerSpaceIdPrefix(id4));
					console.log("STARTING SPACE ID: " + setCheckerSpaceIdPrefix(getStartingCheckerId()));
				} 
			} else {
				jump = false;
			}
			
			if (!jump) {
//				lockCheckers(dataColor);
				changeTurn();
				console.log("NO JUMP");
			}
		}
	}
}

function setCheckerJumpId(row, column) {
	return "piece-".concat(row).concat("-").concat(column);
}

function checkForCapturedPiece() {
	var removeCheckerRow = 0;
	var removeCheckerColumn = 0;
	var checkerJumped = false;

	var rightRowJump = startingRow - 2;
	var rightColJump = startingColumn + 2;
	if (rightRowJump === endingRow && rightColJump === endingColumn) {
		removeCheckerRow = startingRow - 1;
		removeCheckerColumn = startingColumn + 1;
		updateScoreCount();
		checkerJumped = true;
	}

	var leftRowJump = startingRow - 2;
	var leftColJump = startingColumn - 2;
	if (leftRowJump === endingRow && leftColJump === endingColumn) {
		removeCheckerRow = startingRow - 1;
		removeCheckerColumn = startingColumn - 1;
		updateScoreCount();
		checkerJumped = true;
	}

	var rightRowJump = startingRow + 2;
	var rightColJump = startingColumn + 2;
	if (rightRowJump === endingRow && rightColJump === endingColumn) {
		removeCheckerRow = startingRow + 1;
		removeCheckerColumn = startingColumn + 1;
		updateScoreCount();
		checkerJumped = true;
	}

	var leftRowJump = startingRow + 2;
	var leftColJump = startingColumn - 2;
	if (leftRowJump === endingRow && leftColJump === endingColumn) {
		removeCheckerRow = startingRow + 1;
		removeCheckerColumn = startingColumn - 1;
		updateScoreCount();
		checkerJumped = true;
	}

	if (checkerJumped) {
		removeJumpedChecker(removeCheckerRow, removeCheckerColumn);
		findCheckerByVector();
	} else {
//		lockCheckers(dataColor);
		changeTurn();
	}
}

function updateScoreCount() {
	switch (dataColor) {
	case "RED":
		scoreCounterId = "player-score-".concat("RED");
		redScore += 1;
		score = redScore;
		break;
	case "BLACK":
		blackScore += 1;
		score= blackScore;
		scoreCounterId = "player-score-".concat("BLACK");
		break;
	}
	var playerScore = document.getElementById(scoreCounterId);
	playerScore.innerHTML = score;
}

function setStartingCheckerVector(startingVector) {
	var array = startingVector.split(",");
	startingRow = parseInt(array[0]);
	startingColumn = parseInt(array[1]);
}

function setEndingCheckerVector(endingVector) {
	endingCheckerVector = endingVector;
	var array = endingVector.split(",");
	endingRow = parseInt(array[0]);
	endingColumn = parseInt(array[1]);
}

function kingCheckerPiece() {
	var vector = getEndingCheckerVector();
	var array = vector.split(",");
	var row = parseInt(array[0]);
	var king = null;
	if (row == 0) {
		var id = "#" + getEndingCheckerId();
		$(id).attr("data-type", "KING");
	}
	if (row == 7) {
		var id = "#" + getEndingCheckerId();
		$(id).attr("data-type", "KING");
	}
//	console.log("END ID: " + getEndingCheckerId());
}
function getEndingCheckerVector() {
	return endingCheckerVector;
}

function getCurrentSpace() {
	return currentSpace;
}

function setCurrentSpace(current) {
	this.currentSpace = current;
}

function getPreviousSpace() {
	return previousSpace;
}

function setPreviousSpace(previous) {
	previousSpace = previous;
}

function cancelMove() {
	var current = getCurrentSpace();
	var previous = getPreviousSpace();
	previous.appendChild(current);
}

function getStartingCheckerPos(startingPos) {
	return startingCheckerPos;
}

function setStartingCheckerPos(startingPos) {
	startingCheckerPos = startingPos;
}

function getEndingCheckerPos() {
	return endingCheckerPos;
}

function setEndingCheckerPos(endingPos) {
	endingCheckerPos = endingPos;
}

function getCheckerPieceVector(id) {
	return id.replace('piece-', '').replace('-', ',');
}

function getCheckerSpaceVector(id) {
	return id.replace('space-', '').replace('-', ',');
}

function setCheckerPieceIdPrefix(id) {
	return id.replace('space-', 'piece-');
}

function setCheckerSpaceIdPrefix(id) {
	return id.replace('piece-', 'space-');
}

function setStartingCheckerId(id) {
	startingCheckerId = id;
}

function getStartingCheckerId() {
	return startingCheckerId;
}

function updateCheckerPieceId(spaceId) {
	var pieceId = setCheckerPieceIdPrefix(spaceId);
	document.getElementById(getStartingCheckerId()).setAttribute("id", pieceId);
}

function setCurrentRow(currRow) {
	currentRow = currRow;
}

function setCurrentColumn(currCol) {
	currentColumn = currCol;
}

function setMoveRow(movRow) {
	moveRow = movRow;
}

function setMoveColumn(movCol) {
	moveColumn = movCol;
}

function getMoveRow() {
	return moveRow;
}

function getMoveColumn() {
	return moveColumn;
}


function setJumpCheckerVectorPos(position) {
	jumpCheckerPos = position;
}

function getJumpCheckerVectorPos() {
	return jumpCheckerPos;
}

function updateScore(color) {
	$.post("/updateScore", function(data) {
		var scoreData = JSON.parse(data);
	}, "json");
}

function removePiece() {
	var removePieceArray = [];
	removePieceArray.push(getJumpCheckerVectorPos());
	console.log("JUMP CHECKER POSITION: " + getJumpCheckerVectorPos());
	console.log("JUMP CHECKER ARRAY: " + removePieceArray);
	var isValid = null;
	$.post( "/removePiece", {"model" : JSON.stringify(removePieceArray)});
}

function removeJumpedChecker(checkerRow, checkerColumn) {
	var checkerId = "piece-".concat(checkerRow).concat("-").concat(checkerColumn);
	var jumpedChecker = document.getElementById(checkerId);
	setJumpCheckerVectorPos([getCheckerPieceVector(checkerId), dataColor]);
	console.log("JUMPED CHECKER " + checkerId + " " + dataColor);
	var jumpedCheckerParent = jumpedChecker.parentNode;
	jumpedCheckerParent.removeChild(jumpedChecker);
	removePiece();
}

function bindButtons() {
	var submitLink = document.getElementById("submitLink");
	submitLink.onclick = function() {
		lockCheckers(dataColor);
	}
	$.get("/getTurn", function(data) {
		var json = JSON.parse(data);
		var lockColor = invertLockColor(json.turn);
		lockCheckers(lockColor);
	}, "json");
}

function changeTurn() {
	$.post("/checkTurn", {"color" : dataColor}, function(data) {
		var json = JSON.parse(data);
		var lockColor = invertLockColor(json.turn);
		lockCheckers(lockColor);
	}, "json");
}

function invertLockColor(color) {
	var lockColor = null;
	switch(color) {
		case "BLACK": lockColor = "RED";
		break;
		case "RED": lockColor = "BLACK";
		break;
	}
	return lockColor;
}

function lockCheckers(dataColor) {
	pieces = document.getElementsByClassName("Piece");
	for (var i = 0; i < pieces.length; i++) {
		if (pieces[i].getAttribute("data-color") === dataColor) {
			pieces[i].setAttribute('draggable', false);
			pieces[i].setAttribute('ondragstart', false);
//			pieces[i].classList.add("inactive");
			pieces[i].style.opacity = 0.5;
		} else {
			pieces[i].setAttribute('draggable', true);
			pieces[i].setAttribute('ondragstart', "drag(event)");
//			pieces[i].classList.remove("inactive");
			pieces[i].style.opacity = 1;
		}
	}
}