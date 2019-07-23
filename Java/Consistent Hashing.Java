import java.util.*;

/** 
 * 带虚拟节点的一致性Hash算法 
 */
 
public class ConsistentHashingWithVirtualNode {
	public static int serverNum;

	//待添加入Hash环的服务器列表  
	public static String[] servers = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
	/*
	 * , "10", 
			"11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
			"21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
			"31", "32", "33", "34", "35", "36", "37", "38", "39", "40",
			"41", "42", "43", "44", "45", "46", "47", "48", "49", "50",
			"51", "52", "53", "54", "55", "56", "57", "58", "59", "60",
			"61", "62", "63", "64", "65", "66", "67", "68", "69", "70",
			"71", "72", "73", "74", "75", "76", "77", "78", "79", "80",
			"81", "82", "83", "84", "85", "86", "87", "88", "89", "90",
			"91", "92", "93", "94", "95", "96", "97", "98", "99"
	 */
	//真实结点列表,考虑到服务器上线、下线的场景，即添加、删除的场景会比较频繁，这里使用LinkedList会更好  
	private static List<String> realNodes = new LinkedList<String>();

	//虚拟节点，key表示虚拟节点的hash值，value表示虚拟节点的名称  
	private static SortedMap<Integer, String> virtualNodes = new TreeMap<Integer, String>();  

	//虚拟节点的数目，一个真实结点对应500个虚拟节点 
	// VIRTUAL_NODES的值比较重要，当节点数目较少时，虚拟节点数目越大，命中率越高。500-1000倍基本就行了。
	private static final int VIRTUAL_NODES = 500;

	//使用FNV1_32_HASH算法计算服务器的Hash值 
	private static int getHash(String str){  
		final int p = 16777619;  
		int hash = (int)2166136261L;
		for (int i = 0; i < str.length(); i++) {
			hash = (hash ^ str.charAt(i)) * p;  
		}
		hash += hash << 13;  
		hash ^= hash >> 7;  
		hash += hash << 3;  
		hash ^= hash >> 17;  
		hash += hash << 5;  

		// 如果值为负数，取其绝对值 
		if (hash < 0) hash = Math.abs(hash);
		return hash;  
	}

	//得到应当路由到的结点  
	private static String getServer(String key) {
		//得到该key的hash值  
		int hash = getHash(key);
		// 得到大于该Hash值的所有Map  
		SortedMap<Integer, String> subMap = virtualNodes.tailMap(hash);
		System.out.println(virtualNodes);

		String virtualNode;
		if(subMap.isEmpty()){
			//如果没有比该key的hash值大的，则从第一个node开始  
			Integer i = virtualNodes.firstKey();
			//返回对应的服务器  
			virtualNode = virtualNodes.get(i);
		} else {
			//第一个Key就是顺时针过去离node最近的那个结点  
			Integer i = subMap.firstKey();
			//返回对应的服务器  
			virtualNode = subMap.get(i);
		}  
		//virtualNode虚拟节点名称要截取一下 
		virtualNode = virtualNode.substring(0, virtualNode.indexOf("&&"));
		return virtualNode;
	}


	public static void main(String[] args) {
		// require data
//		Scanner keyboard = new Scanner(System.in);
//		System.out.println("serverNum:");
//		serverNum = keyboard.nextInt();
//
//		servers = new String[serverNum];

		for(int i = 0; i < servers.length; i++) {
			servers[i] = String.valueOf(i);
		}

		System.out.println(Arrays.toString(servers));

		//先把原始的服务器添加到真实结点列表中  
		for(int i = 0; i < servers.length; i++)
			realNodes.add(servers[i]);

		//再添加虚拟节点，遍历LinkedList使用foreach循环效率会比较高  
		for (String str : realNodes){  
			for(int i = 0; i < VIRTUAL_NODES; i++){  
				String virtualNodeName = str + "&&VN" + String.valueOf(i);  
				int hash = getHash(virtualNodeName);
				virtualNodes.put(hash, virtualNodeName);
			}  
		}  
		System.out.println();

		HashMap<String,Integer> map = new HashMap<String, Integer>(); 
		List<String> randId = new ArrayList<String>();
		int temp;
		// amount is something like the total students amount
		// amount 分一下，一万以内第一种方法比第二种快一点。
//		System.out.println("amount:");
//		int amount = keyboard.nextInt();
		int amount = 1000;

		int t1 = (int) System.currentTimeMillis();
		if (amount <= 10000) {
			// first 不重复的数字
			for(int i = 0; randId.size() < amount; i++) {
				temp = new Random().nextInt();
				if(!randId.contains(String.valueOf(temp))) {
					randId.add(String.valueOf(temp));
				}
			}
		} else {
			// second UUID 的目的，是让分布式系统中的所有元素，都能有唯一的辨识资讯，而不需要透过中央控制端来做辨识资讯的指定。
			for(int i = 0; i < amount; i++) {
				randId.add(UUID.randomUUID().toString().replace("-", ""));
			}
		}
		for (int i = 0; i < randId.size(); i++) {
			// 遍历randId， 得到每一个i的节点
			String aString = getServer(randId.get(i));
			// 获得当前map里有没有aString，有几个iString。
			Integer aInteger = map.get(aString);
			if(aInteger == null) {
				map.put(aString, 1);
			} else {
				map.put(aString, aInteger + 1);
			}
		}
		Set<String> set = map.keySet();
		int t2 = (int) System.currentTimeMillis();
		int t = (t2 - t1);
		System.out.println("total time is " + t + "ms");
		for(String a:set){
			System.out.println("节点【" + a + "】分配到元素个数为==>" + map.get(a));
		}
	}
}
