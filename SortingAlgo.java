public class SortingAlgo {

    public static void InsertionSorted(int[] arr, int l, int r) {
        for (int i = l + 1; i < r; i++) {
            int index = i;
            while (index != l && arr[index] < arr[index - 1]) {
                int temp = arr[index];
                arr[index] = arr[index - 1];
                arr[index - 1] = temp;
                index -= 1;
            }
        }
    }

    public static void MergeSorted(int[] arr, int l, int r, int cutoff) {
        int n = r - l;
        cutoff = Math.max(cutoff, 2);
        if (n < cutoff) {
            InsertionSorted(arr, l, r);
        } else {
            int mid = l + n / 2;
            MergeSorted(arr, l, mid, cutoff);
            MergeSorted(arr, mid, r, cutoff);
            int cursor1 = l + 0;
            int cursor2 = mid;
            int[] tmp = new int[n];
            while (cursor1 + cursor2 - mid - l < n) {
                while (
                    cursor1 < mid &&
                    (cursor2 == r || arr[cursor1] < arr[cursor2])
                ) {
                    tmp[cursor1 + cursor2 - mid - l] = arr[cursor1];
                    cursor1 += 1;
                }
                while (
                    cursor2 < r &&
                    (cursor1 == mid || arr[cursor2] <= arr[cursor1])
                ) {
                    tmp[cursor1 + cursor2 - mid - l] = arr[cursor2];
                    cursor2 += 1;
                }
            }
            for (int i = 0; i < n; i++) {
                arr[l + i] = tmp[i];
            }
        }
    }
}
