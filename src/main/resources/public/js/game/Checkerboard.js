var Checkerboard = (function() {
	var pieces = null;
	var checkerVectorArray = [];

	function updateModel() {
		var updatedSpaceArray = [];
		updatedSpaceArray.push(getStartingCheckerPos());
		updatedSpaceArray.push(getEndingCheckerPos());
		var isValid = null;
		$.post( "/updateBoardModel", {"model" : JSON.stringify(updatedSpaceArray)}, function(data) {
			var valid =  JSON.parse(data);
			if (!valid.valid) {
				cancelMove();
			}
		}, "json");
	}

	return {
		updateModel : updateModel
	};

})();