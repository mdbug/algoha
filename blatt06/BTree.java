import java.util.Collections;

/**
 * Created by micha on 09.05.2017.
 */
public class BTree {
    BTreeNode root;

    public boolean search(int x) {
        return search(x, root);
    }

    public boolean search(int x, BTreeNode node) {
        if (node == null)
            return false;
        int i = Collections.binarySearch(node.values, x);
        if (i >= 0) {
            return true;
        } else {
            return search(x, node.children.get(-i-1));
        }
    }

    public int insert(int x) {

    }



}
