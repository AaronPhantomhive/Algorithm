/*
 * Xiaofan Di.
 * CSE464: Maximize House Robbing
 * April 26, 2019
 */
 
public class HouseRob {

    private static int rob(int[] houses) {
        // Implement rob(), return best possible value
    	int rob = 0;
    	int robX = 0;
    	int robnum = 0;
    	for (int i = 0; i < houses.length; i++) {
    		robnum = robX + houses[i];
    		robX = Math.max(robX, rob);
    		rob = robnum;
    	}
		return Math.max(robX, rob);
    }

    public static void main(String[] args) {
        // Should value 12
        int[] firstSet = {2,7,9,3,1};
        System.out.println("Highest value from first set: " + rob(firstSet));

        // Should value 68
        int[] secondSet = {5,6,8,2,3,6,3,7,3,8,11,2,4,7,2,13,6,9,9,2};
        System.out.println("Highest value from first set: " + rob(secondSet));
    }
}
