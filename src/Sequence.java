
public class Sequence {
	private long code;
	private int len;

	public Sequence(String seq) throws SequenceException {
		if (seq.length() > 31){
			throw new SequenceException("Sequence Length > 31: "+seq);
		}
		if (seq.length() == 0){
			throw new SequenceException("Empty Sequence: "+seq);
		}
		this.len = seq.length();
		encode(seq);
	}
	
	private void encode(String seq) throws SequenceException{
		for (char c : seq.toUpperCase().toCharArray()){
			code = code<<2;
			if (c == 'A'){
				code = code ^ 0;
			}
			else if (c == 'T'){
				code = code ^ 3;
			}
			else if (c == 'G'){
				code = code ^ 2;
			}
			else if (c == 'C'){
				code = code ^ 1;
			}
			else {
				throw new SequenceException("Illegal Sequence Character: "+c+" Sequence: "+seq); 
			}
		}
		
	}
	
	private String decode(){
		long decode = this.code;
		long mask = 3;
		String seq = "";
		for (int i = 1; i <= len; i++){
			if ((decode & mask) == 0){
				seq = "A"+seq;
				decode = decode>>2;
			}
			else if ((decode & mask) == 3){
				seq = "T"+seq;
				decode = decode>>2;
			}
			else if ((decode & mask) == 1){
				seq = "C"+seq;
				decode = decode>>2;
			}
			else if ((decode & mask) == 2){
				seq = "G"+seq;
				decode = decode>>2;
			}
		}
		return seq;
	}
	
	public long getLong(){
		return code;
	}
	
	public int getLength(){
		return len;
	}
	
	public String toString(){
		return decode();
	}
	
	
}
