import java.util.List;
import java.util.ArrayList;
/**
 *	SortMethods - Sorts a list of City objects in ascending or descending order by population and name.
 *
 *	@author Win Chang
 *	@since	November 26 2024
 */
public class SortMethods {
	/*
	/**
	 *	Bubble Sort algorithm - in ascending order
	 *	@param arr		array of Integer objects to sort
	 */
	/*
	public void bubbleSort(Integer [] arr) {
		for (int outer = arr.length - 1; outer > 0; outer--){
			for (int inner = 0; inner < outer; inner++)
				if (arr[inner].compareTo(arr[inner + 1]) > 0)
					swap(arr, inner, inner + 1);
		}
	}
	*/
	
	/**
	 *	Swaps two City objects in list arr
	 *	@param arr		list of City objects
	 *	@param x		index of first object to swap
	 *	@param y		index of second object to swap
	 */
	private void swap(List<City> arr, int x, int y) {
		City temp = arr.get(x);
		arr.set(x, arr.get(y));
		arr.set(y, temp);
	}
	
	/**
	 *	Selection Sort algorithm - sort cities in ascending population order;
	 *	@param arr		list of City objects to sort
	 */
	public void selectionSort(List<City> arr) {
		for (int outer = arr.size() - 1; outer >= 1; outer--){
			int iMax = 0;
			for (int inner = 1; inner <= outer; inner++)
				if (arr.get(inner).compareTo(arr.get(iMax)) > 0)
					iMax = inner;
			swap(arr, iMax, outer);
		}
	}
	
	/**
	 *	Insertion Sort algorithm - sort cities in ascending name order
	 *	@param arr		list of City objects to sort
	 */
	public void insertionSort(List<City> arr) {
		for (int outer = 1; outer < arr.size(); outer++){
			int inner = outer;
			CityComparatorByName comparator = new CityComparatorByName();
			while(inner > 0 && comparator.compare(arr.get(inner - 1), arr.get(inner)) > 0){
				swap(arr, inner - 1, inner);
				inner--;
			}
		}
	}
	
	/**
	 *	Merge Sort algorithm - sort cities in ascending order depending on the type
	 *	@param arr		list of City objects to sort
	 * 	@param left		the lower bound of the current range to sort
	 * 	@param right 	the upper bound of the current range to sort
	 * 	@param type 	the type of comparator to use when sorting.
	 * 						type 1: sort by population
	 * 						type 2: sort by name
	 */
	
	public void mergeSort(List<City> arr, int left, int right, int type) {
		if (right - left + 1 <= 2){
			if (right - left + 1 == 2){
				if (type == 1 && arr.get(left).compareTo(arr.get(right)) < 0)
					swap(arr, left, right);
				if (type == 2){
					CityComparatorByName comparator = new CityComparatorByName();
					if (comparator.compare(arr.get(left), arr.get(right)) > 0)
						swap(arr, left, right);
				}
			}
			return;
		}
		int middle = (left + right) / 2;
		mergeSort(arr, left, middle, type);
		mergeSort(arr, middle + 1, right, type);
		merge(arr, left, middle, right, type);
	}
	
	/**
	 *	Merge the current range of the list using two pointers method
	 * 	@param arr		list of City objects to sort
	 * 	@param left 	the lower bound of the current range to sort
	 * 	@param middle	the middle index of the current range to sort
	 * 	@param right 	the upper bound of the current range to sort
	 */
	
	public void merge(List<City> arr, int left, int middle, int right, int type){
		List<City> arrLeft = new ArrayList<City>();
		List<City> arrRight = new ArrayList<City>();
		for (int j = left; j <= middle; j++) arrLeft.add(arr.get(j));
		for (int j = middle + 1; j <= right; j++) arrRight.add(arr.get(j));
		
		int i = 0, j = 0, k = left;
		while(i < arrLeft.size() && j < arrRight.size()){
			if (type == 1){
				if (arrLeft.get(i).compareTo(arrRight.get(j)) < 0){
					arr.set(k, arrRight.get(j));
					j++;
				}
				else{
					arr.set(k, arrLeft.get(i));
					i++;
				}
			}
			else{
				CityComparatorByName comparator = new CityComparatorByName();
				if (comparator.compare(arrLeft.get(i), arrRight.get(j)) > 0){
					arr.set(k, arrRight.get(j));
					j++;
				}
				else{
					arr.set(k, arrLeft.get(i));
					i++;
				}
			}
			k++;
		}
		while(i < arrLeft.size()){
			arr.set(k, arrLeft.get(i));
			i++;
			k++;
		}
		while(j < arrRight.size()){
			arr.set(k, arrRight.get(j));
			j++;
			k++;
		}
	}
	
	/*****************************************************************/
	/************************* For Testing ***************************/
	/*****************************************************************/
	
	/**
	 *	Print an array of Integers to the screen
	 *	@param arr		the array of Integers
	 */
	 /*
	public void printArray(Integer[] arr) {
		if (arr.length == 0) System.out.print("(");
		else System.out.printf("( %4d", arr[0]);
		for (int a = 1; a < arr.length; a++) {
			if (a % 10 == 0) System.out.printf(",\n  %4d", arr[a]);
			else System.out.printf(", %4d", arr[a]);
		}
		System.out.println(" )");
	}

	public static void main(String[] args) {
		SortMethods se = new SortMethods();
		se.run();
	}
	
	public void run() {
		Integer[] arr = new Integer[10];
		// Fill arr with random numbers
		for (int a = 0; a < 10; a++)
			arr[a] = (int)(Math.random() * 100) + 1;
		System.out.println("\nBubble Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		bubbleSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();
		
		for (int a = 0; a < 10; a++)
			arr[a] = (int)(Math.random() * 100) + 1;
		System.out.println("\nSelection Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		selectionSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();

		
		for (int a = 0; a < 10; a++)
			arr[a] = (int)(Math.random() * 100) + 1;
		System.out.println("\nInsertion Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		insertionSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();

		
		for (int a = 0; a < 10; a++)
			arr[a] = (int)(Math.random() * 100) + 1;
		System.out.println("\nMerge Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		mergeSort(arr, 0, 9);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();

	}*/
}
