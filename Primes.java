/***************************************************
/                                                  /
/  Primes                                          /
/  This program determines all prime numbers       /
/  between a user input and two.                   /
/  By: Matt Kline                                  /
/  Janurary 27, 2015                               /
/  Limitations- Does not work without user input   /  
/                                                  /
/**************************************************/

import javax.swing.JOptionPane;
import java.util.*;

public class Primes {

   private Integer upper_limit = 0;
   private Integer pointer;
   private Integer multiple_count;
   private Integer i = 0;

   private ArrayList<Integer> primes = new ArrayList<Integer>();
   
   public static void main (String args[]) {
      Primes primeFinder = new Primes();
      
      primeFinder.validity();
      primeFinder.findingPrimes();
      primeFinder.output();   
   }
   
   public void validity() {
      while (upper_limit < 2) {
         upper_limit = Integer.parseInt(JOptionPane.showInputDialog ("Input an upper limit greater than 2"));
      }
   }
   
   public void findingPrimes() {
      for (i = 1; i <= upper_limit; i++) {
         multiple_count = 0;
         for (pointer = i; pointer >= 1; pointer--) {
            if(i%pointer==0)
	            multiple_count = multiple_count + 1;
         }
         if (multiple_count == 2)
            primes.add(i);
      }
   }
   
   public void output() {
      JOptionPane.showMessageDialog (null, "Primes Report by Matt Kline" + "\n" + "Upper Limit = " + upper_limit + "\n" + "Primes: " + primes);   
   }
}