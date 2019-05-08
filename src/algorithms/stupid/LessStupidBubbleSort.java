package algorithms.stupid;

import algorithms.Sort;

public class LessStupidBubbleSort implements Sort {
	int namePadding = 0;
	
	@Override
	public <T extends Comparable<? super T>> void sort(T[] arr) {
		
	}
	
	@Override
	public void sort(int[] arr) {
		int n = arr.length;
		
		while(n > 1) {
			int nn = 0;
			
			for(int i = 1; i < n; i++) {
				if(arr[i - 1] > arr[i]) {
					int a = arr[i];
					arr[i] = arr[i - 1];
					arr[i - 1] = a;
					nn = i;
				}
			}
			
			n = nn;
		}
	}
	
	@Override
	public String toString() {
		String s = "Less Stupid Bubble Sort";
		
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