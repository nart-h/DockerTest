import java.io.*;
import java.util.Arrays;

public class Solution {
    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] dimensions= Arrays.stream(bufferedReader.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        EdgeWeightedDigraph g = new EdgeWeightedDigraph(dimensions[0]*dimensions[1]);
        final int HORIZONTAL_WEIGHT = 10;
        final int DIAGONAL_WEIGHT = 14;

        char[][] grid = new char[dimensions[0]] [dimensions[1]];

        for (int i = 0; i < dimensions[0] ; i++) {
            String line = bufferedReader.readLine();
            for (int j = 0; j < dimensions[1]; j++)
                grid [i][j] = line.charAt(j);
        }

        for (int i = 0; i < dimensions[0] ; i++) {
            for (int j = 0; j < dimensions[1] ; j++)
            if(grid[i][j] == '.') {

                int source =j + (i * dimensions[1]);
                if(i-1>=0 && grid[i-1][j] =='.')
                    g.addEdge(new DirectedEdge (source, source-dimensions[1], HORIZONTAL_WEIGHT)); //up
                if(i+1<dimensions[0] && grid[i+1][j] =='.')
                    g.addEdge(new DirectedEdge (source, source+dimensions[1], HORIZONTAL_WEIGHT)); //down
                if(j-1>=0 && grid[i][j-1] =='.' )
                    g.addEdge(new DirectedEdge (source, source-1, HORIZONTAL_WEIGHT)); //left
                if(j+1<dimensions[1] && grid[i][j+1] =='.')
                    g.addEdge(new DirectedEdge (source, source+1, HORIZONTAL_WEIGHT)); //right

                if(i-1>=0 && j-1>=0 && grid[i-1][j-1] =='.')
                    g.addEdge(new DirectedEdge (source, source-dimensions[1]-1, DIAGONAL_WEIGHT)); //upper left
                if(i-1>=0 && j+1<dimensions[1] && grid[i-1][j+1] =='.')
                    g.addEdge(new DirectedEdge (source, source-dimensions[1]+1, DIAGONAL_WEIGHT)); //upper right
                if(i+1<dimensions[0] && j-1>=0 && grid[i+1][j-1] =='.')
                    g.addEdge(new DirectedEdge (source, source+dimensions[1]-1, DIAGONAL_WEIGHT)); //lower left
                if(i+1<dimensions[0] && j+1<dimensions[1] && grid[i+1][j+1] =='.')
                    g.addEdge(new DirectedEdge (source, source+dimensions[1]+1, DIAGONAL_WEIGHT)); //lower right

            }
        }

        int T = Integer.parseInt(bufferedReader.readLine());
        int[] result= new int[T];

        for (int i = 0; i <T ; i++) {
            int[] input = Arrays.stream(bufferedReader.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();

            Dijkstra dijkstra=new Dijkstra(g, (input[0]-1)*dimensions[1] + (input[1]-1) ); // s= (r-1)*m + c-1

            int sink= (input[2]-1)*dimensions[1] + (input[3]-1);
            if(dijkstra.hasPathTo(sink) )
                result[i]=dijkstra.distTo(sink);
            else result[i]=-10;
        }

        for (double r: result) {
            bufferedWriter.write(r/10+"\n");
        }

        bufferedReader.close();
        bufferedWriter.close();
    }
}
