
public class KendallTau {
	//两个排列之间的逆序数可以看作是以a为排列的标准，b排列自身的逆序数。要以a为排列标准，
	//首先需要将a排列的索引提取出来放到新排列aIndex中，即aIndex[ a[ i ] ] = i 。
	//接着要以a排列的索引去确定b的索引，即bIndex[ i ] = aIndex[ b[ i ] ] 。
	//从而bIndex中索引的逆序数就是a，b之间的逆序数了。 
	//一种特殊情况就是：a[ i ] = i 自然数序，则aIndex[ i ] = i ，bIndex[ i ] = b[ i ] ，则以a为排列的标准，b排列的逆序数就是b排列自身的逆序数。
	// 首先考虑简单的平方量级的算法。在插入排序或者冒泡排序中，元素交换的次数等于该排列的逆序数，因此在排序过程中统计交换次数即可。但是这种方法效率比较低。 
	//更高效的方法启发自高效的排序算法，比如归并排序，它可以使得算法变成线性对数量级。在将两个有序的排列归并在一起时，前子数组首元素如果小于后子数组首元素，则逆序数为0，反之，逆序数为前子数组当前的元素个数。
	private static long counter = 0;

	    public static long distance(int[] a, int[] b) {
	        if (a.length != b.length) {
	            throw new IllegalArgumentException("Array dimensions disagree");
	        }
	        int N = a.length;
	        int[] aIndex = new int[N];// 记录a数组的索引
	        for (int i = 0; i < N; i++) {
	            aIndex[a[i]] = i;
	        }
	        int[] bIndex = new int[N];// b数组引用a数组的索引
	        for (int i = 0; i < N; i++) {
	            bIndex[i] = aIndex[b[i]];
	        }
	        return mergeCount(bIndex);
	    }

	    // 使用插入排序方法求逆序数
	    public static long insertionCount(int[] a) {
	        for (int i = 1; i < a.length; i++) {
	            for (int j = i; j > 0 && a[j] < a[j - 1]; j--) {
	                int temp = a[j];
	                a[j] = a[j - 1];
	                a[j - 1] = temp;
	                counter++;// 插入排序每交换一次，就存在一对逆序数
	            }
	        }
	        return counter;
	    }

	    // 使用归并排序方法求逆序数
	    private static int[] aux;

	    public static long mergeCount(int[] a) {
	        aux = new int[a.length];
	        mergeSort(a, 0, a.length-1);
	        return counter;
	    }

	    private static void mergeSort(int[] a, int lo, int hi) {
	        if (hi <= lo) {
	            return;
	        }
	        int mid = lo + (hi - lo) / 2;
	        mergeSort(a, lo, mid);
	        mergeSort(a, mid + 1, hi);
	        merge(a, lo, mid, hi);
	    }

	    public static void merge(int[] a, int lo, int mid, int hi) {
	        int i = lo, j = mid + 1;
	        for (int k = lo; k <= hi; k++) {
	            aux[k] = a[k];
	        }
	        for (int k = lo; k <= hi; k++) {
	            if (i > mid) {
	                a[k] = aux[j++];
	            } else if (j > hi) {
	                a[k] = aux[i++];
	            } else if (aux[j] < aux[i]) {
	                a[k] = aux[j++];
	                counter += mid - i + 1;// 每个比前子数组小的后子数组元素，逆序数为前子数组现有的长度
	            } else {
	                a[k] = aux[i++];
	            }
	        }
	    }

	    public static void main(String[] args) {
	        int[] a = new int[] { 0, 3, 1, 6, 2, 5, 4,7,8};
	        int[] b = new int[] { 8,7,1, 0, 3, 6, 4, 2, 5 };
	        for (int i = 0; i < a.length; i++) {
	            System.out.println(a[i] + " " + b[i]);
	        }
	        System.out.println("Inversions:" + distance(a, b));
	    }
}
