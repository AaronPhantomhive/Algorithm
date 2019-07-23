/**
 * A binary tree implementation. When adding values to
 * a tree, there is no assumed "correct" location for that value.
 * So, we will give the root package access, so that we can manually
 * build our trees from a tester class.
 * 
 * @author Norm Krumpe
 *
 */

public class BinaryTree {
	
	Node root;
		
	/**
	 * Returns the height of this tree
	 * @return
	 */
	public int getHeight() {
		return getHeight(root);
	}
	
	private int getHeight(Node root){
		if(root == null)
			return 0;
		return 1 + Math.max(getHeight(root.left),getHeight(root.right));//?
	}
	
	public int getNumberOfNodes() {
		return getNumberOfNodes(root);
	}
	
	private int getNumberOfNodes(Node root) {
		if(root == null)
			return 0;
		return 1 + getNumberOfNodes(root.left) + getNumberOfNodes(root.right);
	}
	
	public int getNumberOfLeaves() {
		return getNumberOfLeaves(root);
	}
	
	private int getNumberOfLeaves(Node root) {
		if(root == null)
			return 0;
		if(root.left == null && root.right == null)
			return 1;
		return getNumberOfLeaves(root.left) + getNumberOfLeaves(root.right);
	}
	
	/**
	 * Prints the preorder traversal of this tree
	 */
	public void preorderTraversal() {
		preorderTraversal(root);
	}
	
	private void preorderTraversal(Node root) {
		if (root == null) 
			return;

		System.out.print(root.data + " ");
		preorderTraversal(root.left);
		preorderTraversal(root.right);
	}
	
	/**
	 * Prints the inorder traversal of this tree
	 */
	public void inorderTraversal() {
		inorderTraversal(root);
	}
	
	private void inorderTraversal(Node root) {
		if (root == null) 
			return;
		
		inorderTraversal(root.left);
		System.out.print(root.data + " ");
		inorderTraversal(root.right);
	}
	
	/**
	 * Prints the postorder traversal of this tree
	 */
	public void postorderTraversal() {
		postorderTraversal(root);
	}
	
	private void postorderTraversal(Node root) {
		if (root == null) 
			return;
		
		postorderTraversal(root.left);
		postorderTraversal(root.right);
		System.out.print(root.data + " ");
	}
	
	public boolean isFull() {
		//For a full tree, the number of node is 2 ^ height -1
		return getNumberOfNodes() == Math.pow(2, getHeight()) - 1;
	}
	
	public boolean contains(int value) {
		return contains(root, value);
	}
	
	private boolean contains(Node root, int value) {
		if(root == null)
			return false;
		if (root.data == value)
			return true;
		return contains(root.left, value) || contains(root.right, value);
	}
	
	public int getMin() {
		return getMin(root);
	}
	
	
	private int getMin(Node root) {
		if(root == null)
			return Integer.MAX_VALUE;
		return Math.min(root.data, Math.min(getMin(root.left), getMin(root.right)));
	}

}
