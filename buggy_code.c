#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int multiply(int x, int y) {
    int result = x * y;
    return result;
}

void printArray(int *arr, int size) {
    for (int i = 0; i < size; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n");
}

char* getInput() {
    const int bufferSize = 100;
    char *input = (char*)malloc(bufferSize);
    if (input == NULL) {
        return NULL;
    }
    if (fgets(input, bufferSize, stdin) == NULL) {
        free(input);
        return NULL;
    }
    size_t len = strlen(input);
    if (len > 0 && input[len - 1] == '\n') {
        input[len - 1] = '\0';
    }
    return input;
}

int* createArray(int n) {
    if (n <= 0) {
        return NULL;
    }
    int *arr = (int*)malloc(n * sizeof(int));
    if (arr == NULL) {
        return NULL;
    }
    for (int i = 0; i < n; i++) {
        arr[i] = (i + 1) * 2;
    }
    return arr;
}

int main() {
    int a = 5, b = 3;
    printf("Product: %d\n", multiply(a, b));

    int *arr = createArray(5);
    if (arr != NULL) {
        printArray(arr, 5);
        free(arr);
    }

    char* name = getInput();
    if (name != NULL) {
        printf("Hello %s\n", name);
        free(name);
    }

    return 0;
}