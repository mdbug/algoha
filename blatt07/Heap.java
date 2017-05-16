import java.util.ArrayList;

/**
 * Die Klasse stellt einen Heap dar
 */
public class Heap {
    private ArrayList<Integer> data;

    /**
     * Konstruiert einen neuen leeren Heap
     */
    public Heap() {
        data = new ArrayList<>();
        // Das erste Element wird ignoriert
        data.add(1337);
    }

    /**
     * Entfernt das groesste Element vom Heap und gibt es zurueck
     * @return das groesste Element
     */
    public int getMax() {
        if (data.isEmpty())
            throw new RuntimeException("Der Heap ist leer");

        swap(1, data.size() - 1);
        int max = data.remove(data.size() - 1);
        downheap();
        return max;
    }

    /**
     * Gibt das Feld (den Heap) in Stringdarstellung zurueck
     * @return das Feld (den Heap) in Stringdarstellung
     */
    public String toString() {
        StringBuilder s = new StringBuilder("[");
        for (int i = 1; i < data.size(); ++i) {
            s.append(data.get(i));
            if (i < data.size() - 1) {
                s.append(", ");
            }
        }
        s.append(']');
        return s.toString();
    }

    /**
     * Gibt zurueck, ob der Heap leer ist
     * @return true, falls der Heap leer ist. false, sonst.
     */
    public boolean isEmpty() {
        return data.size() <= 1;
    }


    /**
     * Fuegt den Wert i zum Heap hinzu
     * @param i Wert, der hinzugefuegt werden soll
     */
    public void add(int i) {
        data.add(i);
        upheap();
    }

    private void upheap() {
        int i = data.size() - 1;
        while (i > 1 && data.get(i / 2) < data.get(i)) {
            swap(i, i / 2);
            i = i / 2;
        }
    }

    private void downheap() {
        int i = 1;
        while(i < data.size()) {
            int val = data.get(i);
            int left = i * 2;
            int right = i * 2 + 1;
            int leftVal = (i * 2) < data.size() ? data.get(i * 2) : Integer.MIN_VALUE;
            int rightVal = (i * 2 + 1) < data.size() ? data.get(i * 2 + 1) : Integer.MIN_VALUE;
            if (leftVal > rightVal) {
                if (leftVal > val) {
                    swap(i, i*2);
                    i = left;
                } else {
                    break;
                }
            } else if (rightVal > val) {
                swap(i, i*2 + 1);
                i = right;
            } else {
                break;
            }
        }
    }

    private void swap(int i, int j) {
        int tmp = data.get(i);
        data.set(i, data.get(j));
        data.set(j, tmp);
    }
}
