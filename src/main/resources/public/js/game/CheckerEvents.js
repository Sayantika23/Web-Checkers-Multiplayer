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

document.addEventListener('DOMContentLoaded', initialize, false);

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
	setStartingCheckerPos([ getCheckerSpaceVector(parent.id), dataColor ]);
}

function drop(ev) {
	ev.preventDefault();
	var data = ev.dataTransfer.getData("text");
	ev.target.appendChild(document.getElementById(data));
	setCurrentSpace(document.getElementById(data));
	setEndingCheckerPos([ getCheckerSpaceVector(ev.target.id), dataColor ]);
	setEndingCheckerVector(getCheckerSpaceVector(ev.target.id));
	updateCheckerPieceId(ev.target.id);
	setEndingCheckerId(setCheckerPieceIdPrefix(ev.target.id));
	setEndingCheckerSpaceId(ev.target.id);
	checkForCapturedPiece();
	kingCheckerPiece();
	updateCheckerboard(getUpdatedModel());
	updateCurrentPlayer(invertCheckerColor(dataColor));
}

function validateMove(event) {
	var valid = JSON.parse(event.data);
	if (!valid.valid) {
		cancelMove();
	}
	$('#game-board').load(document.URL + ' #game-board', function() {
		initialize();
	});
}

function getUpdatedModel() {
	var updatedSpaceArray = [];
	updatedSpaceArray.push(getStartingCheckerPos());
	updatedSpaceArray.push(getEndingCheckerPos());
	return JSON.stringify(updatedSpaceArray);
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
			var adjacentCheckerId1 = setCheckerJumpId(adjacentCheckerRow1,
					adjacentCheckerCol1);
			var adjacentChecker1 = document.getElementById(adjacentCheckerId1);
			var id1 = setCheckerJumpId(rightRowJump1, rightColJump1);
			var checkerToJump1 = document.getElementById(id1);
			if (rightRowJump1 <= 7 && rightRowJump1 >= 0 && rightColJump1 <= 7
					&& rightColJump1 >= 0) {
				if (checkerToJump1 == null
						&& setCheckerSpaceIdPrefix(id1) != setCheckerSpaceIdPrefix(getStartingCheckerId())
						&& adjacentChecker1 != null) {
					jump = true;
				}
			} else {
				jump = false;
			}

			var leftRowJump2 = startingRow - 2;
			var leftColJump2 = startingColumn - 2;
			var adjacentCheckerRow2 = startingRow - 1;
			var adjacentCheckerCol2 = startingColumn - 1;
			var adjacentCheckerId2 = setCheckerJumpId(adjacentCheckerRow2,
					adjacentCheckerCol2);
			var adjacentChecker2 = document.getElementById(adjacentCheckerId2)
			var id2 = setCheckerJumpId(leftRowJump2, leftColJump2);
			var checkerToJump2 = document.getElementById(id2);

			if (leftRowJump2 <= 7 && leftRowJump2 >= 0 && leftColJump2 <= 7
					&& leftColJump2 >= 0) {
				if (checkerToJump2 == null
						&& setCheckerSpaceIdPrefix(id2) != setCheckerSpaceIdPrefix(getStartingCheckerId())
						&& adjacentChecker2 != null) {
					jump = true;
				}
			} else {
				jump = false;
			}

			var leftRowJump3 = startingRow + 2;
			var leftColJump3 = startingColumn - 2;
			var adjacentCheckerRow3 = startingRow + 1;
			var adjacentCheckerCol3 = startingColumn - 1;
			var adjacentCheckerId3 = setCheckerJumpId(adjacentCheckerRow3,
					adjacentCheckerCol3);
			var adjacentChecker3 = document.getElementById(adjacentCheckerId3)
			var id3 = setCheckerJumpId(leftRowJump3, leftColJump3);
			var checkerToJump3 = document.getElementById(id3);

			if (leftRowJump3 <= 7 && leftRowJump3 >= 0 && leftColJump3 <= 7
					&& leftColJump3 >= 0) {
				if (checkerToJump3 == null
						&& setCheckerSpaceIdPrefix(id3) != setCheckerSpaceIdPrefix(getStartingCheckerId())
						&& adjacentChecker3 != null) {
					jump = true;
				}
			} else {
				jump = false;
			}

			var rightRowJump4 = startingRow + 2;
			var rightColJump4 = startingColumn + 2;
			var adjacentCheckerRow4 = startingRow + 1;
			var adjacentCheckerCol4 = startingColumn + 1;
			var adjacentCheckerId4 = setCheckerJumpId(adjacentCheckerRow4,
					adjacentCheckerCol4);
			var adjacentChecker4 = document.getElementById(adjacentCheckerId4)
			var id4 = setCheckerJumpId(rightRowJump4, rightColJump4);
			var checkerToJump4 = document.getElementById(id4);

			if (rightRowJump4 <= 7 && rightRowJump4 >= 0 && rightColJump4 <= 7
					&& rightColJump4 >= 0) {
				if (checkerToJump4 == null
						&& setCheckerSpaceIdPrefix(id4) != setCheckerSpaceIdPrefix(getStartingCheckerId())
						&& adjacentChecker4 != null) {
					jump = true;
				}
			} else {
				jump = false;
			}

			if (!jump) {
				changeTurn();
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
		score = blackScore;
		scoreCounterId = "player-score-".concat("BLACK");
		break;
	}
	updateScore(dataColor);
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
	if (previous != null) {
		previous.appendChild(current);
	}
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
	$.post("/updateScore", {
		"color" : color
	}, function(data) {
		var scores = JSON.parse(data);
		var playerOneScore = scores.score.playerOne;
		var playerTwoScore = scores.score.playerTwo;
		updateScoreContainers(playerOneScore, playerTwoScore);
	}, "json");
}

