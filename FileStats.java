/*********************************************
/                                            /
/  File Stats                                /
/  This program reads a file and determines  /
/  the number of lines, letters, and digits  /
/  By: Matt Kline                            /
/  Janurary 27, 2015                         /
/  Limitations- Does not work without a file /  
/                                            /
/********************************************/

import java.lang.*;
import java.io.*;
import java.util.Scanner;

public class FileStats {
      private int digit = 0;
      private int letter = 0;
      private int line = 0;
      private String filename;
      private String currentLine;
      private char character = 0;
      private int count;

      private Scanner keyboard = new Scanner (System.in);

      public static void main (String args[]) {
         FileStats counter = new FileStats();
         
         counter.file_reading();
         counter.output();
      }
         
      public void file_reading() {
         try {
         System.out.print("Enter the file name:  ");
         filename = keyboard.nextLine();
         
         File input = new File(filename);
         Scanner inputfile = new Scanner (input);

         while (inputfile.hasNextLine()) {
            currentLine = inputfile.nextLine();
            for (count=0; count<currentLine.length(); count++) {
               character = currentLine.charAt(count);
               if (Character.isDigit(character))
                  digit++;
               if (Character.isLetter(character))        
                  letter++;
            }  
            line++;
         }        

         inputfile.close();
         }
      
      catch (IOException e) {
         System.err.println ("Caught IO Exception: " + e.getMessage());
      }
   }
         
   public void output() {
      System.out.println ("File analyzed: " + filename);
      System.out.println (line + " lines, " + digit + " digits and " + letter + " letters.");
   }
}