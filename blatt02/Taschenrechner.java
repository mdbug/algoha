import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

/**
 * Blatt 2
 * UPN-Kommandozeilen-Taschenrechner
 */
public class Taschenrechner {
    private static HashMap<String, Double> mapVars = new HashMap<>();
    private static HashMap<String, Operator> operators = new HashMap<>();
    private static Stack<Double> stack = new Stack<>();

    static {
        operators.put("+", (lhs, rhs) -> lhs + rhs);
        operators.put("-", (lhs, rhs) -> lhs - rhs);
        operators.put("*", (lhs, rhs) -> lhs * rhs);
        operators.put("/", (lhs, rhs) -> lhs / rhs);
    }

    /**
     * Funktion zur Auswertung eines UPN-Ausdrucks
     * @param exprUPN Ein UPN-Ausdruck
     * @return Das Ergebis des UPN-Ausdrucks / Im Falle eines Fehlers NaN
     */
    private static double eval(String exprUPN) {
        String[] tokens = exprUPN.split("\\s+");
        for (String token : tokens) {
            try {
                if (operators.containsKey(token)) {
                    double rhs = stack.pop();
                    double lhs = stack.pop();
                    double result = operators.get(token).calc(lhs, rhs);
                    stack.push(result);
                } else if (mapVars.containsKey(token)) {
                    stack.push(mapVars.get(token));
                } else {
                    Double d = Double.parseDouble(token);
                    stack.push(d);
                }
            } catch (NumberFormatException | EmptyStackException e) {
                return Double.NaN;
            }
        }
        if (stack.size() == 1) {
            return stack.pop();
        } else {
            return Double.NaN;
        }
    }

    /**
     * Funktion, die entweder einen uebergebenen Ausdruck auswertet und das Ergebnis als String zurueckgibt,
     * oder im Fall der Form „VarName = UPN-Ausdruck“ den Wert des Ausdrucks ermittelt und die
     * Variable in mapVars mit diesem Wert anlegt bzw. ersetzt.
     * @param expr UPN-Ausdruck
     * @return Ergebnis String
     */
    public static String analyze(String expr) {
        String[] hs = expr.split("\\s*=\\s*");
        switch (hs.length) {
            case 1:
                return "result: " + eval(expr);
            case 2:
                String var = hs[0];
                double val = eval(hs[1]);
                mapVars.put(hs[0].trim(), eval(hs[1]));
                return "result: " + var + "=" + val;
            default:
                return "Error: Invalid Expression";
        }
    }

    /**
     * Kommandozeilen-Taschenrechner
     * @param args
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String z;
        while (true) {
            z = in.nextLine();
            if (z.equals("quit")) {
                break;
            }
            if (z.equals("clear")) {
                mapVars.clear();
            }
            System.out.println("task '" + z + "' => " + analyze(z));
        }
    }
}