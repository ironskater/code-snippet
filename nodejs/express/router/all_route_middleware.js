const all_route_middleware = function(req, res, next) {
	console.log('I am all_route_middleware');
	next(); // Call next() so Express will call the next middleware function in the chain.
}

module.exports = all_route_middleware;