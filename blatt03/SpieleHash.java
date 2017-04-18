import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Blatt03
 * Speichert eine Liste von Spielnamen in einer Hashtabelle
 */
public class SpieleHash {
    final int SIZE = 53;
    private String[] list = new String[SIZE];

    /**
     * Berechnet h0
     * @param spiel Name des Spiels
     * @return Hash-Index h0
     */
    private int hashIndex(String spiel) {
        return Math.abs(spiel.hashCode()) % 100;
    }

    /**
     * Berechnet Inkrement
     * @param spiel Name des Spiels
     * @return Inkrement
     */
    private int hashIncrement(String spiel) {
        return ((Math.abs(spiel.hashCode()) % 100000) / 1000) + 1;
    }

    /**
     * Fügt ein Spiel zur Liste hinzu
     * @param spiel Name des Spiels
     * @throws RuntimeException Falls das Spiel nicht mehr in die Tabelle ingefuegt werden kann
     */
    public void add(String spiel) throws RuntimeException {
        int h0 = hashIndex(spiel) % SIZE;
        int index = h0;
        while(list[index] != null) {
            index += hashIncrement(spiel);
            index %= SIZE;
            if (index == h0) {
                throw new RuntimeException("Element kann nicht eingefuegt werden");
            }
        }
        list[index] = spiel;
    }

    /**
     * Ist spiel in der Liste?
     * @param spiel Name des Spiels
     * @return true, falls das Spiel in der Liste vorhanden ist. false, sonst.
     */
    public boolean contains(String spiel) {
        int h0 = hashIndex(spiel) % SIZE;
        int index = h0;
        while(!spiel.equals(list[index])) {
            if (list[index] == null) {
                return false;
            }
            index += hashIncrement(spiel);
            index %= SIZE;
            if (index == h0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Test
     * @param args Wird ignoriert
     */
    public static void main(String[] args) {
        SpieleHash spiele = new SpieleHash();
        File file =  new File("blatt03/games50.txt");
        try(Scanner scanner = new Scanner(file)) {
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String game = line.substring(line.indexOf('\t') + 1, line.lastIndexOf('\t'));
                spiele.add(game.trim());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String[] tests = {"FIFA 16", "Star Wars: Battlefront", "WOW"};
        for (String s : tests) {
            System.out.println(s + " => " + (spiele.contains(s) ? "ok" : "not ok"));
        }
    }
}