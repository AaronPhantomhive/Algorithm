import java.util.ArrayList;
import java.util.Arrays;

public class ImprovedSort {
	static int[] run = new int[32];

	private static void insertionSort(int[] arr) {
		// Implement insertion sort, might need to modify parametersint
		int num = arr.length;
		for(int i = 1; i < num; i++) {
			for(int j = 0; j < num - 1; j++) {
				if(arr[j] > arr[j+1]) {
					int storageNum;
					storageNum = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = storageNum;
				}//end if
			}//end j for
		}//end i for
	}//end insertionSort method

	private static void merge(int[] arr) {
		// Implement merge part here, might need to modify parameters
		int length = arr.length;
		int fullArr[] = new int[length];
		for(int i = 0; i < length; i = i+32) {
			for(int j = 0; j < length; j++) {
				System.arraycopy(run[i], 0, fullArr[j], j, arr.length);
			}
		}
        
	}//end merge method

	private static void impSort(int[] arr) {
		// Implement improved sort
		// should call insertionSort() and merge() at some point in this method
		
		// divide the array into 32 
		for(int i = 0; i < arr.length; i = i+32) {
			 for(int j = 0; j < 32; j++) {
				  run[j] = arr[i];
				insertionSort(run);//insertion 32 blocks one by one
			 }
		}
		//merge 32 blocks to one array
		//merge(arr);
		//insertion in the end
		insertionSort(arr);
	}//end impSort method

	private static void printArr(int[] arr) {
		for (int i = 0; i < arr.length; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
	}//end printarr method

	// Driver code -------- no need to touch
	public static void main(String[] args) {
		int[] testArr = new int[320];
		for (int i = 0; i < testArr.length; i++)
			testArr[i] = (int)(Math.random()*1000);
		System.out.println("Before Sorting:");
		printArr(testArr);

		impSort(testArr);

		System.out.println("After sorting:");
		printArr(testArr);
	}
}
