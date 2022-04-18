const express = require('express');
const app = express();

app.use(express.static('pic'));
app.use('/media', express.static('media'));

app.listen(3000, function() {
	console.log('listening on port 3000');
});

/**
 * Any files in the pic directory are served by adding their filename (relative to the base "pic" directory) to the base URL
 *
 * http://localhost:3000/panda/panda.png -> show panda.png on browser
 * http://localhost:3000/media/underwater.jpg -> show underwater.jpg on browser
 */