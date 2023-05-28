// PRACTICE WITH SEARCHING TREES

public class mySearchTree<T extends Comparable<T>> {
	
	// data fields
	private Node<T> root;
	
	// get the root
	public Node<T> getRoot() {
		return this.root;
	}
	
	// set the root
	public void setRoot(Node<T> newRoot) {
		this.root = newRoot;
	}
	
	// add new node to BST
	public Node<T> add(Node<T> node, T value) {
		// create new root if empty
		if (node == null) {
			node = new Node<T>(value);
			return node;
		}
		
		// finding proper place for element
		if (value.compareTo(node.element) < 0)
			node.left = add(node.left, value);
		else if (value.compareTo(node.element) > 0)
			node.right = add(node.right, value);
		
		return node;
	}
	
	// find passed in key in BST
	public void find(Node<T> currNode,T key) {
		// if key is never found print out not found
		if (currNode == null) {
			System.out.println("Key was not found.");
			return;
		}
		// key was found
		else if (currNode.element.compareTo(key) == 0) {
			System.out.println("Key was found.");
			return;
		}
		
		// search for key based on if the key is less than or greater than current node
		if (currNode.element.compareTo(key) > 0)
			find(currNode.left, key);
		else
			find(currNode.right, key);
	}
	
	// counts the number of leafs in BST
	public int leafCount(Node<T> currNode) {
		// find the end of the BST
		if (currNode == null)
			return 0;
		// returns 1 each time a leaf node is found
		if (currNode.left == null && currNode.right == null)
			return 1;
		else
			// return left and right node count
			return leafCount(currNode.left) + leafCount(currNode.right); 
	}
	
	// counts number of parents in BST
	public int parentCount(Node<T> currNode){
		int count = 0;
		// runs through each node and checks if they have children
		if (currNode.left != null || currNode.right != null) {
			count = 1;
			if (currNode.left != null)
				count += parentCount(currNode.left);
			if(currNode.right != null)
				count+= parentCount(currNode.right);
		}
		
		return count;
	}
	
	// find height of the BST
	public int height(Node<T> currNode) {
		// returns if tree is empty or end of BST is found
		if (currNode == null)
			return 0;
		else {
			// recursively finds height of right and left branches
			int rightHeight = height(currNode.right);
			int leftHeight = height(currNode.left);
			
			// compares heights and returns larger one
			if (rightHeight >= leftHeight)
				return rightHeight + 1;
			else
				return leftHeight + 1;
		}
	}
	
	// test to see if BST is perfect
	public boolean isPerfect(Node<T> currNode, int height, int currLevel) {
		// returns if tree is empty or end of BST
		if (currNode == null)
			return true;
		// checks that each right and left branch have equal height and at least two children if not leaf nodes
		if (currNode.left == null && currNode.right == null)
			return (height == currLevel);
		if (currNode.left == null || currNode.right == null)
			return false;
		
		// returns boolean of right and left truth values
		return isPerfect(currNode.left, height, currLevel + 1) && isPerfect(currNode.right, height, currLevel + 1);
	}
	
	// prints out the ancestors of the passed in node
	public boolean ancestors(Node<T> currNode, T value) {
		// return if tree is empty or end of BST is found
		if (currNode == null)
			return false;
		// finding the element
		if (currNode.element.compareTo(value) == 0 && currNode != this.root)
			return true;
		
		// printing out all the nodes that were found before
		if (ancestors(currNode.left, value) || ancestors(currNode.right, value)) {
			System.out.print(currNode.element + " ");
			return true;
		}
		
		return false;
	}
	
	// prints the BST in an in order traversal
	public void inOrderPrint(Node<T> currNode) {
		if (currNode == null)
			return;
		
		inOrderPrint(currNode.left);
		System.out.print(currNode.element + " ");
		inOrderPrint(currNode.right);
	}
	
	// prints the BST in a pre-order traversal
	public void preOrderPrint(Node<T> currNode) {
		if (currNode == null)
			return;
		
		System.out.print(currNode.element + " ");
		inOrderPrint(currNode.left);
		inOrderPrint(currNode.right);
	}
	
