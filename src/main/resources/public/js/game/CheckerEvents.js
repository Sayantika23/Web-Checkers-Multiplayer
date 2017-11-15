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
	setStartingCheckerPos([getCheckerSpaceVector(parent.id), dataColor ]);
}

function drop(ev) {
	ev.preventDefault();
	var data = ev.dataTransfer.getData("text");
	ev.target.appendChild(document.getElementById(data));
	setCurrentSpace(document.getElementById(data));
	setEndingCheckerPos([getCheckerSpaceVector(ev.target.id), dataColor ]);
	// console.log("ENDING CHECKER ID: " + getCheckerSpaceVector(ev.target.id))
	setEndingCheckerVector(getCheckerSpaceVector(ev.target.id));
	updateCheckerPieceId(ev.target.id);
	Checkerboard.updateModel();
	checkForCapturedPiece();
}

function checkForCapturedPiece() {
	console.log("STARTIMG ROW: " + startingRow);
	console.log("ENDING ROW: " + endingRow);
	console.log("STARTIMG COLUMN: " + startingColumn);
	console.log("ENDING COLUMN: " + endingColumn);
	console.log("RIGHT ROW JUMP: " + startingRow);
	console.log("RIGHT COLUMN JUMP: " + endingRow);

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
	var array = endingVector.split(",");
	endingRow = parseInt(array[0]);
	endingColumn = parseInt(array[1]);
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

