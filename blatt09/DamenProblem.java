import java.util.ArrayList;

/**
 * Klasse zur Loesung des Damenproblems
 */
public class DamenProblem {
    /**
     * Gibt alle Loesungen des Damen-Problems zu einem Schachbrett mit uebergebener Groesse aus.
     * @param brettgroesse Groesse des Bretts
     */
    public static void damenProblem(int brettgroesse) {
        ArrayList<ArrayList<Integer>> basket = new ArrayList<>();
        damenProblem(brettgroesse, new ArrayList<>(brettgroesse), basket);

        for (ArrayList<Integer> loesung : basket) {
            printGraphisch(loesung);
//            printLoesung(loesung);
        }
    }

    private static void damenProblem(int n, ArrayList<Integer> feld, ArrayList<ArrayList<Integer>> basket) {
        int spalte = feld.size();
        if (spalte == n) {
            basket.add(new ArrayList<>(feld));
            return;
        }

        OUT: for (int i = 0; i < n; ++i) {
            for (int j = 0; j < feld.size(); ++j) {
                int dame = feld.get(j);
                if (dame == i || (Math.abs(dame - i) == Math.abs(spalte - j))) {
                    continue OUT;
                }
            }
            feld.add(i);
            damenProblem(n, feld, basket);
            feld.remove(feld.size() - 1);
        }
    }

    private static void printLoesung(ArrayList<Integer> loesung) {
        StringBuilder s = new StringBuilder("[");
        for (int i = 0; i < loesung.size(); i++) {
            s.append(loesung.get(i) + 1);
            if (i < loesung.size() - 1) {
                s.append(", ");
            }
        }
        s.append("]");
        System.out.println(s);
    }

    private static void printGraphisch(ArrayList<Integer> loesung) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < loesung.size(); i++) {
            s.append("+-");
        }
        s.append("+\n");
        for (int i = 0; i < loesung.size(); i++) {
            for (int j = 0; j < loesung.size(); j++) {
                s.append('|');
                if (loesung.get(j) == i) {
                    s.append('x');
                } else {
                    s.append(' ');
                }
            }
            s.append("|\n");
        }
        for (int i = 0; i < loesung.size(); i++) {
            s.append("+-");
        }
        s.append("+\n");
        System.out.print(s);
    }

    /**
     * Test
     * @param args
     */
    public static void main(String[] args) {
        damenProblem(6);
    }
}
