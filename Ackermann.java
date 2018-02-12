/***************************************************************
/                                                              /
/  Matt Kline -- Efficency -- Professor Gurka -- CS2           /
/                                                              /
/  This class runs Ackermann sequence using both recursion     /
/  and a table for storing values for increased efficency and  /
/  reduced method calls. This class outputs to a file that     /
/  named Ackermann_Results.txt.                                /
/                                                              /
/**************************************************************/

import java.io.*;

public class Ackermann {
   
   private int recursiveCallCount;
   private int tableAccessCount;
   private int greaterThanArrayCount;
   private int maximumYValue;
   private int[][] table;
   private boolean withinRange = true;
   
   public void driver() {   
      resetVariables();
      
      try {      
         FileWriter fw = new FileWriter ("Efficency_Results.txt", true);
         PrintWriter pw = new PrintWriter (fw);
   
         pw.println ("Ackermann Results -- Efficency Assignment");
         pw.println ("Matt Kline -- Computer Science 2 -- Professor Gurka");
         pw.println (""); //spacing for file
         
         for (int x = 0; x <= 4; x++) {
            withinRange = true;
            for (int y = 0; y <= 15; y++) {
               resetVariables();
               if (!withinRange) {
                  pw.println ("The ack of " + x + ", " + y + " is out of range.");
               }//end if
               else {
                  int result = ackermannUnoptimized (x, y);
                  pw.print ("The ack of " + x + ", " + y + " is " + result + "." +
                            " There were " + recursiveCallCount + " calls using unoptimized.");
               
                  resetVariables();
                  table = new int [5][101];
                  result = ackermannOptimized (x,y);
                
                  pw.println (" There were " +  recursiveCallCount + " calls and " + tableAccessCount + 
                              " table accesses using optimized.");
                  pw.println ("      The y value exceeded the array " + greaterThanArrayCount + " times." + 
                              " The maximum y value was " + maximumYValue);
               }//end else
            }//end for
         }//end for

         pw.println (""); //for spacing on file
         pw.flush();
         pw.close();
      }//end try
   
      catch (IOException e) {
         System.err.println ("Cannot export to the requested file");
      }//end catch
   }//end driver
  
   private int ackermannUnoptimized (int x, int y) {
      try {
         recursiveCallCount ++;
         if (y > maximumYValue)
            maximumYValue = y;
         if (x == 0)
            return y+1;
         else if (y == 0)
            return ackermannUnoptimized (x-1, 1);
         return ackermannUnoptimized (x-1, ackermannUnoptimized(x, y-1));
      }//end try
      
      catch (StackOverflowError e) {
         withinRange = false;
      }//end catch
      return 0;
   }//end ackermannUnoptimized
   
   private int ackermannOptimized (int x, int y) {
      try {
         recursiveCallCount ++;
         int tableResult = checkTable (x, y);         
         if (y > maximumYValue)
            maximumYValue = y;
         if (tableResult == -1 && x == 0)
            return y+1;
         else if (tableResult == -1 && y == 0)
            return ackermannOptimized (x-1, 1);
         else if (tableResult == -1)
            return ackermannOptimized (x-1, ackermannOptimized(x, y-1));
         else if (tableResult == 0 && x == 0)
            table [x][y] = y+1;
         else if (tableResult == 0 && y == 0)
            table [x][y] = ackermannOptimized (x-1, 1);
         else if (tableResult == 0)
            table [x][y] = ackermannOptimized (x-1, ackermannOptimized(x, y-1));
         tableAccessCount++;
         return table [x][y];
      }//end try
      
      catch (StackOverflowError e) {
         withinRange = false;
      }//end catch
      return 0;
   }//end fibonacciOptimized
   
   public int checkTable (int x, int y) {
      try {
         if (x >= 5 || y >= 101) {
            greaterThanArrayCount++;
            return -1;
         }//end if
         tableAccessCount++;
         return table [x][y];
      }//end try
      
      catch (ArrayIndexOutOfBoundsException e) {
      }//end catch
      return 0;
   }//end checkTable
   
   public void resetVariables () {
      recursiveCallCount = 0; 
      maximumYValue = 0;
      greaterThanArrayCount = 0; 
      tableAccessCount = 0;
      table = null;
   }//end reserVariables
}//end Ackermann