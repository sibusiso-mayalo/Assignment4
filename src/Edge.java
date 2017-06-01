
public class Edge
{
  /**
  *@author http://www.vogella.com/tutorials/JavaAlgorithmsDijkstra/article.html
  *
  *@param id - Identifies each edge uniquely by this ID
  *@param sourceVertex - Starting vertex
  *@param destinationVertex - The destination vertex from the source vertex
  *@param weight - The distance magnitude from sourceVertex to destinationVertex
  */

    private final String id;
    private final Vertex sourceVertex;
    private final Vertex destinationVertex;
    private final int weight;

    /** Constructor : sets field values to given values */
    public Edge(String id, Vertex source, Vertex destination, int weight)
    {
        this.id = id;
        this.sourceVertex = source;
        this.destinationVertex = destination;
        this.weight = weight;
    }

    /**
    @return String - return Edge ID
    */
    public String getId()
    {
        return this.id;
    }

    /**
    *@return Vertex - returns the destination Vertex
    */
    public Vertex getDestination()
    {
        return this.destinationVertex;
    }

    /**
    *@return Vertex - return the starting Vertex
    */
    public Vertex getSource()
    {
        return this.sourceVertex;
    }

    /**
    *@return int - return the distnace from source vertex to the destination vertex. 
    */
    public int getWeight()
    {
        return this.weight;
    }
}
