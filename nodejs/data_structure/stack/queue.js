class Node
{
	constructor(value)
	{
		this.value = value;
		this.next = null;
	}
}

class Queue
{
	constructor()
	{
		this.first = null;
		this.last = null;
		this.length = 0;
	}

	peek()
	{
		return this.first;
	}

	enqueue(value)
	{
		const node = new Node(value);

		if(this.length === 0) {
			this.first = node;
			this.last = this.first;
			this.length++;
			return this;
		}

		this.last.next = node;
		this.last = node;

		this.length++

		return this;
	}

	dequeue()
	{
		if(!this.first) {
			return null;
		}

		const firstNode = this.first;
		this.first = firstNode.next;
		firstNode.next = null;
		this.length--;

		if(this.length === 0) {
			this.last = null;
		}

		return firstNode;
	}
}

const myQueue = new Queue();

myQueue.enqueue('google');
console.log(myQueue.peek());
myQueue.enqueue('udemy');
console.log(myQueue.peek());

console.log(myQueue.dequeue());
console.log(myQueue.dequeue());
console.log(myQueue.dequeue());

myQueue.enqueue('amazon');
console.log(myQueue.peek());
myQueue.enqueue('pandora');
console.log(myQueue.peek());

console.log(myQueue.dequeue());
console.log(myQueue.dequeue());
console.log(myQueue.dequeue());