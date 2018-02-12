/************************************************************************
/                                                                       /
/   Class Name: CS 2050                                                 /
/   Professor: Dr. Gurka                                                /
/   Program Name: Lotto                                                 /
/   Programmer: Matt Kline                                              /
/   Date Written: Feburary 10, 2014                                     /
/                                                                       /
/   This program plays through 10 games of Lottery with user inputed    /
/       values and calculates the total gains/loses after playing       /
/   10 times until a jackpot. The results will be outputed to a file    /
/                                                                       /
/   Limitations:                                                        /
/                                                                       /
/                                                                       /
/***********************************************************************/

import java.util.Random;
import javax.swing.JOptionPane;
import java.text.DecimalFormat;
import java.io.*;

public class Lotto{
   
   private final int MAX = 42;
   private final int MIN = 0;
   private int gameCount;
   private String userValues;
   private double earned;
   private int timesPlayed;
   private boolean jackpot;
   private int three;
   private int four;
   private int five;
   private int[] lotto = new int[6];
   private int[] userLotto = new int[6];
   
/*************************************************************************************************/        

   public void startNewGame(){      
      earned = 0;
      timesPlayed = 0;
      jackpot = false;
      three = 0;
      four = 0;
      five = 0;
      boolean promptAgain = false;
      
      do {
         try {
            userValues = JOptionPane.showInputDialog ("Enter your six digits between 1 and 42" + 
                                                       "\nSeperate each number with a comma");
           
            String[] userNumbers = userValues.split(", ");
            
            for(int i = 0; i < userNumbers.length; i++) {
                  userLotto[i] = Integer.parseInt(userNumbers[i]);
                  if ((userLotto[i] >= 1 && userLotto[i] <= 42)) {
                     promptAgain = false;
                  }//end if
                  else if (userLotto[i] < 1 || userLotto[i] > 42){
                     promptAgain = true;
                     JOptionPane.showMessageDialog (null, "One of the numbers that you entered was invalid. Please try agian.");
                  }//end else if                     
            } //end for
         } //end try
    
         catch (NumberFormatException e) {
            JOptionPane.showMessageDialog (null, "One of the numbers that you entered was invalid. Please try agian.");
            promptAgain = true;
         }//end catch
      } while (promptAgain);     
   }//end get input method  
      
/*************************************************************************************************/   
      
   public void generateNumbers(){      
      boolean generateAgain = false;
      Random rn = new Random();
      
      do{
         generateAgain = false;
         for (int count=0; count<6; count++){
            lotto[count] = rn.nextInt(MAX - MIN) + 1;
         } //end for
         for (int testPoint=0; testPoint<lotto.length; testPoint++){
            for (int pointer=testPoint+1; pointer<lotto.length; pointer++)
               if ((testPoint!=pointer) && (lotto[testPoint] == lotto[pointer])){
                  generateAgain = true;
               }//end if
         }//end for
      } while (generateAgain);
      timesPlayed++; 
   }//end generateNumbers method

/*************************************************************************************************/   
   
   public boolean checkForMatches(){           
      int matchCount = 0;
      jackpot = false;
      for (int number=0; number<userLotto.length; number++){
         for (int pointer=0; pointer<lotto.length; pointer++){
            if (userLotto[number] == lotto[pointer]){
               matchCount++;
            }//end if 
         }//end for
      }//end for
      
      if (matchCount == 6){
         jackpot = true;
         gameCount++;
      }//end if
      else if (matchCount == 5){
         five++;
      }//end if
      else if (matchCount == 4){
         four++;
      }//end if
      else if (matchCount == 3){
         three++;
      }//end if
      return jackpot;
   }//end checkForMatches method        

/*************************************************************************************************/   

   public void getMoneyEarned(){
      earned = 2000000 + (1000 * five) + (50 * four) + (10 * three) - timesPlayed;
   }//end getMoneyEarned method 
   
/*************************************************************************************************/   
   
   public void getOutput(String filename) throws IOException{
   try {
      FileWriter fw = new FileWriter (filename, true);
      PrintWriter pw = new PrintWriter (fw);
      DecimalFormat money = new DecimalFormat("###,###.##");
   
      if (gameCount == 1) {
         pw.println ("Lotto -- Matt Kline -- Computer Science 2 -- Professor Gurka");
         pw.println ("The 6 values you entered were " + userValues);
      }//end if
      
      pw.println ("");//spacing for file 
      pw.println ("During game " + gameCount);
      pw.println ("You earned a total of $" + money.format(earned));
      pw.println ("You played a total of " + money.format(timesPlayed) + " times");
      pw.println ("You got 3 numbers correct " + money.format(three) + " times");
      pw.println ("You got 4 numbers correct " + money.format(four) + " times");
      pw.println ("You got 5 numbers correct " + money.format(five) + " times");
      
      pw.flush();
      pw.close();
   }//end try
   
   catch (IOException e) {
      JOptionPane.showMessageDialog (null, "Cannot export to the requested file");
   }//end catch
   }//end getOutput method

/*************************************************************************************************/   
  
   public String getResults(){
      String results = "" + earned + ", " + timesPlayed + ", " + three + ", " + four + ", " + five;
      return results;
   }//end getResults method
}//end of class