package comparisons;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

import algorithms.Sort;
import algorithms.stupid.*;
import algorithms.sanic.*;
import algorithms.other.*;

/**
 * Compares sorting algorithms
 * 
 * @author Alex Pickering
 */
public class SortCompare {
	public static void main(String[] args) throws IOException {
		BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
		
		//Get list size
		System.out.print("Enter the size of the list: ");
		int size = Integer.parseInt(userIn.readLine());
		
		System.out.print("Enter the unit of time to use: ");
		String unit = userIn.readLine();
		
		System.out.print("Enter the number of iterations: ");
		int iterations = Integer.parseInt(userIn.readLine());
		System.out.println();
		
		//Get time unit to use
		long timeDivisor = 0;
		String suffix = "";
		
		switch(unit.toLowerCase()) {
			case "s":
				timeDivisor = 1_000_000_000;
				suffix = "s";
				break;
			
			case "ms":
				timeDivisor = 1_000_000;
				suffix = "ms";
				break;
			
			case "us":
				timeDivisor = 1_000;
				suffix = "μs";
				break;
			
			case "ns":
				timeDivisor = 1;
				suffix = "ns";
				break;
			
			default:
				System.out.println("Unsupported Unit");
				return;
		}
		
		boolean debug = false;
		
		//Setup list of algorithms to test
		ArrayList<Sort> algs = new ArrayList<>();
		
		algs.add(new StupidBubbleSort());
		algs.add(new LessStupidBubbleSort());
		
		algs.add(new CombSort());
		
		algs.add(new InsertionSort());
		algs.add(new BinaryInsertionSort());
		
		algs.add(new SelectionSort());
		
		algs.add(new EndQuickSort());
		
		algs.add(new ArraysSort());
		
		//Find maximum algorithm name length and pad the others
		int maxLen = Collections.max(algs, (a, b) -> a.toString().length() - b.toString().length()).toString().length();
		
		ArrayList<ArrayList<Long>> sortTimes = new ArrayList<>();
		
		System.out.println("0 iterations complete");
		System.out.println("0.0% complete\n\n");
		
		//Run them algorithms 10 times, show the average
		for(int n = 0; n < iterations; n++) {
			
			//Generate juicy-fresh random array
			ArrayList<Integer> sequenceList = new ArrayList<>();
			
			for(int i = 0; i < size; i++) {
				sequenceList.add(i);
			}
			
			Collections.shuffle(sequenceList);
			
			int[] randomArray = sequenceList.stream().mapToInt(x -> x).toArray();
			
			ArrayList<Long> times = new ArrayList<>();
			
			//Run each algorithm
			for(int i = 0; i < algs.size(); i++) {
				Sort s = algs.get(i);
				s.setNamePadding(maxLen);
				
				int[] ar = new int[size];
				System.arraycopy(randomArray, 0, ar, 0, randomArray.length);
				
				long start = System.nanoTime();
				s.sort(ar);
				long end = System.nanoTime();
				
				times.add(end - start);
				
				if(debug && n == 0) {
					System.out.println(s + ": " + Arrays.stream(ar).boxed().collect(Collectors.toList()));
				}
				
				//Dispaly progress bar
				if(!debug) {
					System.out.print("\u001B[1A[");
					for(int j = 0; j < algs.size(); j++) {
						if(j <= i)
							System.out.print("#");
						else System.out.print("-");
					}
					System.out.println("]");
				}
			}
			
			sortTimes.add(times);
			
			//Display percentages
			if(!debug) {
				//Clear the last two lines
				System.out.print("\u001B[3A\u001B[K\u001B[1A\u001B[K");
				System.out.println((n + 1) + " iterations complete");
				System.out.println(String.format("%.2f", (((double) (n + 1) / iterations) * 100)) + "%\n\n");
			}
		}
		
		//Outer list is the iterations
		//Inner list is the algorithms
		
		//Median the times, better show of data
		double[] medians = new double[sortTimes.get(0).size()];
		
		//Assembe into a better formatted list list
		//Outer list is the algorithms
		//Inner list is the iterations
		ArrayList<ArrayList<Long>> sortedLists = new ArrayList<>();
		
		//Setup the internal lists
		for(int i = 0; i < sortTimes.get(0).size(); i++) {
			sortedLists.add(new ArrayList<>());
		}
		
		//Arrange data properly
		for(int i = 0; i < sortTimes.size(); i++) {
			for(int j = 0; j < sortTimes.get(i).size(); j++) {
				sortedLists.get(j).add(sortTimes.get(i).get(j));
			}
		}
		
		//Sort data
		for(int i = 0; i < sortedLists.size(); i++) {
			Collections.sort(sortedLists.get(i));
		}
		
		//Find each median
		for(int i = 0; i < sortedLists.size(); i++) {
			int index = sortedLists.get(i).size() / 2;
			
			if(sortedLists.get(i).size() % 2 == 1) {
				medians[i] = (double) sortedLists.get(i).get(index);
			} else {
				medians[i] = ((double) sortedLists.get(i).get(index) + (double) sortedLists.get(i).get(index - 1)) / 2;
			}
			
			medians[i] /= timeDivisor;
		}
		
		//Sort medians and algs at the same time
		dualSort(medians, algs);
		
		System.out.println("\nMedian Sorting Times:");
		
		//Output data
		for(int i = algs.size() - 1; i >= 0; i--) {
			String num = String.format("%,13.5f", medians[i]);
			num = num.contains(".") ? num.replaceAll("0*$", "").replaceAll("\\.$", "") : num;
			
			System.out.print(String.format("%s %s%s%n", algs.get(i), num, suffix));
		}
		
		userIn.close();
		
		try {
			Thread.sleep(250);
		} catch(InterruptedException e) {
		}
		System.out.println();
	}
	
	private static <T> void dualSort(double[] arr, ArrayList<T> parallelList) {
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
					double val = arr[i];
					arr[i] = arr[i + gap];
					arr[i + gap] = val;
					
					T obj = parallelList.get(i);
					parallelList.set(i, parallelList.get(i + gap));
					parallelList.set(i + gap, obj);
					
					sorted = false;
				}
			}
		}
	}
}
