const XMLHttpRequest = require('xhr2');

const ALL_POKEMONS_URL = 'https://pokeapi.co/api/v2/pokemon?limit=50';
// const ALL_POKEMONS_URL = 'https://pokeapi.co/api/v2/bad_url';

let promise = getPromise(ALL_POKEMONS_URL);

const consumer = () => {
	promise.then(
		(result) => {
			console.log({result});
		},
		(error) => {
			console.log('An Error! ' + error);
		}
	)
}

consumer();

function getPromise(URL)
{
  return new Promise(function (resolve, reject) {
    let req = new XMLHttpRequest();
    req.open("GET", URL);
    req.onload = function () {
      if (req.status == 200) {
        resolve(req.response);
      } else {
        reject("There is an Error!");
      }
    };
    req.send();
  });
}
