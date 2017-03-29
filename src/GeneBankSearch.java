
public class GeneBankSearch {
	
	/**
	 * Launch Program
	 * 
	 * @param args
	 * 
	 * 			: 4 or 5  arguments are required.
	 *			-< 0 (no cache) | 1 (cache) > 	-- specifies if program should use cache;
	 *			                                   if cache value is 1, <cache_value> has to be specified.
	 *			-< b-tree_file				    -- file created by GeneBankCreateBtree program.
	 *			-< query_file >					-- file containing all DNA string to be searched for.
	 *			-[< cache-size >]			 	-- integer between 100 and 500 (inclusive).
	 *					                           only needed if 1 is entered for cache.
	 *			-[< debug_level >]			 	-- [optional] 0 (default) or 1.
	 * 
	 * 
	 */


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int cacheOption = 0;
		String btreeFileName;
		String queryFileName;
		int cacheSize = 0;
		int debugLevel = 0;
		
		
		try {
			if (args.length < 4 || args.length > 6) {
				System.out.println("Incorrect command line usage.  4 or 5 argments required.\n");
				printUsage();
			}
			
			cacheOption = Integer.parseInt(args[1]);
			btreeFileName = args[2];
			queryFileName = args[3];
			
			if (cacheOption != 0 && cacheOption != 1) {
				System.out.println("Cache options must be either 0 or 1.\n");
				printUsage();				
			}
			
			if (cacheOption == 1) {
				if (args.length == 4) {
					System.out.println("Must enter a cache size when cache option is 1.\n");
					printUsage();
				}
				else {
					cacheSize = Integer.parseInt(args[4]);
					if ( cacheSize < 100 || cacheSize > 500 )	{
						System.out.println("Cache size must be an integer between 100 and 500 (inclusive)\n");
						printUsage();
					}
					
					if (args.length == 6) {
						debugLevel = Integer.parseInt(args[5]); 						
					}
				}
			}
			else {
				if (args.length == 5) {
					debugLevel = Integer.parseInt(args[4]); 		
				}
				else if (args.length == 6) {
					System.out.println("Too many arguments for when cache option is 0.\n");
					printUsage();
				}
			}
			
			if (debugLevel != 0 && debugLevel != 1) {
				System.out.println("Debug option must be either 0 or 1.\n");
				printUsage();				
			}
			
//			System.out.println(cacheOption);
//			System.out.println(btreeFileName);
//			System.out.println(queryFileName);
//			System.out.println(cacheSize);
//			System.out.println(debugLevel);			
			
		}
		catch (NumberFormatException e) {
			System.out.println("\nNumberFormatException:");
			System.out.println("\n\tCache option, cache size, and debug level must be integer values.\n");
			printUsage();
		}		
		
	}
	
	/**
	 * Outputs the correct format for the command line when running GeneBankSearch
	 * class
	 */
	private static void printUsage() {
		System.out
		.println( "USAGE:  $ java GeneBankSearch < 0 (no cache) | 1 (cache) >  < b-tree_file >  < query_file >\n"
				+ "        [< cache_size >]  [< debug_level >]\n\n"
				+ "-< 0 (no cache) | 1 (cache) >  -- specifies if program should use cache;\n"
				+ "                                  if cache value is 1, <cache_value> has to be specified.\n"
				+ "-< b-tree_file >               -- file created by GeneBankCreateBTree program.\n"
				+ "-< query_file >                -- file containing all DNA strings to be searched for.\n"
				+ "-[< cache-size >]              -- integer between 100 and 500 (inclusive)\n"
				+ "                                  only needed if 1 is entered for cache\n"
				+ "-[< debug_level >]             -- [optional] 0 (default) or 1.\n");
				// don't think debug level is needed for this program.
		System.exit(1);
	}

}
