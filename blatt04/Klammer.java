/**
 * Die Klasse enthät eine Funktion um zu testen ob die Klammern in einem String ebenentreu sind
 */
public class Klammer {
    private static final String KLAMMERN = "([{}])";

    /**
     * Testet ob die Klammern im übergebenen String ebenentreu(TM) sind
     * @param s zu testender String
     * @return true, falls die Klammern ebenentreu(TM) sind. false, sonst.
     */
    public static boolean isEbenentreu(String s) {
        MyDeque<Character> stack = new MyDeque<>();
        for (int i = 0; i < s.length(); ++i) {
            char zeichen = s.charAt(i);
            if (isKlammerAuf(zeichen)) {
                stack.addLast(zeichen);
            } else if (isKlammerZu(zeichen)){
                Character letzteKlammerAuf = stack.removeLast();
                if (letzteKlammerAuf == null || !isPaar(zeichen, letzteKlammerAuf)) {
                    return false;
                }
            }
        }
        return stack.size() == 0;
    }

    private static boolean isKlammerAuf(char c) {
        int i = KLAMMERN.indexOf(c);
        return i != -1 && i < (KLAMMERN.length() / 2);
    }

    private static boolean isKlammerZu(char c) {
        int i = KLAMMERN.indexOf(c);
        return i >= (KLAMMERN.length() / 2);
    }

    private static boolean isPaar(char k, char l) {
        int i = KLAMMERN.indexOf(k);
        int j = KLAMMERN.indexOf(l);
        return KLAMMERN.length() - j - i == 1;
    }
}