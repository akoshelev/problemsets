public class MinPQ {

    private int[] _arr;
    private int _idx;

    MinPQ(int capacity) {

        _arr = new int[capacity + 1];
        _idx = 0;
    }

    public void insert(int item) {
        _arr[++_idx] = item;
        swim(_idx);
    }

    public int min() {
        return _arr[1];
    }

    public int size() {
        return _idx;
    }

    public int delMin() {
        int minIdx = _idx;
        swap(1, _idx--);
        sink(1);
        return _arr[minIdx];
    }

    private void swim(int idx) {
        while (idx > 1 && _arr[idx] < _arr[idx / 2]) {
            swap(idx, idx / 2);
            idx = idx / 2;
        }
    }

    private void sink(int idx) {
        while (2 * idx <= _idx) {
            int j = 2 * idx;

            // choose the smallest child and swap (if greater that it)
            if (j < _idx && _arr[j] > _arr[j+1]) j++;
            if (_arr[idx] > _arr[j]) {
                swap(idx, j);
                idx = j;
            } else break;
        }
    }

    private void swap(int i, int j) {
        int temp = _arr[i];
        _arr[i] = _arr[j];
        _arr[j] = temp;
    }
}
