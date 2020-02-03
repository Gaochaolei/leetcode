package array;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 找出滑动窗口中最大的数字
 */
public class SlidingWindowArray {

    public static void main(String[] args) {
        int[] testCase = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        SlidingWindowArray slidingWindowArray = new SlidingWindowArray();
        int[] result = slidingWindowArray.getSlidingWindowMaxArray(testCase, 3);
        for (int i : result) {
            System.out.println(i);
        }
    }

    /**
     * @param numArr 数组
     * @param k      窗口大小
     * @return
     */
    public int[] getSlidingWindowMaxArray(int[] numArr, int k) {
        //不考虑数组和k值不合法的情况
        int len = numArr.length;
        int resultLen = len - k + 1;
        int[] resultArr = new int[resultLen];
        //先初始化前k个值
        Deque<Integer> indexDeque = new LinkedList<>();
        int maxIndex = 0;
        for (int i = 0; i < k; i++) {
            cleanDeque(i, k, numArr, indexDeque);
            indexDeque.addLast(i);
        }
        if (indexDeque.getFirst() > 0) {
            maxIndex = indexDeque.getFirst();
        }
        //先初始化第一个窗口的最大值
        resultArr[0] = numArr[maxIndex];
        for (int i = k; i < len; i++) {
            cleanDeque(i, k, numArr, indexDeque);
            indexDeque.addLast(i);
            resultArr[i - k + 1] = numArr[indexDeque.getFirst()];
        }
        return resultArr;
    }

    /**
     * 清除双端队列
     *
     * @param i
     * @param k
     * @param numArr
     * @param indexDeque
     */
    private void cleanDeque(int i, int k, int[] numArr, Deque<Integer> indexDeque) {
        if (!indexDeque.isEmpty() && indexDeque.getFirst() == i - k) {
            //这种情况表示双端队列的第一个元素已经不在窗口内，应该被清掉
            indexDeque.removeFirst();
        }

        while (!indexDeque.isEmpty() && numArr[i] > numArr[indexDeque.getLast()]) {
            //这种情况表明队尾的元素都不是最大值，可以被清理掉
            indexDeque.removeLast();
        }
    }

}
