import java.util.ArrayList;

public class BTreeNode {
    ArrayList<Integer> values = new ArrayList<>();
    ArrayList<BTreeNode> children = new ArrayList<>();
    BTreeNode parent;

    public BTreeNode() {
        children.add(null);
    }
}
