const express = require('express');
const app = express();
const path = require('path');

const some_template_engine_name = 'pug';

app.set('views', path.join(__dirname, 'views'));
app.set('view engine', some_template_engine_name);

app.get('/', function(req, res) {
  res.render('index', { title: 'About dogs', message: 'Dogs rock!' });
});

app.listen(3000, function() {
	console.log('listening on 3000');
});
