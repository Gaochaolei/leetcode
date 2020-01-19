package array;

/**
 * 找到重复的数组元素
 * 说明：
 * <p>
 * 不能更改原数组（假设数组是只读的）。
 * 只能使用额外的 O(1) 的空间。
 * 时间复杂度小于 O(n2) 。
 * 数组中只有一个重复的数字，但它可能不止重复出现一次。
 */
public class DuplicateArray {

    public static void main(String[] args) {
        int[] testCase = new int[]{3,1,3,4,2};
        DuplicateArray duplicateArray = new DuplicateArray();
        System.out.println(duplicateArray.findDuplicate(testCase));
    }

    /**
     * 找到重复的元素，可以通过判断链表的入环点来找到重复的数字
     *
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        if (null == nums || nums.length == 0) {
            return 0;
        }
        //每次走一步
        //每次走两步
        //先通过快慢指针找到第一次相遇点，然后在找环的入口点
        int stepOneValue = nums[0];
        int stepTwoValue = nums[0];
        stepOneValue = nums[stepOneValue];
        stepTwoValue =  nums[nums[stepTwoValue]];
        while (stepOneValue != stepTwoValue){
            stepOneValue = nums[stepOneValue];
            stepTwoValue =  nums[nums[stepTwoValue]];
        }

        int firstValue = nums[0];
        int secondValue = stepTwoValue;

        while (firstValue != secondValue){
            firstValue = nums[firstValue];
            secondValue =  nums[secondValue];
        }
        return firstValue;
    }
}
