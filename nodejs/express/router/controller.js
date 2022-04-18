const express = require('express');
const router = express.Router();

router.get('/', function(req, res) {
	res.send('controller page');
});

router.get('/about', function(req, res) {
	res.send('about controller page');
});

module.exports = router;