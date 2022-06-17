// 1 --> 10 --> 5 --> 16

class Node
{
	constructor(value, next, prev)
	{
		this.value = value;
		this.next = next;
		this.prev = prev;
	}
}

class DoublyLinkedList
{
	constructor(value)
	{
		this.head = new Node(value, null, null);
		this.tail = this.head;
		this.length = 1;
	}

	append(value)
	{
		const newNode = new Node(value, null, this.tail);

		this.tail.next = newNode;
		this.tail = newNode;
		this.length++;

		return this;
	}

	prepend(value)
	{
		const oldHeadNode = this.head;
		this.head = new Node(value, this.head, null);
		oldHeadNode.prev = this.head;
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

		const indexNode = this._traverseToIndex(index);
		const newNode = new Node(value, indexNode, indexNode.prev);

		indexNode.prev.next = newNode;
		indexNode.prev = newNode;

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
			this.head = this.head.next;
			if(this.head) {
				this.head.prev = null;
			}
			this.length--;
			return this;
		} else if(index === (this.length - 1)) {
			const oldTailNode = this.tail;
			this.tail = oldTailNode.prev;
			this.tail.next = null;
			oldTailNode.prev = null;
			this.length--;
			return this;
		} else if(index >= this.length) {
			return false;
		}

		const indexNode = this._traverseToIndex(index);

		indexNode.prev.next = indexNode.next;
		indexNode.next.prev = indexNode.prev;
		indexNode.next = null;
		indexNode.prev = null;

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

const myLinkedList = new DoublyLinkedList(10);

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