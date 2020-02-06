package array;

import java.util.Comparator;
import java.util.PriorityQueue;

public class KthLargest {

    public static void main(String[] args) {
        int[] testCase = new int[]{2, 6, 4, 5, 7, 10, 19, 35, 57, 1, 3, 8, 9};
        KthLargest kthLargest = new KthLargest();
        System.out.println(kthLargest.findKthLargest(testCase, 5));
    }

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        for (int i = 0; i < nums.length; i++) {
            if (priorityQueue.size() < k) {
                priorityQueue.offer(nums[i]);
            } else {
                int topValue = priorityQueue.peek();
                if (topValue < nums[i]) {
                    priorityQueue.poll();
                    priorityQueue.offer(nums[i]);
                }
            }
        }
        return priorityQueue.peek();
    }

}
