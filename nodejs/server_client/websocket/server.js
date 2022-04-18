const http = require('http');
const fs = require('fs');
const socketio = require('socket.io');

const httpserver = http.createServer(
	function(request, response) {
		console.log('Connection');
		let url = new URL(request.url, `http://${request.headers.host}`);
		let path = url.pathname;

		switch(path)
		{
			case '/':
				response.writeHead(200, {'Content-Type': 'text/html'});
				response.write('Hello, World.\n');
				response.end();
				break;

			case '/keypress':
			case '/server-time':
				let resource_path = __dirname + '/html/' + path + '.html';
				fs.readFile(resource_path, function(error, data) {
					// error: 讀檔失敗的error，例如檔案不存在
					// data: 讀檔資料
					if(error) {
						response.writeHead(404);
						response.write('oops this doesn\'t exist - 404\n');
						response.write(String(error));
					} else {
						response.writeHead(200, {"Content-Type": "text/html"});
						response.write(data, "utf8");
					}

					response.end();
				});
				break;

			default:
				response.writeHead(404);
				response.write('oops this doesn\'t exist - 404');
				response.end();
				break;
		}
	}
);

const websocket = socketio(httpserver);

// connection是Socket.IO 內建的事件，當瀏覽器端呼叫 io.connect() 之後，就會自動產生這個事件，進而呼叫回呼函數
websocket.on("connection", (socket) => {
	setInterval(function() {
		socket.emit('periodic_time', // 事件名稱
					{'date': new Date()}); // 事件資料, json format
	}, 1000);

	// 接收來自於瀏覽器的資料
	socket.on('client_data', function(data) {
		process.stdout.write(data.letter);
	});
});
httpserver.listen(8081);

console.log('server starts running...');
