/**
 * Testklasse fuer Heap
 */
public class TestHeap {
    public static void main(String[] args) {
        Heap heap = new Heap();
        System.out.println(heap);
        int[] elements = {1, 6, 8, 18, 23, 5, 17, 20, 26, 21, 9};
        for(int k : elements) {
            heap.add(k);
            System.out.println(heap);
        }
        while(!heap.isEmpty()) {
            int max = heap.getMax();
            System.out.println(heap);
        }
    }
}
