import java.util.ArrayList;
import java.util.regex.PatternSyntaxException;

public class Regex {
    /**
     * Sucht ein Pattern in einem Text und gibt eine ArrayList zurueck, in der
     * die Startindizes aller Vorkommen des Patterns im Text gespeichert sind. Die Vorkommen duerfen sich
     * dabei auch ueberschneiden.
     *
     * @param text Der Text in dem gesucht werden soll
     * @param pattern Das Pattern, das gesucht werden soll
     * @return ArrayList mit Startindizes aller Vorkommen des Patterns im Text
     */
    public static ArrayList<Integer> textSearch(String text, String pattern) {
        ArrayList<CharGroup> charList = compilePattern(pattern);
        ArrayList<Integer> matches = new ArrayList<>();

        for (int i = 0; i <= text.length() - charList.size(); ++i) {
            if (testPattern(text, i, charList)) {
                matches.add(i);
            }
        }
        return matches;
    }

    private static boolean testPattern(String text, int i, ArrayList<CharGroup> charList) {
        for (int j = 0; j < charList.size(); ++j) {
            if (!charList.get(j).test(text.charAt(i + j))) {
                return false;
            }
        }
        return true;
    }

    private static ArrayList<CharGroup> compilePattern(String pattern) {
        ArrayList<CharGroup> charList = new ArrayList<>();
        for (int i = 0; i < pattern.length(); ++i) {
            char c = pattern.charAt(i);
            if (c == '.') {
                charList.add(new CharGroup());
            } else if (c == '[') {
                int closingBracketPosition = pattern.indexOf(']', i);
                if (closingBracketPosition == -1) {
                    throw new PatternSyntaxException("[ without matching ]", pattern, i);
                }
                String chars = pattern.substring(i + 1, closingBracketPosition);
                charList.add(new CharGroup(chars));
                i = closingBracketPosition;
            } else if (c == '\\') {
                charList.add(new CharGroup(pattern.charAt(i + 1)));
                ++i;
            } else {
                charList.add(new CharGroup(c));
            }
        }
        return charList;
    }

    public static void main(String[] args) {
        System.out.println(textSearch("abcabcdababdc.", "ab")); //0, 3, 7, 9
        System.out.println(textSearch("abcabcdababdc.", "c.")); //2, 5, 12
        System.out.println(textSearch("abcabcdababdc.", "c\\.")); //12
        System.out.println(textSearch("abcabcdababdc.", "b[cd]")); //1,4,10
        System.out.println(textSearch("abcabcdababdc.", "a....c")); //0,7
        System.out.println(textSearch("a[aababa][ab]a", "a[ab]a")); //3,5
        System.out.println(textSearch("a[aababa][ab]a", "a.\\[a")); //7
    }
}
