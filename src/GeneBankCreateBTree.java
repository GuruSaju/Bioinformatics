import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.sound.midi.Sequence;


public class GeneBankCreateBTree {

	/**
	 * Launch Program
	 * 
	 * @param args
	 * 
	 * 			: 4, 5, or 6 arguments are required.
	 *			-<0 (no cache) | 1 (cache)> 	-- specifies if program should use cache;
	 *			                                   if cache value is 1, <cache_value> has to be specified.
	 *			-<degree>					    -- degree used for BTree. Enter 0 for optimal degree usage.
	 *			-<gbk.file>						-- any gene bank file( *.gbk) containing input DNA sequences.
	 *			-<sequence_length>           	-- integer value between 1 and 31 (inclusive).
	 *			-[<cache-size>]			 		-- integer between 100 and 500 (inclusive).
	 *					                           only needed if 1 is entered for cache.
	 *			-[<debug_level>]			 	-- [optional] 0 (default) or 1.
	 * 
	 * 
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int cacheOption = 0;
		int degree = 0;
		int seqLength = 0;
		int cacheSize = 0;
		int debugLevel = 0;
		String gbkFileName = args[3];
		File gbkFile = new File (gbkFileName);
		BTree btree;
		Cache<Sequence> cache;
		BufferedWriter btreeFile;
		BufferedWriter dump;

		try {
			if (args.length < 5 || args.length > 7) {
				System.out.println("Incorrect command line usage.  4, 5, or 6 argments required.\n");
				printUsage();
			}

			cacheOption = Integer.parseInt(args[1]);
			degree = Integer.parseInt(args[2]);
			seqLength = Integer.parseInt(args[4]);
			btree = new BTree(degree);

			String btreeFileName = gbkFileName + "." + seqLength + "." + degree;
			btreeFile = new BufferedWriter(new FileWriter(btreeFileName));
			dump = new BufferedWriter(new FileWriter("dump"));

			if (cacheOption != 0 && cacheOption != 1) {
				System.out.println("Cache options must be either 0 or 1.\n");
				printUsage();				
			}


			if (degree == 0) {
				// calculate optimal degree using formula
			}
			else if (degree < 0) {
				System.out.println("Degree must be a positive integer value.\n");
				printUsage();				
			}
			// should we place a max value on degree??

			if (seqLength <= 1 || seqLength > 31) {
				System.out.println("Sequence length must be an integer value between 1 and 31 (inclusive)\n");
				printUsage();
			}

			if (cacheOption == 1) {
				if (args.length == 5) {
					System.out.println("Must enter a cache size when cache option is 1.\n");
					printUsage();
				}
				else {
					cacheSize = Integer.parseInt(args[5]);

					if ( cacheSize < 100 || cacheSize > 500 )	{
						System.out.println("Cache size must be an integer between 100 and 500 (inclusive)\n");
						printUsage();
					}

					if (args.length == 7) {
						debugLevel = Integer.parseInt(args[6]); 						
					}

					cache = new Cache<Sequence>(cacheSize);
				}
			}
			else {
				if (args.length == 6) {
					debugLevel = Integer.parseInt(args[5]); 		
				}
				else if (args.length == 7) {
					System.out.println("Too many arguments for when cache option is 0.\n");
					printUsage();
				}
			}

			if (debugLevel != 0 && debugLevel != 1) {
				System.out.println("Debug option must be either 0 or 1.\n");
				printUsage();				
			}			


			Scanner lineScan = new Scanner(gbkFile);

			String currentLine = "";			

			while (lineScan.hasNextLine()) {
				currentLine = lineScan.nextLine();

				while (!currentLine.contains("ORIGIN") && !currentLine.contains("//")) {
					currentLine = lineScan.nextLine();

				}				

				StringBuilder build = new StringBuilder();
				String sequence = "";

				currentLine = lineScan.nextLine();

				int linePosition = 0;
				System.out.println(currentLine);
				for ( int i = 0; build.length() < seqLength; i++) {
					char c = currentLine.charAt(i);					
					if (c == 'a' || c == 'c' || c == 'g' || c == 't' || c == 'n') {
						build.append(currentLine.charAt(i));						
					}
					linePosition++;
				}
				int startPos = linePosition;
				System.out.print(build.toString() + "\t");
				//System.out.println(linePostion);

				sequence = build.toString(); // gives the first valid sequence
				// used for testing
				int testCount = 1;
				int seqCount = 0;

				System.out.println(currentLine.length());

				while (!currentLine.contains("//")) {
					// get all the sequences
					for (int i = linePosition; i < currentLine.length(); i++) {
						char c = currentLine.charAt(i);
						if (c == 'a' || c == 'c' || c == 'g' || c == 't' || c == 'n') {
							if (i < currentLine.length()-1) {
								build.append(c); //hopefully at the end of the line it will hold onto valid chars
								build.deleteCharAt(0); //should take off the first letter of the string
								sequence = build.toString(); //this should happen only with valid dna letters

								//								if (seqCount % 5 == 0) {	
								//
								//									btreeFile.write(sequence + "\n");
								//								}
								//								else {
								//									btreeFile.write(sequence + "\t");
								//								}
								//								seqCount ++;

								if (testCount <= 800) {
									if (testCount % 10 == 9) {
										System.out.println(sequence + "," + i);
									}
									else {
										System.out.print(sequence + "," + i + "\t");
									}
									testCount ++;
								}

							}
							else if ( i == currentLine.length()-1 ) {
								build.append(c); //hopefully at the end of the line it will hold onto valid chars
								build.deleteCharAt(0); //should take off the first letter of the string
								sequence = build.toString(); //this should happen only with valid dna letters
								System.out.println(sequence + "," + i);
								testCount ++;

								currentLine = lineScan.nextLine();								

								int pos = 0;
								c = currentLine.charAt(pos);
								while (!(c == 'a' || c == 'c' || c == 'g' || c == 't' || c == 'n')) {
									if (c != '/') {
										pos++;
										c = currentLine.charAt(pos);
									}
									else return;
								}
								for ( int j = pos; j < pos + seqLength; j++) {
									c = currentLine.charAt(j);
									//System.out.println(c);
									if (c == 'a' || c == 'c' || c == 'g' || c == 't' || c == 'n') {
										build.append(currentLine.charAt(j));
										build.deleteCharAt(0); //should take off the first letter of the string
										sequence = build.toString(); //this should happen only with valid dna letters

										//										if (seqCount % 5 == 0) {	
										//
										//											btreeFile.write(sequence + "\n");
										//										}
										//										else {
										//											btreeFile.write(sequence + "\t");
										//										}
										//										seqCount ++;


										if (testCount <= 800) {	
											if (testCount % 10 == 0) {
												System.out.println(sequence + "," + j);
											}
											else {
												System.out.print(sequence + "," + j + "\t");
											}
											testCount ++;
										}										
									}	
								}
								linePosition = startPos;
							}

							//if (!sequence.contains("n")) {	



							//								try {
							//									Sequence seqContinuous = new Sequence(sequence);
							//									btree.insert(seqContinuous);
							//									cache.search(seqContinuous); // search method finds and moves 
							//																//to front or just adds to front, if full
							//																//removes last and adds to front
							//									if (debugLevel == 1) {
							//										// write to "dump" file.
							//									}
							//								} catch (SequenceException e) {
							//									// should continuously make Sequence objects with valid strings
							//									e.printStackTrace();
							//								}
							//}
						}

					}

				}

			}

			lineScan.close();
			dump.close();
			btreeFile.close();
			//			System.out.println(cacheOption);
			//			System.out.println(degree);
			//			System.out.println(gbkFileName);
			//			System.out.println(seqLength);
			//			System.out.println(cacheSize);
			//			System.out.println(debugLevel);			

		}
		catch (NumberFormatException e) {
			System.out.println("\nNumberFormatException:");
			System.out.println("\n\tCache option, degree, sequence length, cache size, and debug level must be integer values.\n");
			printUsage();
		}
		catch (FileNotFoundException e) {
			System.out.println("File \"" + gbkFile.getName()
					+ "\" could not be opened. Check spelling or pathname");
			System.out.println(e.getMessage());
			System.exit(1);			
		}
		catch (IOException e) {
			System.out.println("Something is wrong with the buffered writer");
			System.exit(1);
		}


	}

	/**
	 * Outputs the correct format for the command line when running GeneBankCreateBtree
	 * class
	 */
	private static void printUsage() {
		System.out
		.println( "USAGE:  $ java GeneBankCreateBtree <0 (no cache) | 1 (cache)>  <degree>  <gbk.file>\n"
				+ "        <sequence_length>  [<cache_size>]  [<debug_level>]\n\n"
				+ "-<0 (no cache) | 1 (cache)>  -- specifies if program should use cache;\n"
				+ "                                if cache value is 1, <cache_value> has to be specified.\n"
				+ "-<degree>                    -- degree used for BTree. Enter 0 for optimial degree usage.\n"
				+ "-<gbk.file>                  -- any gene bank file( *.gbk) containing input DNA sequences.\n"
				+ "-<sequence_length>           -- integer value between 1 and 31 (inclusive)\n"
				+ "-[<cache-size>]              -- integer between 100 and 500 (inclusive)\n"
				+ "                                only needed if 1 is entered for cache\n"
				+ "-[<debug_level>]             -- [optional] 0 (default) or 1.\n");
		System.exit(1);
	}

}
