const numbers = [99, 44, 6, 2, 1, 5, 63, 87, 283, 4, 0];

function selectionSort(array)
{
	if(array.length === 1) {
		return array;
	}

	for(let ix = 0; ix < array.length; ix++) {

		let minValueIdx = ix;

		for(let iy = ix + 1; iy < array.length; iy++) {
			if(array[iy] < array[minValueIdx]) {
				minValueIdx = iy;
			}
		}

		let temp = array[ix];
		array[ix] = array[minValueIdx];
		array[minValueIdx] = temp;
	}

	return array;
}

console.log('before sorting:\n%s', numbers);
console.log('after sorting:\n%s', selectionSort(numbers));