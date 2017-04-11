package blatt02;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

/**
 * blatt02
 */
public class Taschenrechner {
    private static HashMap<String, Double> mapVars = new HashMap<>();
    private static HashMap<String, Operator> operatoren = new HashMap<>();
    private static Stack<Double> stack = new Stack<>();

    static {
        operatoren.put("+", (lhs, rhs) -> lhs + rhs);
        operatoren.put("-", (lhs, rhs) -> lhs - rhs);
        operatoren.put("*", (lhs, rhs) -> lhs * rhs);
        operatoren.put("/", (lhs, rhs) -> lhs / rhs);
    }

    private static double eval(String exprUPN) {
        String[] tokens = exprUPN.split("\\s+");
        for (String token : tokens) {
            try {
                Double d = Double.parseDouble(token);
                stack.push(d);
            } catch (NumberFormatException e) {
                if (operatoren.containsKey(token)) {
                    double rhs = stack.pop();
                    double lhs = stack.pop();
                    double result = operatoren.get(token).calc(lhs, rhs);
                    stack.push(result);
                } else if (mapVars.containsKey(token)) {
                    stack.push(mapVars.get(token));
                } else {
                    return Double.NaN;
                }
            }
        }
        if (stack.size() == 1) {
            return stack.pop();
        } else {
            return Double.NaN;
        }
    }

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

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String z = "";
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
