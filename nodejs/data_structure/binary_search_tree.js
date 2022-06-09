class Node
{
	constructor(value) {
		this.right = null;
		this.left = null;
		this.value = value;
	}
}

class BinarySearchTree
{
	constructor() {
		this.root = null;
	}

	// impl 1
	insert(value) {
		const node = new Node(value);

		if(this.root === null) {
			this.root = node;
			return this;
		}

		let currentNode = this.root;

		while(true) {
			if(value < currentNode.value) {
				if(currentNode.left === null) { // or using if(!currentNOde.left)
					currentNode.left = node;
					return this;
				}
				currentNode = currentNode.left;
			} else {
				if(currentNode.right === null) {
					currentNode.right = node;
					return this;
				}
				currentNode = currentNode.right;
			}
		}
	}

	// impl2: using recursive
	insertByRecursive(value) {
		if(!this.root) {
			this.root = new Node(value);
			return this;
		}

		this._insertByRecursiveHelper(this.root, value);
	}

	_insertByRecursiveHelper(currentNode, value) {
		if(value < currentNode.value) {
			if(!currentNode.left) {
				currentNode.left = new Node(value);
				return this;
			}
			this._insertByRecursiveHelper(currentNode.left, value);
		} else {
			if(!currentNode.right) {
				currentNode.right = new Node(value);
				return this;
			}
			this._insertByRecursiveHelper(currentNode.right, value);
		}
	}

	// impl1
	lookup(value) {
		if(!this.root) {
			return false;
		}

		let currentNode = this.root;
		while(currentNode) {
			if(value < currentNode.value) {
				currentNode = currentNode.left;
			} else if (value > currentNode.value) {
				currentNode = currentNode.right;
			} else {
				return currentNode;
			}
		}

		return false;
	}

	// impl2: using recursive
	lookupByRecursive(value) {
		if(!this.root) {
			return false;
		}

		const ref = {node : this.root};
		this._lookupByRecursiveHelper(ref, value);

		return ref.node === null ? false : ref.node;
	}

	_lookupByRecursiveHelper(ref, value) {
		if(!ref.node) {
			return;
		}

		if(value < ref.node.value) {
			ref.node = ref.node.left;
			this._lookupByRecursiveHelper(ref, value);
			return;
		} else if (value > ref.node.value) {
			ref.node = ref.node.right;
			this._lookupByRecursiveHelper(ref, value);
			return;
		} else {
			return;
		}
	}

	remove() {

	}
}

const tree = new BinarySearchTree();
// tree.insert(9);
// tree.insert(4);
// tree.insert(6);
// tree.insert(20);
// tree.insert(170);
// tree.insert(15);
// tree.insert(1);

tree.insertByRecursive(9);
tree.insertByRecursive(4);
tree.insertByRecursive(6);
tree.insertByRecursive(20);
tree.insertByRecursive(170);
tree.insertByRecursive(15);
tree.insertByRecursive(1);

// expected tree structure
//        9
//    4      20
//  1   6  15  170

// console.log(JSON.stringify(traverse(tree.root), null, '    '));
// ======== level3 node
// console.log(tree.lookup(1));
console.log(tree.lookupByRecursive(1));
console.log(tree.lookupByRecursive(6));
console.log(tree.lookupByRecursive(15));
console.log(tree.lookupByRecursive(170));
// ======== level2 node
console.log(tree.lookupByRecursive(4));
console.log(tree.lookupByRecursive(20));
// ======== level1 node
// console.log(tree.lookup(9));
console.log(tree.lookupByRecursive(9));
// ======== non-exist node
// console.log(tree.lookup(5566));
console.log(tree.lookupByRecursive(5566));

function traverse(node) {
	const tree = {value : node.value};
	tree.left = node.left === null ? null : traverse(node.left);
	tree.right = node.right === null ? null : traverse(node.right);
	return tree;
}
