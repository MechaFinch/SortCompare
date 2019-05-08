package algorithms;

/**
 * Contains a declaration for the sort method
 * 
 * @author Alex Pickering
 */
public interface Sort {
	/**
	 * Sorts a generic comparable array
	 * 
	 * @param arr The array to be sorted
	 */
	public <T extends Comparable<? super T>> void sort(T[] arr);
	
	/**
	 * Sorts an int array
	 * 
	 * @param arr The array to be sorted
	 */
	public void sort(int[] arr);
	
	/**
	 * Returns the name of the sorting algorithm
	 * 
	 * @return The name of the algorithm
	 */
	public String toString();
	
	/**
	 * Sets the length the name will be padded to in toString
	 */
	public void setNamePadding(int l);
	
	default void swap(int[] arr, int a, int b) {
		if(a == b)
			return;
		
		int v = arr[a];
		arr[a] = arr[b];
		arr[b] = v;
	}
}