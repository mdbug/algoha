import java.util.ArrayList;

/**
 * Die Klasse stellt einen Heap dar
 */
public class Heap {
    private ArrayList<Integer> heap;

    public Heap() {
        heap = new ArrayList<Integer>();
        // Das erste Element bleibt leer
        heap.add(null);
    }

    public int getMax() {
        if (heap.isEmpty())
            throw new RuntimeException("Der Heap ist leer");
        swap(2, heap.size() - 1);
        int max = heap.remove(heap.size() - 1);
        downheap();
        return max;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 1; i < heap.size(); ++i) {
            s.append(i).append(" ");
        }
        s.deleteCharAt(s.length() - 1);
        return s.toString();
    }

    public boolean isEmpty() {
        return heap.size() <= 1;
    }

    public void add(int i) {
        heap.add(i);
        upheap();
    }

    private void upheap() {

    }

    private void downheap() {
        int i = 1;
        while(true) {
            int val = heap.get(i);
            int left = i*2;
            int right = i*2 + 1;
            int leftVal = (i*2) < heap.size() ? heap.get(i*2) : Integer.MIN_VALUE;
            int rightVal = (i*2 + 1) < heap.size() ? heap.get(i*2 + 1) : Integer.MIN_VALUE;
            int j = leftVal ==
            if (leftVal > rightVal) {
                if (leftVal > val) {
                    swap(i, i*2);
                    i = i*2;
                }
            } else if (leftVal > val) {
                swap(i, i*2 + 1);
                i = i*2 + 1;
            } else {
                break;
            }
        }
    }

    private void swap(int i, int j) {
        int tmp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, tmp);
    }
}
