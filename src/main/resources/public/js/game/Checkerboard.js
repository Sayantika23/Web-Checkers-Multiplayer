define(function(require){
  'use strict';
  
  console.log("checkerboard");
  
	var squares = [];
	var length = 8;

	for (var i = 0; i < length; i++) {
		for (var j = 0; j < length; j++) {
			squares.push([i, j])
		}
	}
	console.log(squares);
});