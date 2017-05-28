import java.io.InputStream;
import java.util.*;

/**
 * Die Klasse repraesentiert einen Graphen
 */
public class Graph {
    private ArrayList<SortedSet<Integer>> adjazenzliste;

    /**
     * Erzeugt einen leeren Graphen mit v Knoten
     * @param v Anzahl der Knoten
     */
    public Graph(int v) {
        adjazenzliste = new ArrayList<>();
        for (int i = 0; i < v; ++i) {
            adjazenzliste.add(new TreeSet<>());
        }
    }

    /**
     * Erzeugt einen Graphen aus der Kantenliste list
     * @param list Kantenliste
     */
    public Graph(int[] list) {
        this(list[0]);
        int kanten = list[1];
        for (int i = 0; i < kanten; ++i) {
            adjazenzliste.get(list[i * 2 + 2] - 1).add(list[i * 2 + 3] - 1);
        }
    }

    /**
     * Erzeugt einen Graphen mit v Knoten und e zufaelligen Kanten
     * @param v Anzahl der Knoten
     * @param e Anzahl der Kanten
     */
    public Graph(int v, int e) {
        this(v);
        for (int i = 0; i < e; ++i) {
            boolean erfolg;
            do {
                erfolg = addEdge((int) (Math.random() * v) + 1, (int) (Math.random() * v) + 1);
            } while(!erfolg);
        }
    }

    /**
     * Erzeugt einen Graphen aus der Adjazenzliste, die aus dem
     * InputStream in geladen wird. Eintraege in einer Zeile
     * sind jeweils durch ein Semikolon getrennt.
     * @param in InputStream
     */
    public Graph(InputStream in) {
        try(Scanner scanner = new Scanner(in)) {
            int node = 1;
            while(scanner.hasNextLine()) {
                adjazenzliste.add(new TreeSet<>());
                String line = scanner.nextLine();
                String[] tokens = line.split("\\s*;\\s*");
                for (String token : tokens) {
                    addEdge(node, Integer.parseInt(token));
                }
                ++node;
            }
        }
    }

    /**
     * Gibt die Anzahl der Knoten zurueck
     * @return Anzahl der Knoten
     */
    public int getVertexCount() {
        return adjazenzliste.size();
    }

    /**
     * Gibt die Anzahl der Kanten zurueck
     * @return Anzahl der Kanten
     */
    public int getEdgeCount() {
        int kanten = 0;
        for (SortedSet<Integer> node : adjazenzliste) {
            kanten += node.size();
        }
        return kanten;
    }

    /**
     * Fuegt eine Kante von from zu to hinzu
     * @param from Ausgangsknoten
     * @param to Zielknoten
     * @return true, falls die Kante hinzugefuegt werden konnte. false, sonst.
     */
    public boolean addEdge(int from, int to) {
        return from < adjazenzliste.size() && adjazenzliste.get(from - 1).add(to - 1);
    }

    /**
     * Gibt die Liste der direkten Nachfolger von v zurueck
     * @param v Knoten
     * @return Liste der direkten Nachfolger von v
     */
    public ArrayList<Integer> getAdjacent(int v) {
        return new ArrayList<>(adjazenzliste.get(v));
    }

    /**
     * Gibt eine Stringrepraesentation zurueck
     * @return String
     */
    public String toString() {
        StringBuilder s = new StringBuilder("[");

        for (SortedSet<Integer> node : adjazenzliste) {
            s.append('[');
            for (int edge : node) {
                s.append(edge + 1).append(',');
            }
            s.setCharAt(s.length() - 1, ']');
        }
        s.append(']');
        return s.toString();
    }

    /**
     * Durchlauft den Graphen nach der Tiefensuche.
     * @param start Startknoten
     * @return eine Liste mit der Reihenfolge der durchlaufenen Knoten zurueck
     */
    public ArrayList<Integer> dfs(int start) {
        ArrayList<Integer> list = new ArrayList<>();
        dfs(start-1, list);
        return list;
    }

    private void dfs(int start, ArrayList<Integer> list) {
        if (list.indexOf(start+1) != -1) {
            return;
        }
        list.add(start + 1);
        for (int child : adjazenzliste.get(start)) {
            dfs(child, list);
        }
    }

    /**
     * Durchlauft den Graphen nach der Breitensuche.
     * @param start Startknoten
     * @return eine Liste mit der Reihenfolge der durchlaufenen Knoten zurueck
     */
    public ArrayList<Integer> bfs(int start) {
        Queue<Integer> queue = new ArrayDeque<>();
        ArrayList<Integer> ret = new ArrayList<>();
        boolean[] visited = new boolean[adjazenzliste.size()];
        queue.add(start-1);
        ret.add(start);
        visited[start-1] = true;
        while(!queue.isEmpty()) {
            int u = queue.poll();
            for (int w : adjazenzliste.get(u)) {
                if (!visited[w]) {
                    queue.add(w);
                    ret.add(w+1);
                    visited[w] = true;
                }
            }
        }
        return ret;
    }
}
