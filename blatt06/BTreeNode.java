import java.util.ArrayList;

/**
 * Created by micha on 09.05.2017.
 */
public class BTreeNode {
    public ArrayList<Integer> values = new ArrayList<>();
    public ArrayList<BTreeNode> children = new ArrayList<>();
    public BTreeNode parent;
}