	// main method
	@SuppressWarnings("removal")
	public static void main(String[]args) {
		int height = 0;
		
		// INTEGER TYPE BINARY SEARCH TREE
		
		// creating instance of BST with integer nodes
		mySearchTree<Integer> tree1 = new mySearchTree<>();
		
		// adding nodes
		tree1.setRoot(tree1.add(tree1.getRoot(), new Integer(4)));
		tree1.add(tree1.getRoot(), new Integer(2));
		tree1.add(tree1.getRoot(), new Integer(3));
		tree1.add(tree1.getRoot(), new Integer(0));
		tree1.add(tree1.getRoot(), new Integer(8));
		tree1.add(tree1.getRoot(), new Integer(6));
		tree1.add(tree1.getRoot(), new Integer(10));
		
		// in order traversal
		System.out.print("In Order Traversal: ");
		tree1.inOrderPrint(tree1.getRoot());
		System.out.println();
		
		// pre-order traversal
		System.out.print("Pre-Order Traversal: ");
		tree1.preOrderPrint(tree1.getRoot());
		System.out.println();
		
		// testing find function
		tree1.find(tree1.getRoot(), new Integer(6));
		tree1.find(tree1.getRoot(), new Integer(15));
		
		// printing out number of leaves
		System.out.println("Number of Leaves: " + tree1.leafCount(tree1.getRoot()));
		
		// printing out number of parents
		System.out.println("Number of Parents: " + tree1.parentCount(tree1.getRoot()));
		
		// find the height of the BST
		height = tree1.height(tree1.getRoot());
		System.out.println("Height of tree: " + height);
		
		// testing if the BST is perfect
		if (tree1.isPerfect(tree1.getRoot(), height, 1))	
			System.out.println("The tree is perfect.");
		else
			System.out.println("The tree is not perfect.");
		
		// add a new node to unbalance the tree
		tree1.add(tree1.getRoot(), new Integer(20));
		
		// test that the BST is not perfect
		if (tree1.isPerfect(tree1.getRoot(), height, 1))	
			System.out.println("The tree is perfect.");
		else
			System.out.println("The tree is not perfect.");
		
		// printing out the ancestors from the leaf level
		System.out.print("Ancestors: ");
		if (!tree1.ancestors(tree1.getRoot(), new Integer(20)))
				System.out.print("none");
		System.out.println();
		
		// printing out the ancestors from the root level
		System.out.print("Ancestors: ");
		if (!tree1.ancestors(tree1.getRoot(), new Integer(4)))
				System.out.print("none");
		System.out.println("\n");
		
		
		// STRING TYPE BINARY SEARCH TREE
		
		// creating instance of BST with String nodes
		mySearchTree<String> tree2 = new mySearchTree<>();
		
		// adding nodes
		tree2.setRoot(tree2.add(tree2.getRoot(), new String("c")));
		tree2.add(tree2.getRoot(), new String("a"));
		tree2.add(tree2.getRoot(), new String("b"));
		tree2.add(tree2.getRoot(), new String("d"));
		tree2.add(tree2.getRoot(), new String("e"));
		tree2.add(tree2.getRoot(), new String("f"));
		tree2.add(tree2.getRoot(), new String("g"));
		
		// in order traversal
		System.out.print("In Order Traversal: ");
		tree2.inOrderPrint(tree2.getRoot());
		System.out.println();
		
		// pre-order traversal
		System.out.print("Pre-Order Traversal: ");
		tree2.preOrderPrint(tree2.getRoot());
		System.out.println();
		
		// find the height of the BST
		tree2.find(tree2.getRoot(), new String("e"));
		tree2.find(tree2.getRoot(), new String("z"));
		
		// printing out number of leaves
		System.out.println("Number of Leaves: " + tree2.leafCount(tree2.getRoot()));
		
		// printing our number of parents
		System.out.println("Number of Parents: " + tree2.parentCount(tree2.getRoot()));
		
		// find the height of the BST
		height = tree2.height(tree2.getRoot());
		System.out.println("Height of tree: " + height);
		
		// testing if the BST is perfect
		if (tree2.isPerfect(tree2.getRoot(), height, 1))	
			System.out.println("The tree is perfect.");
		else
			System.out.println("The tree is not perfect.");
		
		// add a new node to unbalance the tree
		tree2.add(tree2.getRoot(), new String("y"));
		
		// test that the BST is not perfect
		if (tree2.isPerfect(tree2.getRoot(), height, 1))	
			System.out.println("The tree is perfect.");
		else
			System.out.println("The tree is not perfect.");
		
		// printing out the ancestors from the leaf level
		System.out.print("Ancestors: ");
		if (!tree2.ancestors(tree2.getRoot(), new String("y")))
				System.out.print("none");
		System.out.println();
		
		// printing out the ancestors from the root level
		System.out.print("Ancestors: ");
		if (!tree2.ancestors(tree2.getRoot(), new String("z")))
				System.out.print("none");
		System.out.println();
	}
}

// Node class defines a tree node
class Node<T> {
	// data fields
	T element;
	Node<T> right;
	Node<T> left;
	
	// overloaded constructor
	public Node(T value) {
		this.element = value;
		this.right = null;
		this.left = null;
	}
}
