var webSocket = new WebSocket("ws://" + location.hostname + ":" + location.port + "/update");

webSocket.onmessage = function(data) {
	validateMove(data)
};

webSocket.onclose = function() {
	alert("WebSocket connection closed")
};

function updateCheckerboard(data) {
	webSocket.send(data);
}