const MongoClient = require('mongodb').MongoClient;;

MongoClient.connect('mongodb://10.10.172.96/animals', function(err, client) {
	if(err) throw err;

	let db = client.db('animals');

	db.collection('mammals').find().toArray(function(err, result) {
		if(err) throw err;

		console.log(result);
		client.close();
	});
});
