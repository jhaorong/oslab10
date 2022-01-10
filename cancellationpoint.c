#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <unistd.h>
int* result;
void *child(void *arg){
	printf("child thread :\n");
	int *input = (int *)arg;
	result = malloc(sizeof(int)*1);
	result[0] = input[0] + input[1];
	
	sleep(10);
	printf("pthread_cancel failed\n");
	pthread_exit((void *) result);
}

int main()
{
	pthread_t t;
	void *ret;
	int input[2] = {1,2};
	pthread_create(&t,NULL,child,(void*)input);
	for(int i=1;i<=3;i++)
        {
                printf("sleep : %d\n",i);
                sleep(1);
        }
	int a = pthread_cancel(t);


	if(a == 0)
	{
		printf("pthread_cancel successfully\n");
	}
	printf("main thread\n");
	printf("result : %d + %d = %d\n",input[0],input[1],result[0]);
	free(result);
	return 0;

}
