<<<<<<< HEAD
public class BTree<T extends Comparable<T>> {
	// three methods
	// search
	// insert
	// splitchild-splits child node and pushes an element to the parent node

	public int degree;
	BTreeNode root;

	public BTree(int degree) {
		this.degree = degree;
		this.root = new BTreeNode(degree);
	}

	// public T search(BTreeNode root,T element){
	// int i=0;
	// while( i< root.getSize()&& element.compareTo((T)
	// root.getKey(i).getKey())>0) {
	// i++;
	// }
	// if (i<root.getSize()&& element.compareTo((T)
	// root.getKey(i).getKey())==0){
	//
	//
	// return this.get;
	// }
	// else{
	// search(root.getChild(i),element);
	// }
	// return null;
	//
	// }
	public T search(T key) {// get frequency..

		BTreeObject<T> currentobject = findKeyObject(key);

		if (currentobject != null) {
			return currentobject.getKey();
		}

		return null;
	}

	private BTreeObject<T> findKeyObject(T key) {
		return root.search(key);
	}

	public void insert(T element) {
		BTreeObject<T> currentobject = findKeyObject(element);

		if (currentobject != null) {
			currentobject.incrementFreq();
		} else {
			BTreeNode<T> currentroot = root;
			if (currentroot.isFull()) {
				BTreeNode<T> newnode = new BTreeNode<T>(degree);
				this.root = newnode;
				newnode.leaf(false);
				newnode.setChild(0, currentroot);
				newnode.splitChild(0);
				newnode.insert(element);
			} else {
				currentroot.insert(element);
			}
		}

	}

}
=======

public class BTree {
//three methods
	//search
	//insert
	//splitchild-splits child node and pushes an element to the parent node
	
	public int degree;
	BTreeNode root;
	 public BTree(int degree){
		 this.degree=degree;
		 this.root = new BTreeNode(degree);
	 }

	 public boolean search(BTreeNode root,BTreeObject element){
		int i=0;
		 while( i< root.getSize()&& element.compareTo(root.getElement(i))>0) {
			i++;
		}
		if (i<root.getSize()&& element.compareTo(root.getElement(i))==0){
			
			root.getElement(i).incrementFreq();
			return true;
		}
		else{
			search(root.getChild(i),element);
		}
		return false;
		 
	 }
     
	 public void insert(BTreeObject element){
		 BTreeNode Tmproot=root;
		 boolean there=search(Tmproot,element);
		 if(there){
			 return;
		 }
		 
		 
	 }
	

}

>>>>>>> 2086c5a3692c4b11a437a5aec6741719a2bb7bb9
