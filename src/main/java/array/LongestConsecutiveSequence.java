package array;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 最长连续序列
 */
public class LongestConsecutiveSequence {

    public static void main(String[] args) {
        int[] testCase = new int[]{2, 6, 4};
        LongestConsecutiveSequence longestConsecutiveSequence = new LongestConsecutiveSequence();
        System.out.println(longestConsecutiveSequence.getLongestConsecutiveSequenceLength(testCase));

    }

    /**
     *  使用map加双向搜索
     * @param arr
     * @return
     */
    public int getLongestConsecutiveSequenceLength(int[] arr) {
        if (null == arr || arr.length == 0) {
            return 0;
        }
        int len = arr.length;
        Map<Integer, Integer> integerMap = new ConcurrentHashMap<>();
        //初始化map
        for (int i = 0; i < len; i++) {
            integerMap.put(arr[i], 0);
        }
        //遍历map找出最长的连续子序列
        int maxSequenceLength = 0;
        //使用迭代器
        for (Integer num : integerMap.keySet()) {
            int value = integerMap.get(num);
            if (value > 0) {
                continue;
            }
            int minTemp = num;
            while (integerMap.containsKey(minTemp)) {
                integerMap.put(minTemp, 1);
                minTemp--;
            }
            minTemp++;
            int maxTemp = num;
            while (integerMap.containsKey(maxTemp)) {
                integerMap.put(maxTemp, 1);
                maxTemp++;
            }
            maxTemp--;
            int maxTempSequenceLength = maxTemp - minTemp + 1;
            if (maxTempSequenceLength > maxSequenceLength) {
                maxSequenceLength = maxTempSequenceLength;
            }
        }
        return maxSequenceLength;
    }
}
