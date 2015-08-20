public class BruteForceInt {
  protected int value = 0;
  protected int max;
  protected BruteForceInt
  next = null, 
  prev = null;

  protected boolean increment() {
    if (value < max) {
      value++;
      return true;
    } else if (next != null) {
      value = 0;
      return next.increment();
    } else
      return false;
  }

  protected boolean decrement() {
    if (value < max) {
      value++;
      return true;
    } else if (prev != null) {
      value = 0;
      return prev.decrement();
    } else
      return false;
  }

}
