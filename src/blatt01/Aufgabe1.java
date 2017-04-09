package blatt01;

import java.util.ArrayList;

/**
 * blatt01
 */
public class Aufgabe1 {
    /**
     * Gibt alle Zahlentripel a, b, c zurückgibt, die gleichzeitig die folgenden 4 Eigenschaften erfüllen:
     * a,b,c \in N
     * a < b < c
     * a^2 + b^2 = c^2
     * a + b + c = n
     *
     * @param n Parameter n (s.o.)
     * @return Eine ArrayList mit Tripeln, die die Eigenschaften erfüllen
     */
    public static ArrayList<Tripel> getTripel(int n) {
        ArrayList<Tripel> list = new ArrayList<>();
        for (int a = 1; a <= n-2; ++a) {
            for (int b = a+1; b <= n-a-1; ++b) {
                int c = n - b - a;
                if (a*a + b*b == c*c) {
                    list.add(new Tripel(a, b, c));
                }
            }
        }
        return list;
    }

    public static void main(String[] args) {
        System.out.println("n = 30:");
        System.out.println(getTripel(30));
        System.out.println("n = 252:");
        System.out.println(getTripel(252));
    }
}
