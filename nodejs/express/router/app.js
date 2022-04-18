const controller = require('./controller');
const all_route_service = require('./all_route_service');
const specific_route_service = require('./specific_route_service');

const express = require('express');
const app = express();

app.get('/', function(req, res){
	res.send('hello world');
});

// Function added with use() for a specific route
app.use('/specific', specific_route_service, function(req, res) {
	res.send('specific service');
});

// Function added with use() for all routes and verbs
app.use(all_route_service);
app.use('/controller', controller);
app.listen(3000, function() {
	console.log('listening on port 3000');
});

/**
 * localhost:3000 -> hello world
 * localhost:3000/controller -> web: controller page, console: I am all_route_service
 * localhost:3000/controller/about -> web: about controller page, console: I am all_route_service
 * localhost:3000/specific -> web: specifc service, console: I am all_route_service then I am specific_route_service
 */