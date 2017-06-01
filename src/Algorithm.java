import java.util.*;
public class Algorithm
{
  /**
  *
  *@author http://www.vogella.com/tutorials/JavaAlgorithmsDijkstra/article.html
  *
  *@param nodes  - List of all Vertexes in the graph
  *@param edges - List of all edges in the graph
  *@param settledNodes - visited Vertexes

  *@param unSettledNodes - unvisited Vertexes
  *@param predecessors  - previously accessed Vertexes (will be used in building the paths)
  *@param distance - associated distance between nodes and vertexes
  */
    private final List<Vertex> nodes;
    private final List<Edge> edges;
    private Set<Vertex> settledNodes;
    private Set<Vertex> unSettledNodes;
    private Map<Vertex, Vertex> predecessors;
    private Map<Vertex, Integer> distance;

    public Algorithm(Graph graph)
    {
        // create a copy of the array so that we can operate on this array
        this.nodes = new ArrayList<Vertex>(graph.getVertexes());
        this.edges = new ArrayList<Edge>(graph.getEdges());
    }

    /**
    */
    public void execute(Vertex source)
    {
      settledNodes = new HashSet<Vertex>();
      unSettledNodes = new HashSet<Vertex>();
      distance = new HashMap<Vertex, Integer>();
      predecessors = new HashMap<Vertex, Vertex>();

      distance.put(source, 0);
      unSettledNodes.add(source);

      while (unSettledNodes.size() > 0)
      {
        Vertex node = getMinimum(unSettledNodes);
        settledNodes.add(node);
        unSettledNodes.remove(node);

        this.findMinimalDistances(node);
      }
    }

    /**
    * This function find the minimum distances from a node to the neighbouring nodes
    */
    private void findMinimalDistances(Vertex node)
    {
        List<Vertex> adjacentNodes = getNeighbors(node);
        for (Vertex target : adjacentNodes)
        {
            if (getShortestDistance(target) > getShortestDistance(node) + getDistance(node, target))
            {
                distance.put(target, getShortestDistance(node) + getDistance(node, target));
                predecessors.put(target, node);
                unSettledNodes.add(target);
            }
        }
    }

    /**
    *@return int -  distance from Vertex (node) to destination vertex(target)
    */
    private int getDistance(Vertex node, Vertex target)
    {
        for (Edge edge : edges)
        {
            if (edge.getSource().equals(node) && edge.getDestination().equals(target))
            {
                return edge.getWeight();
            }
        }
        throw new RuntimeException("Should not happen");
    }

    /**
    *@return List<Vertex> - returns the unvisited neighbouring vertexes from a node
    */
    private List<Vertex> getNeighbors(Vertex node)
    {
        List<Vertex> neighbors = new ArrayList<Vertex>();
        for (Edge edge : edges)
        {
            if (edge.getSource().equals(node) && !isSettled(edge.getDestination()))
            {
                neighbors.add(edge.getDestination());
            }
        }
        return neighbors;
    }

    /**
    *@return Vertex -  return shortest distance from a node to another node (1 degree away) using the getShortestDistance method
    */
    private Vertex getMinimum(Set<Vertex> vertexes)
    {
        Vertex minimum = null;

        for (Vertex vertex : vertexes)
        {
            if (minimum == null)
            {
                minimum = vertex;
            } else
            {
                if (getShortestDistance(vertex) < getShortestDistance(minimum))
                {
                    minimum = vertex;
                }
            }
        }
        return minimum;
    }

    /**
    *@return booelan - checks if a node has been visited and declared settled yet
    */
    private boolean isSettled(Vertex vertex)
    {
      return settledNodes.contains(vertex);
    }

    /**
    *@return int - used by getMinimum function
    */
    private int getShortestDistance(Vertex destination)
    {
        Integer d = distance.get(destination);
        if (d == null)
        {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }

    /*
     * This method returns the path from the source to the selected target and
     * NULL if no path exists
     */
    public LinkedList<Vertex> getPath(Vertex target)
    {
        LinkedList<Vertex> path = new LinkedList<Vertex>();
        Vertex step = target;

        // check if a path exists
        if (predecessors.get(step) == null)
        {
            return null;
        }

        path.add(step);

        while (predecessors.get(step) != null)
        {
            step = predecessors.get(step);
            path.add(step);
        }
        // Put it into the correct order
        Collections.reverse(path);
        return path;
    }
}
