package array;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 找到流的中位数
 */
public class MedianFinder {

    private PriorityQueue<Integer> low = new PriorityQueue(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2.compareTo(o1);
        }
    });
    private PriorityQueue<Integer> high = new PriorityQueue(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1.compareTo(o2);
        }
    });

    public MedianFinder() {

    }

    public void addNum(int num) {
        low.offer(num); //先把元素添加到大顶堆中去
        high.add(low.remove());//在把大顶堆中的最大值放入小顶堆中
        if (low.size() < high.size()) {
            //此时大顶堆元素小于小顶堆，需要把小顶堆的最小元素放回到大顶堆中，用于平衡两个堆
            //始终保持low.size() >= high.size()
            low.offer(high.remove());
        }
    }

    public double findMedian() {
        return low.size() > high.size() ? (double) low.poll() : (low.poll() + high.poll()) * 0.5;
    }

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(4);
        medianFinder.addNum(2);
        medianFinder.addNum(3);
        medianFinder.addNum(1);
//        medianFinder.addNum(5);
        System.out.println(medianFinder.findMedian());
    }
}
