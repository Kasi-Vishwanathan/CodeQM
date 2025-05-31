/* buggy_c_code.c */
#include <stdio.h>

// Function prototypes using modern C (C99) style
int compute_sum(int arr[], int n);
void display(int arr[], int n);

int compute_sum(int arr[], int n) {
    int sum = 0;
    for (int i = 0; i < n; i++) {
        sum += arr[i];
    }
    return sum;
}

void display(int arr[], int n) {
    printf("Elements: ");
    for (int i = 0; i < n; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n");
}

int main(void) {
    int number[] = {3, 7, 1, 9, 4, 2};
    const int num_elements = sizeof(number) / sizeof(number[0]);
    
    int result = compute_sum(number, num_elements);
    printf("Sum is: %d\n", result);
    display(number, num_elements);

    int max = number[0];
    for (int i = 1; i < num_elements; i++) {
        if (number[i] > max) {
            max = number[i];
        }
    }
    printf("Maximum is: %d\n", max);
    
    return 0;
}