/**
 * Test der Klasse Klammer
 */
public class TestKlammer {
    public static void main(String[] args) {
        String[] tests = {
                "( ( [ [ ] ] ) )",
                "( [ ) ]",
                "( [ ] ] )",
                "( ( ) ) )",
                "( ( )",
                "( { [ ] ) }",
                "Gar keine Klammern"
        };

        for(String s : tests) {
            System.out.printf("%-20s -> %b\n", s, Klammer.isEbenentreu(s));
        }
    }
}
