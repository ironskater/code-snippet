/**
 * https://expressjs.com/en/resources/middleware/morgan.html
 */
const logger = require('morgan');
const express = require('express');
const app = express();
const port = 3000;

app.use(logger('dev'));
// app.use(logger('combined'));

app.get('/', (req, res) => {
	res.send('<h1>hello world</h1>');
})

app.listen(port, () => {
	console.log(`started on ${port}`);
})