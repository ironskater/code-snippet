/**
 *  Factorial Problem
 */
function findFactorialRecursive(number) // O(n)
{
	if(number === 0) {
		return 0;
	} else if(number < 0) {
		return 'number can be less than 0';
	} else if(number === 1) {
		return 1;
	}

	return number * findFactorialRecursive(number - 1);
}

function findFactorialIterative(number) // O(n)
{
	let rtn = 1;

	for(let ix = 1; ix <= number; ix++) {
		rtn = rtn * ix;
	}

	return rtn;
}

console.log(findFactorialRecursive(5));
console.log(findFactorialIterative(5));


/**
 *  Fibonacci Problem
 */
function fibonacciRecursive(n) // O(2^n)
{
	if(n < 2) {
		return n;
	}

	return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
}

function fibonacciIterative(n) // O(n - 2)
{
	if(n < 2) {
		return n;
	}

	let rtn = 0;
	const f = [0, 1];
	const loops = n - 2;

	for(let ix = 0; ix <= loops; ix++) {
		rtn = f[0] + f[1];
		f[0] = f[1];
		f[1] = rtn;
	}

	return rtn
}

console.log(fibonacciIterative(41));
console.log(fibonacciRecursive(41));
/**
 * we can observe this one takes more time than iterative because of O(2^N).
 * for fibonacci problem, although recursive solution is more readable, it's not an ideal solution.
 */