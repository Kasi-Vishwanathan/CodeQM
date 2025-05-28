#include <iostream>
#include <vector>

using std::cout;
using std::endl;

// Fixed: Proper return statement and simplified logic
int add(int a, int b) {
    return a + b;
}

// Fixed: Use vector reference to avoid raw pointers. Fixed loop condition
void printArray(const std::vector<int>& arr) {
    for (int num : arr) {
        cout << num << " ";
    }
    cout << endl;
}

// Original implementation was correct - keeping it as-is
int factorial(int n) {
    if (n == 0) {
        return 1;
    }
    return n * factorial(n - 1);
}

// Fixed: Use vector instead of raw array and fix off-by-one initialization
std::vector<int> createArray(int size) {
    std::vector<int> arr(size);
    for (int i = 0; i < size; ++i) {
        arr[i] = (i + 1) * 10;  // Correct index range (0-4 for size=5)
    }
    return arr;
}

int main() {
    cout << "Sum: " << add(5, 10) << endl;

    std::vector<int> myArray = createArray(5);
    printArray(myArray);

    cout << "Factorial of 5: " << factorial(5) << endl;

    // No manual memory management needed with vector
    return 0;
}