package algorithms.other;

import algorithms.Sort;

import java.util.Arrays;

public class BinaryInsertionSort implements Sort {
	int namePadding = 0;
	
	@Override
	public <T extends Comparable<? super T>> void sort(T[] arr) {
		
	}
	
	@Override
	public void sort(int[] arr) {
		for(int i = 1; i < arr.length; i++) {
			int val = arr[i],
				index = Math.abs(Arrays.binarySearch(arr, 0, i, val) + 1);
			System.arraycopy(arr, index, arr, index + 1, i - index);
			arr[index] = val;
		}
	}
	
	@Override
	public String toString() {
		String s = "Binary Insertion Sort";
		
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