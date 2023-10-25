import java.util.Random;

public class BigO {
  
  /* This does a bit of random useless processing num ** 3 times, and returns how long it takes in ms */
  static long nCubed(int num)
  {
    long startTime = System.currentTimeMillis();
    long temp = 0;
    Random rand = new Random();
    // Setting upper bound to generate random numbers in specific range
    int upperbound = 1000;
    for (long i = 0; i < num; i++){
      for (long j = 0; j < num; j++){
        for (long k = 0; k < num; k++){
          temp = i * j * k + rand.nextInt(upperbound);
        }
      }
    }
    return System.currentTimeMillis() - startTime;
  }
    
  /* This does a lot of random useless processing num ** 2 times, and returns how long it takes in ms */
  static long nSquared(int num){
    long startTime = System.currentTimeMillis();
    Random rand = new Random();
    // Setting upper bound to generate random numbers in specific range
    int upperbound = 1000;
    String s = "I am a big fat hungry walrus who lives in Greenland and eats a lot of prawns on Tuesdays.";
    for (int i = 0; i < num; i++){
      for (int j = 0; j < num; j++){
        // NOTE: This starts the top half of the inner loop
        String sub = s.substring(12).toLowerCase().toUpperCase().toLowerCase().toUpperCase().toLowerCase().toUpperCase();
        sub = s.substring(12).toLowerCase().toUpperCase().toLowerCase().toUpperCase().toLowerCase().toUpperCase();
        sub = s.substring(12).toLowerCase().toUpperCase().toLowerCase().toUpperCase().toLowerCase().toUpperCase();
        char c = sub.charAt(5);
        String sChar = Character.toString(c);
        sChar = null;
        // NOTE: this starts the bottom half of the inner loop
        // TODO for the next question, comment out the top, then the bottom, to see which is slower
        int myRandomInt = rand.nextInt(upperbound);
        myRandomInt = 0;
        int yourRandomInt = rand.nextInt(upperbound);
        yourRandomInt = 0;
        int herRandomInt = rand.nextInt(upperbound);
        herRandomInt = 0;
        int hisRandomInt = rand.nextInt(upperbound);
        hisRandomInt = 0;
        int theirRandomInt = rand.nextInt(upperbound);
        theirRandomInt = 0;
        int ourRandomInt = rand.nextInt(upperbound);
        ourRandomInt = 0;
        int studentRandomInt = rand.nextInt(upperbound);
        studentRandomInt = 0;
      }
    }
    return System.currentTimeMillis() - startTime;
  }

    // main driver program
  public static void main(String args[]){
    // TODO first run the code asis to see the performance of the nSquared and nCubed methods.
    // TODO adjust the next line to see where n0 is.
    // TODO that is, in the "for" statement, play with any/all of the 3 numbers as needed to find
    // TODO the approximate value of n0 where
    for (int n = 10; n <= 1000; n *= 10) { // TODO change this line of code!
      long nSquaredTime = nSquared(n);
      System.out.println("\nFor " + Integer.toString(n) + " items, n**2 time in ms is: " + Long.toString(nSquaredTime));
      long nCubedTime = nCubed(n);
      System.out.println("For " + Integer.toString(n) + " items, n**3 time in ms is: " + Long.toString(nCubedTime));
    }
  }
}
