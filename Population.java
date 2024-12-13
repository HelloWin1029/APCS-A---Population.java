import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
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
	    cities = new ArrayList<City>();
	}
	
	public static void main(String args[]){
	    Population us = new Population();
	    us.run();
	}
	
	public void run(){
	    printIntroduction();
	    
	    java.util.Scanner input = null;
	    input = FileUtils.openToRead(DATA_FILE);
	    
	    // read in input
	    while(input.hasNext()){
	        String[] info = input.nextLine().split("[\t\n]");
	        String state = info[0], name = info[1], designation = info[2];
	        int population = Integer.parseInt(info[3]);
	        
	        cities.add(new City(name, state, designation, population));
	    }
	    System.out.println(cities.size() + " cities in database\n");
	    
	    String enter = "";
	    do{
			printMenu();
	        do{
	            enter = Prompt.getString("Enter selection");
	        } while(enter.length() != 1 || !(enter.charAt(0) >= '1') || !(enter.charAt(0) <= '9'));
	        
	        if (!enter.equals("9")){
	            System.out.println();
	            query(enter.charAt(0) - '0');
	        }
	    } while(!enter.equals("9"));
	    System.out.println("\nThanks for using Population!");
	}
	
	/**	
	 *  Run the queries from the user depending on the type
	 *  @param x       the query type from the user
	*/
	public void query(int x){
		SortMethods sort = new SortMethods();
	    if (x == 1){ // selection sort
	        System.out.println("Fifty least populous cities");
	        List<City> copy = cities;
	        long startMillisec = System.currentTimeMillis();
	        sort.selectionSort(copy);
	        long endMillisec = System.currentTimeMillis();
			
			String state = "States", city = "City", type = "Type", population = "Population";
			System.out.printf("     %-22s %-22s %-12s %12s\n", state, city, type, population);
	        for (int i = 0; i < 50; i++){
				System.out.printf("%3d: ", i + 1);
				System.out.println(copy.get(i).toString());
			}
				
	        System.out.println("\nElapsed Time " + (endMillisec - startMillisec) + " millisecond\n");
	    }
	    
	    else if (x == 2){ // merge sort
	        System.out.println("Fifty most populous cities");
	        
	        List<City> copy = cities;
	        long startMillisec = System.currentTimeMillis();
	        sort.mergeSort(copy, 0, copy.size() - 1, 1);
	        long endMillisec = System.currentTimeMillis();
	        
	        String state = "States", city = "City", type = "Type", population = "Population";
			System.out.printf("     %-22s %-22s %-12s %12s\n", state, city, type, population);
	        for (int i = 0; i < 50; i++){
				System.out.printf("%3d: ", i + 1);
				System.out.println(copy.get(i).toString());
			}
	        
	        System.out.println("\nElapsed Time " + (endMillisec - startMillisec) + " millisecond\n");
	    }
	    
	    else if (x == 3){ // insertion sort
	        System.out.println("Fifty cities sorted by name");
	        
	        List<City> copy = cities;
	        long startMillisec = System.currentTimeMillis();
	        sort.insertionSort(copy);
	        long endMillisec = System.currentTimeMillis();
	        
	        String state = "States", city = "City", type = "Type", population = "Population";
			System.out.printf("     %-22s %-22s %-12s %12s\n", state, city, type, population);
	        for (int i = 0; i < 50; i++){
				System.out.printf("%3d: ", i + 1);
				System.out.println(copy.get(i).toString());
			}
	        
	        System.out.println("\nElapsed Time " + (endMillisec - startMillisec) + " millisecond\n");
	    }
	    
	    else if (x == 4){ // merge sort
	        System.out.println("Fifty cities sorted by name descending");
	        
	        List<City> copy = cities;
	        long startMillisec = System.currentTimeMillis();
	        sort.mergeSort(copy, 0, copy.size() - 1, 2);
	        long endMillisec = System.currentTimeMillis();
	        
	        String state = "States", city = "City", type = "Type", population = "Population";
			System.out.printf("     %-22s %-22s %-12s %12s\n", state, city, type, population);
	        for (int i = copy.size() - 1; i >= copy.size() - 50; i--){
				System.out.printf("%3d: ", copy.size() - i);
				System.out.println(copy.get(i).toString());
			}
	        
	        System.out.println("\nElapsed Time " + (endMillisec - startMillisec) + " millisecond\n");
	    }
	    
	    else if (x == 5){ // merge sort
	        boolean found = false;
	        String state = "";
	        do{
	            state = Prompt.getString("Enter state name (ie. Alabama)");
	            for (int i = 0; i < cities.size() && !found; i++){
	                if (state.equalsIgnoreCase(cities.get(i).getState())){
	                    found = true;
	                    state = cities.get(i).getState();
	                }
	            }
	        } while(!found);
	        
	        List<City> copy = new ArrayList<City>();
	        for (int i  = 0; i < cities.size(); i++){
				if (cities.get(i).getState().equalsIgnoreCase(state))
					copy.add(cities.get(i));
			}
	        sort.mergeSort(copy, 0, copy.size() - 1, 1);
	        
	        System.out.println("\nFifty most populous cities in " + state);
	        String statePrint = "States", city = "City", type = "Type", population = "Population";
			System.out.printf("     %-22s %-22s %-12s %12s\n", statePrint, city, type, population);
	        for (int i = 0; i < 50; i++){
				System.out.printf("%3d: ", i + 1);
				System.out.println(copy.get(i).toString());
			}
			System.out.println();
	    }
	    
	    else{ // merge sort
	        boolean found = false;
	        String name = "";
	        do{
	            name = Prompt.getString("Enter city name");
	            for (int i = 0; i < cities.size() && !found; i++){
	                if (name.equalsIgnoreCase(cities.get(i).getName())){
	                    found = true;
	                    name = cities.get(i).getName();
	                }
	            }
	        } while(!found);
	        
	        List<City> copy = new ArrayList<City>();
	        for (int i  = 0; i < cities.size(); i++){
				if (cities.get(i).getName().equalsIgnoreCase(name))
					copy.add(cities.get(i));
			}
	        sort.mergeSort(copy, 0, copy.size() - 1, 1);
	        
	        System.out.println("\nCity " + name + " by population");
	        String statePrint = "States", city = "City", type = "Type", population = "Population";
			System.out.printf("     %-22s %-22s %-12s %12s\n", statePrint, city, type, population);
	        for (int i = 0; i < copy.size(); i++){
				System.out.printf("%3d: ", i + 1);
				System.out.println(copy.get(i).toString());
			}
			System.out.println();
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
