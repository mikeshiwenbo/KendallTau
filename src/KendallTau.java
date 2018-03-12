
public class KendallTau {
	//��������֮������������Կ�������aΪ���еı�׼��b�����������������Ҫ��aΪ���б�׼��
	//������Ҫ��a���е�������ȡ�����ŵ�������aIndex�У���aIndex[ a[ i ] ] = i ��
	//����Ҫ��a���е�����ȥȷ��b����������bIndex[ i ] = aIndex[ b[ i ] ] ��
	//�Ӷ�bIndex������������������a��b֮����������ˡ� 
	//һ������������ǣ�a[ i ] = i ��Ȼ������aIndex[ i ] = i ��bIndex[ i ] = b[ i ] ������aΪ���еı�׼��b���е�����������b�����������������
	// ���ȿ��Ǽ򵥵�ƽ���������㷨���ڲ����������ð�������У�Ԫ�ؽ����Ĵ������ڸ����е�����������������������ͳ�ƽ����������ɡ��������ַ���Ч�ʱȽϵ͡� 
	//����Ч�ķ��������Ը�Ч�������㷨������鲢����������ʹ���㷨������Զ����������ڽ�������������й鲢��һ��ʱ��ǰ��������Ԫ�����С�ں���������Ԫ�أ���������Ϊ0����֮��������Ϊǰ�����鵱ǰ��Ԫ�ظ�����
	private static long counter = 0;

	    public static long distance(int[] a, int[] b) {
	        if (a.length != b.length) {
	            throw new IllegalArgumentException("Array dimensions disagree");
	        }
	        int N = a.length;
	        int[] aIndex = new int[N];// ��¼a���������
	        for (int i = 0; i < N; i++) {
	            aIndex[a[i]] = i;
	        }
	        int[] bIndex = new int[N];// b��������a���������
	        for (int i = 0; i < N; i++) {
	            bIndex[i] = aIndex[b[i]];
	        }
	        return mergeCount(bIndex);
	    }

	    // ʹ�ò������򷽷���������
	    public static long insertionCount(int[] a) {
	        for (int i = 1; i < a.length; i++) {
	            for (int j = i; j > 0 && a[j] < a[j - 1]; j--) {
	                int temp = a[j];
	                a[j] = a[j - 1];
	                a[j - 1] = temp;
	                counter++;// ��������ÿ����һ�Σ��ʹ���һ��������
	            }
	        }
	        return counter;
	    }

	    // ʹ�ù鲢���򷽷���������
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
	                counter += mid - i + 1;// ÿ����ǰ������С�ĺ�������Ԫ�أ�������Ϊǰ���������еĳ���
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
