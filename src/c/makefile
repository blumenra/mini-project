
all: ../../learntree

../../learntree: compile.o
	gcc -g  -Wall compile.o -o ../../learntree

compile.o: compile.c
	gcc -g  -Wall -c compile.c -o compile.o

.PHONY:
	clean

clean:
	rm -f ./*.o  ../../learntree  ../java/learntree/*.class