package algorithms.other;

import algorithms.Sort;

public class SelectionSort implements Sort {
	int namePadding = 0;
	
	@Override
	public <T extends Comparable<? super T>> void sort(T[] arr) {
		
	}
	
	@Override
	public void sort(int[] arr) {
		for(int i = 0; i < arr.length - 1; i++) {
			int min = i;
			
			for(int j = i + 1; j < arr.length; j++) {
				if(arr[j] < arr[min])
					min = j;
			}
			
			swap(arr, min, i);
		}
	}
	
	@Override
	public String toString() {
		String s = "Selection Sort";
		
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