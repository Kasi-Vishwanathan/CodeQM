/* buggy_c_test.c */
#include <stdio.h>
#include <stddef.h> // For size_t

// Function prototypes with const and size_t
int compute_sum(const int arr[], size_t n);
void display(const int arr[], size_t n);

int compute_sum(const int arr[], size_t n) {
    int sum = 0;
    for (size_t i = 0; i < n; i++) {
        sum += arr[i];
    }
    return sum;
}

void display(const int arr[], size_t n) {
    printf("Elements: ");
    for (size_t i = 0; i < n; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n");
}

int main(void) {
    int number[] = {3, 7, 1, 9, 4, 2};
    const size_t num_elements = sizeof(number) / sizeof(number[0]);
    
    int result = compute_sum(number, num_elements);
    printf("Sum is: %d\n", result);
    display(number, num_elements);

    int max = number[0];
    for (size_t i = 1; i < num_elements; i++) {
        if (number[i] > max) {
            max = number[i];
        }
    }
    printf("Maximum is: %d\n", max);
    
    return 0;
}