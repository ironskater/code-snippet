class Stack
{
	constructor()
	{
		this.ary = [];
	}

	peek()
	{
		return this.ary[this.ary.length - 1];
	}

	push(value)
	{
		this.ary.push(value);

		return this;
	}

	pop()
	{
		if(this.ary.length === 0) {
			return this.ary;
		}

		return this.ary.pop(this.ary.length - 1);
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