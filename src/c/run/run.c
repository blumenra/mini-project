#include <unistd.h>

int main(int argc, char *argv[]){

	char *javaFileToRun[argc + 2];
	unsigned int i = 0;

	javaFileToRun[i++] = "java";
	javaFileToRun[i++] = "src/java/learntree/Main";
	
	for(; i < argc + 1; i++){
		javaFileToRun[i] = argv[i-1];
	}
	
	javaFileToRun[i] = NULL;

	execvp(javaFileToRun[0], javaFileToRun);

	return 1;
}