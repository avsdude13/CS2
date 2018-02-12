import java.lang.*;
import java.util.Scanner;

public class File Stats {
   public static void main (String args[]) {
  		int digit = 0;
      int letter = 0;
      int line = 1;
      
      Scanner keyboard = new Scanner (System.in);

      System.out.print("Enter the file name:  ");
      filename = keyboard.nextLine(); 

      File input = new File(filename);
      Scanner inputfile = new Scanner (input);

      while (inputfile.hasNextLine()) {
           line++;
           while (inputfile.hasNext()) {
               if (character.isdigit())
                  digit++;
               else if (character.isletter())
                  letter++;
               }
          }   
      inputfile.close()
      
      System.out.println ("File analyzed: " + filename);
      System.out.println (line + " lines, " + digit + " digits and " + letter + " letters.");
   }
}