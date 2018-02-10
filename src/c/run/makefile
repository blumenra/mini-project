
all: ../../../learntree

../../../learntree: run.o
	gcc -g  -Wall run.o -o ../../../learntree

run.o: run.c
	gcc -g  -Wall -c run.c -o run.o

.PHONY:
	clean

clean:
	rm -f ./*.o  ../../../learntree
