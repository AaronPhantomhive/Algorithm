/*
 * dix@miamioh.edu
 */
 
public class ActivitySelection {

    private static void findMaxActivities(int[] startTime, int[] finishTime) {
        // Implement findMaxActivities
        // Print the indices of the selected activities
    	int i = 0;
    	System.out.println(i);
    	for (int j = 0; j < startTime.length; j++) {
    		if (startTime[j] >= finishTime[i]) {
    			i = j;
    			System.out.println(j);
    		}
    	}	
    }

    public static void main(String[] args) {
        int[] start1 = {1, 3, 0, 5, 8, 5};
        int[] finish1 = {2, 4, 6, 7, 9, 9};
        System.out.println("should select activities: ");
        findMaxActivities(start1, finish1);

        int[] start2 = {10, 12, 30};
        int[] finish2 = {20, 25, 30};
        System.out.println("should select activities: ");
        findMaxActivities(start2, finish2);
    }
}
