

import static C.*;

public class Main {
  public static void main(String... args) throws Exception {



    OrderedBruteForce obf = new OrderedBruteForce(
      "0123456789", 
      new BruteForceCallback(){
        @Override
        public void onResult(String result) {
          System.out.println(result);
        }
      });
    obf.start_right(2);
  }
}
