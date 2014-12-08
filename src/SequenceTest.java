import java.util.ArrayList;
import java.util.Random;

public class SequenceTest {
	private int success;
	private int failure;
	private int total;
	private ArrayList<String> failMessages;

	public static void main(String[] args) {
		SequenceTest t = new SequenceTest();
		if (args.length == 0) {
			t.runTests(10000);
		} else {
			t.runTests(Integer.parseInt(args[0]));
		}
	}

	private void runTests(int tests) {
		failMessages = new ArrayList<String>();
		@SuppressWarnings("unused")
		Sequence sequence = null;
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
		System.out.println("Running test battery...");
		String[] base = { "A", "T", "G", "C" };
		Random rand = new Random(1337);
		for (int i = 1; i < 32; i++) {
			String[] seq1 = new String[i];
			String[] seq2 = new String[i];
			for (int j = 0; j < tests; j++) {
				for (int ind = 0; ind < i; ind++) {
					seq1[ind] = base[rand.nextInt(4)];
				}
				String str1 = "";
				for (String s : seq1) {
					str1 = str1 + s;
				}
				for (int ind = 0; ind < i; ind++) {
					seq2[ind] = base[rand.nextInt(4)];
				}
				String str2 = "";
				for (String s : seq2) {
					str2 = str2 + s;
				}
				test(str1);
				test(str2);
				test(str1, str2);
				test(str2, str1);
				test(str1, str1);
				test(str2, str2);
			}
		}

		System.out.println("Total: " + total);
		System.out.println("Success: " + success);
		System.out.println("Failure: " + failure);
		for (String m : failMessages) {
			System.out.println(m);
		}
	}

	private void test(String str) {
		total++;
		try {
			Sequence seq = new Sequence(str);
			if (seq.toString().equals(str)) {
				// System.out.println("Passed: " + str);
				success++;
			} else {
				String message = "\nFAILED: " + str + " Returned: "
						+ seq.toString();
				System.err.println(message);
				failMessages.add(message);
				failure++;
			}
		} catch (SequenceException e) {
			String message = "\nFAILED: " + str + "-- ERROR: " + e.getMessage();
			System.err.println(message);
			failMessages.add(message);
			failure++;
		}
	}

	private void test(String str1, String str2) {
		total++;
		try {
			Sequence seq1 = new Sequence(str1);
			Sequence seq2 = new Sequence(str2);
			if (!(seq1.toString().equals(str1))
					|| !(seq2.toString().equals(str2))) {
				String message = "\nFAILED: " + str1 + " Returned: "
						+ seq1.toString();
				System.err.println(message);
				failMessages.add(message);
				failure++;
				return;
			}
			if (!(seq1.compareTo(seq2) == str1.compareTo(str2))) {
				String message = "\nFAILED: " + str1 + " compareTo " + str2
						+ " Returned: " + seq1.compareTo(seq2) + " Expected: "
						+ str1.compareTo(str2);
				System.err.println(message);
				failMessages.add(message);
				failure++;
				return;
			} else {
				success++;
			}
		} catch (SequenceException e) {
			String message = "\nFAILED: " + str1 + " and " + str2
					+ "-- ERROR: " + e.getMessage();
			System.err.println(message);
			failMessages.add(message);
			failure++;
		}
	}

}
