public class Vertex
{
  /**
  *@author http://www.vogella.com/tutorials/JavaAlgorithmsDijkstra/article.html
  */

  /**
  *@param id - Stores the unique ID of a Vertex(node)
  *@param name - Name of the node
  */
    final private String id;
    final private String name;

    /** Constructor
    * sets name and id to parsed values
    */
    public Vertex(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
    *@return String - return the ID of the Vertex
    */
    public String getId() {
        return this.id;
    }

    /**
    *@return String - returns name of the vertex
    */
    public String getName() {
        return this.name;
    }

    /**
    *@return int - hash function
    */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    /**
    *@return boolean - checks if a two Vertex are the same
    */
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        Vertex other = (Vertex) obj;
        if (id == null)
        {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
