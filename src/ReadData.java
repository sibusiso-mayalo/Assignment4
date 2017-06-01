import java.util.*;
import java.io.*;
public class ReadData
{
  /**
  *@param data - will store the actual data set for the graph
  *@param scan - scanner object
  *@param file - file object
  *@param filename  - the file that contains the data
  */
  private ArrayList<String> data;
  private Scanner scan;
  private File file;
  private String filename;

  /**
  *Constructor
  */
  public ReadData()
  {
    this.filename = "graphData.txt"; //Lifetime - Adventure
    this.data = new ArrayList<String>();
    try
    {
      this.file = new File(this.filename);
      this.scan = new Scanner(this.file);
    }catch(FileNotFoundException err)
    {
      System.out.println("Data file not found");
      System.exit(0);
    }
    this.read_data();
  }

  /**
  *Will read the data and populates it to the data ArrayList
  */
  private void read_data()
  {
    String line;
    while(this.scan.hasNext())
    {
      line = this.scan.nextLine();
      this.data.add(line);
    }
  }

  /**
  *@return  the actual data in an array
  */
  public ArrayList<String> getData()
  {
    return this.data;
  }

}
