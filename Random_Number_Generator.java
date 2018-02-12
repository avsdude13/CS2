/************************************************************************
/                                                                       /
/   Class Name: CS 2050
/   Professor: Dr. Gurka
/   Program Name: Random_Number_Generator                               /
/   Programmer: Matt Kline                                              /  
/   Date Written: Feburary 8, 2014                                      /   
/                                                                       /   
/   This program demonstrates the use of the random number generator    /
/        and will output the results to a file of your choice.          /
/                                                                       /
/   Limitations: Numbers have to be int's and greater than 1            /
/                                                                       /
/***********************************************************************/


import java.util.Random;
import java.util.Scanner;
import java.io.*;
import javax.swing.JOptionPane;

public class Random_Number_Generator{
   public static void main (String [] args) throws IOException { 
      
      Scanner keyboard = new Scanner(System.in);
      
      String filename = JOptionPane.showInputDialog ("Enter a file to output the results");
      int max = Integer.parseInt(JOptionPane.showInputDialog ("Enter the maximum value"));
      int size = Integer.parseInt(JOptionPane.showInputDialog ("Enter the amount of times to run"));
            
      FileWriter fw = new FileWriter (filename, true);
      PrintWriter pw = new PrintWriter (fw);
      
      Random rn = new Random();
      int[] array = new int[size];
      
      int count = 0;
      int total = 0;
      
      for(int i =0; i < size; i++)
      {
         int answer = rn.nextInt(max) + 1;
         array [i] = answer;
      }//end for
      
      pw.println ("Generated " + size + " numbers between 1 and " + max + ".");
      
      for (int number=1; number < max + 1; number++){
         for (int pointer=0; pointer < array.length; pointer++){
            if (array[pointer] == number){
               count++;
            }//end if 
         }//end for
        
         pw.println("The number " + number + " appeared " + count + " times.");
         count = 0;
      }//end for
           
      for (total=0; total<array.length; total++){
         pw.print (array[total] + ", ");
      }//end for
      
      pw.println ("\n" + total + " numbers were successfully generated.");
      pw.println (""); //spacing for file output
      
      pw.flush();
      pw.close();
      
      JOptionPane.showMessageDialog (null, "File Output complete.");
   
   }//end main
}//end class