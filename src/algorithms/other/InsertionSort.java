package algorithms.other;

import algorithms.Sort;

public class InsertionSort implements Sort {
	int namePadding = 0;
	
	@Override
	public <T extends Comparable<? super T>> void sort(T[] arr) {
		
	}
	
	@Override
	public void sort(int[] arr) {
		for(int i = 1; i < arr.length; i++) {
			int n = arr[i],
				j = i - 1;
			
			for(; j >= 0 && arr[j] > n; j--)
				arr[j + 1] = arr[j];
			
			arr[j + 1] = n;
		}
	}
	
	@Override
	public String toString() {
		String s = "Insertion Sort";
		
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