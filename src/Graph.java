
import java.util.List;
public class Graph
{
  /**
  *@author http://www.vogella.com/tutorials/JavaAlgorithmsDijkstra/article.html
  *
  *@param vertexes - nodes of the graph
  *@param edges - edges of the graph
  */
    private final List<Vertex> vertexes;
    private final List<Edge> edges;

    /** Constructor */
    public Graph(List<Vertex> vertexes, List<Edge> edges)
    {
        this.vertexes = vertexes;
        this.edges = edges;
    }

    /**
    *@return List<Vertex> return the list of vertex on the graph
    */
    public List<Vertex> getVertexes(){ return this.vertexes; }

    /**
    *@return List<Edge>  - return the list of the edges in the graph
    */
    public List<Edge> getEdges(){ return this.edges;  }
}
