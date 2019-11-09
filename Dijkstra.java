import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Queue;
import java.util.Stack;

public class Dijkstra {

    private int[] distTo;
    private DirectedEdge[] edgeTo;
    private int V;

    private Queue<Integer> pq;


    public Dijkstra(EdgeWeightedDigraph G, int s) {

        V = G.numOfVerticies();
        validateVertex(s);
        for (DirectedEdge e : G.edges()) {
            if (e.weight() < 0)
                throw new IllegalArgumentException();
        }

        distTo = new int[G.numOfVerticies()];
        edgeTo = new DirectedEdge[G.numOfVerticies()];

        for (int v = 0; v < G.numOfVerticies(); v++)
            distTo[v] = Integer.MAX_VALUE;
        distTo[s] = 0;

        // relax vertices in order of distance from s
        pq = new java.util.PriorityQueue<Integer>(new Comp());
        pq.add(s);
        while (!pq.isEmpty()) {
            int v = pq.remove();
            for (DirectedEdge e : G.adj(v))
                relax(e);
        }

    }

    private class Comp implements Comparator<Integer>
    {
        @Override
        public int compare(Integer o1, Integer o2) {
            if(distTo[o1] < distTo[o2]) return -1;
            if(distTo[o1] > distTo[o2]) return +1;
            return 0;
        }
    }

    private void relax(DirectedEdge e) {
        int v = e.from(), w = e.to();
        if (distTo[w] > distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
            if (pq.contains(w))
                pq.remove(w);
            pq.add(w);
        }
    }

    public int distTo(int v) {
        validateVertex(v);
        return distTo[v];
    }


    public boolean hasPathTo(int v) {
        validateVertex(v);
        return distTo[v] < Integer.MAX_VALUE;
    }

    public Iterable<DirectedEdge> pathTo(int v) {
        validateVertex(v);
        if (!hasPathTo(v)) return null;
        Stack<DirectedEdge> path = new Stack<DirectedEdge>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            path.push(e);
        }
        return path;
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException();
    }

}
