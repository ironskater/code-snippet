const numbers = [99, 44, 6, 2, 1, 5, 63, 87, 283, 4, 0];

// impl1
function insertionSort1(array)
{
	if(array.length === 1) {
		return array;
	}

	for(let ix = 0; ix < array.length; ix++) {

		if(array[ix] < array[0]) {
			/**
			 * work as insert
			 * param1: insert idx
			 * param2: delete count
			 * param3: insert value
			 */
			array.splice(0, 0, array[ix]);
			/**
			 * work as delete
			 * delete the value at ix+1 after it's be inserted to the beginning of array
			 */
			array.splice(ix+1, 1);
		} else {
			for(let iy = 0; iy < ix - 1; iy++) {
				if(array[iy] < array[ix] && array[ix] <= array[iy + 1]) {
					/**
					 * insert data to iy+1 index
					 */
					array.splice(iy+1, 0, array[ix]);
					/**
					 * deletet data from ix+1 index
					 */
					array.splice(ix+1, 1);
				}
			}
		}
	}

	return array;
}

// impl2
function insertionSort2(array)
{
	if(array.length === 1) {
		return array;
	}

	const rtn = [];

	for(let ix = 0; ix < array.length; ix++) {
		if(ix === 0) {
			rtn.push(array[0]);
			continue;
		}

		if(array[ix] < rtn[0]) {
			rtn.splice(0, 0, array[ix]);
		} else if(rtn[rtn.length - 1] <= array[ix]) {
			rtn.push(array[ix]);
		} else {
			for(let iy = 0; iy < rtn.length - 1; iy++) {
				if(rtn[iy] < array[ix] && array[ix] <= rtn[iy + 1]) {
					/**
					 * insert data to iy+1 index
					 */
					rtn.splice(iy+1, 0, array[ix]);
				}
			}
		}
	}

	return rtn;
}

console.log('before sorting:\n%s', numbers);
console.log('after sorting of impl1:\n%s', insertionSort1(numbers));
console.log('after sorting of impl2:\n%s', insertionSort2(numbers));