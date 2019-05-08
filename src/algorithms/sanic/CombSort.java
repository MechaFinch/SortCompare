package algorithms.sanic;

import algorithms.Sort;

public class CombSort implements Sort {
	int namePadding = 0;
	
	@Override
	public <T extends Comparable<? super T>> void sort(T[] arr) {
		
	}
	
	@Override
	public void sort(int[] arr) {
		int gap = arr.length;
		double shrink = 1.3;
		boolean sorted = false;
		
		while(!sorted) {
			gap = (int) ((double) gap / shrink);
			
			if(gap <= 1) {
				gap = 1;
				sorted = true;
			}
			
			for(int i = 0; i + gap < arr.length; i++) {
				if(arr[i] > arr[i + gap]) {
					swap(arr, i, i + gap);
					
					sorted = false;
				}
			}
		}
	}
	
	@Override
	public String toString() {
		String s = "Comb Sort";
		
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
