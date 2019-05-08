package algorithms.sanic;

import algorithms.Sort;

public class EndQuickSort implements Sort {
	int namePadding = 0;
	
	@Override
	public <T extends Comparable<? super T>> void sort(T[] arr) {
		
	}
	
	@Override
	public void sort(int[] arr) {
		qsort(arr, 0, arr.length - 1);
	}
	
	private void qsort(int[] arr, int start, int end) {
		if(start >= end)
			return;
		
		int index = partition(arr, start, end);
		
		qsort(arr, start, index - 1);
		qsort(arr, index + 1, end);
	}
	
	private int partition(int[] arr, int start, int end) {
		//Current implementation - pivot = end
		
		int pivVal = arr[end],
			pivInd = start;
		
		for(int i = start; i < end; i++) {
			if(arr[i] < pivVal) {
				swap(arr, i, pivInd);
				pivInd++;
			}
		}
		
		swap(arr, pivInd, end);
		
		return pivInd;
	}
	
	@Override
	public String toString() {
		String s = "Last Item Pivot Quicksort";
		
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
