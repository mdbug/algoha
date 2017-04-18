package blatt01;

import java.util.ArrayList;

/**
 * Das Interface stellt ein Filterkriterium für int Werte da
 */
public interface Filter {
    /**
     * Die Methode filtert ein Array von int Werten
     * @param liste das Array mit Werten, das gefiltert werden soll
     * @return eine ArrayList mit den Werten, die durch den Filter kommen
     */
    ArrayList<Integer> filter(int[] liste);
}
