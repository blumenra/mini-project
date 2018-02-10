#include <unistd.h>

int main(int argc, char *argv[]){

	int javaFilesCount = 2;
	char *args[argc + 1 + javaFilesCount];
	unsigned int i = 0;
	args[i++] = "javac";
	args[i++] = "src/java/learntree/Example.java";
	args[i++] = "src/java/learntree/Main.java";

	for(; i < argc + 1; i++){
		args[i] = argv[i-1];
	}
	args[i] = NULL;

	execvp(args[0], args);


	return 1;
}