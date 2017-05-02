/**
 * Die Klasse stellt ein Glied einer doppelt verketteten Liste dar
 */
public class Node<T> {
    private Node<T> previous;
    private Node<T> next;
    private T value;

    /**
     * Konstruiert einen neuen Node
     * @param value Wert
     */
    public Node(T value) {
        this.value = value;
    }

    /**
     * Gibt den Vorgaenger zurueck
     * @return Vorgaenger
     */
    public Node<T> getPrevious() {
        return previous;
    }

    /**
     * Setzt den Vorgaenger
     * @param previous Vorgaenger
     */
    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    /**
     * Gibt den Nachfolger zurueck
     * @return Nachfolger
     */
    public Node<T> getNext() {
        return next;
    }

    /**
     * Setzt den Nachfolger
     * @param next Nachfolger
     */
    public void setNext(Node next) {
        this.next = next;
    }

    /**
     * Gibt den Wert zurueck
     * @return Wert
     */
    public T getValue() {
        return value;
    }

    /**
     * Setzt den Wert
     * @param value Wert
     */
    public void setValue(T value) {
        this.value = value;
    }
}