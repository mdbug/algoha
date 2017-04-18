package blatt01;

import java.util.ArrayList;

/**
 * Laesst keine Zahlen durch, die in der Folge bereits vorkamen
 */
public class UniqueFilter implements Filter {
    /**
     * {inheritDoc}
     */
    @Override
    public ArrayList<Integer> filter(int[] liste) {
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i : liste) {
            if (!numbers.contains(i)) {
                numbers.add(i);
            }
        }
        return numbers;
    }
}
