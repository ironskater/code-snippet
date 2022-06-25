const numbers = [99, 44, 6, 2, 1, 5, 63, 87, 283, 4, 0];

function bubbleSort(array)
{
	if(array.length === 1) {
		return array;
	}

	for(let ix = 0; ix < array.length - 1; ix++) {
		for(let iy = 0; iy < array.length - 1; iy++) {
			if(array[iy] > array[iy + 1]) {
				let temp = array[iy];
				array[iy] = array[iy + 1];
				array[iy + 1] = temp;
			}
		}
	}

	return array;
}

console.log('before sorting:\n%s', numbers);
console.log('after sorting:\n%s', bubbleSort(numbers));