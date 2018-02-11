
all: ../../../compile

../../../compile: compile.o
	gcc -g  -Wall compile.o -o ../../../compile

compile.o: compile.c
	gcc -g  -Wall -c compile.c -o compile.o

.PHONY:
	clean

clean:
	rm -f ./*.o  ../../../compile  ../../java/learntree/*.class