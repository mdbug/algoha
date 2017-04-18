package blatt01;

import java.util.ArrayList;

/**
 * Laesst nu Zahlen mit 2 Ziffern durch, also 10 bis 99 und -10 bis -99
 */
public class TwoDigitsFilter implements Filter {

    private boolean hasTwoDigits(int val) {
        int abs = Math.abs(val);
        return abs >= 10 && abs <= 99;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayList<Integer> filter(int[] liste) {
        ArrayList<Integer> twoDigitNumbers = new ArrayList<>();
        for (int i : liste) {
            if (hasTwoDigits(i)) {
                twoDigitNumbers.add(i);
            }
        }
        return twoDigitNumbers;
    }
}
