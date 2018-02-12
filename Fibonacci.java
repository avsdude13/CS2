/***************************************************************
/                                                              /
/  Matt Kline -- Efficency -- Professor Gurka -- CS2           /
/                                                              /
/  This class runs the Fibonacci sequence using both recursion /
/  and a table for storing values for increased efficency and  /
/  reduced method calls. This class outputs to a file that     /
/  named Fibonacci_Results.txt.                                /
/                                                              /
/**************************************************************/

import java.io.*;

public class Fibonacci {

   private int recursiveCallCount;
   private int tableAccessCount;
   private int[] table;
   
   public void driver() {   
      try {      
         FileWriter fw = new FileWriter ("Efficency_Results.txt", false);
         PrintWriter pw = new PrintWriter (fw);
   
         pw.println ("Fibonacci Results -- Efficency Assignment");
         pw.println ("Matt Kline -- Computer Science 2 -- Professor Gurka");
         pw.println (""); //spacing for file
         
         for (int current = 1; current <= 30; current++) {
            int result = fibonacciUnoptimized (current);
            pw.println ("The fib of " + current + " is " + result + "." +
                        " There were " + recursiveCallCount + " calls using unoptimized.");
            recursiveCallCount = 0; 
            table = new int [31];
            table [1] = 1; //initialized to 1 for base case
            table [2] = 1; //initialized to 1 for base case
            result = fibonacciOptimized (current);
            pw.println (" There were " + recursiveCallCount + " calls and " + tableAccessCount + 
                        " table accesses using optimized.");
            recursiveCallCount = 0; 
            tableAccessCount = 0;
            table = null;
         }//end for
         
         pw.println (""); //for spacing on file
         pw.flush();
         pw.close();
      }//end try
      
      catch (IOException e) {
         System.err.println ("Cannot export to the requested file");
      }//end catch
   }//end driver
   
   public int fibonacciUnoptimized (int n) {
      recursiveCallCount ++;
      if (n == 1 || n == 2)
         return 1;
      return fibonacciUnoptimized (n-1) + fibonacciUnoptimized (n-2);
   }//end fibonacciUnoptimized
   
   public int fibonacciOptimized (int n) {
      recursiveCallCount ++;
      if (checkTable(n) == 0) {
         table [n] = fibonacciOptimized (n-1) + fibonacciOptimized (n-2);
      }//end if
      tableAccessCount++;
      return table [n];
   }//end fibonacciOptimized
   
   public int checkTable (int n) {
      tableAccessCount++;
      return table [n];
   }//end checkTable
}//end Fibonacci