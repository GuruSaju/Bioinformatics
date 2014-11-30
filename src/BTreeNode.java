
public class BTreeNode<T extends Comparable<T>> {
	public BTreeObject[] elements;
	public BTreeNode<T>[] children;
	public boolean leaf;
	
	
	public BTreeNode(int degree){
		this.elements=new BTreeObject[2*degree-1];
		this.children= new BTreeNode[2*degree];
		this.leaf=true;
	}
	
	public void setChild(int index, BTreeNode<T> node) {
		children[index] = node;
	}

	public BTreeNode<T> getChild(int index) {
		BTreeNode<T> bTreeNode  = (BTreeNode<T>) children[index];
		return bTreeNode ;
		}

}
