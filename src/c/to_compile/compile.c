#include <unistd.h>
#include <stdio.h>

int main(int argc, char *argv[]){

	printf("Compiling..\n");

	int javaFilesCount = 3;
	char *javaFilesToCompile[1 + javaFilesCount];
	unsigned int i = 0;

	javaFilesToCompile[i++] = "javac";
	javaFilesToCompile[i++] = "src/java/learntree/Main.java";
	javaFilesToCompile[i++] = "src/java/learntree/Example.java";
	javaFilesToCompile[i++] = "src/java/learntree/Data_Set.java";
	javaFilesToCompile[i] = NULL;

	
	// for(; i < argc + 1; i++){
	// 	args[i] = argv[i-1];
	// }

	execvp(javaFilesToCompile[0], javaFilesToCompile);

	return 1;
}