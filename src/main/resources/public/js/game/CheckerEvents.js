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
    setEndingCheckerVector(getCheckerSpaceVector(ev.target.id));
    updateCheckerPieceId(ev.target.id);
    Checkerboard.updateModel();
    checkForCapturedPiece();
}

function setStartingCheckerVector(startingVector) {
	var array = startingVector.split(",");
	startingRow = array[0];
	startingColumn = array[1];
	console.log(startingRow + " " + startingColumn);
}

function setEndingCheckerVector(endingVector) {
	var array = endingVector.split(",");
	endingRow = array[0];
	endingColumn = array[1];
	console.log(endingRow + " " + endingColumn);
}

function checkForCapturedPiece() {
	var capturedPieceRow = 0;
	var capturedPieceColumn = 0;
	if (endingRow == startingRow + 2 && endingColumn == startingColumn + 2) {
		alert();
	}
	else if (endingRow == startingRow + 2 && endingColumn == startingColumn - 2) {
		alert();
	}
	
	else if (endingRow == startingRow - 2 && endingColumn == startingColumn + 2) {
		alert();
	}
	
	else if (endingRow == startingRow - 2 && endingColumn == startingColumn - 2) {
		alert();
	}
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