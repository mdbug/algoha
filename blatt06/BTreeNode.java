import java.util.ArrayList;

/**
 * Die Klasse repraesentiert einen Node eines B-Baums
 */
public class BTreeNode {
    ArrayList<Integer> values = new ArrayList<>();
    ArrayList<BTreeNode> children = new ArrayList<>();
    BTreeNode parent;
}
