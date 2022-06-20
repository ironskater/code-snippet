class Node
{
	constructor(value)
	{
		this.value = value;
		this.next = null;
	}
}

class Stack
{
	constructor()
	{
		this.top = null;
		this.bottom = null;
		this.length = 0;
	}

	peek()
	{
		return this.top;
	}

	push(value)
	{
		const newNode = new Node(value);

		if(this.length === 0) {
			this.bottom = newNode;
			this.top = this.bottom;
			this.length++;
			return this;
		}

		const holdingPointer = this.top;
		this.top = newNode;
		this.top.next = holdingPointer;
		this.length++;
		return this;
	}

	pop()
	{
		if(!this.top) {
			return null;
		}

		const holdingPointer = this.top;
		this.top = holdingPointer.next;
		holdingPointer.next = null;
		this.length--;
		if(this.length === 0) {
			this.bottom = null;
		}
		return holdingPointer;
	}
}

const myStack = new Stack();

myStack.push('google');
console.log(myStack.peek());
myStack.push('udemy');
console.log(myStack.peek());

console.log(myStack.pop());
console.log(myStack.pop());
console.log(myStack.pop());