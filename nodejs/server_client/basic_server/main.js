// node main.js
// curl -XGET localhost 8081 -> to get the response from server

var http = require("http")

http.createServer(
	function(request, response) {
		response.writeHead(200, {'Content-Type': 'text/plain'});
		response.write('hello, ');
		response.end('world!\n');
	}
).listen(8081);

console.log('running')
