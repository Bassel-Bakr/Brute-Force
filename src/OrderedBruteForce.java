public class OrderedBruteForce implements BruteForce {
  private char data[];
  private BruteForceCallback callback;

  public OrderedBruteForce(char chars[], BruteForceCallback callback) {
    data = chars;
    this.callback = callback;
  }
  public OrderedBruteForce(String chars, BruteForceCallback callback) {
    data = chars.toCharArray();
    this.callback = callback;
  }

  @Override
  public void start_left(int len) {
    if (len < 1) {
      if (callback != null) 
        callback.onResult(new String(data));
      return;
    }

    int n_chars = data.length;
    BruteForceInt harmonic[] = new BruteForceInt[len];
    for (int i = len - 1; i >= 0; i--)
      harmonic[i] = new BruteForceInt();

    for (int i = len - 2; i >= 0; i--) {
      harmonic[i].value = 0;
      harmonic[i].max = n_chars - 1;
      harmonic[i].next = harmonic[i + 1];
    }

    int i = len - 1;
    harmonic[i].value = 0;
    harmonic[i].max = n_chars - 1;
    harmonic[i].next = null;

    BruteForceInt num = harmonic[0];

    char entry[] = new char[len];
    do
    {
      for (i = len - 1; i >= 0; i--)
        entry[i] = data[harmonic[i].value];

      if (callback != null)
        callback.onResult(new String(entry));
    }
    while (num.increment());
  }

  @Override
  public void start_right(int len) {
    if (len < 1) {
      if (callback != null) 
        callback.onResult(new String(data));
      return;
    }

    int n_chars = data.length;
    BruteForceInt harmonic[] = new BruteForceInt[len];
    for (int i = len - 1; i >= 0; i--)
      harmonic[i] = new BruteForceInt();

    for (int i = len - 1; i > 0; i--) {
      harmonic[i].value = 0;
      harmonic[i].max = n_chars - 1;
      harmonic[i].prev = harmonic[i - 1];
    }

    int i = len - 1;
    harmonic[0].value = 0;
    harmonic[0].max = n_chars - 1;
    harmonic[0].prev = null;

    BruteForceInt num = harmonic[i];

    char entry[] = new char[len];
    do
    {
      for (i = len - 1; i >= 0; i--)
        entry[i] = data[harmonic[i].value];

      if (callback != null)
        callback.onResult(new String(entry));
    }
    while (num.decrement());
  }

  @Override
  public long max(int len) {
    long power = data.length;
    long p = power;
    long n = len;
    while (--n > 0)
      power *= p;
    return power;
  }

}
