<<<<<<< HEAD
public class BTreeObject<T extends Comparable<T>> {
	public T longkey;
	public int frequency;

	public BTreeObject(T longkey) {
		this.longkey = longkey;
		this.frequency = 0;
	}

	public int getFreq() {
		return frequency;
	}

	public void incrementFreq() {
		frequency++;
	}

	public T getKey() {
		return longkey;
	}

	// public int compareTo(BTreeObject longkey) {
	// if (this.longkey > longkey.getKey()){
	// return 1;
	// }
	// if (this.longkey < longkey.getKey()){
	// return -1;
	// }
	// return 0;
	// }

}
=======

public class BTreeObject {
	public long longkey;
	public int frequency;
	
	
	
	public BTreeObject(long longkey) {
		this.longkey = longkey;
		this.frequency = 0;
	}
	
	
	public int getFreq() {
		return frequency;
	}
	
	
	public void incrementFreq() {
		frequency++;
	}
	
	
	public long getKey() {
		return longkey;
	}
	
	
	
	
	
	public int compareTo(BTreeObject longkey) {
		if (this.longkey > longkey.getKey()){
			return 1;
		}
		if (this.longkey < longkey.getKey()){
			return -1;
		}
		return 0;
	}
	
	
}
>>>>>>> 2086c5a3692c4b11a437a5aec6741719a2bb7bb9
