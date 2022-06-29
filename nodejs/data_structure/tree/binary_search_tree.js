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

    breadthFirstTraversalByIterative()
    {
        let rtn = [];
        let queue = [this.root];

        while(queue.length > 0) {
            let currentNode = queue.shift();
            rtn.push(currentNode.value);
            console.log('push value: ', currentNode.value);

            if(currentNode.left) {
                queue.push(currentNode.left);
            }
            if(currentNode.right) {
                queue.push(currentNode.right);
            }
        }

        return rtn;
    }

    breadthFirstTraversalByRecursive()
    {
        const rtn = [];

        this._breadthFirstTraversalByRecursiveHelper([this.root], rtn);

        return rtn;
    }

    _breadthFirstTraversalByRecursiveHelper(queue, list)
    {
        if(!queue.length) {
            return list;
        }

        let currentNode = queue.shift();

        list.push(currentNode.value);

        if(currentNode.left) {
            queue.push(currentNode.left);
        }

        if(currentNode.right) {
            queue.push(currentNode.right);
        }

        return this._breadthFirstTraversalByRecursiveHelper(queue, list);
    }

    depthFirstSearchInorder()
    {
        const rtn = [];
        this._depthFirstSearchInorderHelper(this.root, rtn);
        return rtn;
    }

    _depthFirstSearchInorderHelper(node, list)
    {
        if(node.left) {
            this._depthFirstSearchInorderHelper(node.left, list);
        }

        list.push(node.value);

        if(node.right) {
            this._depthFirstSearchInorderHelper(node.right, list);
        }

        return;
    }

    depthFirstSearchPreorder()
    {
        const rtn = [];
        this._depthFirstSearchPreorderHelper(this.root, rtn);
        return rtn;
    }

    _depthFirstSearchPreorderHelper(node, list)
    {
        list.push(node.value);

        if(node.left) {
            this._depthFirstSearchPreorderHelper(node.left, list);
        }

        if(node.right) {
            this._depthFirstSearchPreorderHelper(node.right, list);
        }

        return;
    }

    depthFirstSearchPostorder()
    {
        const rtn = [];
        this._depthFirstSearchPostorderHelper(this.root, rtn);
        return rtn;
    }

    _depthFirstSearchPostorderHelper(node, list)
    {
        if(node.left) {
            this._depthFirstSearchPostorderHelper(node.left, list);
        }

        if(node.right) {
            this._depthFirstSearchPostorderHelper(node.right, list);
        }

        list.push(node.value);

        return;
    }

	remove(value) {
		if(!this.root) {
			return false;
		}

		let currentNode = this.root;
		let parentNode = null;

		while(currentNode) {
			if(value < currentNode.value) {
				parentNode = currentNode;
				currentNode = currentNode.left;
			} else if(value > currentNode.value) {
				parentNode = currentNode;
				currentNode = currentNode.right;
			} else if(value === currentNode.value) { // match means we found the node we want to delete

				// scenario 1: no right child
				if(currentNode.right === null) {
					if(parentNode === null) {
						this.root = currentNode.left;
					} else {
						/**
						 * exampmle
						 *         8 (parent node)
						 *        /
						 *       7 (current node)
						 *      /
						 *     6
						 */
						if(currentNode.value < parentNode.value) {
							parentNode.left = currentNode.left;
						/**
						 * exampmle
						 *     8 (parent node)
						 *      \
						 *       10 (current node)
						 *      /
						 *     9
						 */
						} else if (currentNode.value > parentNode.value) {
							parentNode.right = currentNode.left;
						}
					}
				/**
				 * exampmle
				 *     30 (parent node)
				 *      \
				 *       54 (current node)
				 *      /  \
				 *     38   55
				 *      \
				 *      44
				 */
				} else if(currentNode.right.left === null) {
					currentNode.right.left = currentNode.left;
					if(parentNode === null) {
						this.root = currentNode.right;
					} else {
						if(currentNode.value < parentNode.value) {
							parentNode.left = currentNode.right;
						} else if(currentNode.value > parentNode.value) {
							parentNode.right = currentNode.right;
						}
					}
				/**
				 * right child has a left child
				 *         60 (parent node)
				 *        /
				 *       30 (current node)
				 *      /  \
				 *     1   55
				 *        /
				 *      38
				 */
				} else {
					// find the right child's left most child
					let leftmost = currentNode.right.left;
					let leftmostParent = currentNode.right;
					while(leftmost.left !== null) {
						leftmostParent = leftmost;
						leftmost = leftmost.left;
					}

					// parent's left subtree is now leftmost's right subtree
					leftmostParent.left = leftmost.right;
					leftmost.left = currentNode.left;
					leftmost.right = currentNode.right;

					if(parentNode === null) {
						this.root = leftmost;
					} else {
						if(currentNode.value < parentNode.value) {
							parentNode.left = leftmost;
						} else if(currentNode.value > parentNode.value) {
							parentNode.right = leftmost;
						}
					}
				}
				return true;
			}
		}
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

// breadth first traversal
console.log('breadth first traversal by iterative: ', tree.breadthFirstTraversalByIterative());
console.log('breadth first traversal by recursive: ', tree.breadthFirstTraversalByRecursive());

// depth first traversal
// inorder: [1, 4, 6, 9, 15, 20, 170]
console.log('depth first traversal by inorder: ', tree.depthFirstSearchInorder());
// preorder: [9, 4, 1, 6, 20, 15, 170]
console.log('depth first traversal by preorder: ', tree.depthFirstSearchPreorder());
// postorder: [1, 6, 4, 15, 170, 20, 9]
console.log('depth first traversal by postorder: ', tree.depthFirstSearchPostorder());

// expected tree structure
//        9
//    4      20
//  1   6  15  170
console.log(JSON.stringify(traverse(tree.root), null, '    '));
tree.remove(9);
console.log('-----------------------------------------------');
console.log(JSON.stringify(traverse(tree.root), null, '    '));

// ======== level3 node
// console.log(tree.lookup(1));
// console.log(tree.lookupByRecursive(1));
// console.log(tree.lookupByRecursive(6));
// console.log(tree.lookupByRecursive(15));
// console.log(tree.lookupByRecursive(170));
// ======== level2 node
// console.log(tree.lookupByRecursive(4));
// console.log(tree.lookupByRecursive(20));
// ======== level1 node
// console.log(tree.lookup(9));
// console.log(tree.lookupByRecursive(9));
// ======== non-exist node
// console.log(tree.lookup(5566));
// console.log(tree.lookupByRecursive(5566));

function traverse(node) {
	const tree = {value : node.value};
	tree.left = node.left === null ? null : traverse(node.left);
	tree.right = node.right === null ? null : traverse(node.right);
	return tree;
}
