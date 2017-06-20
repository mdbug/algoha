/**
 * Die Klasse stellt eine Gruppe von Zeichen dar
 */
public class CharGroup {
    // Moegliche Zeichen
    private String chars;
    // Beliebiges Zeichen? (Falls true, wird der Inhalt von chars ignoriert) (Regex: ".")
    private boolean any = false;

    /**
     * Erstellt eine neue Zeichengruppe
     * @param chars String mit allen erlaubten Zeichen
     */
    public CharGroup(String chars) {
        this.chars = chars;
    }

    /**
     * Erstellt eine neue Zeichengruppe, die nur ein Zeichen enthaelt
     * @param c Zeichen
     */
    public CharGroup(char c) {
        this.chars = Character.toString(c);
    }

    /**
     * Erstellt eine neue Zeichengruppe, die ein beliebiges Zeichen darstellt (Regex: ".")
     */
    public CharGroup() {
        any = true;
    }

    /**
     * Testet ob das Zeichen in dieser Zeichengruppe enthalten ist
     * @param c das zu testende Zeichen
     * @return true, falls das Zeichen, in dieser Zeichengruppe enthalten ist. false, sont.
     */
    public boolean test(char c) {
        return any || chars.indexOf(c) != -1;
    }
}
