const controller = require('./controller');
const all_route_middleware = require('./all_route_middleware');
const specific_route_middleware = require('./specific_route_middleware');

const express = require('express');
const app = express();

app.get('/', function(req, res){
	res.send('hello world');
});

app.use(all_route_middleware);

// Function added with use() for a specific route
// app.use('/specific', specific_route_middleware, function(req, res) {
// 	res.send('specific middleware');
// });

// 也能這樣call
app.use('/specific', specific_route_middleware);

app.use('/specific', function(req, res) {
	res.send('specific middleware: seperate');
});

// Function added with use() for all routes and verbs
app.use('/controller', controller);
app.listen(3000, function() {
	console.log('listening on port 3000');
});

/**
 * localhost:3000 -> web: hello world
 * localhost:3000/controller -> web: controller page, console: I am all_route_middleware
 * localhost:3000/controller/about -> web: about controller page, console: I am all_route_middleware
 * localhost:3000/specific -> web: specifc service, console: I am all_route_middleware then I am specific_route_middleware
 */