function updateCurrentPlayer(color) {
	$(".player-turn").each(function() {
		var badges = getBadgeColors(color);
		if ($(this).data("color") === color) {
			$(this).addClass("isMyTurn");
			$("#current-turn").removeClass("visible");
			$("#current-turn").addClass("hidden");
			$("#opponent-turn").removeClass("hidden");
			$("#opponent-turn").addClass("visible");
			$("#turn-badge").removeClass(badges[1]);
			$("#turn-badge").addClass(badges[0]);
		} else {
			$(this).removeClass("isMyTurn");
			$("#opponent-turn").removeClass("visible");
			$("#opponent-turn").addClass("hidden");
			$("#current-turn").removeClass("hidden");
			$("#current-turn").addClass("visible");
			$("#turn-badge").removeClass(badges[0]);
			$("#turn-badge").removeClass(badges[1]);
		}
	});
}

function getBadgeColors(color) {
	var badges = [];
	switch(color) {
		case "BLACK": {
			badges[0] = "badge-black";
			badges[1] = "badge-red";
		}
		break;
		case "RED": {
			badges[0] = "badge-red";
			badges[1] = "badge-black";
		}
		break;
	}
	return badges;
}

function getScore() {
	$.get("/getScore", function(data) {
		var scores = JSON.parse(data);
		var playerOneScore = scores.score.playerOne;
		var playerTwoScore = scores.score.playerTwo;
		updateScoreContainers(playerOneScore, playerTwoScore);
	}, "json");
}

function updateScoreContainers(playerOneScore, playerTwoScore) {
	var playerScoreBlack = document.getElementById("player-score-BLACK");
	var playerScoreRed = document.getElementById("player-score-RED");
	playerScoreBlack.innerHTML = playerOneScore;
	playerScoreRed.innerHTML = playerTwoScore;
}

function removePiece() {
	var removePieceArray = [];
	removePieceArray.push(getJumpCheckerVectorPos());
	var isValid = null;
	$.post("/removePiece", {
		"model" : JSON.stringify(removePieceArray)
	});
}

function removeJumpedChecker(checkerRow, checkerColumn) {
	var checkerId = "piece-".concat(checkerRow).concat("-").concat(
			checkerColumn);
	var jumpedChecker = document.getElementById(checkerId);
	setJumpCheckerVectorPos([ getCheckerPieceVector(checkerId), dataColor ]);
	var jumpedCheckerParent = jumpedChecker.parentNode;
	jumpedCheckerParent.removeChild(jumpedChecker);
	removePiece();
}

function initialize() {
	$.get("/getTurn", function(data) {
		var json = JSON.parse(data);
		lockCheckers(invertCheckerColor(json.turn));
		updateCurrentPlayer(invertCheckerColor(json.turn));
	}, "json");
	getScore();
}

function changeTurn() {
	$.post("/checkTurn", {
		"color" : dataColor
	}, function(data) {
		var json = JSON.parse(data);
		lockAllCheckers();
	}, "json");
}

function invertCheckerColor(color) {
	var checkerColor = null;
	switch (color) {
	case "BLACK":
		checkerColor = "RED";
		break;
	case "RED":
		checkerColor = "BLACK";
		break;
	}
	return checkerColor;
}

function lockCheckers(color) {
	var playerTurns = document.getElementsByClassName("player-turn");
	for (var i = 0; i < playerTurns.length; i++) {
		if (playerTurns[i].getAttribute("name") !== color) {
			playerTurns[i].classList.remove("isMyTurn");
			lockOpponentCheckers(color);
		} else {
			playerTurns[i].classList.add("isMyTurn");
			lockAllCheckers();
		}
	}
	;
}
function lockOpponentCheckers(color) {
	pieces = document.getElementsByClassName("Piece");
	for (var i = 0; i < pieces.length; i++) {
		if (pieces[i].getAttribute("data-color") !== color) {
			pieces[i].setAttribute('draggable', false);
			pieces[i].setAttribute('ondragstart', false);
			pieces[i].style.opacity = 0.5;
		} else {
			pieces[i].setAttribute('draggable', true);
			pieces[i].setAttribute('ondragstart', "drag(event)");
			pieces[i].style.opacity = 1;
		}
	}
}

function lockAllCheckers() {
	pieces = document.getElementsByClassName("Piece");
	for (var i = 0; i < pieces.length; i++) {
		pieces[i].setAttribute('draggable', false);
		pieces[i].setAttribute('ondragstart', false);
		pieces[i].style.opacity = 0.5;
	}
}