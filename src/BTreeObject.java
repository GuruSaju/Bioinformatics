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
