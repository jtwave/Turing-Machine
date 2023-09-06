# Specifiy the target
all: watch




# Specify the object files that the target depends on
# Also specify the object files needed to create the executable
watch: Watch.o Movie.o Cast.o
	g++ Watch.o Movie.o Cast.o -o watch

# Specify how the object files should be created from source files
Watch.o: Watch.cpp
	g++ -c Watch.cpp
	
Movie.o: Movie.cpp
	g++ -c Movie.cpp

Cast.o: Cast.cpp
	g++ -c Cast.cpp

# Specify the object files and executables that are generated
# and need to be removed to re-compile the whole thing
clean:
	rm -f *.o watch
