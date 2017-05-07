/**
 * PACKAGE_NAME
 */
public class BinTree {
    private TreeNode root;

    /**
     * Suchen eines Wertes. Falls x nicht vorhanden ist, wird null zurückgegeben.
     * @param x Such-Wert
     * @return TreeNode. Falls x nicht vorhanden ist, wird null zurückgegeben.
     */
    private TreeNode getNode(int x) {
        TreeNode node = root;
        while (node != null) {
            int value = node.getValue();
            if (x == value) {
                return node;
            }
            if (x < value) {
                node = node.getLeft();
            } else {
                node = node.getRight();
            }
        }
        return null;
    }

    /**
     * Suchen des Elternknoten eines Wertes. Falls x nicht vorhanden oder in der Wurzel steht,
     * wird null zurückgegeben.
     * @param x Such-Wert
     * @return Elternknoten. Falls x nicht vorhanden oder in der Wurzel steht,
     * wird null zurückgegeben.
     */
    private TreeNode getParentNode(int x) {
        TreeNode node = getNode(x);
        if (node == null) {
            return null;
        } else {
            return node.getParent();
        }
    }

    /**
     * Gibt zurück, ob der Wert x im Baum vorhanden ist oder nicht.
     * @param x Wert
     * @return true, falls der Wert x im Baum vorhanden ist. false, sonst.
     */
    public boolean search(int x) {
        return getNode(x) != null;
    }

    /**
     * Einfügen eines Wertes. Falls versucht wird, einen Wert einzufügen, der schon vorhanden
     * ist, wird eine ArithmeticException ausgelöst.
     * @param x Wert, der eingefügt werden soll
     */
    public void insert(int x) {
        if (root == null) {
            root = new TreeNode(x);
            return;
        }
        TreeNode node = root;
        while (true) {
            int value = node.getValue();
            if (x == value) {
                throw new ArithmeticException("Wert ist schon vorhanden");
            } else if (x < value) {
                TreeNode left = node.getLeft();
                if (left == null) {
                    node.setLeft(new TreeNode(x));
                    return;
                }
                node = left;
            } else { // x > value
                TreeNode right = node.getRight();
                if (right == null) {
                    node.setRight(new TreeNode(x));
                    return;
                }
                node = right;
            }
        }
    }
    /**
     * Löschen aller Daten.
     */
    public void clear() {
        root = null;
    }

    /**
     * Löschen eines Wertes. Falls versucht wird, einen Wert zu löschen, der nicht vorhanden
     * ist, wird eine ArithmeticException ausgelöst.
     * @param x Wert, der gelöscht werden soll
     */
    public void remove(int x) {
        TreeNode node = getNode(x);
        if (node == null) {
            throw new ArithmeticException("Wert ist nicht vorhanden");
        }

        if (node.isLeaf()) {
            clearNode(node);
        } else if (node.isNonBranchingNode()) {
            if (node.getLeft() == null) {
                copyNode(node.getRight(), node);
            } else {
                copyNode(node.getLeft(), node);
            }
        } else {
            TreeNode minRight = getMinRight(node);
            // node durch minRight ersetzen
            node.setValue(minRight.getValue());
            // rechte Seite von minRight nach minRight schieben
            if (minRight.getRight() != null) {
                copyNode(minRight.getRight(), minRight);
            } else {
                clearNode(minRight);
            }
            node.getParent().setLeft(minRight);
        }
    }

    /**
     * Löscht einen TreeNode. Darf nur mit Blättern aufgerufen werden
     * @param node TreeNode, welcher gelöscht werden soll.
     */
    private void clearNode(TreeNode node) {
        if (node == root) {
            root = null;
            return;
        }
        if (node.getParent().getLeft() == node) {
            node.getParent().setLeft(null);
        } else {
            node.getParent().setRight(null);
        }
    }

    /**
     * Gibt den TreeNode mit dem kleinsten Wert im rechten Teilbaum zurück
     * @param node TreeNode
     * @return TreeNode mit dem kleinsten Wert im rechten Teilbaum
     */
    private TreeNode getMinRight(TreeNode node) {
        if (node == null) {
            return null;
        }
        node = node.getRight();
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    /**
     * Kopiert Daten und Referenzen eines TreeNodes in einen anderen
     * @param n1 Quelle
     * @param n2 Ziel
     */
    private void copyNode(TreeNode n1, TreeNode n2) {
        if (n1 == null || n2 == null) {
            throw new NullPointerException("Nullpointer");
        }
        n2.setValue(n1.getValue());
        n2.setLeft(n1.getLeft());
        n2.setRight(n1.getRight());
    }

    public static void main(String[] args) {
        BinTree tree = new BinTree();
        tree.insert(20);
        tree.insert(10);
        tree.insert(30);
        tree.insert(50);
        System.out.println("30 gefunden: "+tree.search(30));
        System.out.println("35 gefunden: "+tree.search(35));
        TreeNode node = tree.getNode(50);
        if(node==null){
            System.out.println("Knoten nicht gefunden.");
        }else{
            System.out.println("Knoten gefunden: "+node.getValue());
        }
        tree.remove(30);
        System.out.println("Knoten geloescht: 30");
        System.out.println("Suche Vater von 50: "+tree.getParentNode(50).getValue());//20
    }
}