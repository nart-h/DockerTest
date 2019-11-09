import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class EdgeWeightedDigraph {

    private final int V;
    private int E;
    private List<DirectedEdge>[] adj;

    public EdgeWeightedDigraph(int V) {
        if (V < 0) throw new IllegalArgumentException("Number of vertices in a Digraph must be nonnegative");
        this.V = V;
        this.E = 0;
        adj = (ArrayList[]) new ArrayList[V];
        for(int k=0; k<V; k++)
            adj[k] = new ArrayList<DirectedEdge>();
    }

    public int numOfVerticies() { return V; }

    public int numOfEdges() {
        return E;
    }

    public void addEdge(DirectedEdge e) {
        if(e==null)
            throw new IllegalArgumentException();
        int v = e.from();
        int w = e.to();
        validateVertex(v);
        validateVertex(w);
//        if(isValidVertex(v) && isValidVertex(w)) {
        adj[v].add(e);
        E++;

    }

//    public void addEdge(int v, int w, BigDecimal weight){
//        validateVertex(v);
//        validateVertex(w);
//        adj[v].add(new DirectedEdge(v,w,weight));
//        E++;
//    }

    public Iterable<DirectedEdge> edges() {
        List<DirectedEdge> list = new ArrayList<>();
        for (int v = 0; v < V; v++) {
            for (DirectedEdge e : adj[v]) {
                list.add(e);
            }
        }
        return list;
    }

    public Iterable<DirectedEdge> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    public int outdegree(int v) {
        validateVertex(v);
        return adj[v].size();
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException();
    }

    private boolean isValidVertex(int v) {
        if (v < 0 || v >= V)
            return false;
        return true;
    }

}
