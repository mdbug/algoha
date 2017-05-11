import java.util.ArrayList;

public class BTreeNode {
    ArrayList<Integer> values = new ArrayList<>();
    ArrayList<BTreeNode> children = new ArrayList<>();
    BTreeNode parent;

    public BTreeNode() {
        for(int i = 0; i < 2*BTree.ORDNUNG + 1; ++i) {
            children.add(null);
        }
    }
}
