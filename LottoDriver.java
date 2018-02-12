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

import javax.swing.JOptionPane;
import java.text.DecimalFormat;
import java.io.*;

public class LottoDriver{
   private String filename;
   private int numberOfGames = 0;
   private int gameCount = 0;
   private double totalEarned = 0;
   private int totalTimesPlayed = 0;
   private int totalThree = 0;
   private int totalFour = 0;
   private int totalFive = 0;
   private DecimalFormat money = new DecimalFormat("###,###.##");
   
/*************************************************************************************************/           
   
   public static void main(String[] args) throws IOException{      
      LottoDriver driver = new LottoDriver();
      
      driver.startNewGame();
      driver.getFinalOutput();
   }//end of main

/*************************************************************************************************/   
  
   public void startNewGame() throws IOException{
      Lotto lottery = new Lotto();
      LottoDriver driver = new LottoDriver();
      
      filename = JOptionPane.showInputDialog ("Enter a file to output the results");
      numberOfGames = Integer.parseInt(JOptionPane.showInputDialog ("Enter the number of games to play"));
      
      lottery.startNewGame();
      do {
         lottery.generateNumbers();
         boolean jackpot = lottery.checkForMatches();
         if (jackpot){
            gameCount++;
            lottery.getMoneyEarned();
            lottery.getOutput(filename);
            System.out.println ("Game number " + gameCount + " has been completed");
            String results = lottery.getResults();
         
            String[] tempResults = results.split (", ");
         
            totalEarned = totalEarned + Double.parseDouble(tempResults[0]);
            totalTimesPlayed = totalTimesPlayed + Integer.parseInt(tempResults[1]);
            totalThree = totalThree + Integer.parseInt(tempResults[2]);
            totalFour = totalFour + Integer.parseInt(tempResults[3]);
            totalFive = totalFive + Integer.parseInt(tempResults[4]);
         }//end if     
      } while (gameCount < numberOfGames);
   }//end of startNewGame method

/*************************************************************************************************/   

   public void getFinalOutput() {
   try{
   
      FileWriter fw = new FileWriter (filename, true);
      PrintWriter pw = new PrintWriter (fw);
      
      pw.println ("");
      pw.println ("During the " + numberOfGames + " games played");
      pw.println ("You earned a total of $" + money.format(totalEarned) + " times over " + numberOfGames + " games");
      pw.println ("That is an average of $" + money.format(totalEarned/numberOfGames) + " times per game");
      pw.println ("You played a total of " + money.format(totalTimesPlayed) + " over " + numberOfGames + " games");
      pw.println ("That is an average of " + money.format(totalTimesPlayed/numberOfGames) + " per game");      
      pw.println ("You got 3 numbers correct " + money.format(totalThree) + " times over "+ numberOfGames + " games");
      pw.println ("That is an average of " + money.format(totalThree/numberOfGames) + " per game");
      pw.println ("You got 4 numbers correct " + money.format(totalFour) + " times over "+ numberOfGames + " games");
      pw.println ("That is an average of " + money.format(totalFour/numberOfGames) + " per game");
      pw.println ("You got 5 numbers correct " + money.format(totalFive) + " times over "+ numberOfGames + " games");
      pw.println ("That is an average of " + money.format(totalFive/numberOfGames) + " per game");      
            
      pw.flush();
      pw.close();  
   }//end try block
   
   catch (IOException e) {
      JOptionPane.showMessageDialog (null, "Cannot export to the requested file");
   }//end catch    
   }//end of getFinalOutput
}//end of LottoDriver class