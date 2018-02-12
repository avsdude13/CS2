/***************************************************************
/                                                              /
/  Matt Kline -- Efficency -- Professor Gurka -- CS2           /
/                                                              /
/  This class acts as a driver for Fibonacci and Ackermann.    /
/                                                              /
/**************************************************************/

public class Efficency {

   public static void main (String [] args) {
      Fibonacci fibonacci = new Fibonacci();      
      Ackermann ackermann = new Ackermann();
     
      fibonacci.driver();  
      ackermann.driver();
   }//end main
}//end Efficency