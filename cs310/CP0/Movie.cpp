#include <iostream>
#include <stdlib.h>
#include "Movie.h"
using namespace std;

Movie::Movie(string title, unsigned int year, string direct) {
 movieTitle = title;
 releaseYear = year;
 director = direct;
}

Movie::Movie() {
 movieTitle = "";
 releaseYear = 0;
 director = "";
}

void Movie::show() {
 cout << movieTitle << " (" << releaseYear << "): " << director << endl;
}


