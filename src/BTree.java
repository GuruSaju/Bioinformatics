
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

