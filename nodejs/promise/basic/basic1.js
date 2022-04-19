/**
 * output:
 *	initial
 *	end
 *	Do that
 *	do this whatever happened before
 */
new Promise((resolve, reject) => {
	console.log('initial');
	resolve();
	console.log('end');
})
.then(() => {
	throw new Error('Something failed');
})
.then(
	undefined,
	() => {
		console.log('Do that');
	}
)
//.catch(() => {
//	console.log('Do that');
//})
.then(() => {
	console.log('do this whatever happened before');
})
;
