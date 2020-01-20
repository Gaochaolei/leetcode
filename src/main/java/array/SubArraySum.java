package array;

import java.util.HashMap;
import java.util.Map;

public class SubArraySum {

    public static void main(String[] args) {
        int[] testCase = new int[]{1, 1, 1, 1};
        SubArraySum subArraySum = new SubArraySum();
        System.out.println(subArraySum.subArraySum(testCase, 2));
    }

    public int subArraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Map<Integer, Integer> sumCountMap = new HashMap<>();
        int len = nums.length;
        int sum = 0;
        int count = 0;
        for (int i = 0; i < len; i++) {
            sum += nums[i];
            if (sum == k) {
                count++;
            }
            if (sumCountMap.containsKey(sum - k)) {
                count += sumCountMap.get(sum - k);
            }
            if (sumCountMap.containsKey(sum)) {
                sumCountMap.put(sum, sumCountMap.get(sum) + 1);
            } else {
                sumCountMap.put(sum, 1);
            }
        }
        return count;
    }
}
