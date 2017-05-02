/**
 * Deque, umgesetzt mit einer doppelt verketteten Liste
 */
public class MyDeque<T> {
    private Node<T> start = null;
    private Node<T> end = null;
    private int size = 0;

    /**
     * Fügt das ein Element am Anfang der Liste ein
     * @param element Das Element welches eingefügt werden soll
     */
    public void addFirst(T element) {
        Node<T> node = new Node<>(element);
        node.setNext(start);
        start = node;
        if (size == 0) {
            end = node;
        }
        ++size;
    }

    /**
     * Fügt das ein Element am Ende der Liste ein
     * @param element Das Element welches eingefügt werden soll
     */
    public void addLast(T element) {
        Node<T> node = new Node<>(element);
        node.setPrevious(end);
        end = node;
        if (size == 0) {
            start = node;
        }
        ++size;
    }

    /**
     * Entfernt das erste Element aus der Liste und gibt den Wert des Elements zurück.
     * @return den Wert des ersten Elements oder null, falls die Liste leer ist
     */
    public T removeFirst() {
        if (start == null) {
            return null;
        }
        T first = start.getValue();
        if (size > 1) {
            start = start.getNext();
        } else {
            start = null;
            end = null;
        }
        --size;
        return first;
    }

    /**
     * Entfernt das letzte Element aus der Liste und gibt den Wert des Elements zurück.
     * @return den Wert des letzten Elements oder null, falls die Liste leer ist
     */
    public T removeLast() {
        if (end == null) {
            return null;
        }
        T last = end.getValue();
        if (size > 1) {
            end = end.getPrevious();
        } else {
            start = null;
            end = null;
        }
        --size;
        return last;
    }

    /**
     * Gibt die Anzahl der Elemente in dieser Liste zurück
     * @return die Anzahl der Elemente in dieser Liste
     */
    public int size() {
        return size;
    }
}
