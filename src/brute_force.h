#include <functional>

class BruteForce
{
  private:
	char *data;
	  std::function < void (char *) > callback;

  public:
	  BruteForce(char *chars,
                  std::function<void(char*)> callback);
	
  void start_left(unsigned int len);
	void start_right(unsigned int len);
	unsigned long long max(unsigned int len);
 };

typedef struct HarmonicInt
{
	unsigned int value, max;
	HarmonicInt *next, *prev;

	bool operator++()
	{
		if (value < max)
		{
			value++;
			return true;
		}
		else if (next)
		{
			value = 0;
			return (*next)++;
		}
		else
			return false;
	}

	bool operator--()
	{
		if (value < max)
		{
			value++;
			return true;
		}
		else if (prev)
		{
			value = 0;
			return (*prev)--;
		}
		else
			return false;
	}

	~HarmonicInt()
	{
	}
};