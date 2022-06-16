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