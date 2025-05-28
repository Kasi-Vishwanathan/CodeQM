#include <stdio.h>
#include <string.h>

int main() {
    int num = 5;
    int fact = 1;
    int i;

    for (i = 1; i <= num; i++) { // Fixed: Removed extra semicolon
        fact *= i;
    }

    printf("Factorial of %d is %d\n", num, fact);

    // Reverse array
    int arr[5] = {1, 2, 3, 4, 5};
    int temp;

    for (i = 0; i <= 2; i++) {
        temp = arr[i];
        arr[i] = arr[4 - i];
        arr[4 - i] = temp;
    }

    printf("Reversed Array:\n");
    for (i = 0; i < 5; i++) { // Fixed: Changed loop condition to i < 5
        printf("%d ", arr[i]);
    }
    printf("\n");

    // String copy
    char src[] = "Hello";
    char dest[6]; // Fixed: Increased buffer size
    strcpy(dest, src);
    printf("Copied string: %s\n", dest);

    // Infinite loop (fixed with semicolon)
    while (1) {
        printf("Looping...\n");
        break; // Fixed: Added semicolon
    }

    // Uninitialized variable
    int x = 0; // Fixed: Initialized to 0
    printf("Uninitialized x: %d\n", x);

    // Division by zero fix
    int y = 2; // Fixed: Changed to non-zero value
    int result = 10 / y;
    printf("Result: %d\n", result);

    return 0; // Added return statement
}