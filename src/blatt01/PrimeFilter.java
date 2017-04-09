package blatt01;

import java.util.ArrayList;

/**
 * Laesst nur Primzahlen durch
 */
public class PrimeFilter implements Filter {

    private boolean isPrime(int val) {
        if (val < 2)
            return false;
        if (val == 2)
            return true;
        if (val % 2 == 0)
            return false;
        for (int i = 3; i * i <= val; i +=2) {
            if (val % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayList<Integer> filter(int[] liste) {
        ArrayList<Integer> primes = new ArrayList<>();
        for (int i : liste) {
            if (isPrime(i)) {
                primes.add(i);
            }
        }
        return primes;
    }
}
