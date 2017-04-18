package blatt01;

import java.util.Arrays;

/**
 * Die Klasse fasst drei int-Werte zusammen
 */
public class Tripel {
    private int[] werte;

    /**
     * Konstruktor
     * Erzeugt ein neues Trippel-Objekt
     * @param x 1. Wert
     * @param y 2. Wert
     * @param z 3. Wert
     */
    public Tripel(int x, int y, int z) {
       werte = new int[] {x, y, z};
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "(" + werte[0] + "/" + werte[1] + "/" + werte[2] + ")";
    }
}
