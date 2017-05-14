import java.util.ArrayList;
import java.util.Collections;

/**
 * Die Klasse stellt einen B-Baum der Ordnung 2 dar
 */
public class BTree {
    public final static int ORDNUNG = 2;
    private BTreeNode root = new BTreeNode();

    /**
     * Gibt zurueck, ob der Wert x im B-Baum enthalten ist
     *
     * @param x zu suchender Wert
     * @return true, falls x im B-Baum ist. false, sonst.
     */
    public boolean search(int x) {
        return search(x, root).found;
    }

    private static class SearchResult {
        public final boolean found;
        public final BTreeNode node;
        public final int position;

        protected SearchResult(boolean found, BTreeNode node, int position) {
            this.found = found;
            this.node = node;
            this.position = position;
        }
    }

    private SearchResult search(int x, BTreeNode node) {
        assert node != null;
        int i = Collections.binarySearch(node.values, x);
        if (i >= 0) {
            return new SearchResult(true, node, i);
        } else {
            if (-i - 1 >= node.children.size() || node.children.get(-i - 1) == null) {
                return new SearchResult(false, node, -i - 1);
            }
            return search(x, node.children.get(-i - 1));
        }
    }

    /**
     * Fuegt den Wert x in den Baum ein.
     * Der Rueckgabewert zeigt an, welche Operation dazu noetig war.
     *
     * @param x Wert, der in den Baum eingefuegt werden soll.
     * @return 0 Der Wert konnte einfach eingefuegt werden.
     * 1 Die Anzahl der Knoten waechst um 1.
     * 2 Die Anzahl der Ebenen waechst um 1.
     */
    public int insert(int x) {
        assert root != null;
        return insert(x, root);
    }

    private int insert(int x, BTreeNode node) {
        assert node != null;
        SearchResult searchResult = search(x, node);

        if (searchResult.found) {
            throw new ArithmeticException("Der Wert ist schon vorhanden");
        }

        return insertIntoNode(x, searchResult.node, searchResult.position);
    }

    private int insertIntoNode(int x, BTreeNode node, int index) {
        assert index <= node.values.size();
        node.values.add(index, x);

        int returnValue = 0;
        int size = node.values.size();
        if (size > 2 * ORDNUNG) {
            returnValue = 1;
            BTreeNode right = splitNode(node);

            if (node == root) {
                root = new BTreeNode();
                node.parent = root;
                root.children.add(0, node);
                returnValue = 2;
            }

            int center = node.values.remove(size / 2);
            int i = Collections.binarySearch(node.parent.values, center);
            node.parent.children.add(-i, right);
            right.parent = node.parent;
            returnValue = Math.max(returnValue, insertIntoNode(center, node.parent, -i - 1));
        }
        return returnValue;
    }

    private BTreeNode splitNode(BTreeNode node) {
        BTreeNode right = new BTreeNode();
        int size = node.values.size();
        right.values = new ArrayList<>(node.values.subList(size / 2 + 1, size));
        for (int j = size - 1; j > size / 2; --j) {
            node.values.remove(j);
        }
        if (node.children.size() > 0) {
            right.children = new ArrayList<>(node.children.subList(size / 2 + 1, size));
            for (int j = size - 1; j > size / 2; --j) {
                node.children.remove(j);
            }
        }
        return right;
    }

    /**
     * Gibt den Baum in-order als String aus
     * @return String
     */
    public String toString() {
        return toString(root);
    }

    private String toString(BTreeNode node) {
        if (node == null)
            return "";

        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < node.values.size() + 1; ++i) {
            if (i < node.children.size()) {
                sb.append(toString(node.children.get(i)));
            }
            if (i < node.values.size()) {
                sb.append(node.values.get(i));
                sb.append(" ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
