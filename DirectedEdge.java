import java.math.BigDecimal;

public class DirectedEdge {
    private final int v; // source
    private final int w; // sink
    private final int weight;


    public DirectedEdge(int v, int w, int weight) {
//        if (v < 0) throw new IllegalArgumentException("Vertex names must be nonnegative integers");
//        if (w < 0) throw new IllegalArgumentException("Vertex names must be nonnegative integers");
//        if (Double.isNaN(weight)) throw new IllegalArgumentException("Weight is NaN");
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public int from() {
        return v;
    }

    public int to() {
        return w;
    }

    public int weight() {
        return weight;
    }

}
