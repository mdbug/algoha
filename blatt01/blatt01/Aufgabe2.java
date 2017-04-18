package blatt01;

/**
 * blatt01
 */
public class Aufgabe2 {
    /**
     * Gibt eine gefilterte Liste von int Werten auf dem Bildschirm aus
     * @param liste die int Werte, die gefiltert werden sollen
     * @param filter der Filter
     */
    public static void print(int[] liste, Filter filter) {
        System.out.println(filter.filter(liste));
    }

    public static void main(String[] args) {
        int[] test = {5,9,2,7,2,6,7,12,5,43,4};
        print(test, new PrimeFilter());
        print(test, new TwoDigitsFilter());
        print(test, new UniqueFilter());
    }
}
