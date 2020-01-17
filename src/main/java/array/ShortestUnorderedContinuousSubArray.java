package array;

import java.util.Arrays;

/**
 * 最短无序子数组
 */
public class ShortestUnorderedContinuousSubArray {

    public static void main(String[] args) {
        int[] testCase = new int[]{2, 6, 4, 8, 9, 10, 15};
        ShortestUnorderedContinuousSubArray shortestUnorderedContinuousSubArray = new ShortestUnorderedContinuousSubArray();
        System.out.println(shortestUnorderedContinuousSubArray.shortestUnorderedContinuousSubArrayLength(testCase));
        System.out.println(shortestUnorderedContinuousSubArray.shortestUnorderedContinuousSubArrayLengthBySort(testCase));

    }

    /**
     * 左右双指针法
     *
     * @param arr
     * @return
     */
    public int shortestUnorderedContinuousSubArrayLength(int[] arr) {
        if (null == arr || arr.length == 0) {
            return 0;
        }
        int len = arr.length;
        int max = arr[0];
        int min = arr[len - 1];
        int leftBorder = 1;
        int rightBorder = 0;
        //循环遍历找出无需数组的最左边界和无序数组的最右边界
        for (int i = 0; i < len; i++) {
            //先找从左到右找出右边界
            if (max > arr[i]) {
                //说明右边的数无序，边界右移
                rightBorder = i;
            } else {
                //最大值需要更新，用于找到右边界用
                max = arr[i];
            }

            //在找从右到左找出左边界
            if (min < arr[len - 1 - i]) {
                //说明左边的数无序，边界左移
                leftBorder = len - 1 - i;
            } else {
                //最小值需要更新，用于找到左边界用
                min = arr[len - 1 - i];
            }
        }

        return rightBorder - leftBorder + 1;
    }

    /**
     * 先排序，在找左右边界
     *
     * @param arr
     * @return
     */
    public int shortestUnorderedContinuousSubArrayLengthBySort(int[] arr) {
        if (null == arr || arr.length == 0) {
            return 0;
        }
        int len = arr.length;
        //先排序
        int[] sortedArr = Arrays.copyOf(arr, len);
        Arrays.sort(sortedArr);
        int leftBorder = 1;
        int rightBorder = 0;
        //找左边界
        for (int i = 0; i < len; i++) {
            if (sortedArr[i] != arr[i]) {
                leftBorder = i;
                break;
            }
        }
        //找右边界
        for (int i = len - 1; i >= 0; i--) {
            if (sortedArr[i] != arr[i]) {
                rightBorder = i;
                break;
            }
        }
        return rightBorder - leftBorder + 1;
    }
}

