/**
 * @author dix@miamioh.edu
 */
 
import java.util.Random;

public class TrivialHash {

    private final static int MAX = 1000;
    private static boolean[][] hash = new boolean[MAX+1][2];

    private static boolean search(int target) {
        // Implement search(), return true if found - needs to be of O(1) complexity
    	if(target >= 0) {
    		if(hash[target][0] == true)
    			return true;
    	} else if(target < 0) {
			target = Math.abs(target);
			if(hash[target][1] == true)
    			return true;
		}
        return false;
    }

    private static void insert(int[] a, int n) {
        // Implement insert()
    	for (int i = 0; i < MAX+1; i++) {
    		hash[i][0] = false;
    		hash[i][1] = false;
    	}
    	for (int j = 0; j < n; j++) {
    		if(a[j] >= 0) {
    			hash[a[j]][0] = true;
    		} else if(a[j] < 0) {
    			hash[Math.abs(a[j])][1] = true;
    		}
    	}
    }

    // Driver code -------- no need to touch
    public static void main(String[] args) {
        int[] testArr = new int[10];
        Random random = new Random();
        for (int i = 0; i < testArr.length; i++)
            testArr[i] = random.nextInt(25 + 1 + 25) - 25;
        insert(testArr, testArr.length);
        if (search(testArr[2]))
            System.out.println("Value exists.");
        else
            System.out.println("Value doesn't exist");
    }
}
