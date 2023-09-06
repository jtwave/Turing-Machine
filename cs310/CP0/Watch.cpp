#include <iostream>
#include <stdlib.h>
#include "Movie.h"
using namespace std;

int main(int argc, char *argv[]) {
   Movie one;
   Movie *two = new Movie("Saturday Night Fever", 1977, "John Bednam");
   Movie *three = new Movie("Wall-E", 2007, "John Taylor");
   one.show();
   two->show();
   delete(two);
   three->show();
   delete(three);
}
