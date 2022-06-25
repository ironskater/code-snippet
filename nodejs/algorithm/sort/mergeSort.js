const numbers = [99, 44, 6, 2, 1, 5, 63, 87, 283, 4, 0];
// const numbers = [2, 2, 3, 4, 3, 3, 4, 1, 4, 4];

function mergeSort(array)
{
    // base condition of recursive
    if(array.length === 1) {
        return array;
    }

    // split array into right and left
    let halfIdx = array.length/2;
    let left = array.slice(0, halfIdx);
    let right = array.slice(halfIdx);

    // console.log('left: ', left);
    // console.log('right: ', right);

    return merge2(
        mergeSort(left),
        mergeSort(right)
    )
}

// impl1
function merge1(left, right)
{
    let rtn = [];
    let leftIdx = 0;
    let rightIdx = 0;

    while(true) {
        if(left[leftIdx] < right[rightIdx]) {
            rtn.push(left[leftIdx++]);
        } else if(left[leftIdx] > right[rightIdx]) {
            rtn.push(right[rightIdx++]);
        } else {
            rtn.push(left[leftIdx++]);
            rtn.push(right[rightIdx++]);
        }

        if(leftIdx === left.length && rightIdx !== right.length) {
            rtn = rtn.concat(right.slice(rightIdx));
            break;
        } else if(leftIdx !== left.length && rightIdx === right.length) {
            rtn = rtn.concat(left.slice(leftIdx));
            break;
        } else if(leftIdx === left.length && rightIdx === right.length) {
            break;
        }
    }

    return rtn;
}

// impl2
function merge2(left, right)
{
    let rtn = [];
    let leftIdx = 0;
    let rightIdx = 0;

    console.log(left, right);

    while(leftIdx !== left.length && rightIdx !== right.length) {
        if(left[leftIdx] < right[rightIdx]) {
            rtn.push(left[leftIdx++]);
        } else if(left[leftIdx] > right[rightIdx]) {
            rtn.push(right[rightIdx++]);
        } else {
            rtn.push(left[leftIdx++]);
            rtn.push(right[rightIdx++]);
        }
    }

    return rtn.concat(left.slice(leftIdx)).concat(right.slice(rightIdx));
}

console.log('before sorting:\n%s', numbers);
console.log('after sorting:\n%s', mergeSort(numbers));