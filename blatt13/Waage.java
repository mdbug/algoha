import java.util.ArrayList;

/**
 * Die Klasse stellt eine Balkenwaage dar
 */
public class Waage {
    private int[] gewichte = {1, 3, 8, 20};

    /**
     * Testet ob, ob das Artikelgewicht mit den verfuegbaren Gewichten gewogen werden kann
     * und gibt die Kombinationen auf dem Bildschirm aus
     * @param artikelGewicht Gewicht des Artikels
     */
    public void test(int artikelGewicht) {
        test(new ArrayList<>(), 0, 0, artikelGewicht);
    }

    private void test(ArrayList<Integer> kombination, int sum, int i, int artikelGewicht) {
        if (i == gewichte.length) {
            if (Math.abs(sum) == artikelGewicht) {
                System.out.println(kombination);
                printSymmetrischeKombination(kombination);
            }
            return;
        }
        test(kombination, sum, i + 1, artikelGewicht);

        kombination.add(gewichte[i]);
        test(kombination, sum + gewichte[i], i + 1, artikelGewicht);
        kombination.remove(kombination.size() - 1);

        // Symmetrie
        if (Math.abs(sum - gewichte[i]) != Math.abs(sum + gewichte[i])) {
            kombination.add(-gewichte[i]);
            test(kombination, sum - gewichte[i], i + 1, artikelGewicht);
            kombination.remove(kombination.size() - 1);
        }
    }

    private void printSymmetrischeKombination(ArrayList<Integer> kombination) {
        StringBuilder s = new StringBuilder("[");
        for(int i = 0; i < kombination.size(); ++i) {
            s.append(-kombination.get(i));
            if (i < kombination.size() - 1) {
                s.append(", ");
            }
        }
        s.append("]");
        System.out.println(s);
    }
}
