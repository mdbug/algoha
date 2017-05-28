public class TestGraph {
    public static void main(String[] args) {
        Graph g = new Graph(new int[] {6,10,1,5,1,4,2,3,2,6,3,4,3,5,4,5,4,2,5,6,6,1});
        System.out.println(g.dfs(1));
        System.out.println(g.bfs(1));
    }
}
