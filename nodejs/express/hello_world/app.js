/**
 * import express module
 */
const express = require('express');

/**
 * This object, which is traditionally named app, has methods for routing HTTP requests,
 * configuring middleware, rendering HTML views, registering a template engine,
 * and modifying application settings that control how the application behaves
 * (e.g. the environment mode, whether route definitions are case sensitive, etc.)
 */
const app = express();

/**
 * The app.get() method specifies a callback function that will be invoked whenever
 * there is an HTTP GET request with a path ('/') relative to the site root.
 *
 * The callback function takes a request and a response object as arguments,
 * and simply calls send() on the response to return the string "Hello World!"
 */
app.get('/', function(req, res) {
	res.send('hello world: root');
});
app.get('/layer1', function(req, res) {
	res.send('hello world: layer1');
});

app.listen(3000, function() {
	console.log('listening on port 3000');
});