package models;

public class Node {

	private int information;
	private Node left;
	private Node right;
	
	public Node(int information) {
		this.information = information;
	}
	
	public int getInformation() {
		return information;
	}
	
	public void setInformation(int information) {
		this.information = information;
	}
	
	public Node getLeft() {
		return left;
	}
	
	public void setLeft(Node left) {
		this.left = left;
	}
	
	public Node getRight() {
		return right;
	}
	
	public void setRight(Node right) {
		this.right = right;
	}
	
	@Override
	public String toString() {
		return String.valueOf(information);
	}
}