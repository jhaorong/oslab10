#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <pthread.h>
static __thread int glob = 0;
static void *incr(void *loops)
{
	int loc,j;
	int *input = (int*)loops;
	for(j=0;j<input[0];j++)
	{
		loc = glob;
		loc++;
		glob = loc;
	}
	printf("thread%d ,glob = %d\n",input[0],input[1]);
}
int main()
{
	pthread_t id[2];
	int A[2];
	int B[2];
	A[0] = 1;
	A[1] = 50;
	B[0] = 2;
	B[1] = 100;
	printf("(main thread)glob value before running 2 threads:%d\n",glob);
	pthread_create(&id[0],NULL,incr,(void*)A);
	pthread_create(&id[1],NULL,incr,(void*)B);

	pthread_join(id[0],NULL);
	pthread_join(id[1],NULL);
	printf("(main thread)glob value after running 2 threads:%d\n",glob);
	return 0;
}
