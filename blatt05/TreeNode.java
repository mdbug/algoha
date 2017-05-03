/**
 * Die Klasse stellt Element eines Binärbaums dar
 */
public class TreeNode {
    private int value;
    private TreeNode parent;
    private TreeNode left;
    private TreeNode right;

    /**
     * Konstruiert einen neuen TreeNode
     * @param value der Wert
     */
    public TreeNode(int value) {
        this.value = value;
    }

    /**
     * Gibt den Wert zurueck
     * @return int Wert
     */
    public int getValue() {
        return value;
    }

    /**
     * Setzt den Wert
     * @param value int Wert
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Gibt den Parentnode zurueck
     * @return der Parentnode
     */
    public TreeNode getParent() {
        return parent;
    }

    /**
     * Setzt den Parentnode
     * @param parent der Parentnode
     */
    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    /**
     * Gibt das linke Kind zurueck
     * @return das linke Kind
     */
    public TreeNode getLeft() {
        return left;
    }

    /**
     * Setzt das linke Kind
     * @param left das linke Kind
     */
    public void setLeft(TreeNode left) {
        this.left = left;
        if (left != null) {
            left.parent = this;
        }
    }

    /**
     * Gibt das rechte Kind zurueck
     * @return das rechte Kind
     */
    public TreeNode getRight() {
        return right;
    }

    /**
     * Setzt das rechte Kind
     * @param right das rechte Kind
     */
    public void setRight(TreeNode right) {
        this.right = right;
        if (right != null) {
            right.parent = this;
        }
    }

    /**
     * Testet ob es sich um ein Blatt handelt
     * @return true, falls dieser Node kein Kind hat. false, sonst.
     */
    public boolean isLeaf() {
        return left == null && right == null;
    }

    /**
     * Testet ob dieser Node genau ein Kind hat
     * @return true, genau dann wenn dieser TreeNode genau ein Kind hat. false, sonst.
     */
    public boolean isNonBranchingNode() {
        return left == null ^ right == null;
    }
}