/**
 * Die Klasse implementiert den Dijkstraalgorithmus
 */
public class Dijkstra {
    /**
     * Die Methode gibt das DijkstraSchema
     * mit dem kürzesten Abstand von Knoten 1 zu allen anderen Knoten
     * formatiert auf dem Bildschirm aus
     *
     * @param kanten Kantenliste. Das erste Element ist die Anzahl der Knoten. Darauf
     *               folgen für jede Kante jeweils 3 weitere Elemente in der Reihenfolge „Ausgangsknoten, Zielknoten,
     *               Gewicht“
     */
    public static void printDijkstra(int[] kanten) {
        int anzahlKnoten = kanten[0];
        int[][] adjazenzmatrix = getAdjazenzmatrix(kanten);
        boolean[] bekannt = new boolean[anzahlKnoten + 1];
        int[] d = new int[anzahlKnoten + 1];
        int[] p = new int[anzahlKnoten + 1];

        printTableHeader(anzahlKnoten);

        int v = 1;
        while (v != -1) {
            bekannt[v] = true;
            int next = -1;
            int min = Integer.MAX_VALUE;
            for (int i = 2; i <= anzahlKnoten; ++i) {
                if (!bekannt[i]) {
                    int gewicht = adjazenzmatrix[v][i];
                    if (gewicht != 0 && (d[i] == 0 || d[v] + gewicht < d[i])) {
                        d[i] = d[v] + gewicht;
                        p[i] = v;
                    }
                    if (d[i] != 0 && d[i] < min) {
                        min = d[i];
                        next = i;
                    }
                }
            }
            printTableRow(v, d, p);
            v = next;
        }
    }

    private static void printTableHeader(int anzahlKnoten) {
        StringBuilder header = new StringBuilder();
        for (int i = 2; i <= anzahlKnoten; ++i) {
            header.append(String.format("%2d ", i));
        }
        header.append("|");
        header.append(header);
        header.insert(0, "vi|");
        header.append('\n');
        for (int i = 0; i < anzahlKnoten * 2; ++i) {
            header.append("---");
        }
        System.out.println(header);
    }

    private static void printTableRow(int v, int[] d, int[] p) {
        System.out.printf("%2d|", v);
        for (int i = 2; i < d.length; ++i) {
            if (d[i] != 0) {
                System.out.printf("%2d ", d[i]);
            } else {
                System.out.print("-- ");
            }
        }
        System.out.print('|');
        for (int i = 2; i < p.length; ++i) {
            if (p[i] != 0) {
                System.out.printf("%2d ", p[i]);
            } else {
                System.out.print("-- ");
            }
        }
        System.out.print("|\n");
    }

    private static int[][] getAdjazenzmatrix(int[] kanten) {
        int[][] matrix = new int[kanten[0] + 1][kanten[0] + 1];
        for (int i = 1; i < kanten.length; ++i) {
            int start = kanten[i];
            ++i;
            int ziel = kanten[i];
            ++i;
            int gewicht = kanten[i];
            matrix[start][ziel] = gewicht;
        }
        return matrix;
    }
}
