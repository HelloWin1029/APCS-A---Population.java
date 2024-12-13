import java.util.List;
import java.util.Scanner.
/**
 *	Population - <description goes here>
 *
 *	Requires FileUtils and Prompt classes.
 *
 *	@author	Win Chang
 *	@since	December 6 2024
 */
public class Population {
	
	// List of cities
	private List<City> cities;
	
	// US data file
	private final String DATA_FILE = "usPopData2017.txt";
	
	// Constructor
	public Population(){
	    cities = new List<City>();
	}
	
	public static void main(String args[]){
	    Population us = new Population();
	    us.run();
	}
	
	public void run(){
	    printIntroduction();
	    printMenu();
	    
	    java.util.Scanner input = null;
	    input = FileUtils.openToRead(DATA_FILE);
	    
	    while(input.hasNext()){
	        String[] info = input.nextLine().split("[\t\n]");
	        String state = info[0], name = info[1], designation = info[2];
	        int population = Integer.parseInt(info[3]);
	        
	        cities.add(new City(name, state, designation, population));
	    }
	    
	    String enter = "";
	    do{
	        do{
	            enter = Prompt.getString("Enter selection");
	        } while(enter.size() != 1 || !(enter.charAt(0) >= '1') || !(enter.charAt(0) <= '9'));
	        
	        if (!enter.equals("9")){
	            System.out.println();
	            query(enter.charAt(0) - '0');
	        }
	    } while(!enter.equals("9"));
	}
	
	/**	
	 *  Run the queries from the user depending on the type
	 *  @param x       the query type from the user
	*/
	public void query(int x){
	    if (x == 1){ // selection sort
	        System.out.println("Fifty least populous cities");
	        
	        
	        long startMillisec = System.currentTimeMillis();
	        
	        System.out.println();
	        long endMillisec = System.currentTimeMillis();
	        System.out.prinln(endMillisec - startMillisec);
	    }
	    
	    else if (x == 2){ // merge sort
	        System.out.println("Fifty most populous cities");
	        
	        
	        long startMillisec = System.currentTimeMillis();
	        
	        System.out.println();
	        long endMillisec = System.currentTimeMillis();
	        System.out.prinln(endMillisec - startMillisec);
	    }
	    
	    else if (x == 3){
	        System.out.println("Fifty cities sorted by name");
	        
	        
	        long startMillisec = System.currentTimeMillis();
	        
	        System.out.println();
	        long endMillisec = System.currentTimeMillis();
	        System.out.prinln(endMillisec - startMillisec);
	    }
	    
	    else if (x == 4){
	        System.out.println("Fifty cities sorted by name descending");
	        
	        
	        long startMillisec = System.currentTimeMillis();
	        
	        System.out.println();
	        long endMillisec = System.currentTimeMillis();
	        System.out.prinln(endMillisec - startMillisec);
	    }
	    
	    else if (x == 5){
	        boolean found = false;
	        String state = "";
	        do{
	            state = Prompt.getString("Enter state name (ie. Alabama)");
	            for (int i = 0; i < cities.size(); i++){
	                if (state.equals(cities.get(i).getState))
	                    found = true;
	            }
	        } while(!found);
	        
	        long startMillisec = System.currentTimeMillis();
	    }
	    
	    else{
	        
	    }
	}
	
	/**	Prints the introduction to Population */
	public void printIntroduction() {
		System.out.println("   ___                  _       _   _");
		System.out.println("  / _ \\___  _ __  _   _| | __ _| |_(_) ___  _ __ ");
		System.out.println(" / /_)/ _ \\| '_ \\| | | | |/ _` | __| |/ _ \\| '_ \\ ");
		System.out.println("/ ___/ (_) | |_) | |_| | | (_| | |_| | (_) | | | |");
		System.out.println("\\/    \\___/| .__/ \\__,_|_|\\__,_|\\__|_|\\___/|_| |_|");
		System.out.println("           |_|");
		System.out.println();
	}
	
	/**	Print out the choices for population sorting */
	public void printMenu() {
		System.out.println("1. Fifty least populous cities in USA (Selection Sort)");
		System.out.println("2. Fifty most populous cities in USA (Merge Sort)");
		System.out.println("3. First fifty cities sorted by name (Insertion Sort)");
		System.out.println("4. Last fifty cities sorted by name descending (Merge Sort)");
		System.out.println("5. Fifty most populous cities in named state");
		System.out.println("6. All cities matching a name sorted by population");
		System.out.println("9. Quit");
	}
	
}