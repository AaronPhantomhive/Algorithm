/*
为了做项目（从题库中随机选取试题）用到的蓄水池算法
*/
import java.util.*;

public class ReservoirSampling {
	public static int question, questionBank, students;
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter the number of the question");
		question = keyboard.nextInt();
		System.out.println("Enter the number of the question bank");
		questionBank = keyboard.nextInt();
		System.out.println("Enter the number of the student");
		students = keyboard.nextInt();

		int t1 = (int) System.currentTimeMillis();
		// Initialize data, from 0 - n-1
		int[] bankData = new int[questionBank];
		for(int i = 0; i < questionBank; i++) {
			bankData[i] =  i;
		}

		for (int i = 0; i < students; i++) {
			int[] finalResult = reservoirSampling(bankData, question);
			System.out.println(Arrays.toString(finalResult));
		}

		int t2 = (int) System.currentTimeMillis();
		int t = (t2-t1);
		System.out.println("total time is " + t + "ms");
	}

	public static int[] reservoirSampling(int[] bankData,int question) {
		if(bankData == null) {
			return new int[0];
		}
		else if(bankData.length < question) {
			return new int[0];
		}
		else if(bankData.length == question) {
			ArrayList<Integer> list = new ArrayList<Integer>();
			int temp;
			for (int i = 0; list.size() <question ; i++) {
				temp = new Random().nextInt(question);
				if(!list.contains(temp)) {
					list.add(temp);
				}
			}
			// convert the ArrayList to an Int array
			int[] finalResult = new int[list.size()];
			for (int i = 0; i < finalResult.length; i++)
			{
				finalResult[i] = list.get(i).intValue();
			}
			return finalResult;
		}
		else if(bankData.length > question && (question*students) <= bankData.length) {
			int temp = questionBank/question;
			int index = 0;
			int[] finalResult = new int[question];
			int rand;
		    for(int i = temp, j = 0; i <= questionBank; i += temp) {
		    	rand = new Random().nextInt(i - index - 1) + index;
		    	finalResult[j] = rand;
		    	j++;
		        index = i;
		    }
		    return finalResult;
		}
		else if(bankData.length > question && (question*students) > bankData.length) {
			// create an array equals questions
			int[] finalResult = new int[question];
			for(int i = 0; i < questionBank; i++) {
				if(i < question) {
					finalResult[i] = bankData[i];
				} else {
					int j = new Random().nextInt(i);
					if(j < question) {
						finalResult[j] = bankData[i];
					}
				}
			}
			return finalResult;
		}
		return null;
	}
}
