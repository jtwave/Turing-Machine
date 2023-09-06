#include <iostream>
#include <stdlib.h>
using namespace std;

class Movie {
   public:
      Movie(string, unsigned int, string);
      Movie();
      void show();

   private:
      string movieTitle = "";
      unsigned int releaseYear = 0;
      string director = "";
};


