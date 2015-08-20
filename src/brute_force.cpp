#include "brute_force.h"

#include <memory>

#include <unistd.h>

unsigned long long BruteForce::max(unsigned int length)
{
	unsigned long long power = strlen(data);
	unsigned long long p = power;
	unsigned long long n = length;
	while (--n > 0)
		power *= p;
	return power;
}

BruteForce::BruteForce(char *chars, std::function < void (char *) > cllbck)
{
	data = chars;
	callback = cllbck;
}

void BruteForce::start_left(unsigned int length)
{
	unsigned int n_chars = strlen(data);
	auto harmonic = std::unique_ptr < BruteForceInt[] > (new BruteForceInt[length]);

	for (int i = length - 2; i >= 0; i--)
	{
		harmonic[i].value = 0;
		harmonic[i].max = n_chars - 1;
		harmonic[i].next = &harmonic[i + 1];
	}
	unsigned int i = length - 1;
	harmonic[i].value = 0;
	harmonic[i].max = n_chars - 1;
	harmonic[i].next = nullptr;

	BruteForceInt & num = harmonic[0];

	char entry[length + 1] = { 0 };
	do
	{
		for (int i = length - 1; i >= 0; i--)
			entry[i] = data[harmonic[i].value];

		if (callback)
			callback(entry);
	}
	while (num++);
}



void BruteForce::start_right(unsigned int length)
{
	unsigned int n_chars = strlen(data);
	auto harmonic = std::unique_ptr < BruteForceInt[] > (new BruteForceInt[length]);

	for (int i = length - 1; i > 0; i--)
	{
		harmonic[i].value = 0;
		harmonic[i].max = n_chars - 1;
		harmonic[i].prev = &harmonic[i - 1];
	}
	harmonic[0].value = 0;
	harmonic[0].max = n_chars - 1;
	harmonic[0].next = nullptr;

  BruteForceInt & num = harmonic[length - 1];

	char entry[length + 1] = { 0 };
	do
	{
		for (int i = length - 1; i >= 0; i--)
			entry[i] = data[harmonic[i].value];

		if (callback)
			callback(entry);
	}
	while (num--);
}




int main()
{
	BruteForce bf("0123456789",
          [](auto str)
				  {
				    puts(str);
				    usleep(1);
          }
	);
	bf.start_right(2); // from 00 through 99
	return 0;
}







