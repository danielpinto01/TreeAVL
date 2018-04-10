package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import models.Node;
import models.Tree;
import views.WindowTree;

public class Controller implements ActionListener{

	private Tree tree;
	private WindowTree window;

	public Controller() {
		tree = new Tree();
				tree.add(new Node(8));
				tree.add(new Node(10));
				tree.add(new Node(9));
		//		tree.add(new Node(2));
		//		tree.add(new Node(4));
		//		tree.add(new Node(8));
		//		tree.add(new Node(9));
		//		tree.add(new Node(1));
				
		window = new WindowTree(this);
		window.paintTree(tree.getRoot());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("ADD")) {
			tree.add(new Node(Integer.valueOf(JOptionPane.showInputDialog("ID"))));			
		}else if(e.getActionCommand().equals("BALANCED")) {
			int height = tree.treeHeight(tree.getRoot());
			JOptionPane.showMessageDialog(window, "balanceo inicial: " + height);
			tree.getTreeHeight(height);
			JOptionPane.showMessageDialog(window, "balanceo final: " + tree.treeHeight(tree.getRoot()));
		}else{
			tree.delete(Integer.parseInt(JOptionPane.showInputDialog("Información del nodo a borrar")));
		}
		window.paintTree(tree.getRoot());
	}

	public static void main(String[] args) {
		new Controller();
	}
}