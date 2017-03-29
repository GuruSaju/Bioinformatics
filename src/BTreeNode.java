<<<<<<< HEAD
public class BTreeNode<T extends Comparable<T>> {
	public BTreeObject[] elements;
	public BTreeNode<T>[] children;
	public boolean leaf;
	// public int size;
	int degree;
	int noOfElements;

	public BTreeNode(int degree) {
		this.degree = degree;
		this.elements = new BTreeObject[2 * degree - 1];
		this.children = new BTreeNode[2 * degree];
		this.leaf = true;
	}

	public void setChild(int index, BTreeNode<T> node) {
		children[index] = node;
	}

	public BTreeNode<T> getChild(int index) {
		BTreeNode<T> bTreeNode = (BTreeNode<T>) children[index];
		return bTreeNode;
	}

	public BTreeObject getElement(int index) {
		return this.elements[index];
	}

	public boolean leaf() {
		return leaf;
	}

	public void leaf(boolean leaf) {
		this.leaf = leaf;
	}

	public int contains() {
		return noOfElements;
	}

	public void contains(int n) {
		this.noOfElements = n;
	}

	// public void sizeset(int newsize){
	// this.size=newsize;
	//
	// }
	// public int getSize(){
	// return this.size;
	// }
	public boolean isFull() {
		return this.noOfElements == (2 * degree) - 1;
	}

	public BTreeObject<T> getKey(int index) {
		return (BTreeObject<T>) elements[index];
	}

	public BTreeObject<T> removeKey(int index) {
		BTreeObject<T> treeobject = (BTreeObject<T>) elements[index];
		elements[index] = null;
		return treeobject;
	}

	public void setKey(int index, BTreeObject<T> obj) {
		elements[index] = obj;
	}

	public BTreeNode<T> removeChild(int index) {
		BTreeNode<T> childnode = (BTreeNode<T>) children[index];
		children[index] = null;
		return childnode;
	}

	public BTreeObject<T> search(T key) {

		int i = this.noOfElements - 1;
		while (i >= 0 && key.compareTo(this.getKey(i).getKey()) < 0) {
			i--;
		}
		if (i >= 0 && key.compareTo(this.getKey(i).getKey()) == 0) {
			return this.getKey(i);

		} else if (!this.leaf()) {
			return this.getChild(i + 1).search(key);
		}
		return null;
	}

	public void splitChild(int index) {
		BTreeNode<T> newnode = new BTreeNode<T>(degree);
		BTreeNode<T> childnode = getChild(index);

		newnode.leaf(childnode.leaf());
		newnode.contains(degree - 1);

		for (int i = 0; i <= degree - 2; i++) {
			newnode.setKey(i, childnode.removeKey(i + degree));
		}

		if (!childnode.leaf()) {
			for (int i = 0; i <= degree - 1; i++) {
				newnode.setChild(i, childnode.removeChild(i + degree));
			}
		}

		childnode.contains(degree - 1);

		for (int i = this.noOfElements; i >= index + 1; i--) {
			this.setChild(i + 1, this.removeChild(i));
		}
		this.setChild(index + 1, newnode);

		for (int i = this.contains() - 1; i >= index; i--) {
			this.setKey(i + 1, this.removeKey(i));
		}
		this.setKey(index, childnode.removeKey(degree - 1));
		this.noOfElements = this.noOfElements + 1;

	}

	public void insert(T key) {
		int i = this.noOfElements - 1;
		if (this.leaf()) {
			while (i >= 0 && (this.getKey(i) != null)
					&& key.compareTo(this.getKey(i).getKey()) < 0) {
				this.setKey(i + 1, this.removeKey(i));
				i--;
			}

			this.setKey(i + 1, new BTreeObject<T>(key));
			this.noOfElements += 1;

		} else {
			while (i >= 0 && key.compareTo(this.getKey(i).getKey()) < 0) {
				i--;
			}
			i++;

			if (this.getChild(i).isFull()) {
				this.splitChild(i);
				if (key.compareTo(this.getKey(i).getKey()) > 0) {
					i++;
				}
			}
			this.getChild(i).insert(key);
		}
	}
}
=======

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
>>>>>>> 2086c5a3692c4b11a437a5aec6741719a2bb7bb9
