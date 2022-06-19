// 1 --> 10 --> 5 --> 16

class Node
{
	constructor(value, next)
	{
		this.value = value;
		this.next = next;
	}
}

class LinkedList
{
	constructor(value)
	{
		this.head = new Node(value, null);
		this.tail = this.head;
		this.length = 1;
	}

	append(value)
	{
		const newNode = new Node(value, null);

		this.tail.next = newNode;
		this.tail = newNode;
		this.length++;

		return this;
	}

	prepend(value)
	{
		this.head = new Node(value, this.head);
		this.length++;

		return this;
	}

	insert(index, value)
	{
		if(index >= this.length) {
			return this.append(value);
		} else if(index === 0) {
			return this.prepend(value);
		}

		const newNode = new Node(value, null);
		const leader = this._traverseToIndex(index - 1);

		const holdingPointer = leader.next;
		leader.next = newNode;
		newNode.next = holdingPointer;
		this.length++;

		return this;
	}

	_traverseToIndex(index)
	{
		let counter = 0;
		let currentNode = this.head;

		while(counter != index) {
			currentNode = currentNode.next;
			counter++;
		}

		return currentNode;
	}

	remove(index)
	{
		if(index === 0) {
			const oldHead = this.head;
			this.head = oldHead.next;
			oldHead.next = null;
			this.length--;
			return this;
		} else if(index >= this.length) {
			return false;
		}

		const leaderNode = this._traverseToIndex(index - 1);

		leaderNode.next = null;
		this.tail = leaderNode;

		this.length--;

		return this;
	}

	// null --> 1 --> 10 --> 5 --> 16
	reverse()
	{
		if(!this.head || !this.head.next) {
			return this;
		}

		const nodeAry = [null, this.head, this.head.next];
		this.tail = this.head;

		do {
			nodeAry[1].next = nodeAry[0];

			if(!nodeAry[2]) {
				this.head = nodeAry[1];
				break;
			}

			nodeAry[0] = nodeAry[1];
			nodeAry[1] = nodeAry[2];
			nodeAry[2] = nodeAry[2].next;
		} while(true);

		return this;
	}

	toPrintable()
	{
		const array = [];
		if(this.length === 0) {
			return array;
		}
		let currentNode = this.head;

		while(currentNode !== null) {
			array.push(currentNode.value);
			currentNode = currentNode.next;
		}
		return array;
	}
}

const myLinkedList = new LinkedList(10);

for(let ix = 0; ix < 2; ix++)
{
	myLinkedList.append(ix);
	console.log(myLinkedList.toPrintable());
}

for(let ix = 0; ix < 2; ix++)
{
	myLinkedList.prepend(ix);
	console.log(myLinkedList.toPrintable());
}

myLinkedList.insert(0, 100);
console.log(myLinkedList.toPrintable());

myLinkedList.insert(1, 101);
console.log(myLinkedList.toPrintable());

myLinkedList.insert(myLinkedList.length - 1, 102);
console.log(myLinkedList.toPrintable());

console.log(myLinkedList.length);

for(let ix = 0, originalLength = myLinkedList.length;
	ix < originalLength;
	ix++)
{
	myLinkedList.remove(0);
	console.log(myLinkedList.toPrintable());
}

// test reverse
// 1 --> 2 --> 3 --> 4
const list = new LinkedList(1);
list.append(2);
list.append(3);
list.append(4);

console.log('before revserse: %s', list.toPrintable());
console.log('after revserse: %s', list.reverse().toPrintable());