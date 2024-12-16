import java.util.Comparator;
/**
 *	CityComparatorByName.java
 * 	The class made to compare the city by name
 *
 *	@author	Win Chang
 *	@since	December 6 2024
 */
public class CityComparatorByName implements Comparator<City>{
	
	/**
	 * 	Compare cities' names and return an integer value of their difference.
	 * 	If the names are equal, then return the difference in populations.
	 *	@param a		The first city for comparison
	 *	@param b		The second city for comparison
	 * 	@return 		An integer value representing the difference between the cities
	 */
	public int compare(City a, City b){
		if (a.getName().equals(b.getName()))
			return a.getPopulation() - b.getPopulation();
		return a.getName().compareTo(b.getName());
	}
}
