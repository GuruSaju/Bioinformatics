import java.util.ArrayList;
import java.util.Random;

public class SequenceTest {
	private int success;
	private int failure;
	private int total;
	private ArrayList<String> failMessages;
	
	public static void main(String[] args) {
		SequenceTest t = new SequenceTest();
		if (args.length==0){
			t.runTests(10000);
		}else{
			t.runTests(Integer.parseInt(args[0]));
		}
	}

	private void runTests(int tests) {
		failMessages = new ArrayList<String>();		Sequence sequence = null;
		success = 0;
		failure = 0;
		total = 0;
		try {
			total++;
			sequence = new Sequence("");
			System.err.println("\nNo error thrown for empty sequence!");
			failure++;
		} catch (SequenceException e) {
			System.out.println("Passed empty sequence test");
			success++;
		}
		try {
			total++;
			sequence = new Sequence("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
			System.err.println("\nNo error thrown for length 32 sequence!");
			failure++;
		} catch (SequenceException e) {
			System.out.println("Passed long sequence test");
			success++;
		}
		try {
			total++;
			sequence = new Sequence("GATCB");
			System.err.println("\nNo error thrown for illegal character!");
			failure++;
		} catch (SequenceException e) {
			System.out.println("Passed illegal character test");
			success++;
		}
		sequence = null;

		String[] base = { "A", "T", "G", "C" };
		Random rand = new Random(1337);
		for (int i = 1; i < 32; i++) {
			String[] seq = new String[i];
			for (int j = 0; j < tests; j++) {
				for (int ind = 0; ind < i; ind++) {
					seq[ind] = base[rand.nextInt(4)];
				}
				String str = "";
				for (String s : seq) {
					str = str + s;
				}
				test(str);
			}
		}
		//Exhaustive test would take days to complete. 
		// for (String s : base){
		// for(String s2 : base){
		// for(String s3 : base){
		// for(String s4 : base){
		// for(String s5 : base){
		// for(String s6 : base){
		// for(String s7 : base){
		// for(String s8 : base){
		// for(String s9 : base){
		// for(String s10 : base){
		// for(String s11 : base){
		// for(String s12 : base){
		// for(String s13 : base){
		// for(String s14 : base){
		// for(String s15 : base){
		// for(String s16 : base){
		// for(String s17 : base){
		// for(String s18 : base){
		// for(String s19 : base){
		// for(String s20 : base){
		// for(String s21 : base){
		// for(String s22 : base){
		// for(String s23 : base){
		// for(String s24 : base){
		// for(String s25 : base){
		// for(String s26 : base){
		// for(String s27 : base){
		// for(String s28 : base){
		// for(String s29 : base){
		// for(String s30 : base){
		// for(String s31 : base){
		// test(s31+s29+s27+s25+s23+s21+s19+s17+s15+s13+s11+s9+s7+s5+s3+s+s2+s4+s6+s8+s10+s12+s14+s16+s18+s20+s22+s24+s26+s28+s30);
		// }
		// }
		// }
		// }
		// }
		// }
		// }
		// }
		// }
		// }
		// }
		// }
		// }
		// }
		// }
		// }
		// }
		// }
		// }
		// }
		// }
		// }
		// }
		// }
		// }
		// }
		// }
		// }
		// }
		// }
		// }
		System.out.println("Total: " + total);
		System.out.println("Success: " + success);
		System.out.println("Failure: " + failure);
		for(String m : failMessages){
			System.out.println(m);
		}
	}

	private void test(String seq) {
		total++;
		try {
			Sequence s = new Sequence(seq);
			if (s.toString().equals(seq)) {
				 System.out.println("Passed: " + seq);
				success++;
			} else {
				String message = "\nFAILED: " + seq + " Returned: "+ s.toString();
				System.err.println(message);
				failMessages.add(message);
				failure++;
			}
		} catch (SequenceException e) {
			e.printStackTrace();
		}
	}
}
