package algorithms.stupid;

import algorithms.Sort;

/**
 * Stupid Sorts - Bubblesort
 * 
 * @author Alex Pickering
 */
public class StupidBubbleSort implements Sort {
	int namePadding = 0;
	
	@Override
	public <T extends Comparable<? super T>> void sort(T[] arr) {
		System.out.println("Unimplemented");
	}
	
	@Override
	public void sort(int[] arr) {
		boolean swapped = true;
		
		while(swapped) {
			swapped = false;
			
			for(int i = 1; i < arr.length; i++) {
				if(arr[i - 1] > arr[i]) {
					int a = arr[i];
					arr[i] = arr[i - 1];
					arr[i - 1] = a;
					
					swapped = true;
				}
			}
		}
	}
	
	@Override
	public String toString() {
		String s = "Stupid Bubble Sort";
		
		while(s.length() < namePadding) {
			s += " ";
		}
		
		return s;
	}
	
	@Override
	public void setNamePadding(int len) {
		namePadding = len;
	}
}