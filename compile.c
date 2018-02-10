#include <unistd.h>

int main(int argc, char *argv[]){

	char *args[argc + 2];
	unsigned int i = 0;
	args[i++] = "javac";
	args[i++] = "../IdeaProjects/learntree/src/Main.java";

	for(; i < argc + 1; i++){
		args[i] = argv[i-1];
	}
	args[i] = NULL;
	execvp(args[0], args);

	return 1;
}