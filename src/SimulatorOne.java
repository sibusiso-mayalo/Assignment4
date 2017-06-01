import java.util.*;
public class SimulatorOne
{
  /**
  *@author Sibusiso Mayalo
  */

  /**
  *@return String - return the path from source vertex to destination vertex
  */
  private static String processCalls(ArrayList<Vertex> vertexes, ArrayList<Edge> edges,int from, int to)
  {
    Graph graph = new Graph(vertexes, edges);
    Algorithm dikstraj = new Algorithm(graph);

    dikstraj.execute(vertexes.get(from));

    LinkedList<Vertex> path = dikstraj.getPath(vertexes.get(to));
    String returnPath = "";

    if(path == null)
    {
      return "Path does not exist. Null";
    }else
    {
      for(Vertex paths: path)
      {
        returnPath = returnPath + " "+ paths.getId();
      }
    }
    return returnPath.substring(1, returnPath.length());
  }

  public static void main(String[] args)
  {
    Scanner input = new Scanner(System.in);

    ArrayList<Vertex> vertexes = new ArrayList<>(0);
    ArrayList<Edge> edges = new ArrayList<>(0);
    ArrayList<String> victims = new ArrayList<>(0);
    ArrayList<String> hospitals = new ArrayList<String>();

    ReadData readObj = new ReadData();
    ArrayList<String> data = readObj.getData(); //graph data

    //Reading victim nodes from user
    System.out.println("Please enter victim nodes <0 - 4> one at a time:");
    System.out.println("Press enter when you are done");
    String line = input.nextLine();

    try
    {
      while(line.length() != 0)
      {
        victims.add(line);
        line = input.nextLine();
      }
    }catch(NullPointerException err){}

    //Reading hopital nodes from user
    System.out.println("\nPlease enter hopital nodes <0 - 4> one at a time:");
    System.out.println("Press enter when you are done");
    line = input.nextLine();

    try
    {
      while(line.length() != 0)
      {
        hospitals.add(line);
        line = input.nextLine();
      }
    }catch(NullPointerException err){}

    System.out.println("\nPlease enter the current node of the ambulance");
    System.out.println("Press enter when you are done");
    int ambulance = Integer.parseInt( input.nextLine());


    //Constructing the graph
    for(int i = 0; i < data.size(); i++)
    {
      line = data.get(i);
      String[] parts = line.split(" ");

      int loopCounter = 0;
      int weight = 0;
//Justin bieber - chance the rapper
      Vertex source = null;
      Vertex destination = null;
      Edge edge = null;

      for(String z : parts)
      {
        //System.out.println(line);
        if(loopCounter % 2 == 0 && loopCounter < parts.length)
          {
            boolean sourceNull= true;
            try
            {
              if(source == null)
              {
                source = new Vertex(parts[0], "Node"+parts[0]);
                vertexes.add(source);
                sourceNull = false;
              }

              if(!sourceNull)
              {
                source = new Vertex(parts[loopCounter], "Node" + loopCounter );
              }

              destination = new Vertex(parts[loopCounter + 1], "Node" + loopCounter + 1);
              weight = Integer.parseInt( parts[loopCounter + 2] );

              edge = new Edge("Edge-" + source.getId()+ "-" + destination.getId(), source, destination, weight);
              edges.add(edge);
              vertexes.add(destination);
              vertexes.add(source);

          //System.out.println("From Node : " + parts[0] + "to Node " + parts[loopCounter+1]+ " is " + parts[loopCounter+2]);
            }catch(ArrayIndexOutOfBoundsException error)
            {
              //continue; // exception will be raised when there is no more data for constructing the graph
            }

          }
          loopCounter++;
        }
    }

    //Processing calls
    int patientNode, hospitalNode = 0;

    for(String patient: victims)
    {
      ArrayList<String> temporaryPaths = new ArrayList<String>();
      patientNode= Integer.parseInt(patient); //patient node

      String path_Ambulance_To_Victim, path_Victim_To_Hospital = "";

      for(String hospital: hospitals) // for each patient check all routs to hospitals
      {
        hospitalNode = Integer.parseInt(hospital); // hospital node

        path_Ambulance_To_Victim = processCalls(vertexes, edges, ambulance, patientNode); //from ambulance to victim
        path_Victim_To_Hospital = processCalls(vertexes, edges, patientNode, hospitalNode ); //from victim to hospital

        temporaryPaths.add( path_Ambulance_To_Victim + path_Victim_To_Hospital.substring(1, path_Victim_To_Hospital.length()));
      }

      //from all the routes from patient to hospital, determine min-route
      int source, destination, pathLength = 0;
      int minimum = Integer.MAX_VALUE;
      String hold_path = "";
      boolean flag = false;

      for(String route: temporaryPaths)
      {
        if(route.indexOf("not exist") != -1)
        {
          temporaryPaths.remove(route);
        }
      }

      if(temporaryPaths.size() == 0)
      {
        System.out.println("Path does not exists");
      }else
      {
        for(String rout: temporaryPaths)
        {
          int i = 0;
          String indexes[] = rout.split(" ");
          for(; i< indexes.length-1; i++)
          {
            source = Integer.parseInt( indexes[i] );
            destination = Integer.parseInt( indexes[i+1]);
            for(Edge edge: edges)
            {
              if(edge.getId() == "Edge-"+source+"-"+destination)
              {
                pathLength+= edge.getWeight();
              }
            }
          }

          if(pathLength < minimum)
          {
            minimum = pathLength;
            hold_path = rout;
          }
          pathLength = 0;
          i = 0;
        }

        System.out.println("route is" + hold_path);
        ambulance = Integer.parseInt( hold_path.substring( hold_path.length()-1, hold_path.length()) );
        System.out.println("Current <new> location of ambulance :"+ambulance);
      }

  }
}
}
