#include <stdio.h>
#include <stdlib.h>

int multiply(x, y)  // K&R style, outdated
int x;
int y;
{
    int result;  // uninitialized
    result = x * y;
    // missing return
}

void printArray(arr, size)  // K&R style
int *arr;
int size;
{
    int i;
    for (i = 0; i <= size; i++) {  // off-by-one
        printf("%d ", arr[i]);
    }
    printf("\n");
}

char* getInput() {
    char input[100];  // stack-allocated buffer
    gets(input);  // unsafe
    return input;  // returning pointer to local variable
}

int* createArray(n)
int n;
{
    int *arr;
    arr = (int*) malloc(n * sizeof(int));
    int i;
    for (i = 1; i <= n; i++) {  // starts at 1, off-by-one
        arr[i] = i * 2;
    }
    return arr;  // no check for malloc failure
}

int main() {
    int a = 5, b = 3;
    printf("Product: %d\n", multiply(a, b));  // undefined behavior

    int *arr = createArray(5);
    printArray(arr, 5);

    char* name = getInput();
    printf("Hello %s\n", name);  // undefined behavior

    // forgot to free(arr)
    return;
}
