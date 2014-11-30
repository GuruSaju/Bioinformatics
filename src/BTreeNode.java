
public class BTreeNode<T extends Comparable<T>> {
	public BTreeObject[] elements;
	public BTreeNode<T>[] children;
	public boolean leaf;
	public int size;
	
	
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
	public BTreeObject getElement(int index){
		return this.elements[index];
	}
    public boolean leaf(){
    	return leaf;
    }
    public void sizeset(int newsize){
    	this.size=newsize;
    	
    }
    public int getSize(){
    	return this.size;
    }
}
