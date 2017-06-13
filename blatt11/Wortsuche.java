import java.util.HashMap;

/**
 * Wortsuche nach Boyer-Moore-Sunday-Verfahren
 */
public class Wortsuche {
    private String text;

    /**
     * Setzt den Text, in dem gesucht wird.
     */
    public Wortsuche(String text) {
        this.text = text;
    }

    /**
     * Gibt die last-Tabelle fuer den String pattern als HashMap
     * zurueck. Nicht vorhandene Zeichen haben keinen Eintrag.
     */
    private HashMap<Character, Integer> getLastTabelle(String pattern) {
        HashMap<Character, Integer> lastTabelle = new HashMap<>();
        for (int i = 0; i < pattern.length(); ++i) {
            lastTabelle.put(pattern.charAt(i), i);
        }
        return lastTabelle;
    }

    /**
     * Ueberprueft, ob der String pattern mit dem kompletten String ab
     * der Position pos in der benoetigten Laenge uebereinstimmt.
     */
    private boolean fits(String pattern, int pos) {
        for (int i = 0; i < pattern.length(); ++i) {
            if (pattern.charAt(i) != text.charAt(pos + i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Sucht den String pattern nach dem Boyer-Moore-Sunday Verfahren
     * undgibt die Index-Position des 1. Vorkommens zurueck. Kommt der
     * String nicht vor, wird -1 zurueckgegeben.
     *
     * @param pattern Suchmuster
     * @return Index-Position des 1. Vorkommens oder -1 falls das Pattern nicht gefunden wird
     */
    public int findFirst(String pattern) {
        if (pattern.length() > text.length()) {
            return -1;
        }

        HashMap<Character, Integer> lastTabelle = getLastTabelle(pattern);
        int pos = 0;
        while (pos <= text.length() - pattern.length()) {
            if (fits(pattern, pos)) {
                return pos;
            } else if (pos == text.length() - pattern.length()) {
                return -1;
            } else {
                char c = text.charAt(pos + pattern.length());
                pos += pattern.length() - lastTabelle.getOrDefault(c, -1);
            }
        }
        return -1;
    }
}
