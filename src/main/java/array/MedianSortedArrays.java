package array;

public class MedianSortedArrays {

    public static void main(String[] args) {
        MedianSortedArrays medianSortedArrays = new MedianSortedArrays();
        int[] numArr1 = new int[]{1, 2, 3, 6};
        int[] numArr2 = new int[]{1, 2, 4, 6};
        System.out.println(medianSortedArrays.findMedianSortedArrays(numArr1, numArr2));
    }

    /**
     * 使用二分法找到中位数
     *
     * @param numArray1
     * @param numArray2
     * @return
     */
    public double findMedianSortedArrays(int[] numArray1, int[] numArray2) {
        int m = numArray1.length;
        int n = numArray2.length;
        int[] mArray = numArray1;
        int[] nArray = numArray2;
        if (m > n) {
            int temp = m;
            m = n;
            n = temp;
            int[] tempArray = mArray;
            mArray = nArray;
            nArray = tempArray;
        }
        //保证了m<=n后，从mArray中进行二分查找，同时对两个数组进行划分
        int mArrMin = 0;
        int mArrMax = m;
        //先初始化i和j,把两个数组分成左右两个部分，i是mArray的切分点（0~m），j是nArray的切分点（0~n）
        int midPoint = (m + n + 1) / 2;
        int i = (mArrMin + mArrMax) / 2;
        int j = midPoint - i;
        while (mArrMin <= mArrMax) {
            //如果i等于0或者m了，循环可以终止了，此时在nArray中计算出的值就是所要寻找的中位数的分割点
            //会出现三种情况
            //情况1
            if (i > 0 && j > 0 && i < m && j < n) {
                if (mArray[i - 1] <= nArray[j] && nArray[j - 1] <= mArray[i]) {
                    //此时条件满足可以直接退出循环，找到了合适的i和j对两个数组进行分割
                    break;
                }
            }
            //情况2
            if (i > 0 && j < n) {
                if (mArray[i - 1] > nArray[j]) {
                    //此时i值太大了，需要减小i的值
                    mArrMax = i - 1;
                }
            }
            //情况3
            if (j > 0 && i < m) {
                if (nArray[j - 1] > mArray[i]) {
                    //此时i值太小了，需要增大i的值
                    mArrMin = i + 1;
                }
            }
            i = (mArrMin + mArrMax) / 2;
            j = midPoint - i;
        }

        int leftMax = 0;
        if (i == 0) {
            leftMax = nArray[j - 1];
        } else if (j == 0) {
            leftMax = mArray[i - 1];
        } else {
            leftMax = Math.max(mArray[i - 1], nArray[j - 1]);
        }

        int rightMin = 0;
        if (i == m) {
            rightMin = nArray[j];
        } else if (j == n) {
            rightMin = mArray[i];
        } else {
            rightMin = Math.min(mArray[i], nArray[j]);
        }
        //根据m+n是奇数还是偶数来计算中位数的值
        if ((m + n) % 2 == 1) {
            //如果是奇数
            return (double) leftMax;
        } else {
            //如果是偶数
            return ((double) (leftMax + rightMin)) * 0.5;
        }
    }
}
