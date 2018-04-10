package models;

public class Tree {

	private Node root;

	public void add(Node node) {
		if (root != null) {
			add(root, node);
		}else {
			root = node;
		}
	}

	private void add(Node base, Node node) {
		if (node.getInformation() < base.getInformation()) {
			if (base.getLeft() != null) {
				add(base.getLeft(), node);
			}else {
				base.setLeft(node);
			}
		}else {
			if (base.getRight() != null) {
				add(base.getRight(), node);
			}else {
				base.setRight(node);
			}
		}
	}

	public void delete(int info) {
		delete(null, root, info);
	}

	public void delete(Node father, Node actual, int info) {
		if (actual.getInformation() == info) {
			if (isComplete(actual)) {
				deleteComplete(actual);
			}else if (hasOneChildren(actual)) {
				deleteOneChild(father, actual);
			}else {
				deleteLeaf(father, actual);
			}
		}else {
			if (info < actual.getInformation()) {
				delete(actual, actual.getLeft(), info);
			}else {
				delete(actual, actual.getRight(), info);
			}
		}
	}

	private void deleteComplete(Node actual) {
		Node maxLeft = getMaxNode(actual.getLeft());
		Node minRight = getMinNode(actual.getRight());
		int data = (Math.abs(maxLeft.getInformation() - actual.getInformation()) 
				< Math.abs(minRight.getInformation() - actual.getInformation())) ?
						maxLeft.getInformation(): minRight.getInformation();
						delete(data);
						actual.setInformation(data);
	}

	public Node getMinNode(Node base) {
		Node actual = base;
		while (actual.getLeft() != null) {
			actual = actual.getLeft();
		}
		return actual;
	}

	public Node getMaxNode(Node base) {
		Node actual = base;
		while (actual.getRight() != null) {
			actual = actual.getRight();
		}
		return actual;
	}

	private void deleteOneChild(Node father, Node actual) {
		if (actual == root) {
			root = getOneChild(actual);
		}else if (father.getLeft().equals(actual)) {
			father.setLeft(getOneChild(actual));
		}else {
			father.setRight(getOneChild(actual));
		}
	}

	private void deleteLeaf(Node father, Node actual) {
		if (father == null) {
			root = null;
		}else if (father.getLeft() != null && father.getLeft().equals(actual)) {
			father.setLeft(null);
		}else {
			father.setRight(null);
		}
	}

	private Node getOneChild(Node actual) {
		return actual.getLeft() != null ? actual.getLeft() : actual.getRight();
	}

	private boolean hasOneChildren(Node actual) {
		return actual.getLeft() != null || actual.getRight() != null;
	}

	private boolean isComplete(Node actual) {
		return actual.getLeft() != null && actual.getRight() != null;
	}

	public void print() {
		System.out.println("------------------");
		print(root);
	}

	private void print(Node node) {
		if(node != null) {
			System.out.println(node.getInformation());
			print(node.getLeft());
			print(node.getRight());
		}
	}

	public Node getRoot() {
		return root;
	}

	public int treeHeight(Node actual) {
		if (actual != null) {
			if (actual.getRight() == null && actual.getLeft() == null) {
				return 1;
			}else {
				int heightLeft = treeHeight(actual.getLeft());
				int heightRight = treeHeight(actual.getRight());
				if (actual != root) {
					return heightLeft + heightRight + 1;
				}else {
					return heightLeft - heightRight;
				}
			}
		}else {
			return 1;
		}
	}

	public void getTreeHeight(int height) {
		if (height != 0 && height != -1 && height != 1) {
			if (root.getLeft() != null && root.getLeft().getLeft() != null) {
				balancedToLeft();
			}else if(root.getLeft() != null && root.getLeft().getRight() != null) {
				balancedToLeftRight();
			}else if(root.getRight() != null && root.getRight().getRight() != null) {
				balancedToRight();
			}else if(root.getRight() != null && root.getRight().getLeft() != null){
				balancedToRightLeft();
			}
		}
	}

	private void balancedToRightLeft() {
		Node rightNode = root.getRight();
		Node auxRightLeft = root.getRight().getLeft();
		root.setRight(auxRightLeft);
		root.getRight().setRight(rightNode);
		rightNode.setLeft(null);
		balancedToRight();
	}

	private void balancedToRight() {
		Node actualRoot = root;
		root = root.getRight();
		root.setLeft(actualRoot);
		actualRoot.setRight(null);
	}

	private void balancedToLeftRight() {
		Node leftNode = root.getLeft();
		Node auxLeftRight = root.getLeft().getRight();
		root.setLeft(auxLeftRight);
		root.getLeft().setLeft(leftNode);
		leftNode.setRight(null);
		balancedToLeft();
	}

	private void balancedToLeft() {
		Node actualRoot = root;
		root = root.getLeft();
		root.setRight(actualRoot);
		actualRoot.setLeft(null);
	}
}