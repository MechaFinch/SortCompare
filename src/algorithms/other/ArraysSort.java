package algorithms.other;

import algorithms.Sort;

import java.util.Arrays;

public class ArraysSort implements Sort {
	int namePadding = 0;
	
	@Override
	public <T extends Comparable<? super T>> void sort(T[] arr) {
		
	}
	
	@Override
	public void sort(int[] arr) {
		Arrays.sort(arr);
	}
	
	@Override
	public String toString() {
		String s = "Arrays.sort()";
		
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