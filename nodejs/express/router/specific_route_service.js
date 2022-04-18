const specific_route_service = function(req, res, next) {
	console.log('I am specific_route_service');
	next(); // Call next() so Express will call the next middleware function in the chain.
}

module.exports = specific_route_service;