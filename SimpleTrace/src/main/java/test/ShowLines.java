package test;

// ShowLines.java
// Andrew Davison, March 2009, ad@fivedots.coe.psu.ac.th

/* Load a file line-by-line into an ArrayList.
   The show() method displays the specified line
   (counting from 1, not 0).
*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class ShowLines
{
	// TODO a mettre en parametre d'entr�e
	static private String rootPath = "D:/workspaces/cartographie/cartoJacoco/JavaAgentTest/src/main/java";
	private ArrayList<String> code;
  
  

  public ShowLines(String fileName, String fpath)
  {
    code = new ArrayList<String>();
    
    String line = null;
    BufferedReader in = null;

    try {
    	
    	// TODO ou trouver les sources ???
    	// pour chaque jar / projet, il faudrait avoir un repertoire � partir duquel on pourrait trouver les sources
    	System.out.println(">>> chargement fichier fileName " + fileName);
    	System.out.println(">>> chargement fichier fpath " + fpath);
    	
    	String path = rootPath + "/" + fpath.replace(".", "/") + ".java";
    	
      in = new BufferedReader(new FileReader(path));
      while ((line = in.readLine()) != null)
         code.add(line);
    }
    catch (IOException ex) {
      System.out.println("Problem reading " + fileName);
    } 
    finally {
      try {
        if (in != null)
          in.close();
      }
      catch (IOException e) {}
    }
  }  // end of showLines()


  public String show(int lineNum)
  // lineNum starts at 1, not 0
  {
    if (code == null)
       return "No code to show for line " + lineNum;

    if ((lineNum < 1) || (lineNum > code.size()))
      return "Line no. " + lineNum + " out of range";

    return ( "" + lineNum + ".\t" + code.get(lineNum-1)); 
  }  // end of show()

    
  // --------------------------------------------------

  public static void main(String[] args)
  {  
     ShowLines sl = new ShowLines("Comparison.java", ""); 
     System.out.println( sl.show(18));
     System.out.println( sl.show(1));
     System.out.println( sl.show(2000));
  }


}  // end of ShowLines class
