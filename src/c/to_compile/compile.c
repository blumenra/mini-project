#include <unistd.h>
#include <stdio.h>

int main(int argc, char *argv[]){

	printf("Compiling..\n");

	int javaFilesCount = 4;
	char *javaFilesToCompile[1 + javaFilesCount];
	unsigned int i = 0;

	javaFilesToCompile[i++] = "javac";
	javaFilesToCompile[i++] = "src/java/LT/Main.java";
	javaFilesToCompile[i++] = "src/java/LT/Example.java";
	javaFilesToCompile[i++] = "src/java/LT/Data_Set.java";
	javaFilesToCompile[i++] = "src/java/LT/DecisionTree.java";
	javaFilesToCompile[i] = NULL;

	
	// for(; i < argc + 1; i++){
	// 	args[i] = argv[i-1];
	// }

	execvp(javaFilesToCompile[0], javaFilesToCompile);

	return 1;
}