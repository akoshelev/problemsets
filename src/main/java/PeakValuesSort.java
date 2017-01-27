public final class PeakValuesSort {



    public static void sort(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            boolean isPeak = i % 2 == 0;
            if (isPeak && i < arr.length - 1 && arr[i] < arr[i + 1])
                swap(arr, i, i + 1);
            else if (!isPeak && i < arr.length - 1 && arr[i] > arr[i + 1])
                swap(arr, i, i + 1);
        }

        assert isSorted(arr);
    }

    private static boolean isSorted(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            boolean isPeak = i % 2 == 0;
            if (isPeak) {
                int left = i > 0 ? arr[i - 1] : Integer.MIN_VALUE;
                int right = i < arr.length - 1 ? arr[i + 1] : Integer.MIN_VALUE;
                assert(arr[i] >= left && arr[i] <= right);
            } else {
                int left = arr[i - 1];
                int right = i < arr.length - 1 ? arr[i + 1] : Integer.MAX_VALUE;
                assert(arr[i] <= left && arr[i] >= right);
            }
        }

        return true;
    }

    private static void swap(Integer[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
